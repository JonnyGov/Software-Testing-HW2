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
		//@change - getting what the injection did
		Object[] toReturn=new Object[4];
		
		// first injection
		toReturn=injection.v1v3v4Dealings(ID);	
		count=(int) toReturn[0];
		max=(float) toReturn[1];
		min=(float) toReturn[2];
		grades=(ArrayList<Float>) toReturn[3];
		// note - trying to live as match of the original as possible
		
		interval=max-min;
		if ((count % 2)>0)
			{System.out.println("count: "+count);
			median=grades.get((int)count/2);}
		else 
			{System.out.println("count: "+count);
			median=(grades.get(count/2-1)+grades.get(count/2))/2;}	
		 System.out.println("interval: "+interval);

		 //Second injection
		 finalGrade=injection.f1f2Dealings(interval , median,  surveyType);
		 //
		return (int)finalGrade;}
		
	
	
	
}// END of SqlQureys

} // END of ConstructorInjection

