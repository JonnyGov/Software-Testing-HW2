package MockInjecton;

import java.sql.SQLException;
/**
 * This interface replace the Statment class. 
 * <br>
 * It is allow to put fake or real Statment class in the injection code . 
 * @author Ofek and Yonathan
 */
public interface StatmentService {
	public ResultService executeQuery(String str) throws SQLException;

}
