package MockInjecton;

import java.sql.SQLException;
import java.util.ArrayList;


public class MockConnection implements ConnectionService{
	
	public interface rowInDatabase{
		public String getRow(int i);
		public String getWhere();
	}
	public class settings implements rowInDatabase{
		String type;
		int f1;
		int f2;
		public settings(String type, int f1, int f2) {
			this.type = type;
			this.f1 = f1;
			this.f2 = f2;
		}
		@Override
		public String getRow(int i) {
			switch (i) {
			case 1: return String.valueOf(f1);
			case 2: return String.valueOf(f2);
			default : return null;
			}
		}
		@Override
		public String getWhere() {
			return type;
		}
	}
	public class survey  implements rowInDatabase{
		int id;
		int v1;
		int v2;
		int v3;
		int v4;
		public survey(int id, int v1, int v2, int v3, int v4) {
			this.id = id;
			this.v1 = v1;
			this.v2 = v2;
			this.v3 = v3;
			this.v4 = v4;
		}
		@Override
		public String getRow(int i) { // get just v1 v3 v4 like the request.
			switch (i) {
			case 1: return  String.valueOf(v1);
			case 2: return String.valueOf(v3);
			case 3: return String.valueOf(v4);
			default : return null;
			}
		}
		@Override
		public String getWhere() {
			return String.valueOf(id);
		}
		
	}
	
	public  ArrayList <survey> surveyTable=null;
	public  ArrayList <settings> settingsTable=null;
	public MockConnection(ArrayList<survey> surveyTable, ArrayList<settings> settingsTable) {
		this.surveyTable = surveyTable;
		this.settingsTable = settingsTable;
	}
	@Override
	public StatmentService createStatement() throws SQLException {
		return new MockStatment(this);
	}

}
