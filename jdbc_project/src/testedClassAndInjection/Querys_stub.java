package testedClassAndInjection;

import java.util.ArrayList;

public class Querys_stub implements Querys_interface {
	
	 private ArrayList<Object> arrayListV1V3V4 =null ;
	 private	int[] Af1f2 =null ;
	 private	int[] Bf1f2 =null;

	 public Querys_stub(ArrayList<Object> arrayListV1V3V4 ,int[] Af1f2,int[] Bf1f2) {
		 this.arrayListV1V3V4= arrayListV1V3V4;
		 this.Af1f2 =Af1f2;
		 this.Bf1f2 =Bf1f2;
	 }
	
	 //If a null is sent then the data will be used like in DB
	@Override
	public ArrayList<Object> v1v3v4Dealings(float min, String ID) {
		if(arrayListV1V3V4==null) {
		ArrayList<Object> arrayList =new ArrayList<Object> ();
		float[] floatArr = new float[3];
		floatArr[0]=4; //v1
		floatArr[1]=4;//v3
		floatArr[2]=5;//v4
		arrayList.add(floatArr);
		return arrayList ;
		}
		return arrayListV1V3V4;
	}

	@Override
	public int[] f1f2Dealings(String surveyType) {

		int[] f1f2 = new int[2];
		
		
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
		
		
		return f1f2 ;
		
		
		
	}

}
