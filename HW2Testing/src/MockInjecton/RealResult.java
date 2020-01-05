package MockInjecton;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
/**
 * This class implements <b>ResultService</b> and works like the real ResultSet.
 * @author Ofek and Yonathan
 *@see
 *{@link ResultService}
 */
public class RealResult implements ResultService {
	private ResultSet rs;
	public RealResult(Statement stmt,String str) throws SQLException{
		rs=stmt.executeQuery(str);
	}
	@Override
	public boolean next() throws SQLException {
		return rs.next();
	}

	@Override
	public float getFloat(int column) throws SQLException {
		return rs.getFloat(column);
	}

	@Override
	public void close() throws SQLException {
		rs.close();

	}
	@Override
	public int getInt(int column) throws SQLException {
		return rs.getInt(column);
	}

}
