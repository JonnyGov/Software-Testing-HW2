package MockInjecton;

import java.sql.SQLException;
import java.sql.Statement;

public interface ConnectionService {
public StatmentService createStatement() throws SQLException;
}
