package MockInjecton;
import java.sql.SQLException;
import java.util.ArrayList;


import MockInjecton.MockConnection.rowInDatabase;
import MockInjecton.MockConnection.settings;
import MockInjecton.MockConnection.survey;

public class MockStatment implements StatmentService {

	public MockConnection mockConnection=null;
	public ArrayList<MockResult> mockResults=new ArrayList<MockResult>();
	public SQLException exception=null;
	
	/** Constructor 
	 * @param mockConnection - the fake DB 
	 */
	public MockStatment(MockConnection mockConnection) {
		this.mockConnection=mockConnection;
	}
	/** executes real querys on the fake table
	 * @param str - the query to be executed
	 */
	public MockResult executeQuery(String str)throws SQLException {
		if(mockConnection.exception!=null)throw exception=new SQLException("can't get data.");
		ArrayList <? extends rowInDatabase> table = null;
		String where=null;
		// query for the survey table
		if(str.contains("sakila.survey")) {
			if(mockConnection.surveyTable==null)throw exception=new  SQLException("survey not exists.");
			ArrayList <survey> surveyTable=new ArrayList <survey>();
			String newStr[]=str.split("ID=");
			newStr=newStr[1].split("\"");
			where=newStr[1];
			for ( survey row:mockConnection.surveyTable) {
				if (row.getWhere().contentEquals(where))
					surveyTable.add(row);
			}
			table=surveyTable;
		// query for the settings table
		}else if (str.contains("sakila.settings")) {
			if(mockConnection.settingsTable==null)throw exception=new  SQLException("settings not exists.");
			ArrayList <settings> surveyTable=new ArrayList <settings>();
			String newStr[]=str.split("=");
			newStr=newStr[1].split("\"");
			where=newStr[1];
			for ( settings row:mockConnection.settingsTable) {
				if (row.getWhere().contentEquals(where))
					surveyTable.add(row);
			}
			table=surveyTable;
		}
		mockResults.add(new MockResult(table,where,this));
		return mockResults.get(mockResults.size()-1);
	}
	
}
