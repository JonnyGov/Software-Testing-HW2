package MockInjecton;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class RealConnection implements ConnectionService{
	Connection conn;
	RealConnection(Connection conn) throws SQLException{
		this.conn=conn;
	}
	RealConnection() throws SQLException{
		conn=null;
	}

	@Override
	public StatmentService createStatement() throws SQLException {
		return  new RealStatment(conn);
	}
}
