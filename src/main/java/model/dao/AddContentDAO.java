package model.dao;

import java.sql.*;

import model.bean.AddContentBean;
import model.dbconnection.ConnectMySQL;

public class AddContentDAO {

	private static final String INSERT_CONTENT_SQL = "INSERT INTO content" + "  (title, brief, content, authorId) VALUES "
			+ " (?, ?, ?, ?);";
	private static final String SELECT_CONTENT_BY_ID = "select id, title, brief, content from content where id =?";
	private static final String UPDATE_CONTENT_SQL = "update content set title = ?, brief = ?, content =? where id = ?;";

	public static boolean insertContent(AddContentBean addcontent) throws SQLException, ClassNotFoundException {
		Connection connection = null;
		boolean boolSucess = false;
		try {
			 connection = ConnectMySQL.getMySQLConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CONTENT_SQL);
			preparedStatement.setString(1, addcontent.getTitle());
			preparedStatement.setString(2, addcontent.getBrief());
			preparedStatement.setString(3, addcontent.getContent());
			preparedStatement.setInt(4, addcontent.getAuthorId());
			
			boolSucess = preparedStatement.executeUpdate() > 0;
		} catch (SQLException e) {
			printSQLException(e);
		}
		connection.close();
		return boolSucess;
	}

	public static AddContentBean selectContent(int id) throws ClassNotFoundException, SQLException {
		AddContentBean addcontent = null;
		Connection connection = null;
		try {
			 connection = ConnectMySQL.getMySQLConnection();

			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CONTENT_BY_ID);
			preparedStatement.setInt(1, id);
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				String title = rs.getString("title");
				String brief = rs.getString("brief");
				String content = rs.getString("content");
				addcontent = new AddContentBean();
				addcontent.setTitle(title);
				addcontent.setBrief(brief);
				addcontent.setContent(content);
				addcontent.setId(id);
			}
		}catch(Exception e){e.printStackTrace();}  
		connection.close();
		return addcontent;
	}

	public boolean updateContent(AddContentBean addcontent) throws SQLException {
		boolean rowUpdated = false;
		Connection connection = null;
		try {
			 connection = ConnectMySQL.getMySQLConnection();

			PreparedStatement statement = connection.prepareStatement(UPDATE_CONTENT_SQL);
			statement.setString(1, addcontent.getTitle());
			statement.setString(2, addcontent.getBrief());
			statement.setString(3, addcontent.getContent());
			statement.setInt(4, addcontent.getId());

			rowUpdated = statement.executeUpdate() > 0;
		} catch (Exception e) {
			e.printStackTrace();
		}
		connection.close();
		return rowUpdated;
	}

	private static void printSQLException(SQLException ex) {
		for (Throwable e : ex) {
			if (e instanceof SQLException) {
				e.printStackTrace(System.err);
				System.err.println("SQLState: " + ((SQLException) e).getSQLState());
				System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
				System.err.println("Message: " + e.getMessage());
				Throwable t = ex.getCause();
				while (t != null) {
					System.out.println("Cause: " + t);
					t = t.getCause();
				}
			}
		}
	}

}
