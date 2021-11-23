package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.bean.RegisterBean;
import model.dbconnection.ConnectMySQL;

public class RegisterDAO {
	
	private static final String FIND_USERNAME = "select * from register where username =?";
	private static final String FIND_EMAIL = "select * from register where email =? ";
	private static final String INSERT_REGISTER = "Insert into register(username, email,password) values (?,?,?)";
	public static boolean checkUsername(RegisterBean user){  
		boolean isValid = false;
          
        try{  
            Connection con=ConnectMySQL.getMySQLConnection();  
            PreparedStatement ps=con.prepareStatement(FIND_USERNAME);
            
            ps.setString(1, user.getUserName());
            
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
            	isValid = true;
            }
            else {
            	isValid = false;
            }
            con.close();  
        }catch(Exception e){e.printStackTrace();}  
          
        return isValid;  
    }  
	public static boolean checkEmail(RegisterBean user){  
		boolean isValid = false;
          
        try{  
            Connection con=ConnectMySQL.getMySQLConnection();  
            PreparedStatement ps=con.prepareStatement(FIND_EMAIL);
            
            ps.setString(1, user.getEmail());
            
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
            	isValid = true;
            }
            else {
            	isValid = false;
            }
            con.close();  
        }catch(Exception e){e.printStackTrace();}  
          
        return isValid;  
    }  
	public static boolean insertRegister(RegisterBean user) throws SQLException {
		Connection conn;
		boolean flag = false;
		try {
			conn = ConnectMySQL.getMySQLConnection();
			

			PreparedStatement pstm = conn.prepareStatement(INSERT_REGISTER);

			pstm.setString(1, user.getUserName());
			pstm.setString(2, user.getEmail());
			pstm.setString(3, user.getPassword());

			
			flag = pstm.executeUpdate() > 0;

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		
		return flag;

	}
}
