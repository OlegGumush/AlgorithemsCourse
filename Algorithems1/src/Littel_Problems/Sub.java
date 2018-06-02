package Littel_Problems;

import java.util.ArrayList;

public class Sub {

	public static void main(String[] args) {
		int arr[]={1,2,3};
		sub(arr);
	}

	private static void sub(int[] arr) {
		ArrayList<ArrayList<Integer>> ans = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> temp = new ArrayList<Integer>();
		int binary[]=new int[arr.length];
		
		
		for (int i = 0; i < (int) Math.pow(2, arr.length); i++) {
			temp = new ArrayList<Integer>();
			for (int j = 0; j < binary.length; j++) {
				if(binary[j]==1)
					temp.add(arr[j]);
			}
			OnePlus(binary);
			ans.add(temp);
		}
		for (int i = 0; i < ans.size(); i++) {
			System.out.println(ans.get(i));
		}		
	}

	private static void OnePlus(int[] binary) {
		
		for (int i = 0; i < binary.length; i++) {
			if(binary[i]==1)
				binary[i]=0;
			else{
				binary[i]=1;
				return;
			}
		}
		
	}
}
