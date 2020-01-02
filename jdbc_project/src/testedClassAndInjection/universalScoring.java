package testedClassAndInjection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import testedClassAndInjection.ConstructorInjection.calculationsFromQuerys;


public class universalScoring {
	public static Connection conn;
	public static calculationsFromQuerys culFq; // Instead of the mehode being static the injection  is

	public static void main(String[] args)
	{
		//@change - moved the connection to DB in to the constructor of the Injection
		//@change-- added  the constructor injection
		ConstructorInjection ci= new ConstructorInjection(); 
		calculationsFromQuerys culFq= ci.new calculationsFromQuerys(new Querys_normal()); // null until we have implementing classes
		//      
      //@change - original method use replaced by injection
        int x =culFq.surveyScore("111111111","B");
      //     
        System.out.println("score: "+x);
   	}//END of main
	
	/*
	 * @change -moved  surveyScore()to ConstructorInjection 
	 */
	
	}

	
	



