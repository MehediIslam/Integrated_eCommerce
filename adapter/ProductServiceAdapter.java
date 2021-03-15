package adapter;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Vector;
import java.sql.Statement;

import javax.swing.JOptionPane;

import model.ProductServices;
import model.Service;
import model.Session;
public class ProductServiceAdapter {
	Session sess = new Session();
/* =========== Servicelist table [Done by rup]=========== */	
	public Vector<Vector<String>> getServiceList()
	{
		try 
		{
			String sql = "SELECT product_services.id, product_services.service_id, products.product_name, product_services.problem_description, product_services.repair_cost, product_services.status FROM product_services INNER JOIN products ON product_services.product_id=products.product_id";
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
			return list;
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
			return null;
		}
	}
	
	public Vector<Vector<String>> getServiceShipmentList()
	{
		try 
		{
			String sql = "SELECT `service_id`, `customer_id`, `total_charges`, `advance_pay`, `receive_date`, (SELECT `username` FROM `users` WHERE `user_id`=services.receiver_id), `delivery_date`, `status` FROM `services` ";
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
			return list;
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
			return null;
		}
	}
	
	public boolean productserviceEdit(ProductServices ser)
	{
		String sql = "UPDATE product_services SET problem_description='"+ser.getProblemDescription()+"',repair_cost='"+ser.getRepairCost()+"',status='"+ser.getStatus()+"' WHERE id='"+ser.getId()+"'";
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
	
	public boolean insertToProductService(ProductServices proService)
	{
		String sql = "INSERT INTO product_services(service_id, product_id, problem_description, repair_cost, status) VALUES (0,'"+proService.getProductId()+"','"+proService.getProblemDescription()+"','"+proService.getRepairCost()+"','"+proService.getStatus()+"')";
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
	
	public boolean insertToService(Service Service)
	{
		Session session = new Session();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
		String date = formatter.format(Service.getDeliveryDate());
		
		String sql = "INSERT INTO services(customer_id, total_charges, advance_pay, receive_date, receiver_id, delivery_date,status) VALUES ('"+Service.getCustomerId()+"','"+Service.getTotalCharges()+"','"+Service.getAdvancePay()+"',CURDATE(),'"+session.getUserId()+"','"+date+"','Incomplete')";
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
	
	public boolean setServiceId()
	{
		{
			DataAccess data = new DataAccess();
			int count = this.sess.id.size();
			while (count>0)
			{
				String sql = "UPDATE product_services SET service_id='"+sess.getLastId()+"'WHERE id='"+this.sess.id.elementAt(count-1)+"'";
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
	
	
	//Complete Service Shipment
	public void updateServiceStatus(int serviceId)
	{
		DataAccess data = new DataAccess();
		String sql = "SELECT * FROM product_services where service_id = '"+serviceId+"'";
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
				String sql1 = "UPDATE services SET status='Completed' WHERE service_id='"+serviceId+"'";
				JOptionPane.showMessageDialog(null,"Shipment NO: "+serviceId+"\nProduct Service is Completed");
				data.executeSql(sql1);
			}
		}
		catch (Exception e) 
		{
			System.out.println("Error");
			e.printStackTrace();
		}
	}
	
	
	public void updateServiceShipmentDeliverDate(int shipmentID)
	{
		DataAccess data = new DataAccess();
		String sql= "UPDATE services SET delivery_date=CURDATE() WHERE service_id='"+shipmentID+"'";
	
		try 
		{
			data.executeSql(sql);							
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
}
