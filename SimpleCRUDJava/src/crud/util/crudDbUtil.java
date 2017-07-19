package crud.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class crudDbUtil {
	
	private static Connection connection = null;
	
	public static Connection getConnect() {
		if(connection !=null)
			return connection;
		else {
			try {
				Properties prop = new Properties();
				InputStream is = crudDbUtil.class.getClassLoader().getResourceAsStream("/db.properties");
				prop.load(is);
				String driver = prop.getProperty("driver");
				String url = prop.getProperty("url");
				String user = prop.getProperty("user");
				String password = prop.getProperty("password");
				Class.forName(driver);
				connection = DriverManager.getConnection(url, user, password);
			}
			catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
			catch(FileNotFoundException e) {
				e.printStackTrace();
			}
			catch(IOException e) {
				e.printStackTrace();
			}
			return connection;
		}
	}
}
