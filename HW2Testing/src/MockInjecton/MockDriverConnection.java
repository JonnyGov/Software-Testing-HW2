package MockInjecton;

import java.sql.SQLException;

public class MockDriverConnection implements DriverManagerService{
	private MockConnection monck;
	public MockDriverConnection(MockConnection monck) {
	 this.monck=monck;
	}
	@Override
	public ConnectionService getConnection(String str, String string, String string2) throws SQLException {
	return monck;
	}

}
