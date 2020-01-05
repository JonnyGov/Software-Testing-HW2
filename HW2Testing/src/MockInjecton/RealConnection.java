package MockInjecton;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * This class implements <b>ConnectionService</b> and works like the real Connection.
 * @author Ofek and Yonathan
 *@see
 *{@link ConnectionService}
 */
public class RealConnection implements ConnectionService{
	Connection conn;
	public RealConnection(Connection conn) throws SQLException{
		this.conn=conn;
	}
	public RealConnection() {
		conn=null;
	}

	@Override
	public StatmentService createStatement() throws SQLException {
		return  new RealStatment(conn);
	}
}
