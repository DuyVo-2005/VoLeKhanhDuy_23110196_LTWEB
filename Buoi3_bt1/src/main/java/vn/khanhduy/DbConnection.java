package vn.khanhduy;

import java.sql.DriverManager;

import com.sun.jdi.connect.spi.Connection;

public class DbConnection {
	private final String serverName = "";
	private final String dbName = "ltwebs56";
	private final String portNumber = "1433";
	private final String instance = "";
	private final String userID = "sa";
	private final String password = "123456";

	public Connection getConnection() throws Exception {
		String url = "jdbc:sqlserver://" + serverName + "\\" + instance + ":" + portNumber + ";databaseName=" + dbName;
		if (instance == null || instance.trim().isEmpty())
			url = "jdbc:sqlserver://" + serverName + ":" + portNumber + ";databaseName=" + dbName;
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		return (Connection) DriverManager.getConnection(url, userID, password);
	}

	public static void main(String[] args) {
		try {
			System.out.println(new DbConnection().getConnection());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
