package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.bean.ProfileBean;
//import model.bean.RegisterBean;
import model.dbconnection.ConnectMySQL;

public class ProfileDAO {
	
	public static boolean insertProfile(ProfileBean user) throws ClassNotFoundException  {
		boolean flag = false; 

			Connection conn = null;
			try {
			conn = ConnectMySQL.getMySQLConnection();
			String sql = "update register set firstname = ?,lastname= ?, phone =?, description =?  where email = ?";

			PreparedStatement pstm = conn.prepareStatement(sql);

			pstm.setString(1, user.getFirstname());
			pstm.setString(2, user.getLastname());
			pstm.setString(3, user.getPhone());
			pstm.setString(4, user.getDescription());
			pstm.setString(5, user.getEmail());
			

		
				flag = pstm.executeUpdate() > 0;
				 conn.close();  
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			

		
		return flag;

	}

}
