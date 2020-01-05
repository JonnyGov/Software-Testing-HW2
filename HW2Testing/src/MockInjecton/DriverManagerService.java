package MockInjecton;

import java.sql.SQLException;

/**
 * <p>
 * This interface replace the DriverManager class. 
 * <br>
 * It is allow to put fake or real DriverManager class in the injection code. 
 * </p>
 * <p>
 * designed to  working with DB or a similar entity.
 * </p>
 * @author Ofek and Yonathan
 */
public interface DriverManagerService {
public ConnectionService getConnection(String str, String string, String string2) throws SQLException;
}
