package MockInjecton;

import java.sql.SQLException;

public interface StatmentService {
	public ResultService executeQuery(String str) throws SQLException;

}
