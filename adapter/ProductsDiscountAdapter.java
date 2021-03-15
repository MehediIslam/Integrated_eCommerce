package adapter;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Vector;

import model.ProductDiscounts;
import model.Profile;
public class ProductsDiscountAdapter {
	
/* =========== Product Discount List table [Done by rup]=========== */	
	public Vector<Vector<String>> getProDiscountList()
	{
		try 
		{
			String sql = "SELECT product_discounts.id, products.product_name, product_discounts.discount, product_discounts.start_date, product_discounts.end_date FROM product_discounts INNER JOIN products ON product_discounts.product_id=products.product_id";
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
	
	public boolean productsdiscountEdit(ProductDiscounts dis)
	{
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String sDate = formatter.format(dis.getStartDate());
		String eDate = formatter.format(dis.getEndDate());
		String sql = "UPDATE product_discounts SET discount='"+dis.getDiscount()+"',start_date='"+sDate+"',end_date='"+eDate+"' WHERE id='"+dis.getId()+"'";
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
	
	public boolean insertProductsDiscount(ProductDiscounts disc)
	{
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		
		
		String eDate = formatter.format(disc.getStartDate());
		String sDate = formatter.format(disc.getEndDate());
		String sql = "INSERT INTO product_discounts(product_id,discount,start_date,end_date) VALUES ('"+disc.getId()+"','"+disc.getDiscount()+"','"+sDate+"','"+eDate+"')";
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
	
	public boolean checkExisting( int productID)
	{
		{
			String sql = "SELECT * FROM product_discounts WHERE product_id ='"+productID+"' ";
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
}
