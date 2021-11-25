package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.bean.EncryptDecrypt;
import model.bean.RegisterBean;
import model.dao.RegisterDAO;

@WebServlet(urlPatterns = {"/registercontroller"})
public class RegisterController extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String username = req.getParameter("username");
		String email = req.getParameter("email");
		String password = EncryptDecrypt.encrypt(req.getParameter("password"));

		RegisterBean user = new RegisterBean(username, email, password);
		HttpSession session = req.getSession();

		RequestDispatcher rd;

		boolean checkUsername = RegisterDAO.checkUsername(user);
		if(checkUsername)
		{
			req.setAttribute("msg", "Username đã tồn tại");
			RequestDispatcher rs = req.getRequestDispatcher("/view/register.jsp");
			rs.forward(req, resp);
		}
		else {
			boolean checkEmail = RegisterDAO.checkEmail(user);
			if(checkEmail)
			{
				req.setAttribute("msg", "Email đã tồn tại");
				RequestDispatcher rs = req.getRequestDispatcher("/view/register.jsp");
				rs.forward(req, resp);
			}
			else {

				boolean flag = false;
				try {
					flag = RegisterDAO.insertRegister(user);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}


				if(flag) {
					req.setAttribute("email", user.getEmail());
					//req.setAttribute("msg", "Đăng ký thành công");
					rd = req.getRequestDispatcher("/view/login.jsp");
					rd.forward(req, resp);
				}
				else {
					req.setAttribute("msg", "Không đăng ký được");
					rd = req.getRequestDispatcher("/view/register.jsp");
					rd.forward(req, resp);
				}

			}

		}
	}
}



