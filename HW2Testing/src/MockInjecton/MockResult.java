package MockInjecton;
import java.sql.SQLException;
import java.util.ArrayList;

import MockInjecton.MockConnection.rowInDatabase;

public class MockResult implements ResultService{
	private String where;
	private int index=0;
	private ArrayList <? extends rowInDatabase> table;
	
	public MockResult(ArrayList<? extends rowInDatabase> table, String where) {
		this.table=table;
		this.where=where;
		
	}
	@Override
	public boolean next() {
		try {
		table.get(index++);
		return true;
		}catch (IndexOutOfBoundsException e) {
			return false;
		}
	}
	@Override
	public float getFloat(int i) throws SQLException {
		String toReturn=table.get(index-1).getRow(i);
		if (toReturn==null) throw new SQLException ();
		else return Float.valueOf(toReturn).floatValue();
	}
	@Override
	public void close() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public int getInt(int i) throws SQLException {
		String toReturn=table.get(index-1).getRow(i);
		if (toReturn==null) throw new SQLException ();
		else return Integer.valueOf(toReturn).intValue();
	}
}
