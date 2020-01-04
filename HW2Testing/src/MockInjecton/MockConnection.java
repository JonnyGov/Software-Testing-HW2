package MockInjecton;

import java.sql.SQLException;
import java.util.ArrayList;


public class MockConnection implements ConnectionService{
	
	
	/**<b>each entity of class implementing:</b>
	 * <p> 
	 * <b>rowInDatabase</b>- row in a table of DB , 
	 * meaning holding more then one of those is a table.
	 * @see
	 * {@link settings} ,
	 * {@link survey}
	 */
	public interface rowInDatabase{
		
		/** returns the value in  column i of this row
		 * @param i - the column number(index)
		 * @return value from specific cell. 
		 */
		public String getRow(int i);
		/** returning row key
		 * @return
		 */
		public String getWhere();
	}
	
	/**a row in settings table.
	 * <br>
	 * <b>Implements:</b> rowInDatabase Interface
	 * @author Ofek and Yonathan
	 *  @see  {@link rowInDatabase} 
	 */
	public class settings implements rowInDatabase{
		String type;
		int f1;
		int f2;
		
		/**  Constructor =adding row values - f1,f2
		 * @param type - usually A/B (in DB)
		 * @param f1
		 * @param f2
		 */
		public settings(String type, int f1, int f2) {
			this.type = type;
			this.f1 = f1;
			this.f2 = f2;
		}
		@Override
		public String getRow(int i) {
			switch (i) {
			case 1: return String.valueOf(f1);
			case 2: return String.valueOf(f2);
			default : return null;
			}
		}
		/** returning row key = type (A or B in original tables)
		 *
		 */
		@Override
		public String getWhere() {
			return type;
		}
	} // END of settings class
	/**a row in survey table .
	 * <br>
	 * <b>Implements:</b> rowInDatabase Interface
	 * @author Ofek and Yonathan
	 *  @see  {@link rowInDatabase} 
	 */
	public class survey  implements rowInDatabase{
		int id;
		int v1;
		int v2;
		int v3;
		int v4;
		 
		/**Constructor =adding row values v1,v2,v3,v4 
		 * @param id - id of student
		 * @param v1
		 * @param v2 - unused value (like in the HomeWork )
		 * @param v3
		 * @param v4
		 */
		public survey(int id, int v1, int v2, int v3, int v4) {
			this.id = id;
			this.v1 = v1;
			this.v2 = v2;
			this.v3 = v3;
			this.v4 = v4;
		}
	
		@Override
		public String getRow(int i) { // get just v1 v3 v4 like the request.
			switch (i) {
			case 1: return  String.valueOf(v1);
			case 2: return String.valueOf(v3);
			case 3: return String.valueOf(v4);
			default : return null;
			}
		}
		/**returning row key = request id
		 *
		 */
		@Override
		public String getWhere() {
			return String.valueOf(id);
		}
		
	} // END of survey class
	
	public  ArrayList <survey> surveyTable=null;
	public  ArrayList <settings> settingsTable=null;
	
	/** Constructor - making our fake DB by receiving ready tables of settings and survey = 
	 * @param surveyTable
	 * @param settingsTable
	 */
	public MockConnection(ArrayList<survey> surveyTable, ArrayList<settings> settingsTable) {
		this.surveyTable = surveyTable;
		this.settingsTable = settingsTable;
	}
	/** creates a StatmentService to work with our fake DB
	 * 
	 *
	 */
	@Override
	public StatmentService createStatement() throws SQLException {
		return new MockStatment(this);
	}

}
