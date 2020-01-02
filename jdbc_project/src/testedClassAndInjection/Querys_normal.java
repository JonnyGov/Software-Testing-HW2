package testedClassAndInjection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Querys_normal implements Querys_interface {

	@Override
	public ArrayList<Object> v1v3v4Dealings(float min, String ID) {
		Statement stmt;
		//------------- new vars to help with getting the paramters out of the db
		float[] floatArray = new float[3];
 		ArrayList<Object> foatArrays=new ArrayList<Object>();
 		//
		try 
		{
			//@chaged using the static from conn universalScoring
			stmt = universalScoring.conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT v1,v3,v4 FROM sakila.survey WHERE ID= \""+ ID +"\";");

	 		
	 		//refactord such that data will go somewhere before calculations
	 		
	 		while (rs.next()) {
	 			for (int i=1;i<4;i++)
	 			{
	 				floatArray[i-1]=rs.getFloat(i);
	 			};	
	 			foatArrays.add(floatArray);
	 		}	
			rs.close();			
		} catch (SQLException e) {e.printStackTrace();}
		return foatArrays;
		
	}// END OF v1v3v4Dealings();

	
	
	
	@Override
	public float f1f2Dealings(float interval, float median) {
		// TODO Auto-generated method stub
		return 0;
	}

}
