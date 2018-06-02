package LCS;

import java.util.ArrayList;
import java.util.Vector;

public class LCS_Whole_Search {


	public static void main(String[] args) {

		String a = "abcgrt";
		String b = "grtabc";

		WholeSearch(a,b);
	}

	private static ArrayList<String> WholeSearch(String a, String b) {
		ArrayList<String> arr1=new ArrayList<String>();
		ArrayList<String> arr2=new ArrayList<String>();

		int bin1[]=new int [a.length()];
		int bin2[]=new int [b.length()];

		for (int i = 0; i < Math.pow(2, a.length()); i++) {
			arr1.add(RTN_String(a,bin1));
			One_plus(bin1);
		}
		for (int i = 0; i < Math.pow(2, b.length()); i++) {
			arr2.add(RTN_String(b,bin2));
			One_plus(bin2);
		}
		System.out.println(arr1);
		System.out.println(arr2);

		//longest common length and One String
		int max=0;
		String s ="";
		for (int i = 0; i < arr1.size(); i++) {
			for (int j = 0; j < arr2.size(); j++) {
				if(arr1.get(i).equals(arr2.get(j))){
					if(max<arr1.get(i).length()){
						max=arr1.get(i).length();
						s=arr1.get(i);
					}
				}
			}
		}
		System.out.println(max);
		System.out.println(s);

		//return all common longest Strings
		ArrayList<String> AllStrings = new ArrayList<String>();
		for (int i = 0; i < arr1.size(); i++) {
			for (int j = 0; j < arr2.size(); j++) {
				//if two Strings equals and length == max and arraylist not contain ---> add
				if(arr1.get(i).equals(arr2.get(j))){
					if(arr1.get(i).length()==max && !AllStrings.contains(arr1.get(i)))
						AllStrings.add(arr1.get(i));
				}
			}
		}

		System.out.print(AllStrings);
		return arr1;
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

	private static String RTN_String(String a, int[] bin1) {
		String ans="";
		for (int i = 0; i < bin1.length; i++) {
			if(bin1[i]==1){
				ans+=a.charAt(i);
			}
		}
		return ans;
	}




	
	/**
	 * The whole search RECURSION of LCS
	 * Complexity: O(2^(m+n)*min(m,n)) - |X| = n , |Y| = m
	 */
	public static String wholeSearch(String X, String Y) {
		//send to function and get all sub Strings
		Vector<String> sx = getAllSubStrings(X);
		Vector<String> sy = getAllSubStrings(Y);
		String lcs = ""; 
		//find one longest common String
		for(String s1 : sx) {
			for(String s2 : sy) {
				if(s1.equals(s2)) {
					if(s1.length() > lcs.length()) {
						lcs = s1;
					}
				}
			}
		}
		return lcs;
	}

	private static Vector<String> getAllSubStrings(String str) {
		Vector<String> ans = new Vector<String>();
		getAllSubStrings(str,ans,0,"");
		return ans;
	}


	private static void getAllSubStrings(String str, Vector<String> ans,int i,String temp) {
		if(i == str.length()) {
			ans.add(temp);
			return;
		}
		getAllSubStrings(str,ans,i+1,temp);
		getAllSubStrings(str,ans,i+1,temp + str.charAt(i));
	}
}

