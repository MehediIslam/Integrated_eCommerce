package adapter;
import java.sql.ResultSet;
import java.util.Vector;

import model.Profile;
import model.Session;

public class ProfileAdapter 
{
	public boolean insertProfile(Profile profile )
	{
		{
			String sql = "INSERT INTO profiles(user_id,name, phone, email, address, creator_id)  VALUES ('"+profile.getUserId()+"','"+profile.getName()+"', '"+profile.getPhone()+"', '"+profile.getEmail()+"','"+profile.getAddress()+"','"+profile.getCreatorId()+"')";
			
			DataAccess data = new DataAccess();
			try {
				return data.executeSql(sql);	
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		}
	}
	
	
/* =========== Profilelist table [Done by rup]=========== */	
	public Vector<Vector<String>> getProfileList()
	{
		// get user type
		Session session = new Session();
		int sess_id = session.getUserId();
		UserAdapter typeAdapter = new UserAdapter();
		int type_id = typeAdapter.getUserType(sess_id);
		if(type_id == 300)
		{
			try 
			{
				String sql = "SELECT profiles.profile_id, profiles.user_id,profiles.name, profiles.phone, profiles.email, profiles.address, profiles.creator_id FROM profiles INNER JOIN users ON profiles.user_id=users.user_id WHERE users.type='Consummer' ";
				DataAccess data = new DataAccess();
				ResultSet rs=data.getResultSet(sql);
				Vector<Vector<String>> list= new Vector<Vector<String>>();
				while (rs.next())
				{
					Vector <String> result = new Vector <String>();
					result.add(rs.getString(1));
					//result.add(rs.getString(2));
					result.add(rs.getString(3));
					result.add(rs.getString(4));
					result.add(rs.getString(5));
					result.add(rs.getString(6));
					result.add(rs.getString(7));
					
					list.add(result);
				}
				rs.close();
				return list;	
			}
			catch (Exception ex)
			{
				ex.printStackTrace();
				return null;
			}
		}
		
		else
		{
			try 
			{
				String sql = "SELECT * FROM profiles";
				DataAccess data = new DataAccess();
				ResultSet rs=data.getResultSet(sql);
				Vector<Vector<String>> list= new Vector<Vector<String>>();
				while (rs.next())
				{
					Vector <String> result = new Vector <String>();
					result.add(rs.getString(1));
					//result.add(rs.getString(2));
					result.add(rs.getString(3));
					result.add(rs.getString(4));
					result.add(rs.getString(5));
					result.add(rs.getString(6));
					result.add(rs.getString(7));
					
					list.add(result);
				}
				rs.close();
				return list;	
			}
			catch (Exception ex)
			{
				ex.printStackTrace();
				return null;
			}
		}
	}
	
	
	public Vector<Vector<String>> EmployeeList()
	{
		try 
		{
			String sql = "SELECT profiles.profile_id,profiles.name, profiles.phone, profiles.email, profiles.address, profiles.creator_id,users.type,users.status FROM profiles INNER JOIN users ON profiles.user_id=users.user_id WHERE users.type='Employee' OR users.type='Manager' ";;			
			DataAccess data = new DataAccess();
			ResultSet rs=data.getResultSet(sql);
			
			Vector<Vector<String>> list= new Vector<Vector<String>>();
			while (rs.next())
			{
				Vector <String> result = new Vector <String>();
				result.add(rs.getString(1));
				result.add(rs.getString(2));
				result.add(rs.getString(3));
				result.add(rs.getString(4));	
				result.add(rs.getString(5));	
				result.add(rs.getString(6));
				result.add(rs.getString(7));
				result.add(rs.getString(8));
				list.add(result);
			}
			rs.close();
			return list;
			
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
			return null;
		}
	}
	
	public Vector<Vector<String>> ConsumerList()
	{
		try 
		{
			String sql = "SELECT profiles.profile_id,profiles.name, profiles.phone, profiles.email, profiles.address, profiles.creator_id FROM profiles INNER JOIN users ON profiles.user_id=users.user_id WHERE users.type='Consummer' ";;			
			DataAccess data = new DataAccess();
			ResultSet rs=data.getResultSet(sql);
			
			Vector<Vector<String>> list= new Vector<Vector<String>>();
			while (rs.next())
			{
				Vector <String> result = new Vector <String>();
				result.add(rs.getString(1));
				result.add(rs.getString(2));
				result.add(rs.getString(3));
				result.add(rs.getString(4));	
				result.add(rs.getString(5));	
				result.add(rs.getString(6));	
				list.add(result);
			}
			rs.close();
			return list;
			
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
			return null;
		}
	}
	
	public boolean profileEdit(Profile profile)
	{
		String sql = "UPDATE profiles SET name='"+profile.getName()+"',phone='"+profile.getPhone()+"',email='"+profile.getEmail()+"',address='"+profile.getAddress()+"',creator_id='"+profile.getCreatorId()+"' WHERE profile_id='"+profile.getProfileId()+"'";
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
	
	public boolean checkExisting(Profile phn )
	{
		
		String sql = "SELECT * FROM profiles WHERE phone ='"+phn.getPhone()+"' ";
		DataAccess data = new DataAccess();
		try {
			
			ResultSet rs= data.getResultSet(sql);
			if (rs.next())
			{
				return true;
			}
			else
			{
				return false;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}	
}
