package testedClassAndInjection;

import java.sql.SQLException;
import java.util.ArrayList;

public class Querys_stub implements Querys_interface {
	
	 private ArrayList<Object> arrayListV1V3V4 =null ;
	 private	int[] Af1f2 =null ;
	 private	int[] Bf1f2 =null;
	 private    String[] keysID =null;
	 private   	boolean  isThereKeyssurveyType;
	 private 	boolean  connIsnull;

	 public Querys_stub(ArrayList<Object> arrayListV1V3V4 ,int[] Af1f2,int[] Bf1f2,String[] keysID,	boolean  isThereKeyssurveyType, boolean connIsnull) {
		 this.arrayListV1V3V4= arrayListV1V3V4;
		 this.Af1f2 =Af1f2;
		 this.Bf1f2 =Bf1f2;
		 this.keysID = keysID;
		 this.isThereKeyssurveyType =isThereKeyssurveyType;
		 this.connIsnull =connIsnull;
	 }
	
	// If a null is sent then the data will be used like in DB
	@Override
	public ArrayList<Object> v1v3v4Dealings(float min, String ID) {
			int flag=0;
		try {
			// using conn without testing conn or setting it up
			if(connIsnull==true) throw   new NullPointerException("conn is null");

			if(keysID==null) {			// if  did not send keys in DB then test against what was in the exemple DB
			if (!ID.equals("111111111"))
				throw new SQLException("no such ID \n");
			}
			else {						// testing if in DB of our keys
				for(String e : keysID) {
					if(e.equals(ID)) {
						flag=1;
						break;
					}
					
				}
				if(flag ==0) throw new SQLException("no such ID \n");		
			}
			
			
			
			if (arrayListV1V3V4 == null) {
				ArrayList<Object> arrayList = new ArrayList<Object>();
				float[] floatArr = new float[3];
				floatArr[0] = 4; // v1
				floatArr[1] = 4;// v3
				floatArr[2] = 5;// v4
				arrayList.add(floatArr);
				return arrayList;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return arrayListV1V3V4;

	}

	@Override
	public int[] f1f2Dealings(String surveyType) {

		int[] f1f2 = new int[2];
		try {
			// using conn without testing conn or setting it up
			if(connIsnull==true) throw   new NullPointerException("conn is null");
			if(isThereKeyssurveyType==false ||((!surveyType.equals("A"))&&(!surveyType.equals("B")))) throw new SQLException("no sucsh surveyType\n");
		
		if(surveyType.equals("A") && Af1f2!=null ) {
			f1f2[0]=Af1f2[0];
			f1f2[1]=Af1f2[1];
		}
		else if(surveyType.equals("A") ) {
			f1f2[0]=2;
			f1f2[1]=1;
		}
		
		
		
		if(surveyType.equals("B")&& Bf1f2!=null ) {
			f1f2[0]=Bf1f2[0];
			f1f2[1]=Bf1f2[1];
		}else if (surveyType.equals("B"))
		{
			f1f2[0]=1;
			f1f2[1]=0;
		}
		
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		return f1f2 ;
		
		
		
	}

}
