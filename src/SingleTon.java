class singleTon1{
	private static singleTon1 single;
	private singleTon1() {
		
	}
	public static singleTon1 get() {
		if(single==null) {
			single= new singleTon1();
		}
		return single;
	}
}

public class SingleTon {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		singleTon1 single=singleTon1.get();
		singleTon1 single1=singleTon1.get();
		if(single.equals(single1))System.out.println("True");
		else System.out.println("False");

	}

}
