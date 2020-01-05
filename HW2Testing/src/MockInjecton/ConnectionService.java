package MockInjecton;

import java.sql.SQLException;


/**
 * This interface replace the Connection class. 
 * <br>
 * It is allow to put fake or real Connection class in the injection code . 
 * @author Ofek and Yonathan
 * @see RealConnection
 * @see MockConnection
 */
public interface ConnectionService {
public StatmentService createStatement() throws SQLException;
}
