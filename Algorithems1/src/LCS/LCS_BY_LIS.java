package LCS;

import java.util.ArrayList;
import java.util.Arrays;

import LIS.LIS_Dinamic;
public class LCS_BY_LIS {

	public static void main(String[] args) {
		String X = "bdcaba", Y = "abcbdab";
		String ans = lcsWithLis(X, Y);
		System.out.println(ans);
	}
	public static String lcsWithLis(String X, String Y){//O(|Y|) = O(n)
		// build help array for Y
		int size = 'z' - 'a' + 1;
                
                //ArrayList of ArrayLists
		ArrayList<Integer>[]help = new ArrayList[size];
		for (int i = 0; i < help.length; i++) {
			help[i] = new ArrayList<Integer>();
		}
                //we run Y String ans and to ArrayList 
		for (int i = 0; i<Y.length(); i++){
			int ind = Y.charAt(i) - 'a';
			help[ind].add(i);
		}
		// build cluster array
		int j = 0;
		int []temp = new int[X.length()*Y.length()];
		for (int i=0; i<X.length(); i++){
			int ind = X.charAt(i) - 'a';
			for(int k=help[ind].size()-1; k>=0; k--){
				temp[j++] = help[ind].get(k);
			}
		}
		int[]cluster = new int[j];
		for (int i=0; i<cluster.length; i++){
			cluster[i] = temp[i];
		}
		LIS_Dinamic lis = new LIS_Dinamic();
		int[]indeces = Longest_Incresing_Sub(cluster);
		String ans = "";
		for (int i = 0; i < indeces.length; i++) {
			ans = ans + Y.charAt(indeces[i]);
		}
		return ans;
	}

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
