package MockInjecton;

import java.sql.SQLException;

public interface ResultService {
	public boolean next() throws SQLException;
	public float getFloat(int column) throws SQLException;
	public void close() throws SQLException;
	public int getInt(int i) throws SQLException;
}
