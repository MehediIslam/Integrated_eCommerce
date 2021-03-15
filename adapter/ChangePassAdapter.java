package adapter;

import java.sql.ResultSet;

import javax.swing.JOptionPane;

import model.Profile;
import model.Session;
import model.User;

public class ChangePassAdapter {

	Session user_id = new Session();
	
	//get old password
	public String getOldPass() {
		try {
			
			String sql = "SELECT password FROM users WHERE user_id='"+ user_id.getUserId() +"'";
			DataAccess data = new DataAccess();
			ResultSet rs = data.getResultSet(sql);
			String oldpass="";
			
			while(rs.next()){
			     oldpass = rs.getString("password");
			}
			rs.close();
			return oldpass;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
	
	
	//update password
	public boolean updatePassword(String updatePass)
	{
		String sql = "UPDATE users SET password='"+updatePass+"' WHERE user_id='"+user_id.getUserId()+"'";
		DataAccess da= new DataAccess();
		try 
		{
			return da.executeSql(sql);
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
			return false;
		}	
	}

	
}
