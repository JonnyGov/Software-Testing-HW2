package MockInjecton;
import java.sql.SQLException;
import java.util.ArrayList;

import MockInjecton.MockConnection.rowInDatabase;

public class MockResult implements ResultService{
	private int index=0;
	private ArrayList <? extends rowInDatabase> table;
	public boolean isClosed=false;
	public SQLException exception=null;
	public MockStatment mockStatment=null;
	/** Constructor 
	 * @param table - the array list which holds rows 
	 * @param where - the key 
	 */
	public MockResult(ArrayList<? extends rowInDatabase> table, String where,MockStatment mockStatment) {
		this.table=table;
		this.mockStatment=mockStatment;
	}
	/**  Going throw the rows of the table 
	 * @throws SQLException 
	 *
	 */
	@Override
	public boolean next() throws SQLException {
		if (mockStatment.exception!=null) throw exception=new SQLException("can't get data.");
		if (isClosed) throw exception=new SQLException("mock result is closed.");
		
		try {
		table.get(index++);
		return true;
		}catch (IndexOutOfBoundsException e) {
			try {
				table.get(index-2);
				return false;
			}catch (IndexOutOfBoundsException e2) {
				throw exception=new SQLException("Illegal operation on empty result set.");
			}
			
		}
	}
	/** getting the float argument from the table
	 *
	 */
	@Override
	public float getFloat(int i) throws SQLException {
		if (mockStatment.exception!=null) throw exception=new SQLException("can't get data.");
		if (isClosed) throw  exception=new SQLException("mock result is closed.");
		try {
			table.get(index-1);
		}catch (IndexOutOfBoundsException e) {
			throw exception=new SQLException("Illegal operation on empty result set.");
		}
		String toReturn=table.get(index-1).getRow(i);
		if (toReturn==null) throw new SQLException ();
		else return Float.valueOf(toReturn).floatValue();
	}
	@Override
	public void close() throws SQLException{
		if (mockStatment.exception!=null) throw exception=new SQLException("can't get data.");
		if (isClosed) throw  exception=new SQLException("mock result is closed.");
		isClosed=true;
		
	}
	/**getting the float argument from the table
	 *
	 */
	@Override
	public int getInt(int i) throws SQLException {
		if (mockStatment.exception!=null) throw exception=new SQLException("can't get data.");
		if (isClosed) throw  exception=new SQLException("mock result is closed.");
		try {
			table.get(index-1);
		}catch (IndexOutOfBoundsException e) {
			throw exception=new SQLException("Illegal operation on empty result set.");
		}
		String toReturn=table.get(index-1).getRow(i);
		if (toReturn==null) throw new SQLException ();
		else return Integer.valueOf(toReturn).intValue();
	}
}
