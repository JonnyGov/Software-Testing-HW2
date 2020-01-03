package MockInjecton;
import java.sql.SQLException;
import java.util.ArrayList;

import MockInjecton.MockConnection.rowInDatabase;

public class MockStatment implements StatmentService {
	/*public MockStatment(ArrayList <rowInDatabase> table,String where){
		ArrayList <rowInDatabase> newArrayList= new ArrayList <rowInDatabase>();
		for (rowInDatabase row : table)
			if (row.getWhere().contains(where))
				newArrayList.add(row);
		table=newArrayList;
	}*/
	private MockConnection mockConnection;
	public MockStatment(MockConnection mockConnection) {
		this.mockConnection=mockConnection;
	}
	public MockResult executeQuery(String str) {
		ArrayList <? extends rowInDatabase> table = null;
		String where=null;
		if(str.contains("sakila.survey")) {
			table=mockConnection.surveyTable;
			String newStr[]=str.split("ID=");
			newStr=newStr[1].split("\"");
			where=newStr[1];
		}else if (str.contains("sakila.settings")) {
			table=mockConnection.settingsTable;
			String newStr[]=str.split("=");
			newStr=newStr[1].split("\"");
			where=newStr[1];
		}
		
		return new MockResult(table,where);
	}
	
}
