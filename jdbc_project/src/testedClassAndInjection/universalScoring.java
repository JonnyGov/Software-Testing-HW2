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

	public static void main(String[] args)
	{
		//@change-- added  the constructor injection
		ConstructorInjection ci= new ConstructorInjection(); 
		calculationsFromQuerys culFq= ci.new calculationsFromQuerys(new Querys_normal());
		//
		try 
		{
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
        } catch (Exception ex) {/* handle the error*/}
        
        try 
        {
            conn = DriverManager.getConnection("jdbc:mysql://localhost/sakila?serverTimezone=IST","root","Aa123456");
            System.out.println("SQL connection succeed"); 
            
         //@change - original method use replaced by injection
            int x =culFq.surveyScore("111111111","A");
          //
            
            System.out.println("score: "+x);
     	} catch (SQLException ex) 
     	    {/* handle any errors*/
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
            }
   	}//END of main
	
	/*
	 * @change -moved  surveyScore()to ConstructorInjection 
	 */
	
	}

	
	



