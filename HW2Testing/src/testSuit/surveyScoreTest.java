package testSuit;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
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
	public static void initialize() {
		surveyTable = new ArrayList<survey>();
		settingsTable = new ArrayList<settings>();
		dataBase = new MockConnection(surveyTable, settingsTable);
		sql = new MockDriverConnection(dataBase);

		// this is for use without mock survey score:
		/*
		 * try { testClass=new universalScoring(sql); } catch (SQLException e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); }
		 */

		// this is for use with mock survey score:
		try {
			MockSurveyScore mockSurveyScore = new MockSurveyScore(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@BeforeEach
	public void cleanTables() {
		surveyTable.clear();
		settingsTable.clear();
	}
     
	
	//ofek
	
	
	@Test
	void testTwoDiffrentID() {
		surveyTable.add(dataBase.new survey(1, 1, 2, 3, 4));
		surveyTable.add(dataBase.new survey(2, 3, 1, 1, 2));
		settingsTable.add(dataBase.new settings("A", 0, 1));
		MockSurveyScore.surveyScore("1", "A");
		assertEquals(MockSurveyScore.count, 1);
		float testAvg = MockSurveyScore.avgForEachRow.get(0);
		float excepted = (float) (1 + 3 + 4) / 3;
		assertEquals(testAvg, excepted);
		assertEquals(MockSurveyScore.median, excepted);
		assertEquals(MockSurveyScore.finalGrade, excepted * 0 + 1);
		MockSurveyScore.surveyScore("2", "A");
		testAvg = MockSurveyScore.avgForEachRow.get(0);
		excepted = (float) (3 + 2 + 1) / 3;
		assertEquals(testAvg, excepted);
		assertEquals(MockSurveyScore.median, excepted);
		assertEquals(MockSurveyScore.finalGrade, excepted * 0 + 1);
	}

	@Test
	void testMultiSurveysUnSort() {
		surveyTable.add(dataBase.new survey(1, 2, 2, 2, 2));
		surveyTable.add(dataBase.new survey(1, 3, 3, 3, 3));
		surveyTable.add(dataBase.new survey(1, 1, 1, 1, 1));
		settingsTable.add(dataBase.new settings("A", 0, 1));
		MockSurveyScore.surveyScore("1", "A");
		assertEquals(MockSurveyScore.count, 3);
		float testAvg = MockSurveyScore.avgForEachRow.get(0);
		float excepted = (float) (2 + 2 + 2) / 3;
		float exceptedMeidan = excepted;
		assertEquals(testAvg, excepted);
		testAvg = MockSurveyScore.avgForEachRow.get(1);
		excepted = (float) (3 + 3 + 3) / 3;
		assertEquals(testAvg, excepted);
		testAvg = MockSurveyScore.avgForEachRow.get(2);
		excepted = (float) (1 + 1 + 1) / 3;
		assertEquals(testAvg, excepted);
		assertEquals(MockSurveyScore.median, exceptedMeidan);
		assertEquals(MockSurveyScore.finalGrade, exceptedMeidan * 0 + 1);
	}
	@Test
	void testMultiSurveysSort() {
		surveyTable.add(dataBase.new survey(1, 1, 1, 1, 1));
		surveyTable.add(dataBase.new survey(1, 2, 2, 2, 2));
		surveyTable.add(dataBase.new survey(1, 3, 3, 3, 3));
		settingsTable.add(dataBase.new settings("A", 0, 1));
		MockSurveyScore.surveyScore("1", "A");
		assertEquals(MockSurveyScore.count, 3);
		float testAvg = MockSurveyScore.avgForEachRow.get(0);
		float excepted = (float) (2 + 2 + 2) / 3;
		float exceptedMeidan = excepted;
		assertEquals(testAvg, excepted);
		testAvg = MockSurveyScore.avgForEachRow.get(1);
		excepted = (float) (3 + 3 + 3) / 3;
		assertEquals(testAvg, excepted);
		testAvg = MockSurveyScore.avgForEachRow.get(2);
		excepted = (float) (1 + 1 + 1) / 3;
		assertEquals(testAvg, excepted);
		assertEquals(MockSurveyScore.median, exceptedMeidan);
		assertEquals(MockSurveyScore.finalGrade, exceptedMeidan * 0 + 1);
	}
	@Test
	void testF1is0F2is1() {
		surveyTable.add(dataBase.new survey(1, 1, 2, 3, 4));
		settingsTable.add(dataBase.new settings("A", 1, 1));
		MockSurveyScore.surveyScore("1", "A");
		assertEquals(MockSurveyScore.count, 1);
		float testAvg = MockSurveyScore.avgForEachRow.get(0);
		float excepted = (float) (1 + 3 + 4) / 3;
		assertEquals(testAvg, excepted);
		assertEquals(MockSurveyScore.median, excepted);
		assertEquals(MockSurveyScore.finalGrade, excepted * 0 + 1);
	}
	

	
	
	
	
	
	// yonathan
	
	
	@Test
	void testMinosV1() {
		surveyTable.add(dataBase.new survey(1, -5, 2, 3, 4));
		settingsTable.add(dataBase.new settings("A", 1, 1));
		MockSurveyScore.surveyScore("1", "A");
		assertEquals(MockSurveyScore.count, 1);
		float testAvg = MockSurveyScore.avgForEachRow.get(0);
		float excepted = (float) (-5 + 3 + 4) / 3;
		assertTrue(comperFloats(testAvg,excepted));
		assertTrue(comperFloats(MockSurveyScore.median, excepted));
		assertTrue(comperFloats(MockSurveyScore.finalGrade, excepted * 1 + 1));
	}
	@Test
	void testF1is1F2is0() {
		surveyTable.add(dataBase.new survey(1, 1, 2, 3, 4));
		settingsTable.add(dataBase.new settings("A", 1, 0));
		MockSurveyScore.surveyScore("1", "A");
		assertEquals(MockSurveyScore.count, 1);
		float testAvg = MockSurveyScore.avgForEachRow.get(0);
		float excepted = (float) (1 + 3 + 4) / 3;	
		assertTrue(comperFloats(testAvg,excepted));
		assertTrue(comperFloats(MockSurveyScore.median, excepted));
		assertTrue(comperFloats(MockSurveyScore.finalGrade, excepted * 1 + 0));
	}
	
	@Test
	void testV1V2V3arezero() {
		surveyTable.add(dataBase.new survey(1, 0, 2, 0, 0));
		settingsTable.add(dataBase.new settings("A", 1, 0));
		MockSurveyScore.surveyScore("1", "A");
		assertEquals(MockSurveyScore.count, 1);
		float testAvg = MockSurveyScore.avgForEachRow.get(0);
		float excepted = (float) 0;	
		assertTrue(comperFloats(testAvg,excepted));
		assertTrue(comperFloats(MockSurveyScore.median, excepted));
		assertTrue(comperFloats(MockSurveyScore.finalGrade, excepted * 1 + 1));
	}
	
	@Test
	void testV1V2V3areNegative() {
		surveyTable.add(dataBase.new survey(1, -3, 2, -4, -9));
		settingsTable.add(dataBase.new settings("A", 1, 1));
		MockSurveyScore.surveyScore("1", "A");
		assertEquals(MockSurveyScore.count, 1);
		float testAvg = MockSurveyScore.avgForEachRow.get(0);
		float excepted = (float) (-3 + -4 + -9) / 3;	
		assertTrue(comperFloats(testAvg,excepted));
		assertTrue(comperFloats(MockSurveyScore.median, excepted));
		assertTrue(comperFloats(MockSurveyScore.finalGrade, excepted * 1 + 1));
	}
	

private boolean comperFloats(float f1,float f2) {
	float epsilon =(float) 0.0001;
	if(Math.abs(f1-f2)<epsilon) return true;
	else return false;
}

}//END of surveyScoreTest class
