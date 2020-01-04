package MockInjecton;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import MockInjecton.MockConnection.rowInDatabase;
import MockInjecton.MockConnection.settings;
import MockInjecton.MockConnection.survey;

public class MockStatment implements StatmentService {
	private MockConnection mockConnection;
	public MockStatment(MockConnection mockConnection) {
		this.mockConnection=mockConnection;
	}
	public MockResult executeQuery(String str) {
		ArrayList <? extends rowInDatabase> table = null;
		String where=null;
		if(str.contains("sakila.survey")) {
			ArrayList <survey> surveyTable=new ArrayList <survey>();
			String newStr[]=str.split("ID=");
			newStr=newStr[1].split("\"");
			where=newStr[1];
			for ( survey row:mockConnection.surveyTable) {
				if (row.getWhere().contentEquals(where))
					surveyTable.add(row);
			}
			table=surveyTable;
		}else if (str.contains("sakila.settings")) {
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
		
		return new MockResult(table,where);
	}
	
}
