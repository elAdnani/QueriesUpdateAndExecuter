package SqlTool;

/**
 * 
 * @author elAdnani
 *
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLConnections {

	private static final String URL = "";

	private static final String LOGIN = "";

	private static final String MDP = "";

	private static final String DRIVER = "";

	protected static Connection con;

	protected SQLConnections() {
	}

	protected static Connection getConnection() {
		if (con == null) {
			try {
				Class.forName(DRIVER);
				con = DriverManager.getConnection(URL, LOGIN, MDP);

			} catch (SQLException | ClassNotFoundException e) {
				e.printStackTrace();
				con = null;
			}
		}
		return con;
	}

	protected static void deleteConnection() {
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	protected static String getDatabasename() {
		String databaseName = "";
		if (con != null) {
			try {
				databaseName = con.getSchema();
			} catch (SQLException e) {
				e.printStackTrace();
				databaseName = "";
			}
		}
		return databaseName;
	}

}
