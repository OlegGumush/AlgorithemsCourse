package LCS;

import java.util.ArrayList;
import java.util.Arrays;

public class Dinamic_LCS {

	public static void main(String[] args) {

		String a = "abbc";
		String b = "bb";
		String c = "fgcyccu";
		int ans = LCS(a, b);
		System.out.println("Length of common String " + ans);
		String cs = Common_String(a, b);
		System.out.println("Common_String " + cs);
		ArrayList<String> arr = All_Common_String(a, b);
		System.out.println("all common Strings" + arr);
		String ans2 = Common_String_Recursion(a, b);
		System.out.println("Common String recursion: " + ans2);
		int ans3 = LCS_3D(a, b, c);
		All_Subs_K_Length(a, b, 2);
	}

	// All sub strings k length
	public static void All_Subs_K_Length(String a, String b, int k) {

		//init matrix
		int mat[][] = new int[a.length() + 1][b.length() + 1];

		// build matrix
		for (int i = 0; i < mat.length - 1; i++) {
			for (int j = 0; j < mat[0].length - 1; j++) {
				if (a.charAt(i) == b.charAt(j)) {
					mat[i + 1][j + 1] = mat[i][j] + 1;
				} else {
					mat[i + 1][j + 1] = Math.max(mat[i + 1][j], mat[i][j + 1]);
				}
			}
		}
		ArrayList<String> arr = new ArrayList<String>();
		int i = mat.length - 1;
		int j = mat[0].length - 1;

		// recursion solution
		All_Subs_K_Length(mat, i, j, k, arr, a, b, "");
		System.out.println(arr);
	}

	private static void All_Subs_K_Length(int mat[][], int i, int j, int k, ArrayList<String> arr, String a, String b,
			String s) {

		// end of matrix and s = k.length we add
		if ((i == 0 || j == 0) && k == s.length()) {
			if (!arr.contains(s))
				arr.add(s);
			return;
		}
		// end of matrix ans s!=k.length we return
		else if ((i == 0 || j == 0) && s.length() != k)
			return;
		// if not end of matrix and s = k.length we add
		else if (s.length() == k) {
			if (!arr.contains(s))
				arr.add(s);
			return;
		}
		if (a.charAt(i - 1) == b.charAt(j - 1)) {
			All_Subs_K_Length(mat, i - 1, j - 1, k, arr, a, b, a.charAt(i - 1) + s);
			All_Subs_K_Length(mat, i - 1, j - 1, k, arr, a, b, s);
		} else if (mat[i - 1][j] > mat[i][j - 1]) {
			All_Subs_K_Length(mat, i - 1, j, k, arr, a, b, s);
		} else if (mat[i - 1][j] < mat[i][j - 1]) {
			All_Subs_K_Length(mat, i, j - 1, k, arr, a, b, s);
		} else {
			All_Subs_K_Length(mat, i - 1, j, k, arr, a, b, s);
			All_Subs_K_Length(mat, i, j - 1, k, arr, a, b, s);
		}
	}

	// Dinamic LCS
	private static int LCS(String a, String b) {

		int mat[][] = new int[a.length() + 1][b.length() + 1];

		for (int i = 0; i < mat.length - 1; i++) {
			for (int j = 0; j < mat[0].length - 1; j++) {
				if (a.charAt(i) == b.charAt(j)) {
					mat[i + 1][j + 1] = mat[i][j] + 1;
				} else {
					mat[i + 1][j + 1] = Math.max(mat[i + 1][j], mat[i][j + 1]);
				}

			}
		}
		return mat[mat.length - 1][mat[0].length - 1];
	}

	// Dinamic LCS ans return common String Induction
	public static String Common_String(String a, String b) {
		int mat[][] = new int[a.length() + 1][b.length() + 1];

		for (int i = 0; i < mat.length - 1; i++) {
			for (int j = 0; j < mat[0].length - 1; j++) {
				if (a.charAt(i) == b.charAt(j)) {
					mat[i + 1][j + 1] = mat[i][j] + 1;
				} else {
					mat[i + 1][j + 1] = Math.max(mat[i + 1][j], mat[i][j + 1]);
				}

			}
		}
		String temp = "";
		int len = mat[mat.length - 1][mat[0].length - 1];
		int i = mat.length - 1;
		int j = mat[0].length - 1;
		while (len > 0) {
			if (b.charAt(j - 1) == a.charAt(i - 1)) {
				temp = a.charAt(i - 1) + temp;
				i--;
				j--;
				len--;
			} else {
				if (mat[i][j - 1] > mat[i - 1][j])
					j--;
				else
					i--;
			}
		}
		return temp;

	}

	// Return common String recursion
	public static String Common_String_Recursion(String a, String b) {
		// build mat lcs
		int mat[][] = new int[a.length() + 1][b.length() + 1];

		for (int i = 0; i < mat.length - 1; i++) {
			for (int j = 0; j < mat[0].length - 1; j++) {
				if (a.charAt(i) == b.charAt(j)) {
					mat[i + 1][j + 1] = mat[i][j] + 1;
				} else {
					mat[i + 1][j + 1] = Math.max(mat[i + 1][j], mat[i][j + 1]);
				}
			}
		}
		String temp[] = new String[1];
		temp[0] = "";
		int len = mat[mat.length - 1][mat[0].length - 1];
		int i = mat.length - 1;
		int j = mat[0].length - 1;
		Common_String_Recursion(a, b, i, j, mat, temp, len);
		// System.out.println(Arrays.toString(temp));
		return temp[0];

	}

	private static void Common_String_Recursion(String a, String b, int i, int j, int[][] mat, String[] temp, int len) {
		if (len == 0)
			return;
		if (a.charAt(i - 1) == b.charAt(j - 1)) {
			temp[0] = a.charAt(i - 1) + temp[0];
			Common_String_Recursion(a, b, i - 1, j - 1, mat, temp, len - 1);
		} else if (mat[i][j - 1] > mat[i - 1][j]) {
			Common_String_Recursion(a, b, i, j - 1, mat, temp, len);
		} else {
			Common_String_Recursion(a, b, i - 1, j, mat, temp, len);
		}

	}

	// Return all common Strings Recurtion
	public static ArrayList<String> All_Common_String(String a, String b) {
		int mat[][] = new int[a.length() + 1][b.length() + 1];

		for (int i = 0; i < mat.length - 1; i++) {
			for (int j = 0; j < mat[0].length - 1; j++) {
				if (a.charAt(i) == b.charAt(j)) {
					mat[i + 1][j + 1] = mat[i][j] + 1;
				} else {
					mat[i + 1][j + 1] = Math.max(mat[i + 1][j], mat[i][j + 1]);
				}

			}
		}
		ArrayList<String> arr = new ArrayList<String>();
		// String s[]=new String[1];
		String s = "";
		int len = mat[mat.length - 1][mat[0].length - 1];
		int i = mat.length - 1;
		int j = mat[0].length - 1;
		All_Common_String(arr, a, b, mat, len, s, i, j);
		return arr;

	}

	private static void All_Common_String(ArrayList<String> arr, String a, String b, int[][] mat, int len, String s,
			int i, int j) {

		if (len == 0) {
			if (!arr.contains(s))
				arr.add(s);
			return;
		}
		if (a.charAt(i - 1) == b.charAt(j - 1)) {
			s = a.charAt(i - 1) + s;
			All_Common_String(arr, a, b, mat, len - 1, s, i - 1, j - 1);
		} else if (mat[i][j - 1] > mat[i - 1][j]) {
			All_Common_String(arr, a, b, mat, len, s, i, j - 1);
		} else if (mat[i][j - 1] < mat[i - 1][j]) {
			All_Common_String(arr, a, b, mat, len, s, i - 1, j);
		} else {
			All_Common_String(arr, a, b, mat, len, s, i, j - 1);
			All_Common_String(arr, a, b, mat, len, s, i - 1, j);
		}

	}

	// Lcs 3 Strings
	private static int LCS_3D(String a, String b, String c) {
		int mat[][][] = new int[a.length() + 1][b.length() + 1][c.length() + 1];

		for (int i = 0; i < a.length(); i++) {
			for (int j = 0; j < b.length(); j++) {
				for (int k = 0; k < c.length(); k++) {
					if (a.charAt(i) == b.charAt(j) && a.charAt(i) == c.charAt(k)) {
						mat[i + 1][j + 1][k + 1] = mat[i][j][k] + 1;
					} else {
						mat[i + 1][j + 1][k + 1] = max(mat[i][j + 1][k + 1], mat[i + 1][j][k + 1],
								mat[i + 1][j + 1][k]);
					}
				}
			}
		}
		int len = mat[a.length()][b.length()][c.length()];
		int i = a.length() - 1;
		int j = b.length() - 1;
		int k = c.length() - 1;
		String ans = "";
		while (len > 0) {
			if (a.charAt(i) == b.charAt(j) && a.charAt(i) == c.charAt(k)) {
				ans = a.charAt(i) + ans;
				i--;
				j--;
				k--;
				len--;
			} else {
				int matk = mat[i + 1][j + 1][k];
				int mati = mat[i][j + 1][k + 1];
				int matj = mat[i + 1][j][k + 1];
				if (mati >= matj && mati >= matk)
					i--;
				else if (matj >= mati && matj >= matk)
					j--;
				else
					k--;
			}
		}
		System.out.println("common String 3D:" + ans);
		return mat[a.length()][b.length()][c.length()];
	}

	private static int max(int i, int j, int k) {
		if (i < j) {
			i = j;
		} else if (i < k) {
			i = k;
		}
		return i;
	}
}
