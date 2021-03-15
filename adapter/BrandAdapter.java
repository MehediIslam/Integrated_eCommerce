package adapter;
import java.sql.ResultSet;
import java.util.Vector;
import model.Brand;
public class BrandAdapter {
	
/* =========== Brandlist table [Done by rup]=========== */	
	public Vector<Vector<String>> getBrandList()
	{
		try 
		{
			String sql = "SELECT * FROM brands";
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
	
	public boolean brandEdit(Brand brand)
	{
		String sql = "UPDATE brands SET brand_name='"+brand.getBrandName()+"',description='"+brand.getDescription()+"' WHERE brand_id='"+brand.getBrandId()+"'";
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
	
	public boolean insertBrand(Brand brand )
	{
		
			String sql = "INSERT INTO brands VALUES (null,'"+brand.getBrandName()+"' ,'"+brand.getDescription()+"')";
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
