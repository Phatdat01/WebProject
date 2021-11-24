package controller;

import java.io.IOException;
import java.sql.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.bean.AddContentBean;
import model.bean.ProfileBean;
import model.dao.AddContentDAO;

@WebServlet("/addcontent")
public class AddContentController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private AddContentDAO contentDAO;

	public void init() {
		contentDAO = new AddContentDAO();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		try {
			insertContent(request, response);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int idContent = Integer.valueOf(request.getParameter("id"));
		AddContentBean newContent = new AddContentBean();
		try {
			 newContent = AddContentDAO.selectContent(idContent);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		request.setAttribute("addcontent", newContent);
		RequestDispatcher rd = request.getRequestDispatcher("/view/formcontent.jsp");
		rd.forward(request, response);
		

	}

	private void insertContent(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException, IOException, ServletException
	{
		String title = request.getParameter("title");
		String brief = request.getParameter("brief");
		String content = request.getParameter("content");
		String id = request.getParameter("id");
		
		if(id == null) {
			HttpSession session = request.getSession();
			ProfileBean user2 = new ProfileBean();
			user2 = (ProfileBean)session.getAttribute("userLogin");
			
			if(user2 == null) {
				request.getRequestDispatcher("/view/login.jsp").forward(request, response);
	    	}
	    
			int authorId=user2.getId();
			
			AddContentBean newContent = new AddContentBean();
			newContent.setTitle(title);
			newContent.setBrief(brief);
			newContent.setAuthorId(authorId);
			newContent.setContent(content);
			
			boolean flag = contentDAO.insertContent(newContent);
			if(flag) {
				response.sendRedirect("/ProjectJava/content");
			}else {
				response.sendRedirect("/ProjectJava/view/formcontent.jsp");
			}
		}
		else {
			int idContent = Integer.valueOf(id);
			
			AddContentBean newContent = new AddContentBean();
			newContent.setTitle(title);
			newContent.setBrief(brief);
			newContent.setId(idContent);
			newContent.setContent(content);
			
			boolean flag = contentDAO.updateContent(newContent);
			if(flag) {
				response.sendRedirect("/ProjectJava/content");
			}else {
				response.sendRedirect("/ProjectJava/view/formcontent.jsp");
			}
		}
		
		
		
	}

}
