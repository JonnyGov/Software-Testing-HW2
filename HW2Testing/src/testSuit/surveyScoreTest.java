package testSuit;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import MockInjecton.MockConnection;
import MockInjecton.MockDriverConnection;
import MockInjecton.MockSurveyScore;
import MockInjecton.universalScoring;
import MockInjecton.MockConnection.rowInDatabase;
import MockInjecton.MockConnection.settings;
import MockInjecton.MockConnection.survey;

class surveyScoreTest {
	private static ArrayList<survey> surveyTable;
	private static ArrayList<settings> settingsTable;
	private static MockConnection dataBase;
	private static MockDriverConnection sql;
	private static universalScoring testClass;
	private static MockSurveyScore mockSurveyScore;
	@BeforeAll
	public static void inisilize() {
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
		
		//big mock for the survey score function.
		try {
			MockSurveyScore mockSurveyScore=new MockSurveyScore(sql);
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
		MockSurveyScore.surveyScore("1", "A");
		assertEquals(MockSurveyScore.count,2);
		//int i=testClass.surveyScore("1", "A");
		//assertEquals(i,1);
	}

}
