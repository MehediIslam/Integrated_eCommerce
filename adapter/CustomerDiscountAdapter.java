package adapter;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Vector;

import model.CustomerDiscount;
public class CustomerDiscountAdapter {
	
/* ===========  Customer Discount List table [Done by rup]=========== */	
	public Vector<Vector<String>> getCustDiscountList()
	{
		try 
		{
			String sql = "SELECT * FROM customer_discounts";
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
				result.add(rs.getString(4));
				result.add(rs.getString(5));
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
	
	public boolean customerdiscountEdit(CustomerDiscount dis)
	{
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String sDate = formatter.format(dis.getStartDate());
		String eDate = formatter.format(dis.getEndDate());
		
		String sql = "UPDATE customer_discounts SET user_id='"+dis.getUserId()+"',discount='"+dis.getDiscount()+"',start_date='"+sDate+"',end_date='"+eDate+"' WHERE id='"+dis.getId()+"'";
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
