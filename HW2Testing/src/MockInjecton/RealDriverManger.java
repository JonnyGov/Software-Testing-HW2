package MockInjecton;

import java.sql.DriverManager;
import java.sql.SQLException;
/**
 * This class implements <b>DriverManagerService</b> and works like the real DriverManger.
 * @author Ofek and Yonathan
 *@see
 *{@link DriverManagerService}
 */
public class RealDriverManger implements DriverManagerService {
	
	@Override
	public ConnectionService getConnection(String str, String string, String string2) throws SQLException {
		return new RealConnection(DriverManager.getConnection(str,string,string2));
	}

}
