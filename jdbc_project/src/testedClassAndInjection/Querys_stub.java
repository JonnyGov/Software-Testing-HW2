package testedClassAndInjection;

import java.util.ArrayList;

public class Querys_stub implements Querys_interface {

	@Override
	public ArrayList<Object> v1v3v4Dealings(float min, String ID) {
		ArrayList<Object> arrayList =new ArrayList<Object> ();
		float[] floatArr = new float[3];
		floatArr[0]=4; //v1
		floatArr[1]=4;//v3
		floatArr[2]=5;//v4
		arrayList.add(floatArr);
		return arrayList ;
	}

	@Override
	public int[] f1f2Dealings(String surveyType) {
		int[] f1f2 = new int[2];
		if(surveyType.equals("A")) {
			f1f2[0]=2;
			f1f2[1]=1;
		}
		
		if(surveyType.equals("B")) {
			f1f2[0]=1;
			f1f2[1]=0;
		}
		return f1f2 ;
	}

}
