package MockInjecton;

import java.sql.SQLException;

public class MockDriverConnection implements DriverManagerService{
	private MockConnection monck;
	// giving our our mock ConnectionService 
	public MockDriverConnection(MockConnection monck) {
	 this.monck=monck;
	}
	//replace the ConnectionService entity created by a DB = our given mock
	@Override
	public ConnectionService getConnection(String str, String string, String string2) throws SQLException {
	return monck;
	}

}
