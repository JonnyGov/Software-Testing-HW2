package testedClassAndInjection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Querys_stub implements Querys_interface {

	@Override
	public Object[] v1v3v4Dealings(String ID) {
	

		float median=0;
		float interval=0;
		float factor=0;
		ArrayList<Float> grades=new ArrayList();
		int count=0;
		float min=5; 
		float max=0,avg=0;
		float finalGrade=0;
		//@change - returning what was created useing the data 
		Object[] toReturn=new Object[4];
		
		
		try 
		{	//testing if id in db
			if(!ID.equals("111111111")) throw  new Exception("not in DB"); //testing if id in db
			// replaces result set 
			ArrayList<Float> rs=new ArrayList();
			//  replaces getting the results from the db
			rs.add((float) 4);// v1
			rs.add((float) 4);// v3
			rs.add((float) 5);// v4
	 		 
			// same calculation using foreach insted of the for 
	 			for (Float e:rs)
	 			{
	 			avg=avg+e/3;
	 			};	 			 
				grades.add(avg);
				System.out.println("avg grade: "+avg);
				if (avg>max) max=avg;
	 			if (avg<min) min=avg;
				count++;avg=0;
			
					
		} catch (Exception e) {e.printStackTrace();}
		toReturn[0]=count;
		toReturn[1]=max;
		toReturn[2]=min;
		toReturn[3]=grades;
		return toReturn;
	}

	@Override
	public float f1f2Dealings(float interval, float median, String surveyType) {
		float finalGrade=0;
		// replaces result set 
		int f1=0;
		int f2=0;
		//
		try 
		{	// testing if the surveyType is in DB
			if (!surveyType.equals("A") && !surveyType.equals("B") ) throw new Exception("no "+ surveyType +" surveyType");
			//adding to result set according to surveyType
			if(surveyType.equals("A")) {
				f1=2;
				f2=1;
			}
			else {
				f1=1;
				f2=0;
			}
			//replaced rs with its replacment
	 		finalGrade=(float)f1*median+interval/2+(float)f2;		
		} catch (Exception e) {e.printStackTrace();}
		
		return finalGrade;
	}
	

}
