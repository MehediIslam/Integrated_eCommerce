package adapter;

import java.sql.ResultSet;
import java.util.Vector;

import model.ProductsSell;
import model.Sell;
import model.Session;

public class ProductSaleAdapter {
	Session sess = new Session();

	/* =========== Product Sale list table [Done by rup]=========== */
	public Vector<Vector<String>> getSaleList() {
		try {
			String sql = "SELECT products_sells.id, sells.buyer_id, (SELECT product_name FROM products WHERE product_id=products_sells.product_id), products_sells.sell_price, products_sells.quantity, sells.sell_date FROM products_sells INNER JOIN sells ON products_sells.sell_id=sells.sell_id";
			// ResultSet rs= this.statement.executeQuery(sql);

			DataAccess data = new DataAccess();
			ResultSet rs = data.getResultSet(sql);

			Vector<Vector<String>> list = new Vector<Vector<String>>();
			while (rs.next()) {
				Vector<String> result = new Vector<String>();
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

		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	public boolean productSellEdit(ProductsSell ProductsSell) {
		String sql = "UPDATE products_sells SET sell_id='"
				+ ProductsSell.getSellId() + "',product_id='"
				+ ProductsSell.getProductId() + "',sell_price='"
				+ ProductsSell.getSellPrice() + "',quantity='"
				+ ProductsSell.getQuantity() + "' WHERE id='"
				+ ProductsSell.getId() + "'";
		DataAccess da = new DataAccess();
		try {
			return da.executeSql(sql);
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	public Vector<Vector<String>> getProductList() {
		try {
			String sql = "SELECT products.product_id, products.product_name,products.sell_price, products.quantity,(SELECT discount FROM product_discounts WHERE end_date > curdate() and products.product_id=product_discounts.product_id ) FROM products";

			DataAccess data = new DataAccess();
			ResultSet rs = data.getResultSet(sql);

			Vector<Vector<String>> list = new Vector<Vector<String>>();
			while (rs.next()) {
				Vector<String> result = new Vector<String>();
				result.add(rs.getString(1));
				result.add(rs.getString(2));
				result.add(rs.getString(3));
				result.add(rs.getString(4));
				result.add(rs.getString(5));
				list.add(result);
			}
			rs.close();
			return list;

		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	public boolean insertToProductSell(ProductsSell ProductsSel) {
		String sql = "INSERT INTO products_sells VALUES (null,'0','"
				+ ProductsSel.getProductId() + "' ,'"
				+ ProductsSel.getSellPrice() + "','"
				+ ProductsSel.getQuantity() + "')";
		String sql2 = "select last_insert_id()";
		DataAccess data = new DataAccess();
		try {
			boolean a = data.executeSql(sql);
			ResultSet rs = data.getResultSet(sql2);
			if (rs.next()) {
				sess.setLastId(Integer.parseInt(rs.getString(1)));
				sess.id.add(Integer.parseInt(rs.getString(1)));
			}
			return a;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean insertToSells(Sell sell) {
		{
			Session session = new Session();
			String sql = "INSERT INTO sells(buyer_id, total_charges,paid_amount,sell_date,seller_id) VALUES ('"
					+ sell.getBuyerId()
					+ "' ,'"
					+ sell.getTotalCharges()
					+ "','"
					+ sell.getPaidAmount()
					+ "',CURDATE(),'"
					+ session.getUserId() + "')";
			String sql2 = "select last_insert_id()";
			DataAccess data = new DataAccess();
			try {

				boolean a = data.executeSql(sql);
				ResultSet rs = data.getResultSet(sql2);
				if (rs.next()) {
					sess.setLastId(Integer.parseInt(rs.getString(1)));
				}
				return a;

			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		}
	}

	// Statement.RETURN_GENERATED_KEYS
	public boolean setSellId() {
		{
			DataAccess data = new DataAccess();
			int count = this.sess.id.size();
			while (count > 0) {
				String sql = "UPDATE products_sells SET sell_id='"
						+ sess.getLastId() + "'WHERE id='"
						+ this.sess.id.elementAt(count - 1) + "'";
				this.sess.id.remove(this.sess.id.size() - 1);
				count--;

				try {
					data.executeSql(sql);
				}

				catch (Exception e) {
					e.printStackTrace();
					return false;
				}
			}
		}
		return false;
	}
}
