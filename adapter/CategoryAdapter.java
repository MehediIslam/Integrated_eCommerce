package adapter;

import java.sql.ResultSet;
import java.util.Vector;
import model.Categorie;

public class CategoryAdapter {
	
/* =========== Categorylist table [Done by rup]=========== */	
	public Vector<Vector<String>> getCategoryList()
	{
		try 
		{
			String sql = "SELECT * FROM categories";
			
			//ResultSet rs= this.statement.executeQuery(sql);		
			DataAccess data = new DataAccess();
			ResultSet rs=data.getResultSet(sql);		
			Vector<Vector<String>> list= new Vector<Vector<String>>();
			while (rs.next())
			{
				Vector <String> result = new Vector <String>();
				result.add(rs.getString(1));
				result.add(rs.getString(2));
				result.add(rs.getString(3));						
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
	
	public boolean catagoryEdit(Categorie cat)
	{
		String sql = "UPDATE categories SET cat_name='"+cat.getCatName()+"',description='"+cat.getDescription()+"' WHERE cat_id='"+cat.getCatId()+"'";
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
	
	public boolean insertCategory(Categorie cat )
	{
		
			String sql = "INSERT INTO categories VALUES (null,'"+cat.getCatName()+"' ,'"+cat.getDescription()+"')";
			DataAccess data = new DataAccess();
			try 
			{
				
				return data.executeSql(sql);
				
			} catch (Exception e) 
			{
				e.printStackTrace();
				return false;
			}
		
	
	}
}
