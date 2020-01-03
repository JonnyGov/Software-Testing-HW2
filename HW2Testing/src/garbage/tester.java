package garbage;

import java.sql.SQLException;
import java.util.ArrayList;

import MockInjecton.MockConnection;
import MockInjecton.MockDriverConnection;
import MockInjecton.universalScoring;
import MockInjecton.MockConnection.rowInDatabase;
import MockInjecton.MockConnection.settings;
import MockInjecton.MockConnection.survey;

public class tester {
	private ArrayList<survey> surveyTable;
	private ArrayList<settings> settingsTable;
	private MockConnection dataBase;
	private MockDriverConnection sql;
	private universalScoring testClass;
	public void inisilize() throws SQLException {
		surveyTable = new ArrayList<survey>();
		settingsTable = new ArrayList<settings>();
		dataBase = new MockConnection(surveyTable, settingsTable);
		sql = new MockDriverConnection(dataBase);
		testClass=new universalScoring(sql);
		
	}

	public void test() throws SQLException {
		inisilize();
		surveyTable.add(dataBase.new survey(1, 1, 2, 3, 4));
		surveyTable.add(dataBase.new survey(2, 10, 20, 30, 40));
		settingsTable.add(dataBase.new settings("A", 0, 0));
		int i=testClass.surveyScore("1", "A");
		System.out.println("the final grdade: "+i);
}
	public static void main(String[] args) throws SQLException{
		tester t=new tester();
		t.test();
	}
}
