package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.bean.Content;
import model.bean.LoginBean;
import model.bean.ProfileBean;
import model.dbconnection.ConnectMySQL;

public class LoginDAO   {
	private static final String FIND_ACCOUNT = "select id,firstname,lastname,email,phone,description from register where email=? and password=?";
	private static final String FIND_EMAIL = "select id,firstname,lastname,email,phone,description from register where email=?";
	
	public static ProfileBean checkLogin(LoginBean client){  
		ProfileBean user = new ProfileBean();
          
        try{  
            Connection con=ConnectMySQL.getMySQLConnection();
            PreparedStatement ps = null;
            
            if(client.getPassword() != null) {
            	ps=con.prepareStatement(FIND_ACCOUNT);  
            	ps.setString(1, client.getEmail());
            	ps.setString(2, client.getPassword());
            }else {
            	ps=con.prepareStatement(FIND_EMAIL);  
            	ps.setString(1, client.getEmail());
            }
            ResultSet rs = ps.executeQuery();

			while(rs.next()) {				
				user.setId(rs.getInt(1));
				user.setFirstname(rs.getString(2));
				user.setLastname(rs.getString(3));
				user.setEmail(rs.getString(4));
				user.setPhone(rs.getString(5));
				user.setDescription(rs.getString(6));
				
			}
            con.close();  
        }catch(Exception e){e.printStackTrace();}  
          
        return user;  
    }  
	
	
}
