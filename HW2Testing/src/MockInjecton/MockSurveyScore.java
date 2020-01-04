package MockInjecton;

import java.sql.SQLException;
import java.util.ArrayList;

public class MockSurveyScore {
	private static DriverManagerService driverManagerService;

	public MockSurveyScore(DriverManagerService driverManagerService) throws SQLException {
		if (driverManagerService == null)
			this.driverManagerService = new RealDriverManger();
		else {
			this.driverManagerService = driverManagerService;
			conn = driverManagerService.getConnection(null, null, null);

		}

	}

	// public static Connection conn;
	public static ConnectionService conn; // @@change

	public static void main(String[] args) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
		} catch (Exception ex) {
			/* handle the error */}

		try {
			conn = driverManagerService.getConnection("jdbc:mysql://localhost/sakila?serverTimezone=IST", "root",
					"Aa123456");
			System.out.println("SQL connection succeed");
			int x = surveyScore("111111111", "A");
			System.out.println("score: " + x);
		} catch (SQLException ex) {/* handle any errors */
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		}
	}

	public static float median = 0;
	public static float interval = 0;
	public static float factor = 0;
	public static int count = 0;
	public static float min = 5;
	public static float max = 0, avg = 0;
	public static ArrayList<Float> avgForEachRow=new ArrayList<Float>();
	public static float finalGrade = 0;

	public static int surveyScore(String ID, String surveyType) {
		avgForEachRow.clear(); //@@added: clear for the next test.
		 median = 0;
		 interval = 0;
		 factor = 0;
		// Statement stmt; // @@removed
		StatmentService stmt; // @@change
		ArrayList<Float> grades = new ArrayList();
		 count = 0;
		 min = 5;
		 max = 0;
		avg = 0;
		 finalGrade = 0;
		try {
			stmt = conn.createStatement();
			// ResultSet rs = stmt.executeQuery("SELECT v1,v3,v4 FROM sakila.survey WHERE
			// ID= \""+ ID +"\";"); // @@removed
			// @@change:
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
				avgForEachRow.add(avg);//@@ added for testing.
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
			// ResultSet rs = stmt.executeQuery("SELECT settings.f1,settings.f2 FROM
			// sakila.settings WHERE settings.type = \""+ surveyType +"\";");
			// @@change
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
