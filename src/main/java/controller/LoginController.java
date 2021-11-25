package controller;



import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.bean.EncryptDecrypt;
import model.bean.LoginBean;
import model.bean.ProfileBean;
import model.dao.LoginDAO;

@WebServlet(urlPatterns = {"/logincontroller"})
public class LoginController extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getParameter("action");
		HttpSession session = req.getSession();
		
		if(action == null) {
			String email = req.getParameter("email").trim();
			String pass = req.getParameter("password").trim();
			pass = EncryptDecrypt.encrypt(pass);
			boolean remember = req.getParameter("remember") != null;

			LoginBean user = new LoginBean();
			user.setEmail(email);
			user.setPassword(pass);
			
			ProfileBean checkLogin = LoginDAO.checkLogin(user);
			
			if(checkLogin.getEmail()!= null) {
				session.setAttribute("email", user.getEmail());
				session.setAttribute("userLogin", checkLogin);
				
				if(remember) {
					String emailUser = EncryptDecrypt.encrypt( checkLogin.getEmail());
					
					Cookie ckEmail = new Cookie("email", emailUser);
					
					ckEmail.setMaxAge(60*60*24);
	
					resp.addCookie(ckEmail);
					

				}
				RequestDispatcher rd;
				if(checkLogin.getFirstname() == null || checkLogin.getPhone() == null) {					
					rd = req.getRequestDispatcher("/view/profile.jsp");
					rd.forward(req, resp);
				}
				else {
					rd = req.getRequestDispatcher("/content");
					rd.forward(req, resp);
				}
			}
			else {
				req.setAttribute("msg", "Login fail");
				RequestDispatcher rd = req.getRequestDispatcher("/view/login.jsp");
				rd.forward(req, resp);
			}
		}


	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getParameter("action");
		HttpSession session = req.getSession();

		if(action == null) {
			LoginBean account = checkCookie(req);
			
			if(account != null) {
				RequestDispatcher rd = req.getRequestDispatcher("/view/login.jsp");
				rd.forward(req, resp);
			}
			else {
				ProfileBean person = LoginDAO.checkLogin(account);
				if(person.getEmail() != null) {
					// chuyển sang trang đăng nhập thành công
					session.setAttribute("email", account.getEmail());
					session.setAttribute("userLogin", person);
					
					RequestDispatcher rd = req.getRequestDispatcher("/view/profile.jsp");
					rd.forward(req, resp);
				}
				else {
					req.setAttribute("msg", "Login fail");
					RequestDispatcher rd = req.getRequestDispatcher("/view/login.jsp");
					rd.forward(req, resp);
				}
			}
		}
		else {
			if(action.equals("logout")) {
				session.removeAttribute("email");
				session.removeAttribute("userLogin");
				session.removeAttribute("register");
				Cookie [] cookies = req.getCookies();
				
				for(Cookie ck : cookies) {
					if(ck.getName().equals("email")) {
						ck.setMaxAge(0);
						resp.addCookie(ck);
					}
					if(ck.getName().equals("search")) {
						ck.setMaxAge(0);
						resp.addCookie(ck);
					}
					
				}
				session.invalidate();
				
				RequestDispatcher rd = req.getRequestDispatcher("/view/login.jsp");
				rd.forward(req, resp);
			}
		}
	}
	
	private LoginBean checkCookie (HttpServletRequest req) {
		Cookie [] cookies = req.getCookies();
		LoginBean account = null ;
		if(cookies == null) {
			return null;
		}
		else {
			String email = "";
			for(Cookie ck : cookies) {
				if(ck.getName().equals("email")) {
					email = EncryptDecrypt.decrypt(ck.getValue());					
				}
				
			}
			if(!email.isEmpty()) {
				account = new LoginBean();
				account.setEmail(email);
			}

		}
		return account;
	}
}