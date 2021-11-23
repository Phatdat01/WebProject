package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.Content;
import model.dao.ContentDAO;

/**
 * Servlet implementation class search
 */
@WebServlet("/search")
public class Search extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Search() {
        super();
        // TODO Auto-generated constructor stub
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	doPost(req, resp);
    }
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String title = request.getParameter("search");
		
		ContentDAO ctd=new ContentDAO();
		List<Content> lst = null;
		try {
			if(title != null) {
				
				lst = ctd.getContent(title.trim(), 0, 0);
			}
			else {
				lst = ctd.getContent(title, 0, 0);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("tbcontents",lst);
		RequestDispatcher rd =request.getRequestDispatcher("/content/viewcontent.jsp");
		rd.forward(request,response);
	}
}
