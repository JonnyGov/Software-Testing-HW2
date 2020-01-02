package testedClassAndInjection;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Querys_normal implements Querys_interface {

	public Querys_normal() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
		} catch (Exception ex) {
			/* handle the error */}

		try {
			// @changed using the static from conn universalScoring
			universalScoring.conn = DriverManager.getConnection("jdbc:mysql://localhost/sakila?serverTimezone=IST",
					"root", "Aa123456");
			System.out.println("SQL connection succeed");

		} catch (SQLException ex) {/* handle any errors */
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		}
	}

	@Override
	public ArrayList<Object> v1v3v4Dealings(float min, String ID) {
		Statement stmt;
		// ------------- new vars to help with getting the paramters out of the db
		float[] floatArray = new float[3];
		ArrayList<Object> foatArrays = new ArrayList<Object>();
		//
		try {
			// @chaged using the static from conn universalScoring
			stmt = universalScoring.conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT v1,v3,v4 FROM sakila.survey WHERE ID= \"" + ID + "\";");

			// refactord such that data will go somewhere before calculations

			while (rs.next()) {
				for (int i = 1; i < 4; i++) {
					floatArray[i - 1] = rs.getFloat(i);
				}
				;
				foatArrays.add(floatArray);
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return foatArrays;

	}// END OF v1v3v4Dealings();

	@Override
	public int[] f1f2Dealings(String surveyType) {
		Statement stmt;
		int[] f1f2 = new int[2];
		try { // @chaged using the static from conn universalScoring
			stmt = universalScoring.conn.createStatement();
			ResultSet rs = stmt
					.executeQuery("SELECT settings.f1,settings.f2 FROM sakila.settings  WHERE settings.type = \""
							+ surveyType + "\";");
			rs.next();
			// refactord such that data will go somewhere before calculations
			f1f2[0] = rs.getInt(1);
			f1f2[1] = rs.getInt(2);
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return f1f2;
	}

}
