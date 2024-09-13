package dbConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnection {
	
	public DBConnection() {
		super();
	}

	public static Connection getConnection() {
		Connection conn = null;
		String url = "jdbc:sqlserver://localhost:1433;databaseName=UploadFileServletDB;encrypt=false";
		String user = "sa";
		String pass = "sapassword";
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			conn = DriverManager.getConnection(url, user, pass);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return conn;
	}
}
