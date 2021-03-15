package adapter;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.JOptionPane;

import model.ProductPurchases;
import model.Purchase;
import model.Session;
public class ProductPurchaseAdapter {
	Session sess = new Session();
/* =========== Produst Purchase List [Done by rup]=========== */	
	public Vector<Vector<String>> getPurchaseList()
	{
		try 
		{
			String sql = "SELECT product_purchases.id, product_purchases.purchase_id, product_purchases.product_id,products.product_name, product_purchases.buy_price, product_purchases.quantity, product_purchases.status FROM product_purchases INNER JOIN products ON product_purchases.product_id=products.product_id";
			
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
	
	public Vector<Vector<String>> getPurchaseShipmentList()
	{
		try 
		{
			String sql = "SELECT `purchase_id`, `purchaser_id`,(SELECT `supplier_name` FROM `suppliers` WHERE `supplier_id`=purchases.supplier_id) , `total_charge`, `advance_pay`, `receiver_id`, `approx_receive_date`, `receive_date`, `status` FROM `purchases`";
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
				result.add(rs.getString(9));
				list.add(result);
			}
			return list;
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
			return null;
		}
	}
	
	public boolean productpurchaseEdit(ProductPurchases pur)
	{
		String sql = "UPDATE product_purchases SET purchase_id='"+pur.getPurchaseId()+"',product_id='"+pur.getProductId()+"',buy_price='"+pur.getBuyPrice()+"',quantity='"+pur.getQuantity()+"',status='"+pur.getStatus()+"' WHERE id='"+pur.getId()+"'";
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
	
	public boolean insertToPurchases(Purchase purch)
	{
		Session ob = new Session();
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
		String date = formatter.format(purch.getApproxReceiveDate());
		
		String sql = "INSERT INTO purchases(purchaser_id,supplier_id, total_charge, advance_pay, receiver_id, approx_receive_date, receive_date,status) VALUES ('"+ob.getUserId()+"' , '"+purch.getSupplierId()+"' , '"+purch.getTotalCharge()+"' , '"+purch.getAdvancePay()+"' , '"+ob.getUserId()+"' , '"+date+"' , '2000-10-10' , 'Incomplete')";
		String sql2 = "select last_insert_id()";
		
		DataAccess da= new DataAccess();
		try {
			boolean a = da.executeSql(sql);
			ResultSet rs = da.getResultSet(sql2);
			if (rs.next())
			{
				sess.setLastId(Integer.parseInt(rs.getString(1)));
			}
			return a;
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}	
	}
	
	public boolean insertToProductPurchases(ProductPurchases purchase)
	{
		String sql = "INSERT INTO product_purchases (purchase_id, product_id, buy_price, quantity, status) VALUES ('0','"+purchase.getProductId()+"','"+purchase.getBuyPrice()+"','"+purchase.getQuantity()+"','"+purchase.getStatus()+"')";
		String sql2 = "select last_insert_id()";
		DataAccess data = new DataAccess();
		try {
			boolean a = data.executeSql(sql);
			ResultSet rs = data.getResultSet(sql2);
			if (rs.next())
			{
				sess.setLastId(Integer.parseInt(rs.getString(1)));
				sess.id.add(Integer.parseInt(rs.getString(1)));
			}
			return a;
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}	
	}
	
	public boolean setPurchaseId()
	{
		{
			DataAccess data = new DataAccess();
			int count = this.sess.id.size();
			while (count>0)
			{
				String sql = "UPDATE product_purchases SET purchase_id='"+sess.getLastId()+"'WHERE id='"+this.sess.id.elementAt(count-1)+"'";
				this.sess.id.remove(this.sess.id.size()-1);	
				count--;
			try {
					data.executeSql(sql);	
					
				} 
				
				catch (Exception e) 
				{
					e.printStackTrace();
					return false;
				}
			}
		}
		return false;	
	}
	
	
	public boolean setLastSuppId(int proID, int purchaseID)
	{
		String sql = "UPDATE products SET supplier_id=(SELECT supplier_id FROM purchases WHERE purchase_id='"+purchaseID+"') WHERE product_id='"+proID+"'";
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
	
	
	//Complete Purchase shipment
	public void updatePurchaseShipmentStatus(int purchase_id)
	{
		DataAccess data = new DataAccess();
		String sql = "SELECT * FROM product_purchases where purchase_id = '"+purchase_id+"'";
		int a = 0;
		try{
			ResultSet rs=data.getResultSet(sql);
			rs.last();
			int count= rs.getRow();
			rs.beforeFirst();
			
			while (rs.next())
			{
				if(rs.getString(6).equals("Complete"))
				{
					a++;		
				}	
			}
			
			if(a == count)
			{
				String sql1 = "UPDATE purchases SET status='Completed', receive_date = CURDATE() WHERE purchase_id='"+purchase_id+"'";
				JOptionPane.showMessageDialog(null,"Shipment NO: "+purchase_id+"\nProduct Purchase is Completed");
				data.executeSql(sql1);
			}
		}
		catch (Exception e) 
		{
			System.out.println("Error");
			e.printStackTrace();
		}		
	}
}
