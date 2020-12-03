package core.util.db;

import java.io.IOException;
import java.io.StringReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DB {

	private static Connection conn = null;

	public static Connection getConnection() {
        try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (conn == null) {
			try {
				Properties props = loadProperties();
				String url = props.getProperty("dburl");
				conn = DriverManager.getConnection(url, props);
			} catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
		return conn;
	}

	public static void closeConnection() {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
	}

	private static Properties loadProperties() {
	    StringBuilder sb = new StringBuilder();
	    sb.append("user=developer");
        sb.append("\n");
        sb.append("password=59862653");
        sb.append("\n");
        sb.append("dburl=jdbc:mysql://localhost:3306/SISTEMA_CINEMA");
        sb.append("\n");
        sb.append("allowPublicKeyRetrieval=true");
        sb.append("\n");
        sb.append("useSSL=false");

        Properties props = new Properties();
        try {
            props.load(new StringReader(sb.toString()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return props;

	    /*
		try (FileInputStream fs = new FileInputStream("config/db.properties")) {
			Properties props = new Properties();
			props.load(fs);
			return props;
		} catch (IOException e) {
			throw new DbException(e.getMessage());
		}*/
	}

	public static void closeStatement(Statement st) {
		if (st != null) {
			try {
				st.close();
			} catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
	}

	public static void closeResultSet(ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
	}

}
