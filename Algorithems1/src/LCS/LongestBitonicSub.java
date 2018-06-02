package LCS;

public class LongestBitonicSub {


	public static void main(String[] args) {
		int arr[] = {1,2,3,4,5,4,3,2,1,7,8,9};
		bitonic(arr);
	}

	private static void bitonic(int[] arr) {
		int lis[]=new int[arr.length];
		int lds[]=new int[arr.length];

		for(int i = 0; i < lds.length; i++) {
			lis[i]=L_I_S(arr,i);
			lds[i]=L_D_S(arr,i);
		}
		int max = lis[0]+lds[0]-1;
		for (int i = 1; i < lds.length; i++) {
			if((lis[i]+lds[i]-1)>max )
				max = lis[i]+lds[i]-1 ;
		}
		System.out.println(max);
	}


	private static int L_D_S(int arr[], int i) {
		int mat[][]=new int[arr.length-i][arr.length-i];
		
		int len =1;
		mat[0][0]=arr[i];
		for (int j = i+1; j < arr.length; j++) {
			int index = binaryLds(mat,arr[j],len);
			if(index==len)
				len++;
			mat[index][index]=arr[j];
		}
		return len;
	}

	private static int binaryLds(int[][] mat, int val, int len) {
		if(mat[0][0]<val)
			return 0;
		if(mat[len-1][len-1]>val)
			return len;
		
		int low = 0;
		int high = len-1;
		
		while(low<=high){
			int middle = (high+low)/2;
			if(low == high )
				return low;
			if(mat[middle][middle]==val)
				return middle;
			else if (mat[middle][middle]>val)
				low = middle+1;
			else 
				high = middle;
		}
		return -1;
	}

	private static int L_I_S(int[] arr, int i) {
		int mat[][]=new int[i+1][i+1];
		mat[0][0]=arr[0];
		int len =1 ;
		for (int j = 1; j <=i; j++) {
			int index = binaryLis(mat,len,arr[j]);
			if(index==len)
				len++;
			mat[index][index]=arr[j];
		}
		return len;
	}

	private static int binaryLis(int[][] mat,int len,int val) {
		if(mat[len-1][len-1]<val)
			return len;
		if(mat[0][0]>val)
			return 0;

		int low = 0;
		int high = len-1;

		while(low<=high){
			int middle = (low+high)/2;
			if(low==high)
				return low;
			if(mat[middle][middle]==val)
				return middle;
			else if (mat[middle][middle]<val)
				low = middle+1;
			else
				high = middle;
		}
		return -1;
	}

}



