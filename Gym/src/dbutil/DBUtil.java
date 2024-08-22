package dbutil;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DBUtil {
	private static  String driver;
	private static  String url;
	private static  String id;
	private static  String password;
	
	static {
		loadProperties("localDB.properties");
		loadDriver();
		
	}
	
	public static void loadProperties(String filename) {
		try(InputStream resourceStream =DBUtil.class.getResourceAsStream(filename);) {
			Properties properties = new Properties();
			properties.load(resourceStream);
			
			driver = properties.getProperty("driver");
			url = properties.getProperty("url");
			id = properties.getProperty("user");
			password = properties.getProperty("password");
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void loadDriver() {
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection(String dbName) throws SQLException {
		return DriverManager
				.getConnection(url + dbName
						, id
						, password);
	}
	
	public static void closeConnection(Connection conn) {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void closeStatement(Statement stmt) {
		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void closeResultSet(ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void closeAll(ResultSet rs
			, Statement stmt
			, Connection conn) {
		closeResultSet(rs);
		closeStatement(stmt);
		closeConnection(conn);
	}
}











