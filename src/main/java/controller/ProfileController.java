package controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.bean.LoginBean;
import model.bean.ProfileBean;
import model.dao.LoginDAO;
import model.dao.ProfileDAO;

/**
 * Servlet implementation class ProfileController
 */
//@WebServlet("/ProfileController")

@WebServlet(urlPatterns = {"/profilecontroller"})
public class ProfileController extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
    	if(session.getAttribute("userLogin") != null) {
    		doPost(req, resp);
    	}
    	else {
    		resp.sendRedirect("/ProjectJava/logincontroller");
    	}
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException   {
		resp.setContentType("text/html;charset=UTF-8");
		req.setCharacterEncoding("UTF-8");

		RequestDispatcher rd;

		HttpSession session = req.getSession();
		ProfileBean user2 = new ProfileBean();

		user2 = (ProfileBean)session.getAttribute("userLogin");

			String firstname = req.getParameter("firstname");
			String lastname= req.getParameter("lastname");
			String phone = req.getParameter("phone");
			String description = req.getParameter("description");
			
			String email= user2.getEmail();







			boolean flag = false;

			ProfileBean objResigter = new ProfileBean();

			ProfileBean user = new ProfileBean();
			user.setFirstname(firstname);
			user.setLastname(lastname);
			user.setDescription(description);
			user.setEmail(email);
			user.setPhone(phone);



			if(user.getFirstname() == null || user.getPhone() == null || user.getDescription() == null) {


				LoginBean userLogin = new LoginBean();
				userLogin.setEmail(user.getEmail());

				objResigter = LoginDAO.checkLogin(userLogin);

				req.setAttribute("objRegister", objResigter);
				rd = req.getRequestDispatcher("/view/profile.jsp");
				rd.forward(req, resp);
			}else {	
				ProfileBean user3 = new ProfileBean();
				user3.setFirstname(firstname.trim());
				user3.setLastname(lastname.trim());
				user3.setDescription(description.trim());
				user3.setEmail(email.trim());
				user3.setPhone(phone.trim());

				try {
					flag = ProfileDAO.insertProfile(user);

					if(flag) {
						req.setAttribute("msg", "Cập nhật thành công");
						resp.sendRedirect("/ProjectJava/profilecontroller");  

					}
					else {
						rd = req.getRequestDispatcher("/view/profile.jsp");
						rd.forward(req, resp);
					}
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}


		}
	}

