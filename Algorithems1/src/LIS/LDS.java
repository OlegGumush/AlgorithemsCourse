package LIS;
//Longest decreasing sub
import java.util.Arrays;

public class LDS {

	
	public static void main(String[] args) {
		int arr[]={1,0,100,-1,100,99,89,78};
		LDS(arr);
	}

	
	private static void LDS(int[] arr) {
		int mat[][]=new int[arr.length][arr.length];
		mat[0][0]=arr[0];
		int len =1;
		for (int i = 1; i < mat.length; i++) {
			int index = BinarySearchBeetwen(mat , len , arr[i]);
			if(index==len)
				len++;
			mat[index][index]=arr[i];
			copy(index , mat );
		}
		int ans[] = new int[len];
		for (int i = 0; i < ans.length; i++) {
			ans[i]=mat[len-1][i];
		}
		System.out.println(Arrays.toString(ans));	
	}


	private static void copy(int index, int[][] mat) {
		
		for (int i = 0; i < index; i++) {
			mat[index][i]=mat[index-1][i];
		}
		
	}


	private static int BinarySearchBeetwen(int[][] mat, int len, int value) {
		if(value > mat[0][0])
			return 0;
		if(value < mat[len-1][len-1])
			return len;
		
		int low =0;
		int high = len;
		//int high=len-1;
		
		while(high>=low){
			if(high == low )
				return low;
			
			int middle = (high+low)/2;
			if(mat[middle][middle]==value)
				return middle;
			else if (mat[middle][middle]>value)
				low = middle +1 ;
			else
				high = middle;
			
		}
		return -1;
	}
}










