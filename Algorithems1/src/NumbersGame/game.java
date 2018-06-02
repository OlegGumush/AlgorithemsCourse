package NumbersGame;

import java.util.Arrays;

public class game {

	public static void main(String[] args) {
		int arr[]={1,3,6,1,3,6};
		int ans =optimalPrice(arr);
		int ans1=CircledGame(arr);
		System.out.println(""+ans1);
	}

	private static int CircledGame(int[] arr) {
		int temp[]=new int[arr.length-1];
		int diff=0;
		int ans=0;
		int max=Integer.MIN_VALUE;
		for (int i = 0; i < arr.length; i++) {
			diff=arr[i];
			int k=0;
			for (int j = 0; j < arr.length; j++) {
				if(i!=j){
					temp[k++]=arr[j];
				}
			}
			ans=optimalPrice(temp);
			diff=diff-ans;
			if(diff>max)
				max=diff;

		}
		return max;
	}

	private static int optimalPrice(int[] arr) {

		int mat [][] = new int[arr.length][arr.length];

		for (int i = 0; i < mat.length; i++) {
			mat[i][i]=arr[i];
		}
		for (int i = arr.length-2; i >=0; i--) {
			for (int j = i+1; j < mat.length; j++) {
				mat[i][j]=Math.max(arr[i]-mat[i+1][j],arr[j]-mat[i][j-1]);
			}
		}
		return mat[0][mat.length-1];

	}

}
