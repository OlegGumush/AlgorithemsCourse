package LCS;

public class LongestReapitindSub {
	public static void main(String[] args) {
		String s1 = "tototo";
		repeat(s1);
	}

	private static void repeat(String s1) {
		
		int mat[][]=new int[s1.length()+1][s1.length()+1];
		
		for (int i = 1; i < mat.length; i++) {
			for (int j = 1; j < mat[0].length; j++) {
				if(s1.charAt(i-1)==s1.charAt(j-1) && i!=j)
					mat[i][j]=mat[i-1][j-1]+1;
				else
					mat[i][j]=Math.max(mat[i-1][j], mat[i][j-1]);
			}
		}
		System.out.println(mat[mat.length-1][mat[0].length-1]);
	}
}
