package NumbersGame;

import java.util.Arrays;

public class NumberGame_Optimal {


	public static void main(String[] args) {
		int arr[]={1,3,6,1,3,6};
		optimalPrice(arr);
		OptimalPriceWortestGamer(arr);
	}
	private static void OptimalPriceWortestGamer(int[] arr) {
		int mat[][] = new int [arr.length][arr.length];
		for (int i=0; i<arr.length; i++) {
			mat[i][i] = arr[i];
		}
		String s="";
		for (int i = arr.length-2; i >=0; i--) {
			for (int j = i+1; j < arr.length; j++) {
				mat[i][j]=Math.max(arr[i]-mat[i+1][j], arr[j]-mat[i][j-1]);
			}
		}
		int k =0;
		int n = arr.length-1;
		s="";
		int sum=0;
		boolean turn =true;
		while(k<n){
			if(turn){
				if(arr[k] - mat[k+1][n] >arr[n]-mat[k][n-1]){
					s+=arr[k]+" ";
					sum +=arr[k];
					k++;
				}
				else if(arr[k] - mat[k+1][n]< arr[n]-mat[k][n-1]){
					s+=arr[n]+" ";
					sum +=arr[n];
					n--;
				}else{
					if(arr[n]>arr[k]){
						s+=arr[n]+" ";
						sum +=arr[n];
						n--;
					}
					else{
						s+=arr[k]+" ";
						sum +=arr[k];
						k++;
					}
				}
				turn = !turn;
			}else{
				if(arr[k] - mat[k+1][n] < arr[n]-mat[k][n-1]){
					s+=arr[k]+" ";
					sum +=arr[k];
					k++;
				}
				else if(arr[k] - mat[k+1][n]> arr[n]-mat[k][n-1]){
					s+=arr[n]+" ";
					sum +=arr[n];
					n--;
				}else{
					if(arr[n]<arr[k]){
						s+=arr[n]+" ";
						sum -=arr[n];
						n--;
					}
					else{
						s+=arr[k]+" ";
						sum -=arr[k];
						k++;
					}
				}
				turn = !turn;
			}
		}
		s+=arr[k];
		System.out.println("Optimal Profit Wortest Games: "+s);
		System.out.println("Optimal Profit Wortest Games: "+sum);

		System.out.println(mat[0][mat[0].length-1]);

	}
	public static void optimalPrice(int arr[]) {
		int mat[][] = new int [arr.length][arr.length];
		for (int i=0; i<arr.length; i++) {
			mat[i][i] = arr[i];
		}
		String s="";
		for (int i = arr.length-2; i >=0; i--) {
			for (int j = i+1; j < arr.length; j++) {
				mat[i][j]=Math.max(arr[i]-mat[i+1][j], arr[j]-mat[i][j-1]);
			}
		}
		System.out.println("Oprimal Profit Best Gamer:"+mat[0][mat[0].length-1]);

		int i=0;
		int j=arr.length-1;
		String game="";
		while(i<j){
			if(arr[j]-mat[i][j-1] > arr[i] - mat[i+1][j]){
				game+=arr[j]+" ";
				j--;
			}else if(arr[j]-mat[i][j-1] < arr[i] - mat[i+1][j]){
				game+=arr[i]+" ";
				i++;
			}else{
				if(arr[i]>arr[j]){
					game+=arr[i]+" ";
					i++;
				}else{
					game+=arr[j]+" ";
					j--;
				}

			}
		}
		game+=arr[i];
		System.out.println(game);
	}

}





