package Littel_Problems;


public class Pow {

	public static void main(String[] args) {
		int base = 2;
		int exponent = 10;
		
		int ans =pow(base,exponent);
		System.out.println(ans);
	}

	private static int pow(int base, int exponent) {
		
		if (exponent ==0)
			return 1;
		
		if(exponent %2 ==0)
			return pow(base*base,exponent/2);
		else
			return base*pow(base*base,exponent/2);	
	}
	

}











