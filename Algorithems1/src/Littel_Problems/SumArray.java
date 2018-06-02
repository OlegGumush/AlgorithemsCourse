package Littel_Problems;
//a1,a2,a3,a4,a5
//a1,a1+a2,a1+a2+a3,a1+a2+a3+a4,a1+a2+a3+a4+a5
//if zigzag
public class SumArray {

	public static void main(String[] args) {
		int arr[]={5,-2,4,-1,2,1};
		boolean ans =check(arr);
		System.out.println(ans);
	}

	private static boolean check(int[] arr) {
		if(arr.length<2)
			return true;
		
		for (int i = 1; i < arr.length; i++) {
			arr[i]=arr[i]+arr[i-1];
		}
		if(arr[0]>arr[1]){
			boolean a = true;
			for (int i = 1; i < arr.length-1; i++) {
				if(a){
					if(arr[i]>arr[i+1])
						return false;
					a=!a;
				}else{
					if(arr[i]<arr[i+1])
						return false;
					a=!a;
				}
			}
		}else if(arr[0]<arr[1]) {
			boolean a = true;
			for (int i = 1; i < arr.length-1; i++) {
				if(a){
					if(arr[i]<arr[i+1])
						return false;
					a=!a;
				}else{
					if(arr[i]>arr[i+1])
						return false;
					a=!a;
				}
			}
		}else
			return false;
		return true;
	}
}
