package MockInjecton;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Injected class by ConnectionService and driverManagerService <br>
 * has Injected function surveyScore that us StatmentService and ResultService
 * 
 * @author Ofek and Yonathan
 * 
 *@see driverManagerService
 *@see ConnectionService
 *@see #surveyScore
 *@see MockSurveyScore
 */
public class universalScoring {
	// start added: constructor to injection and initialize services.
	private static DriverManagerService driverManagerService;

	// the constructor injection
	public universalScoring(DriverManagerService driverManagerService) throws SQLException {
		if (driverManagerService == null)
			this.driverManagerService = new RealDriverManger();
		else {
			this.driverManagerService = driverManagerService;
			conn = driverManagerService.getConnection(null, null, null); // when test the surveyScore function

		}

	}
	// finish added.

	// public static Connection conn; // @@deleted
	public static ConnectionService conn; // @@change from Connection to ConnectionService

	public static void main(String[] args) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
		} catch (Exception ex) {
			/* handle the error */}

		try {
			// @@change from driverManager to driverManagerService
			conn = driverManagerService.getConnection("jdbc:mysql://localhost/sakila?serverTimezone=IST", "root",
					"Aa123456"); // @change
			System.out.println("SQL connection succeed");
			int x = surveyScore("111111111", "A");
			System.out.println("score: " + x);
		} catch (SQLException ex) {/* handle any errors */
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		}
	}

	/**
	 * 
	 * Injected function by StatmentService and ResultService <br>
	 * and use of conn variable (ConnectionService)
	 * 
	 * @param ID
	 * @param surveyType
	 * @return finalGrade
	 * @see StatmentService
	 * @see ConnectionService
	 * @see ResultService
	 */
	public static int surveyScore(String ID, String surveyType) {
		float median = 0;
		float interval = 0;
		float factor = 0;
		// Statement stmt; // @@deleted
		StatmentService stmt; // @@change from Statement to StatmentService.
		ArrayList<Float> grades = new ArrayList();
		int count = 0;
		float min = 5;
		float max = 0, avg = 0;
		float finalGrade = 0;
		try {
			stmt = conn.createStatement();
			// @@deleted:
			// ResultSet rs = stmt.executeQuery("SELECT v1,v3,v4 FROM sakila.survey WHERE
			// ID= \""+ ID +"\";");
			// @@change from ResultSet to ResultService:
			ResultService rs = stmt.executeQuery("SELECT v1,v3,v4 FROM sakila.survey WHERE ID= \"" + ID + "\";");
			while (rs.next()) {
				for (int i = 1; i < 4; i++) {
					avg = avg + rs.getFloat(i) / 3;
				}
				;
				grades.add(avg);
				System.out.println("avg grade: " + avg);
				if (avg > max)
					max = avg;
				if (avg < min)
					min = avg;
				count++;
				avg = 0;
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		interval = max - min;
		if ((count % 2) > 0) {
			System.out.println("count: " + count);
			median = grades.get((int) count / 2);
		} else {
			System.out.println("count: " + count);
			median = (grades.get(count / 2 - 1) + grades.get(count / 2)) / 2;
		}
		System.out.println("interval: " + interval);
		try {
			stmt = conn.createStatement();
			// @@deleted:
			// ResultSet rs = stmt.executeQuery("SELECT settings.f1,settings.f2 FROM
			// sakila.settings WHERE settings.type = \""+ surveyType +"\";");
			// @@change from ResultSet to ResultSet:
			ResultService rs = stmt
					.executeQuery("SELECT settings.f1,settings.f2 FROM sakila.settings  WHERE settings.type = \""
							+ surveyType + "\";");
			rs.next();
			finalGrade = (float) rs.getInt(1) * median + interval / 2 + (float) rs.getInt(2);
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return (int) finalGrade;
	}

}
