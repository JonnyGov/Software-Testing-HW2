package testSuit;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import MockInjecton.MockConnection;
import MockInjecton.MockDriverConnection;
import MockInjecton.universalScoring;
import MockInjecton.MockConnection.rowInDatabase;
import MockInjecton.MockConnection.settings;
import MockInjecton.MockConnection.survey;

class surveyScoreTest {
	private ArrayList<survey> surveyTable;
	private ArrayList<settings> settingsTable;
	private MockConnection dataBase;
	private MockDriverConnection sql;
	private universalScoring testClass;
	@BeforeAll
	public void inisilize() {
		surveyTable = new ArrayList<survey>();
		settingsTable = new ArrayList<settings>();
		dataBase = new MockConnection(surveyTable, settingsTable);
		sql = new MockDriverConnection(dataBase);
		try {
			testClass=new universalScoring(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Test
	void test() {
		surveyTable.add(dataBase.new survey(1, 1, 2, 3, 4));
		surveyTable.add(dataBase.new survey(2, 10, 20, 30, 40));
		settingsTable.add(dataBase.new settings("A", 0, 1));
		int i=testClass.surveyScore("1", "A");
		assertEquals(i,1);
	}

}
