package MockInjecton;

import java.sql.DriverManager;
import java.sql.SQLException;

public class RealDriverManger implements DriverManagerService {
	
	@Override
	public ConnectionService getConnection(String str, String string, String string2) throws SQLException {
		return new RealConnection(DriverManager.getConnection(str,string,string2));
	}

}
