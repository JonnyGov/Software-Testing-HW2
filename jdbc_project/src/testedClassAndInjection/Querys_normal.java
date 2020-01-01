package testedClassAndInjection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Querys_normal implements Querys_interface {

	@Override
	public  Object[] v1v3v4Dealings(String ID) {
		
		
		float median=0;
		float interval=0;
		float factor=0;
		Statement stmt;
		ArrayList<Float> grades=new ArrayList();
		int count=0;
		float min=5; 
		float max=0,avg=0;
		float finalGrade=0;
		//@change - returning what was created useing the data 
		Object[] toReturn=new Object[4];
		try 
		{
			//@chaged using the static from conn universalScoring
			stmt = universalScoring.conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT v1,v3,v4 FROM sakila.survey WHERE ID= \""+ ID +"\";");
	 		while(rs.next())
	 		{   
	 			for (int i=1;i<4;i++)
	 			{
	 			avg=avg+rs.getFloat(i)/3;
	 			};	 			 
				grades.add(avg);
				System.out.println("avg grade: "+avg);
				if (avg>max) max=avg;
	 			if (avg<min) min=avg;
				count++;avg=0;
			} 
			rs.close();			
		} catch (SQLException e) {e.printStackTrace();}
		toReturn[0]=count;
		toReturn[1]=max;
		toReturn[2]=min;
		toReturn[3]=grades;
		return toReturn;
	}

	
	
	
	
	@Override
	public float f1f2Dealings(float interval ,float median, String surveyType) {
		Statement stmt;
		float finalGrade=0;

		try 
		{	//@chaged using the static from conn universalScoring
			stmt = universalScoring.conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT settings.f1,settings.f2 FROM sakila.settings  WHERE settings.type = \""+ surveyType +"\";");
	 		rs.next();
	 		finalGrade=(float)rs.getInt(1)*median+interval/2+(float)rs.getInt(2);	
	 		rs.close();			
		} catch (SQLException e) {e.printStackTrace();}
		
		return finalGrade;
	}

}
