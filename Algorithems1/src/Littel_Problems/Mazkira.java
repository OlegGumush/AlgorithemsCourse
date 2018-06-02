package Littel_Problems;

import java.util.Arrays;


public class Mazkira {

	public static void main(String[] args) {
		int arr[]={1,2,3};
		Mazkira(arr);
		double ans = getAvarageTime(arr);
		System.out.println(ans);
	}

	private static void Mazkira(int[] arr) {
		Arrays.sort(arr);
		double time=0;
		for (int i = 0; i < arr.length; i++) {
			time =time + arr[i] +time ;
		}
		System.out.println(time/arr.length);
	}
	public static double getAvarageTime(int[] times) {
		Arrays.sort(times);
		double avg = 0;
		for(int i = 0; i < times.length; i++) 
			avg = avg + avg + times[i];
		return avg/times.length;
	}
}
