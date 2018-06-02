package LCS;

import java.util.ArrayList;

public class LongestPolindromicSub {

	public static void main(String[] args) {
		String s = "pforgeeksskeegforp";
		//String s = "aba";
		//Polindrome_Lo_Razif_Length(s);
		Polindrome_Razif_Length(s);
	}
        //O(n^3)
	private static void Polindrome_Razif_Length(String s) {
		ArrayList<String> arr1=new ArrayList<String>();

		int bin[]=new int [s.length()];

		for (int i = 0; i < Math.pow(2, s.length()); i++) {
			String temp = "";
			for (int j = 0; j < bin.length; j++) {
				if(bin[j]==1){
					temp+=s.charAt(j);
				}				
			}
			arr1.add(temp);
			One_plus(bin);
		}
		int max =0;
		String ans = "";
		for (int i = 0; i < arr1.size(); i++) {
			if(arr1.get(i).length()>max && checkPolindrome(arr1.get(i))){	
				if(s.contains(arr1.get(i)))
				{
					max =	arr1.get(i).length();
					ans = 	arr1.get(i);
				}
			}
			
		}

		System.out.println(ans);
	}
	private static boolean checkPolindrome(String s) {
		for (int i = 0; i < s.length()/2; i++) {
			if(s.charAt(i)!=s.charAt(s.length()-1-i))
				return false;
		}
		return true;
	}

	private static void One_plus(int[] bin1) {
		for (int i = 0; i < bin1.length; i++) {
			if(bin1[i]==0){
				bin1[i]=1;
				return;
			}
			else{
				bin1[i]=0;
			}
		}

	}
	private static int[][] Polindrome_Lo_Razif_Length(String s) {
		String s1 ="";
		for (int i = 0; i < s.length(); i++) {
			s1= s.charAt(i)+s1;
		}
		System.out.println(s1);
		int mat[][]=new int[s.length()+1][s1.length()+1];

		for (int i = 0; i < s.length(); i++) {
			for (int j = 0; j < s1.length(); j++) {
				if(s.charAt(i)==s1.charAt(j)){
					mat[i+1][j+1]=mat[i][j]+1;
				}else{
					mat[i+1][j+1]=Math.max(mat[i][j+1], mat[i+1][j]);
				}
			}
		}
		System.out.println(mat[mat.length-1][mat[0].length-1]);
		return mat;
	}
}
