package adapter;
import java.sql.ResultSet;
import java.util.Vector;
import model.Supplier;
public class SuppliersAdapter {
		
	public Vector<Vector<String>> SuppliersList()
	{
		try 
		{
			String sql = "SELECT * FROM suppliers";			
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
	
	public boolean SuppliersEdit(Supplier sup)
	{
		String sql = "UPDATE suppliers SET supplier_name='"+sup.getSupplierName()+"',supplier_phone='"+sup.getSupplierPhone()+"',supplier_email='"+sup.getSupplierEmail()+"',supplier_address='"+sup.getSupplierAddress()+"',security_deposit='"+sup.getSecurityDeposit()+"' WHERE supplier_id='"+sup.getSupplierId()+"'";
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
	
	public boolean insertSupplier(Supplier sup )
	{
		
			String sql = "INSERT INTO suppliers VALUES (null,'"+sup.getSupplierName()+"' ,'"+sup.getSupplierPhone()+"','"+sup.getSupplierEmail()+"','"+sup.getSupplierAddress()+"','"+sup.getSecurityDeposit()+"')";
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
