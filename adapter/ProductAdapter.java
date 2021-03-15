package adapter;
import java.sql.ResultSet;
import java.util.Vector;
import model.Product;

public class ProductAdapter {
	public Vector<Product> getAllProducts()
	{
		String sql = "SELECT * FROM products";
		DataAccess data = new DataAccess();
		try {
			ResultSet rs = data.getResultSet(sql);
			Vector<Product> plist = new Vector<Product>();
			while(rs.next())
			{
				Product p = new Product();
				p.setProductId(rs.getInt("id"));
				p.setProductName(rs.getString("name"));
				p.setSellPrice(rs.getDouble("price"));
				p.setQuantity(rs.getInt("quantity"));
				plist.add(p);
			}
			
			return plist;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public Product getProductById(int id)
	{
		String sql = "SELECT * FROM products WHERE id=" + id;
		DataAccess data = new DataAccess();
		try {
			ResultSet rs = data.getResultSet(sql);
			if(rs.next())
			{
				Product p = new Product();
				p.setProductId(rs.getInt("id"));
				p.setProductName(rs.getString("name"));
				p.setSellPrice(rs.getDouble("price"));
				p.setQuantity(rs.getInt("quantity"));
				return p;
			}
			else
			{
				return null;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	
/* =========== Productlist table [Done by rup]=========== */	
	public Vector<Vector<String>> getProductList()
	{
		try 
		{
			String sql = "SELECT `product_id`, `product_name`, `buy_price`, `sell_price`, `quantity`, `last_purchased`, `last_sold`, (SELECT brand_name FROM `brands` WHERE brand_id=P.brand_id) as brand_name, (SELECT supplier_name FROM `suppliers` WHERE supplier_id=P.supplier_id) as supplier_name, (SELECT cat_name FROM `categories` WHERE cat_id=P.cat_id) as cat_name, `image_location`,`description` FROM `products`P";
			
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
				result.add(rs.getString(10));
				result.add(rs.getString(11));
				result.add(rs.getString(12));
						
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
	
	public Vector<Vector<String>> getProductList2()
	{
		try 
		{
			String sql = "SELECT `product_id`, `product_name`, `buy_price`, `sell_price`, `quantity`, `last_purchased`, `last_sold`, (SELECT brand_name FROM `brands` WHERE brand_id=P.brand_id) as brand_name, (SELECT supplier_name FROM `suppliers` WHERE supplier_id=P.supplier_id) as supplier_name, (SELECT cat_name FROM `categories` WHERE cat_id=P.cat_id) as cat_name, `image_location` FROM `products`P";
			
			DataAccess data = new DataAccess();
			ResultSet rs=data.getResultSet(sql);
			
			Vector<Vector<String>> list= new Vector<Vector<String>>();
			while (rs.next())
			{
				Vector <String> result = new Vector <String>();
				result.add(rs.getString(1));
				result.add(rs.getString(2));
				result.add(rs.getString(3));
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
	
	
	public Vector<Vector<String>> getProductList3()
	{
		try 
		{
			String sql = "SELECT `product_id`, `product_name`, `buy_price`, `sell_price`, `quantity`, `last_purchased`, `last_sold`, (SELECT brand_name FROM `brands` WHERE brand_id=P.brand_id) as brand_name, (SELECT supplier_name FROM `suppliers` WHERE supplier_id=P.supplier_id) as supplier_name, (SELECT cat_name FROM `categories` WHERE cat_id=P.cat_id) as cat_name, `image_location` FROM `products`P";
			
			DataAccess data = new DataAccess();
			ResultSet rs=data.getResultSet(sql);
			
			Vector<Vector<String>> list= new Vector<Vector<String>>();
			while (rs.next())
			{
				Vector <String> result = new Vector <String>();
				result.add(rs.getString(1));
				result.add(rs.getString(2));
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
	
	
	public boolean insertProduct(Product product)
	{
		String sql = "INSERT INTO products(product_name,buy_price,sell_price,quantity,last_purchased,last_sold,brand_id,supplier_id,cat_id,image_location,description) VALUES ('"+product.getProductName()+"','"+product.getBuyPrice()+"', '"+product.getSellPrice()+"','"+product.getQuantity()+"','2000-10-10','2000-10-10','"+product.getBrandId()+"','"+product.getSupplierId()+"','"+product.getCatId()+"','no image','"+product.getDescription()+"')";
		DataAccess data = new DataAccess();
		try {
			return data.executeSql(sql);
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	
	public boolean proEdit(Product product)
	{
		String sql = "UPDATE products SET product_name='"+product.getProductName()+"',buy_price='"+product.getBuyPrice()+"',sell_price='"+product.getSellPrice()+"',quantity='"+product.getQuantity()+"',brand_id='"+product.getBrandId()+"',supplier_id='"+product.getSupplierId()+"',cat_id='"+product.getCatId()+"',image_location='"+product.getImgLocation()+"',description='"+product.getDescription()+"' WHERE product_id='"+product.getProductId()+"'";
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
	
	public boolean updateSoldProduct(Product product)
	{
		String sql = "UPDATE products SET last_sold=CURDATE(), quantity = quantity - '"+product.getQuantity()+"' WHERE product_id='"+product.getProductId()+"'";
	
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
	
	public boolean updateLastPurchase(Product product)
	{
		String sql = "UPDATE products SET last_purchased=CURDATE(), quantity = quantity + '"+product.getQuantity()+"',buy_price='"+product.getBuyPrice()+"' WHERE product_id='"+product.getProductId()+"'";
	
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
