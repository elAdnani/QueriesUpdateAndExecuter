package SqlTool;

/**
 * 
 * @author elAdnani
 *
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PreparedStatementExecuter {
	protected PreparedStatement preparedStatement;
	protected Connection connection;

	protected PreparedStatementExecuter(Connection connection) {
		this.connection = connection;
	}

	protected ResultSet executeQuery(String query, Object... parameters) {
		ResultSet result = null;
		try {
			preparedStatement = connection.prepareStatement(query);
			setObjects(parameters);
			result = preparedStatement.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	protected int executeUpdate(String query, Object... parameters) {
		int result = -1;
		try {
			preparedStatement = connection.prepareStatement(query);
			setObjects(parameters);
			result = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	private void setObjects(Object... parameters) throws SQLException {
		int i = 1;
		for (Object elem : parameters) {
			if (elem.getClass().isArray()) {
				for (Class<?> element : (Class<?>[]) elem) {
					handleObject(i, element);
					i = i + 1;
				}
			} else {
				handleObject(i, elem);
				i = i + 1;
			}
		}
	}

	private void handleObject(int i, Object parameter) throws SQLException {
		switch (parameter.getClass().getSimpleName()) {
		case "String":
			preparedStatement.setString(i, (String) parameter);
			break;
		case "Integer":
			preparedStatement.setInt(i, (int) parameter);
			break;
		default:
			preparedStatement.setObject(i, parameter);
			break;

		}
	}

}
