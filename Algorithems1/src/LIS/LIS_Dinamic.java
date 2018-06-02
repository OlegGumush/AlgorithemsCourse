package LIS;
//Longest Increasing sub
import java.util.ArrayList;
import java.util.Arrays;

public class LIS_Dinamic {

	public static void main(String[] args) {
		int arr[]={100,100,100};
		Longest_Incresing_Sub(arr);
		LIS_BY_LCS(arr);
		LIS_BY_LCS_Path(arr);
		LIS_BY_LCS_All_Pathes(arr);
		LDS_BY_LIS(arr);		
	}




	/////////////////////////////
	//   LDS - only length  /////    
	/////////////////////////////
	private static void LDS_BY_LIS(int[] arr) {
		for (int i = 0; i < arr.length; i++) {
			arr[i]=(-1*arr[i]);
		}
		System.out.print("LDS: ");
		LIS_BY_LCS(arr);
		//LIS_BY_LCS_Path(arr);
		//LIS_BY_LCS_All_Pathes(arr);
	}

	/////////////////////////////
	//   LIS_BY_LCS //////  /////    
	/////////////////////////////
	/**
	 * The LIS algorithm using LCS for arrays instead of strings
	 * Complexity: O(n^2)
	 */
	private static void LIS_BY_LCS(int[] arr) {


		int arr1[]=new int[arr.length];
		for (int i = 0; i < arr1.length; i++) {
			arr1[i]=arr[i];
		}
		Arrays.sort(arr1);

		int mat[][]=new int[arr.length+1][arr.length+1];

		for (int i = 1; i < mat.length; i++) {
			for (int j = 1; j < mat.length; j++) {
				if(arr[i-1]==arr1[j-1])
					mat[i][j]=mat[i-1][j-1]+1;
				else
					mat[i][j]=Math.max(mat[i-1][j], mat[i][j-1]);
			}
		}

		System.out.println("length of longest sub "+mat[mat.length-1][mat.length-1]);

	}

	private static void LIS_BY_LCS_Path(int[] arr) {
		int arr1[]=new int[arr.length];
		for (int i = 0; i < arr1.length; i++) {
			arr1[i]=arr[i];
		}
		Arrays.sort(arr1);

		int mat[][]=new int[arr.length+1][arr1.length+1];

		for (int i = 1; i < mat.length; i++) {
			for (int j = 1; j < mat.length; j++) {
				if(arr[i-1]==arr1[j-1])
					mat[i][j]=mat[i-1][j-1]+1;
				else
					mat[i][j]=Math.max(mat[i-1][j], mat[i][j-1]);
			}
		}
		int len = mat[mat.length-1][mat.length-1];
		int ans[]=new int[len];
		int k = ans.length-1;
		int i = mat.length-1;
		int j = mat.length-1;
		while(len>0){
			if(arr[i-1]==arr1[j-1]){
				ans[k]=arr[i-1];
				k--;
				i--;
				j--;
				len--;
			}else if(mat[i-1][j]>mat[i][j-1])
				i--;
			else
				j--;
		}
		System.out.println(Arrays.toString(ans));

	}

	private static void LIS_BY_LCS_All_Pathes(int[] arr) {
		int arr1[]=new int[arr.length];
		for (int i = 0; i < arr1.length; i++) {
			arr1[i]=arr[i];
		}
		Arrays.sort(arr1);

		int mat[][]=new int[arr.length+1][arr1.length+1];

		for (int i = 1; i < mat.length; i++) {
			for (int j = 1; j < mat.length; j++) {
				if(arr[i-1]==arr1[j-1])
					mat[i][j]=mat[i-1][j-1]+1;
				else
					mat[i][j]=Math.max(mat[i-1][j], mat[i][j-1]);
			}
		}
		int len = mat[mat.length-1][mat.length-1];
		ArrayList<String> array = new ArrayList<String>();

		int n = mat.length-1;
		int m = mat[0].length-1;
		LIS_BY_LCS_All_Pathes(array,mat,len,n,m,arr,arr1,"");
		System.out.println(array);

	}

	private static void LIS_BY_LCS_All_Pathes(ArrayList<String> array, int[][] mat, int len,int n,int m,int arr[],int arr1[],String s) {
		if(len==0){
			s+="  ";
			if(!array.contains(s))
				array.add(s);
			return ;
		}
		if(arr[n-1]==arr1[m-1]){
			s=arr[n-1]+" "+s;
			LIS_BY_LCS_All_Pathes(array,mat,len-1,n-1,m-1,arr,arr1,s);			
		}else if(mat[n-1][m]>mat[n][m-1]){
			LIS_BY_LCS_All_Pathes(array,mat,len,n-1,m,arr,arr1,s);	
		}else if(mat[n-1][m]<mat[n][m-1]){
			LIS_BY_LCS_All_Pathes(array,mat,len,n,m-1,arr,arr1,s);	
		}else{
			LIS_BY_LCS_All_Pathes(array,mat,len,n-1,m,arr,arr1,s);
			LIS_BY_LCS_All_Pathes(array,mat,len,n,m-1,arr,arr1,s);
		}

	}



	//////////////
	///   LIS   //        
	//////////////
	//O(n*log(n))
	//copy --> O(n^2)
	public static int[] Longest_Incresing_Sub(int[] arr) {
		int mat[][]=new int[arr.length][arr.length];
		mat[0][0]=arr[0];
		int len=1;

		for (int i = 1; i < arr.length; i++) {
			int index=BinaryBetween(mat,len,arr[i]);
			if(index==len){
				len++;
			}
			mat[index][index]=arr[i];
			copy(mat,index);
		}

		System.out.println("len of longest sub "+len);
		int ans[]=new int[len];
		for (int i = 0; i < ans.length; i++) {
			ans[i]=mat[len-1][i];
		}
		return ans;

	}

	private static void copy(int[][] mat, int index) {
		for (int i = 0; i < index; i++) {
			mat[index][i]=mat[index-1][i];
		}

	}

	private static int BinaryBetween(int[][] mat, int len, int val) {
		if(mat[len-1][len-1]<val){
			return len;
		}
		if(mat[0][0]>val){
			return 0;
		}
		int low=0;
		//int high = len 
		int high=len-1;
		while(low<=high){
			int mid=(high+low)/2;
			if(high==low)
				return low;
			if(mat[mid][mid]==val){
				return mid;
			}
			else if(mat[mid][mid]<val){
				low=mid+1;
			}
			else
				high=mid;
		}
		return -1;
	}


}
