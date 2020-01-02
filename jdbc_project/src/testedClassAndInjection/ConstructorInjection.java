package testedClassAndInjection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ConstructorInjection {
	
public class calculationsFromQuerys{
	
	private Querys_interface injection;

	
	public calculationsFromQuerys(Querys_interface injection) {
		this.injection=injection;
	}
	
	
	//@changed to non static so injection will work properly -TODO:  how do we keep it? - static injection that will have the  sql one until constracro comes and replaces it ?
	public  int surveyScore(String ID, String surveyType)
	{	float median=0;
		float interval=0;
		float factor=0;
		Statement stmt;
		ArrayList<Float> grades=new ArrayList();
		int count=0;
		float min=5; 
		float max=0,avg=0;
		float finalGrade=0;

		//------------- new vars to help with getting the paramters out of the db
		float[] floatArray = new float[3];
 		ArrayList<Object> foatArrays=new ArrayList<Object>();
 		//
 		
 		/*
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
		*/
 		
 		//injection
 		foatArrays=injection.v1v3v4Dealings(min, ID);
 		
		
		// calculations after getiing the vars;
		
 		for(Object e:foatArrays ) {
 			floatArray=(float[]) e;
 			for (int i=1;i<4;i++)
 			{
 			avg=avg+floatArray[i-1]/3;
 			};	 			 
			grades.add(avg);
			System.out.println("avg grade: "+avg);
			if (avg>max) max=avg;
 			if (avg<min) min=avg;
			count++;avg=0;
 			
 		}
		interval=max-min;
		if ((count % 2)>0)
			{System.out.println("count: "+count);
			median=grades.get((int)count/2);}
		else 
			{System.out.println("count: "+count);
			median=(grades.get(count/2-1)+grades.get(count/2))/2;}	
		 System.out.println("interval: "+interval);
		//------------- new vars to help with getting the paramters out of the db
		 int f1=0;
		 int f2=0;
		try 
		{	//@chaged using the static from conn universalScoring
			stmt = universalScoring.conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT settings.f1,settings.f2 FROM sakila.settings  WHERE settings.type = \""+ surveyType +"\";");
	 		rs.next();
	 		//refactord such that data will go somewhere before calculations
	 		 f1=rs.getInt(1);
	 		 f2 =rs.getInt(2);	
	 		//
	 		rs.close();			
		} catch (SQLException e) {e.printStackTrace();}
		//removed the calculation from the try catch to remove dependency
		finalGrade=(float)f1*median+interval/2+(float)f2;	
		return (int)finalGrade;}
		
	
	
	
}// END of SqlQureys

} // END of ConstructorInjection

