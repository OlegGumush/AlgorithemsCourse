package Littel_Problems;

public class CircleSort {

	public static void main(String[] args) {
		int arr[]={4,5,6,1,2,3};
		boolean ans = CircleSort(arr);
		System.out.println(ans);
	}

	private static boolean CircleSort(int[] arr) {
		int min = arr[0];
		int index=0;
		for (int i = 1; i < arr.length; i++) {
			if(arr[i]<min){
				min=arr[i];
				index = i;
			}
		}
		for (int i = 0; i < arr.length-1; i++) {
			if(arr[(i+index)%arr.length]>arr[(i+1+index)%arr.length])
				return false;
		}
		return true;
	}
	
}
