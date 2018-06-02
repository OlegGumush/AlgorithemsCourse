package ParkingProblem;

public class Parking {

	public static void main(String[] args) {
	String arr[]={"a","v","d","d","s","r","v","a","v","a"};
	int size = Parking(arr);
	System.out.println(size);
	}

	private static int Parking(String[] arr) {
		
		boolean flag=false;
		int count=0;
		arr[0]="v";
		while(!flag){
			count++;
			if(arr[(count)%arr.length]=="v"){
				arr[(count)%arr.length]="x";
				if(arr[0]=="x")
					flag = true;
			}
		}
		return count;
	}
	
	
}
