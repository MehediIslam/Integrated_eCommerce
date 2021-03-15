package adapter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DataAccess {
	
	private Connection connection;
	private Statement statement;
	
	private String server = "localhost";
	private int port = 3306;
	private String username = "root";
	private String password = "";
	private String database = "ishopdb";
	
	public DataAccess()
	{
		try {
			Class.forName("com.mysql.jdbc.Driver");
			this.connection = DriverManager.getConnection("jdbc:mysql://" + this.server + ":" + this.port + "/" + this.database, this.username, this.password);
			this.statement = this.connection.createStatement();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public ResultSet getResultSet(String sql)
	{
		try {
			return this.statement.executeQuery(sql);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public boolean executeSql(String sql)
	{
		try {
			return this.statement.execute(sql);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	protected void finalize() throws Throwable {
		// TODO Auto-generated method stub
		try {
			this.statement.close();
			this.connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally
		{
			super.finalize();
		}
	}
}
