package MockInjecton;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
/**
 * This class implements <b>StatmentService</b> and works like the real Statment.
 * @author Ofek and Yonathan
 *@see
 *{@link StatmentService}
 */
public class RealStatment  implements StatmentService{
	Statement stmt; 
	public RealStatment(Connection conn) throws SQLException {
		stmt=conn.createStatement();
	}
	@Override
	public RealResult executeQuery(String str) throws SQLException {
		return new RealResult(stmt, str);
	}
}
