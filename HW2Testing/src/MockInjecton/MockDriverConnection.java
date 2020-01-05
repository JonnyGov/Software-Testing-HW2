package MockInjecton;

import java.sql.SQLException;

public class MockDriverConnection implements DriverManagerService{
	public MockConnection monck;
	public SQLException exception;
	// giving our our mock ConnectionService 
	public MockDriverConnection(MockConnection monck,boolean throwException) {
	 this.monck=monck;
	 if (throwException)  exception=new SQLException("can't find sql");
	 else exception=null;
	}
	//replace the ConnectionService entity created by a DB = our given mock
	@Override
	public ConnectionService getConnection(String str, String string, String string2) throws SQLException {
		if (exception!=null) throw exception;
		monck.mockDriverConnection=this;
		return monck;
	}

}
