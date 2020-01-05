package MockInjecton;

import java.sql.SQLException;
/**
 * This interface replace the ResultSet class. 
 * <br>
 * It is allow to put fake or real ResultSet class in the injection code . 
 * @author Ofek and Yonathan
 */
public interface ResultService {
	public boolean next() throws SQLException;
	public float getFloat(int column) throws SQLException;
	public void close() throws SQLException;
	public int getInt(int i) throws SQLException;
}
