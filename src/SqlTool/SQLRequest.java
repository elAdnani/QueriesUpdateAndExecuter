package SqlTool;

/**
 * 
 * @author elAdnani
 *
 */
import java.sql.ResultSet;

public class SQLRequest {

	private static PreparedStatementExecuter executer;

	private SQLRequest() {
	}

	public static SQLRequest getInstance() { // singleton
		if (executer == null) {
			executer = new PreparedStatementExecuter(SQLConnections.getConnection());
		}
		return SQLRequestHolder.instance;
	}

	public String getDatabaseName() {
		return SQLConnections.getDatabasename();
	}

	private static class SQLRequestHolder {

		private static final SQLRequest instance = new SQLRequest();

	}

	public ResultSet executeQuery(String query, Object... parameters) {
		return executer.executeQuery(query, parameters);
	}

	public int executeUpdate(String query, Object... parameters) {
		return executer.executeUpdate(query, parameters);
	}

}
