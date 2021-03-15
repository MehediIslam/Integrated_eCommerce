package adapter;

import java.sql.ResultSet;

import javax.swing.JOptionPane;

import model.Session;
import model.User;

public class UserAdapter {

	public boolean validateUser(User user) {
		String sql = "SELECT * FROM users WHERE username='"
				+ user.getUsername() + "' AND password='" + user.getPassword()
				+ "' AND (type='Admin' OR type='Manager') AND status='active' ";
		DataAccess data = new DataAccess();
		try {
			ResultSet rs = data.getResultSet(sql);

			return rs.next();
		} catch (Exception e) {
			return false;
		}
	}

	public boolean validateUserEmploye(User user) {
		String sql = "SELECT * FROM users WHERE username='"
				+ user.getUsername() + "' AND password='" + user.getPassword()
				+ "' AND type='Employee' AND status='active' ";
		DataAccess data = new DataAccess();
		try {
			ResultSet rs = data.getResultSet(sql);

			return rs.next();
		} catch (Exception e) {
			return false;
		}
	}

	public boolean insertLastDate(User user) {

		String sql = " UPDATE users SET last_login=CURDATE() WHERE username='"
				+ user.getUsername() + "' AND password='" + user.getPassword()
				+ "'";
		DataAccess data = new DataAccess();
		try {
			return data.executeSql(sql);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public int getUserId(User user) {
		try {
			String sql = "SELECT * FROM users WHERE username='"
					+ user.getUsername() + "' AND password='"
					+ user.getPassword() + "'";
			DataAccess data = new DataAccess();
			ResultSet rs = data.getResultSet(sql);
			int id = 0;
			;
			while (rs.next()) {
				id = ((Integer) rs.getObject(1)).intValue();
			}
			rs.close();
			return id;
		} catch (Exception ex) {
			ex.printStackTrace();
			return 0;
		}
	}

	public boolean insertUser(User user) {
		{
			String sql = "INSERT INTO users VALUES (null,'"
					+ user.getUsername() + "' ,'" + user.getPassword() + "','"
					+ user.getType() + "','active','2000-10-10')";
			DataAccess data = new DataAccess();
			try {
				return data.executeSql(sql);
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		}
	}

	// Deactivated user Access
	public boolean userDeactive(int id) {

		// get user type
		Session session = new Session();
		int sess_id = session.getUserId();
		UserAdapter typeAdapter = new UserAdapter();
		int type_id = typeAdapter.getUserType(sess_id);

		// Manager,Employee role -> Access
		if (type_id == 200 || type_id == 300) {
			JOptionPane.showMessageDialog(null, "You have no access");
		}

		else {
			JOptionPane.showMessageDialog(null, "This User is Deactivated");
			String sql = "UPDATE users SET status='deactive' WHERE user_id= (SELECT user_id FROM profiles WHERE profile_id='"
					+ id + "')";
			DataAccess data = new DataAccess();
			try {
				return data.executeSql(sql);

			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		}
		return false;
	}

	// Activated user Access
	public boolean userActive(int id) {

		// get user type
		Session session = new Session();
		int sess_id = session.getUserId();
		UserAdapter typeAdapter = new UserAdapter();
		int type_id = typeAdapter.getUserType(sess_id);

		// Manager,Employee role -> Access
		if (type_id == 200 || type_id == 300) {
			JOptionPane.showMessageDialog(null, "You have no access");
		}

		else {
			JOptionPane.showMessageDialog(null, "This User is Activated");
			String sql = "UPDATE users SET status='active' WHERE user_id= (SELECT user_id FROM profiles WHERE profile_id='"
					+ id + "')";
			DataAccess data = new DataAccess();
			try {
				return data.executeSql(sql);

			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		}
		return false;
	}

	// get user type for accessibility
	public int getUserType(int sess_id) {
		try {
			String sql = "SELECT type FROM users WHERE user_id='" + sess_id
					+ "'";
			DataAccess data = new DataAccess();
			ResultSet rs = data.getResultSet(sql);
			int id = 0;
			String type = "";
			;
			while (rs.next()) {
				type = rs.getString("type");
			}
			rs.close();

			if (type.equals("Admin")) {
				id = 100;
			}

			else if (type.equals("Manager")) {
				id = 200;
			}

			else if (type.equals("Employee")) {
				id = 300;
			}

			return id;
		} catch (Exception ex) {
			ex.printStackTrace();
			return 0;
		}
	}
}
