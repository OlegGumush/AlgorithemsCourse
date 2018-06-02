package LCS;
//Shortest String that contain 2 Strings
import java.util.Arrays;


public class ShortestCommonSupersequence {

	public static void main(String[] args) {
		String str1 = "aa";
		String str2 = "ba";
		String s = lengthOfShortestCommonSupersequence1(str1,str2);
		System.out.println(s);
		//System.out.println(ans);

	}

	private static int lengthOfShortestCommonSupersequence(String str1,String str2) {

		int mat[][]=new int [str1.length()+1][str2.length()+1];

		// Fill table in bottom up manner
		for (int i = 0; i <mat.length; i++)
		{
			for (int j = 0; j <mat[0].length; j++)
			{
				// Below steps follow above recurrence
				if (i==0)
					mat[i][j] = j;
				else if (j==0)
					mat[i][j] = i;
				else if (str1.charAt(i-1) == str2.charAt(j-1))
					mat[i][j] = 1 + mat[i-1][j-1];
				else
					mat[i][j] = 1 + Math.min(mat[i-1][j], mat[i][j-1]);
			}
		}

		return mat[mat.length-1][mat[0].length-1];
	}
	private static String lengthOfShortestCommonSupersequence1(String str1,String str2) {
		int mat[][]=new int[str1.length()+1][str2.length()+1];

		for (int i = 0; i < mat.length-1; i++) {
			for (int j = 0; j < mat[0].length-1; j++) {
				if(str1.charAt(i)==str2.charAt(j))
					mat[i+1][j+1]= mat[i][j]+1;
				else
					mat[i+1][j+1]=Math.max(mat[i+1][j], mat[i][j+1]);
			}
		}
		System.out.println("LCS "+mat[mat.length-1][mat[0].length-1]);
		int len = mat[mat.length-1][mat[0].length-1];
		if(len==0)
			return str1+str2;
		int i = str1.length();
		int j = str2.length();
		int arr1[]=new int[len];
		int arr2[]=new int[len];
		int index = len-1;
		String lcs ="";
		while(len>0){
			while(i<arr1[index]){
				lcs+=str1.charAt(i);
				i++;
			}
			while(j<arr1[index]){
				lcs+=str1.charAt(i);
				i++;
			}
			if(str1.charAt(i-1)==str2.charAt(j-1)){
				lcs += str1.charAt(i-1);
				arr1[index]=i-1;
				arr2[index]=j-1;
				len--;
				index--;
				i--;
				j--;
			}else if(mat[i][j-1]>mat[i-1][j]){
				j--;
			}else 
				i--;
		}		
		System.out.println(lcs);
		System.out.println(Arrays.toString(arr1));
		System.out.println(Arrays.toString(arr2));

		i = 0;
		j = 0;
		String s ="";
		int str1_index =0;
		int str2_index=0;
		
		while(i<str1.length() && j<str2.length()){
			if(i<arr1[str1_index])
				s+=str1.charAt(i++);
			else if(j<arr2[str2_index]) {
				s+=str2.charAt(j++);
			}else{
				s+=str1.charAt(i);
				//s+=str2.charAt(j);
				i++;
				j++;
				str1_index++;
				str2_index++;
			}
		}
		while(i<str1.length())
			s+=str1.charAt(i++);
		while(j<str2.length())
			s+=str2.charAt(j++);
		return s;
	}
}










