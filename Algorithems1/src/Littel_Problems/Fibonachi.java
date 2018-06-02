package Littel_Problems;

public class Fibonachi {

	public static void main(String[] args) {
		int n =10;
		
		long ans =fibo(n);
		long ans1 = f(n);
		System.out.println(ans);
	}

	//O(n)
	private static int f(int fibo) {
		int f[]=new int[fibo];
		
		f[0]=1;
		f[1]=1;
		
		for (int i = 2; i < f.length; i++) {
			f[i]=f[i-1]+f[i-2];
		}
		return f[f.length-1];
		
	}
	//O(log(n))
	public static int fibo(int n){
		int mat[][]={{1,1},{1,0}};
		
		
		int ans[][] = pow(mat,n);
		int up  =ans[0][0]+ans[0][1];
		int down=ans[1][0]+ans[1][1];
		return up - down;
		
	}
	private static int[][] pow(int[][] mat, int n) {
		
		if(n==1)
			return mat;
		
		if(n%2 == 0)
			return pow(mult(mat,mat),n/2);
		else
			return mult(mat,pow(mult(mat,mat),n/2));
		
	}

	public static int[][] mult(int A[][],int B[][]){
		
		int C[][]=new int[2][2];
		
		C[0][0] = A[0][0]*B[0][0] + A[0][1]*B[1][0];
		C[1][0] = A[1][0]*B[0][0] + A[1][1]*B[1][0];
		C[0][1] = A[0][0]*B[0][1] + A[0][1]*B[1][1];
		C[1][1] = A[1][0]*B[0][1] + A[1][1]*B[1][1];
		return C;
	}
}
