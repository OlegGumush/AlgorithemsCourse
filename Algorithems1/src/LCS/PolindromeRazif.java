package LCS;

import java.util.ArrayList;

public class PolindromeRazif {

	public static void main(String[] args) {
		longestPalSubstr("abbb");
	}

	private static void longestPalSubstr(String s) {
		int counter=0;
		ArrayList<String> a = new ArrayList<String>();
		for (int i = 0; i < s.length(); i++) {
			counter++;
			for (int j = i+1; j < s.length(); j++) {
				counter++;
				String temp= Polindrome(s.substring(i, j+1));
				if(temp!=null){
					a.add(temp);
				}
			}
		}
		
		int max =-1;
		String ans ="";
		for (int i = 0; i < a.size(); i++) {
			if(a.get(i).length()> max ){
				max = a.get(i).length();
				ans=a.get(i);
			}
		}
		System.out.println(ans);
		
	}

	private static String Polindrome(String s) {
		
		for (int k = 0; k < s.length()/2; k++) {
			if(s.charAt(k)!= s.charAt(s.length()-1-k))
				return null;
		}
		return s;
	}
}
