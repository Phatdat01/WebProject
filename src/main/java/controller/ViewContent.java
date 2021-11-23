package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestMapping;

import model.bean.Content;
import model.dao.ContentDAO;

/*
 * Servlet implementation class ViewContent
 */

@WebServlet("/content")
@RequestMapping("/content")
public class ViewContent extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewContent() {
        super();
        // TODO Auto-generated constructor stub
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	HttpSession session = request.getSession();
    	if(session.getAttribute("email") != null) {
    		doPost(request, response);
    	}
    	else {
    		request.getRequestDispatcher("/view/login.jsp").forward(request, response);
    	}
    }
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		String title = request.getParameter("search");
		String currentPage=request.getParameter("currentPage");
		if(title!=null) {
			title.trim();
			Cookie search = new Cookie("search",title);
			search.setMaxAge(60*60*24);
			response.addCookie(search);
		}
		else {
			Cookie [] cookies = request.getCookies();
			for(Cookie ck : cookies) {
				if(ck.getName().equals("search")) {
					title = ck.getValue().toString();
				}
				
			}
		}
		ContentDAO ctd=new ContentDAO();
		List<Content> lst = null;
		HttpSession session = request.getSession();
		int authorId= 2;
		int idcontent=0;
		if(currentPage == null) {
			currentPage="1";
		}
		idcontent=(Integer.parseInt(currentPage)-1)*10;	
		try {
			lst = ctd.getContent(title,authorId,idcontent);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("tbcontents",lst);
		request.setAttribute("current",currentPage);
		RequestDispatcher rd =request.getRequestDispatcher("/view/viewcontent.jsp");
		rd.forward(request,response);
	}
	
	

}
