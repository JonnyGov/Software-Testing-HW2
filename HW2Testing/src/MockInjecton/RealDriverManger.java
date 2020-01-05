package MockInjecton;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLTimeoutException;

/**
 * This class implements <b>DriverManagerService</b> and works like the real
 * DriverManger.
 * 
 * @author Ofek and Yonathan
 * @see {@link DriverManagerService}
 */
public class RealDriverManger implements DriverManagerService {
	// SQLException nullSQLTimeoutException
	@Override
	public ConnectionService getConnection(String str, String string, String string2)
			throws SQLException, SQLTimeoutException {
		try {
			Connection conn = DriverManager.getConnection(str, string, string2);
			return new RealConnection(conn);
		} catch (SQLTimeoutException e) {
			throw e;
		} catch (SQLException ex) {
			throw ex;
		}

	}

}
