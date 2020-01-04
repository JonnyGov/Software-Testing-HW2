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

/**
 * 
 * Test for calculate final grade for surveys using student's list of grades
 * that each have 3 grades (v1,v3,v4), and settings (f1 , f2)
 * <p>
 * <b>Formula:</b> <br>
 * (<b>Median</b>{<b>List</b>[<b>Average</b>(v1,v3,v4)]} ) *f1 +f2
 *
 * @author Ofek and Yonathan
 *
 */
class surveyScoreTest {
	private static ArrayList<survey> surveyTable;
	private static ArrayList<settings> settingsTable;
	private static MockConnection dataBase;
	private static MockDriverConnection sql;
	private static universalScoring testClass;
	private static MockSurveyScore mockSurveyScore;

	/**
	 * Before doing test: initialize and set unreal database and sql. <br>
	 * Initialize and set new unreal tables for settings and survey like in the real
	 * database.
	 */
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

	/**
	 * 
	 * Before each tests: clean the table to test new values.
	 * 
	 */
	@BeforeEach
	public void cleanTables() {
		surveyTable.clear();
		settingsTable.clear();
	}

	// ofek
	// assertTrue(comperFloats));

	/**
	 * Insert in database for table survey two different id.
	 */
	@Test
	void testTwoDiffrentID() {
		surveyTable.add(dataBase.new survey(1, 1, 2, 3, 4));
		surveyTable.add(dataBase.new survey(2, 3, 1, 1, 2));
		settingsTable.add(dataBase.new settings("A", 0, 1));
		MockSurveyScore.surveyScore("1", "A");
		assertTrue(comperFloats(MockSurveyScore.count, 1));
		float testAvg = MockSurveyScore.avgForEachRow.get(0);
		float excepted = (float) (1 + 3 + 4) / 3;
		assertTrue(comperFloats(testAvg, excepted));
		assertTrue(comperFloats(MockSurveyScore.median, excepted));
		assertTrue(comperFloats(MockSurveyScore.finalGrade, excepted * 0 + 1));
		MockSurveyScore.surveyScore("2", "A");
		testAvg = MockSurveyScore.avgForEachRow.get(0);
		excepted = (float) (3 + 2 + 1) / 3;
		assertTrue(comperFloats(testAvg, excepted));
		assertTrue(comperFloats(MockSurveyScore.median, excepted));
		assertTrue(comperFloats(MockSurveyScore.finalGrade, excepted * 0 + 1));
	}

	/**
	 * It is for test how the median is calculate (when the survey average unsorted)
	 * and if it is good test the final grade.
	 */
	@Test
	void testMultiSurveysUnSort() {
		surveyTable.add(dataBase.new survey(1, 2, 2, 2, 2));
		surveyTable.add(dataBase.new survey(1, 3, 3, 3, 3));
		surveyTable.add(dataBase.new survey(1, 1, 1, 1, 1));
		settingsTable.add(dataBase.new settings("A", 0, 1));
		MockSurveyScore.surveyScore("1", "A");
		assertTrue(comperFloats(MockSurveyScore.count, 3));
		float testAvg = MockSurveyScore.avgForEachRow.get(0);
		float excepted = (float) (2 + 2 + 2) / 3;
		float exceptedMeidan = excepted;
		assertTrue(comperFloats(testAvg, excepted));
		testAvg = MockSurveyScore.avgForEachRow.get(1);
		excepted = (float) (3 + 3 + 3) / 3;
		assertTrue(comperFloats(testAvg, excepted));
		testAvg = MockSurveyScore.avgForEachRow.get(2);
		excepted = (float) (1 + 1 + 1) / 3;
		assertTrue(comperFloats(testAvg, excepted));
		assertTrue(comperFloats(MockSurveyScore.median, exceptedMeidan)); // test median.
		assertTrue(comperFloats(MockSurveyScore.finalGrade, exceptedMeidan * 0 + 1)); // test final grade.
	}

	/**
	 * It is for test how the median is calculate (when the survey average sorted)
	 * and if it is good test the final grade.
	 */
	@Test
	void testMultiSurveysSort() {
		surveyTable.add(dataBase.new survey(1, 1, 1, 1, 1));
		surveyTable.add(dataBase.new survey(1, 2, 2, 2, 2));
		surveyTable.add(dataBase.new survey(1, 3, 3, 3, 3));
		settingsTable.add(dataBase.new settings("A", 0, 1));
		MockSurveyScore.surveyScore("1", "A");
		assertTrue(comperFloats(MockSurveyScore.count, 3));
		float testAvg = MockSurveyScore.avgForEachRow.get(0);
		float excepted = (float) (1 + 1 + 1) / 3;
		assertTrue(comperFloats(testAvg, excepted));
		testAvg = MockSurveyScore.avgForEachRow.get(1);
		excepted = (float) (2 + 2 + 2) / 3;
		float exceptedMeidan = excepted;
		assertTrue(comperFloats(testAvg, excepted));
		testAvg = MockSurveyScore.avgForEachRow.get(2);
		excepted = (float) (3 + 3 + 3) / 3;
		assertTrue(comperFloats(testAvg, excepted));
		assertTrue(comperFloats(MockSurveyScore.median, exceptedMeidan));
		assertTrue(comperFloats(MockSurveyScore.finalGrade, exceptedMeidan * 0 + 1));
	}

	@Test
	void testF1is0F2is1() {
		surveyTable.add(dataBase.new survey(1, 1, 2, 3, 4));
		settingsTable.add(dataBase.new settings("A", 0, 1));
		MockSurveyScore.surveyScore("1", "A");
		assertTrue(comperFloats(MockSurveyScore.count, 1));
		float testAvg = MockSurveyScore.avgForEachRow.get(0);
		float excepted = (float) (1 + 3 + 4) / 3;
		assertTrue(comperFloats(testAvg, excepted));
		assertTrue(comperFloats(MockSurveyScore.median, excepted));
		assertTrue(comperFloats(MockSurveyScore.finalGrade, excepted * 0 + 1));
	}

	/**
	 * Test when get negative values in survey grades. <br>
	 * Grades should be integer from 1 to 5
	 */
	@Test
	void testV1orV2orV3Negative() {
		surveyTable.add(dataBase.new survey(1, -1, 2, 3, 4));
		settingsTable.add(dataBase.new settings("A", 1, 1));
		try {
			MockSurveyScore.surveyScore("1", "A");
			fail();
		} catch (IllegalArgumentException e) {
		}
		surveyTable.set(0, dataBase.new survey(1, 1, 2, -3, 4));
		try {
			MockSurveyScore.surveyScore("1", "A");
			fail();
		} catch (IllegalArgumentException e) {
		}
		surveyTable.set(0,dataBase.new survey(1, 1, 2, 3, -4));
		try {
			MockSurveyScore.surveyScore("1", "A");
			fail();
		} catch (IllegalArgumentException e) {
		}
	}
	/**
	 * Test when get zero values in survey grades. <br>
	 * Grades should be integer from 1 to 5
	 */
	@Test
	void testV1orV2orV3zero() {
		surveyTable.add(dataBase.new survey(1, 0, 2, 3, 4));
		settingsTable.add(dataBase.new settings("A", 1, 1));
		try {
			MockSurveyScore.surveyScore("1", "A");
			fail();
		} catch (IllegalArgumentException e) {
		}
		surveyTable.set(0, dataBase.new survey(1, 1, 2, 0, 4));
		try {
			MockSurveyScore.surveyScore("1", "A");
			fail();
		} catch (IllegalArgumentException e) {
		}
		surveyTable.set(0,dataBase.new survey(1, 1, 2, 3, 0));
		try {
			MockSurveyScore.surveyScore("1", "A");
			fail();
		} catch (IllegalArgumentException e) {
		}
	}
	

	// yonathan

	@Test
	void testF1is1F2is0() {
		surveyTable.add(dataBase.new survey(1, 1, 2, 3, 4));
		settingsTable.add(dataBase.new settings("A", 1, 0));
		MockSurveyScore.surveyScore("1", "A");
		assertEquals(MockSurveyScore.count, 1);
		float testAvg = MockSurveyScore.avgForEachRow.get(0);
		float excepted = (float) (1 + 3 + 4) / 3;
		assertTrue(comperFloats(testAvg, excepted));
		assertTrue(comperFloats(MockSurveyScore.median, excepted));
		assertTrue(comperFloats(MockSurveyScore.finalGrade, excepted * 1 + 0));
	}

	@Test
	void testF1isNegativeF2isNot() {
		surveyTable.add(dataBase.new survey(1, 1, 2, 3, 4));
		settingsTable.add(dataBase.new settings("A", -5, 1));
		MockSurveyScore.surveyScore("1", "A");
		assertEquals(MockSurveyScore.count, 1);
		float testAvg = MockSurveyScore.avgForEachRow.get(0);
		float excepted = (float) (1 + 3 + 4) / 3;
		assertTrue(comperFloats(testAvg, excepted));
		assertTrue(comperFloats(MockSurveyScore.median, excepted));
		assertTrue(comperFloats(MockSurveyScore.finalGrade, excepted * -5 + 1));
	}

	@Test
	void testF1isNotNegativeF2is() {
		surveyTable.add(dataBase.new survey(1, 1, 2, 3, 4));
		settingsTable.add(dataBase.new settings("A", 1, -5));
		MockSurveyScore.surveyScore("1", "A");
		assertEquals(MockSurveyScore.count, 1);
		float testAvg = MockSurveyScore.avgForEachRow.get(0);
		float excepted = (float) (1 + 3 + 4) / 3;
		assertTrue(comperFloats(testAvg, excepted));
		assertTrue(comperFloats(MockSurveyScore.median, excepted));
		assertTrue(comperFloats(MockSurveyScore.finalGrade, excepted * 1 + -5));
	}

	@Test
	void testF1F2AreNegative() {
		surveyTable.add(dataBase.new survey(1, 1, 2, 3, 4));
		settingsTable.add(dataBase.new settings("A", -1, -5));
		MockSurveyScore.surveyScore("1", "A");
		assertEquals(MockSurveyScore.count, 1);
		float testAvg = MockSurveyScore.avgForEachRow.get(0);
		float excepted = (float) (1 + 3 + 4) / 3;
		assertTrue(comperFloats(testAvg, excepted));
		assertTrue(comperFloats(MockSurveyScore.median, excepted));
		assertTrue(comperFloats(MockSurveyScore.finalGrade, excepted * -1 + -5));
	}

	private boolean comperFloats(float f1, float f2) {
		float epsilon = (float) 0.0001;
		if (Math.abs(f1 - f2) < epsilon)
			return true;
		else
			return false;
	}

}// END of surveyScoreTest class
