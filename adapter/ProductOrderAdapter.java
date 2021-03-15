package adapter;
import java.sql.ResultSet;
import java.util.Vector;
import model.ProductOrder;
public class ProductOrderAdapter {
	
/* =========== Orderlist table [Done by rup]=========== */	
	public Vector<Vector<String>> getOrderList()
	{
		try 
		{
			String sql = "SELECT * FROM product_orders";
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
	
	public boolean orderEdit(ProductOrder order)
	{
		String sql = "UPDATE product_orders SET order_id='"+order.getOrderId()+"',product_id='"+order.getProductId()+"',order_price='"+order.getOrderPrice()+"',quantity='"+order.getQuantity()+"',status='"+order.getStatus()+"' WHERE id='"+order.getId()+"'";
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
