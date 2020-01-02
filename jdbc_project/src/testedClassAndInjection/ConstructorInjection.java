package testedClassAndInjection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ConstructorInjection {

	public class calculationsFromQuerys {

		private Querys_interface injection;

		public calculationsFromQuerys(Querys_interface injection) {
			this.injection = injection;
		}

		// @changed to non static so injection will work properly -TODO: how do we keep
		// it? - static injection that will have the sql one until constracro comes and
		// replaces it ?
		public int surveyScore(String ID, String surveyType) {
			float median = 0;
			float interval = 0;
			float factor = 0;
			//Statement stmt; - not needed 
			ArrayList<Float> grades = new ArrayList();
			int count = 0;
			float min = 5;
			float max = 0, avg = 0;
			float finalGrade = 0;
			// ------------- new vars to help with getting the paramters out of the db
			float[] floatArray = new float[3];
			ArrayList<Object> foatArrays = new ArrayList<Object>();
			// injection
			foatArrays = injection.v1v3v4Dealings(min, ID);
			// calculations after getiing the vars;

			for (Object e : foatArrays) {
				floatArray = (float[]) e;
				for (int i = 1; i < 4; i++) {
					avg = avg + floatArray[i - 1] / 3;
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
			interval = max - min;
			if ((count % 2) > 0) {
				System.out.println("count: " + count);
				median = grades.get((int) count / 2);
			} else {
				System.out.println("count: " + count);
				median = (grades.get(count / 2 - 1) + grades.get(count / 2)) / 2;
			}
			System.out.println("interval: " + interval);
			// ------------- new var to help with getting the paramters out of the db
			int[] f1f2 = new int[2];
			f1f2 = injection.f1f2Dealings(surveyType);

			// removed the calculation from the try catch to remove dependency
			finalGrade = (float) f1f2[0] * median + interval / 2 + (float) f1f2[1];
			return (int) finalGrade;
		}// END of surveyScore

	}// END of SqlQureys

} // END of ConstructorInjection
