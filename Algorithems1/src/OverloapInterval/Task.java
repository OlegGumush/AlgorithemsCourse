package OverloapInterval;

import java.util.ArrayList;
import java.util.Arrays;

public class Task {

	public static void main(String[] args) {

		int A[] = {1 , 12 ,42 ,70 , 36 ,-4 , 43 ,15};
		int B[] = {5 ,15 ,44 ,72 ,36 ,2 ,69 , 24};

		Task t = new Task();

		System.out.println(t.task(A , B));
	}



	public int task(int A[] , int B[]){

		Interval[] arr = new Interval[A.length];
		
		for (int i = 0; i < A.length; i++) {
			arr[i] = new Interval(A[i],B[i]);
		}	
		
		Arrays.sort(arr);
		int counter =0;
		
		for (int i = 0; i < B.length-1; i++) {
			
			if(merge(arr[i] , arr[i+1]) != null){
				arr[i+1] = merge(arr[i] , arr[i+1]);
				counter ++;
			}
		}
		
		return arr.length - counter ;
	}

	

	private Interval merge(Interval interval1, Interval interval2) {
	
		if (interval1.end >= interval2.start){
			return new Interval(interval1.start , interval2.end);
		}
		return null;
	}
}

