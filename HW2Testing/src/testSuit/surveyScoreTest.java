package testSuit;


import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import MockInjecton.MockConnection;
import MockInjecton.MockDriverConnection;
import MockInjecton.MockSurveyScore;
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
	//private static universalScoring testClass;

	/**
	 * Before doing test: initialize and set unreal database and sql. <br>
	 * Initialize and set new unreal tables for settings and survey like in the real
	 * database.
	 */
	@BeforeAll
	public static void initialize() {
		surveyTable = new ArrayList<survey>();
		settingsTable = new ArrayList<settings>();

		// this is for use without mock survey score:
		/*
		 * try { testClass=new universalScoring(sql); } catch (SQLException e) { 
		 * Auto-generated catch block e.printStackTrace(); }
		 */

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
		dataBase = new MockConnection(surveyTable, settingsTable, sql);
		sql = new MockDriverConnection(dataBase, false);
		// this is for use with mock survey score:
		try {
			new MockSurveyScore(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Insert in database for table survey two different id. Calculate final grade
	 * for each.
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
	void testMultiSurveysSortAndOdd() {

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

	/**
	 * It is for test how the median is calculate (when the survey average sorted)
	 * and if it is good test the final grade.
	 */
	@Test
	void testMultiSurveysSortAndEven() {

		surveyTable.add(dataBase.new survey(1, 1, 1, 1, 1));
		surveyTable.add(dataBase.new survey(1, 2, 2, 2, 2));
		surveyTable.add(dataBase.new survey(1, 3, 3, 3, 3));
		surveyTable.add(dataBase.new survey(1, 4, 4, 4, 4));
		settingsTable.add(dataBase.new settings("A", 0, 1));
		MockSurveyScore.surveyScore("1", "A");
		assertTrue(comperFloats(MockSurveyScore.count, 4));
		float testAvg = MockSurveyScore.avgForEachRow.get(0);
		float excepted = (float) (1 + 1 + 1) / 3;
		assertTrue(comperFloats(testAvg, excepted));
		testAvg = MockSurveyScore.avgForEachRow.get(1);
		excepted = (float) (2 + 2 + 2) / 3;
		float exceptedMeidan = excepted;
		assertTrue(comperFloats(testAvg, excepted));
		testAvg = MockSurveyScore.avgForEachRow.get(2);
		excepted = (float) (3 + 3 + 3) / 3;
		exceptedMeidan = (float) (exceptedMeidan + excepted) / 2;
		assertTrue(comperFloats(testAvg, excepted));
		testAvg = MockSurveyScore.avgForEachRow.get(3);
		excepted = (float) (4 + 4 + 4) / 3;
		assertTrue(comperFloats(testAvg, excepted));
		assertTrue(comperFloats(MockSurveyScore.median, exceptedMeidan));
		assertTrue(comperFloats(MockSurveyScore.finalGrade, exceptedMeidan * 0 + 1));
	}

	@Test
	void testMultiSurveysUnortAndEven() {

		surveyTable.add(dataBase.new survey(1, 1, 1, 1, 1));
		surveyTable.add(dataBase.new survey(1, 4, 4, 4, 4));
		surveyTable.add(dataBase.new survey(1, 2, 2, 2, 2));
		surveyTable.add(dataBase.new survey(1, 3, 3, 3, 3));
		settingsTable.add(dataBase.new settings("A", 0, 1));
		MockSurveyScore.surveyScore("1", "A");
		assertTrue(comperFloats(MockSurveyScore.count, 4));
		float testAvg = MockSurveyScore.avgForEachRow.get(0);
		float excepted = (float) (1 + 1 + 1) / 3;
		assertTrue(comperFloats(testAvg, excepted));
		testAvg = MockSurveyScore.avgForEachRow.get(1);
		excepted = (float) (4 + 4 + 4) / 3;
		assertTrue(comperFloats(testAvg, excepted));
		testAvg = MockSurveyScore.avgForEachRow.get(2);
		excepted = (float) (2 + 2 + 2) / 3;
		float exceptedMeidan = excepted;
		assertTrue(comperFloats(testAvg, excepted));
		testAvg = MockSurveyScore.avgForEachRow.get(3);
		excepted = (float) (3 + 3 + 3) / 3;
		exceptedMeidan = (float) (exceptedMeidan + excepted) / 2;
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
	 * Test when get negative values in survey grades table. <br>
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
		surveyTable.set(0, dataBase.new survey(1, 1, 2, 3, -4));
		try {
			MockSurveyScore.surveyScore("1", "A");
			fail();
		} catch (IllegalArgumentException e) {
		}
	}

	/**
	 * Test when get zero values in survey grades table. <br>
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
		surveyTable.set(0, dataBase.new survey(1, 1, 2, 3, 0));
		try {
			MockSurveyScore.surveyScore("1", "A");
			fail();
		} catch (IllegalArgumentException e) {
		}
	}

	/**
	 * Test when get values greater then 5 in survey grades table. <br>
	 * Grades should be integer from 1 to 5
	 */
	@Test
	void testV1orV2orV3Greater5() {

		surveyTable.add(dataBase.new survey(1, 8, 2, 3, 4));
		settingsTable.add(dataBase.new settings("A", 1, 1));
		try {
			MockSurveyScore.surveyScore("1", "A");
			fail();
		} catch (IllegalArgumentException e) {
		}
		surveyTable.set(0, dataBase.new survey(1, 1, 2, 8, 4));
		try {
			MockSurveyScore.surveyScore("1", "A");
			fail();
		} catch (IllegalArgumentException e) {
		}
		surveyTable.set(0, dataBase.new survey(1, 1, 2, 3, 8));
		try {
			MockSurveyScore.surveyScore("1", "A");
			fail();
		} catch (IllegalArgumentException e) {
		}
	}

	@Test
	public void testInValidID() {

		surveyTable.add(dataBase.new survey(1, 8, 2, 3, 4));
		settingsTable.add(dataBase.new settings("A", 1, 1));
		try {
			MockSurveyScore.surveyScore("2", "A");
			fail();
		} catch (IllegalArgumentException e) {
		} catch (ArrayIndexOutOfBoundsException e) {
			fail(); // tried to get grade from empty array list.
		}
	}

	@Test
	public void testInValidType() {

		surveyTable.add(dataBase.new survey(1, 8, 2, 3, 4));
		settingsTable.add(dataBase.new settings("A", 1, 1));
		try {
			MockSurveyScore.surveyScore("1", "B");
			fail(); // Calculates final grade without settings.
		} catch (IllegalArgumentException e) {
		}
	}

	@Test
	public void testClosedConnection() {

		dataBase.close();
		try {
			MockSurveyScore.surveyScore("1", "1");
			fail();
		} catch (ArrayIndexOutOfBoundsException e) {
			fail(); // tried to get grade from empty array list.
		}catch (Exception e) {
			assertTrue(dataBase.exception != null);
			assertTrue(dataBase.mockStatment == null);
		}
	}

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

	/**
	 * <p>
	 * This method compare two floats number using epsilon (very small number
	 * 0.0001).<br>
	 * It is round the numbers by epsilon. <br>
	 * </p>
	 * sometimes the numbers are not equals but present the same amount, because of
	 * inaccuracy of java environment calculating.
	 * 
	 * @param f1 - first float variable
	 * @param f2 - second float variable
	 * @return true if they are equals <br>
	 *         false if they are not equals
	 */
	private boolean comperFloats(float f1, float f2) {
		float epsilon = (float) 0.0001;
		if (Math.abs(f1 - f2) < epsilon)
			return true;
		else
			return false;
	}

}// END of surveyScoreTest class
