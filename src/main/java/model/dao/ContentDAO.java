package model.dao;

import model.bean.Content;
import model.dbconnection.ConnectMySQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

public class ContentDAO {
	Connection conn=null;
	PreparedStatement ps=null;
	ResultSet rs=null;

	public List<Content> getContent(String title,int authorId,int idcontent)throws SQLException{
		List<Content> lst=new ArrayList<Content>();
		String select=null;
		if(title==null)
			select="select id,title,brief,createdate from content where authorId=? limit ?,10;";
		else{
			select="select id,title,brief,createdate from content where authorId=? and title like ? limit ?,10;";
		}

		try {
			conn=ConnectMySQL.getMySQLConnection();
			ps=conn.prepareStatement(select);
			
			Content author=new Content();
			author.setId(authorId);
			
			ps.setInt(1,author.getId());
			if(title!=null) {
				ps.setString(2,"%"+title+"%");
				ps.setInt(3,idcontent);
			}
			else {
				ps.setInt(2,idcontent);
			}
			rs=ps.executeQuery();
			
			while(rs.next()) {
				
				lst.add(new Content(rs.getInt(1),
						rs.getString(2),
						rs.getString(3),
						rs.getString(4)));
			}
		}
		catch(Exception e){			
		}
		return lst;
	}
	
	public int getMaxNumPage(String title,int authorId) {
		String sql=null;
		if(title==null)
			sql="select count(id) from content where authorId=?;";
		else{
			sql="select count(id) from content where authorId=? and title like ?;";
		}

		try {
			conn=ConnectMySQL.getMySQLConnection();
			ps=conn.prepareStatement(sql);
			
			Content author=new Content();
			author.setId(authorId);
			
			ps.setInt(1,author.getId());
			if(title!=null) {
				ps.setString(2,title+"%");
			}
			rs=ps.executeQuery();
			while(rs.next()) {
				return rs.getInt(1);
			}
		}
		catch(Exception e){
			e.printStackTrace();
			}
		return 0;
	}

	public void deletecontent(String id) {
		try {
			String delete="delete from Content where id=?";
			conn=ConnectMySQL.getMySQLConnection();
			ps=conn.prepareStatement(delete);
			ps.setString(1,id);
			ps.executeUpdate();
		}
		catch(Exception e) {
		}
	}
}