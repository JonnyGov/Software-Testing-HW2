package MockInjecton;

import java.sql.SQLException;
// a interface designed to  working with DB or a similar entity
public interface DriverManagerService {
public ConnectionService getConnection(String str, String string, String string2) throws SQLException;
}
