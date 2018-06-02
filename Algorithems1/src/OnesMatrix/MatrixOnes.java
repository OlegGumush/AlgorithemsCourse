package OnesMatrix;
//Longest Rectangle see in geeks
public class MatrixOnes {
	
	public static void main(String[] args) {
		int mat[][] =  {{0, 1, 1, 0, 1}, 
						{1, 1, 0, 1, 0}, 
						{0, 1, 1, 1, 0},
						{1, 1, 1, 1, 0},
						{1, 1, 1, 1, 1},
						{0, 0, 0, 0, 0}};
		size(mat);
	}

	private static void size(int[][] mat) {
		int temp[][]=new int[mat.length][mat[0].length];

		//fill first row , col
		for (int i = 0; i < mat.length; i++) {
			temp[i][0]=mat[i][0];
		}
		for (int i = 0; i < temp[0].length; i++) {
			temp[0][i]=mat[0][i];
		}
		//fill all mat
		for (int i = 1; i < temp.length; i++) {
			for (int j = 1; j < temp[0].length; j++) {
				if(mat[i][j]==1){
					temp[i][j]=min(temp[i-1][j-1],temp[i-1][j],temp[i][j-1])+1;
				}else{
					temp[i][j]=0;
				}
			}
		}
		//find max entry , find indexes of start cube
		int max =0;
		int maxi=0;
		int maxj=0;
		for (int i = 0; i < temp.length; i++) {
			for (int j = 0; j < temp[0].length; j++) {
				if(temp[i][j]>max){
					max=temp[i][j];
					maxi=i;
					maxj=j;
				}
			}
		}
		System.out.println(max);
		System.out.println("index of max value end of cude ("+maxi+","+maxj+")");
		System.out.println("indexes of start cude ("+(maxi-max+1)+","+(maxj-max+1)+")");

		for (int i = 0; i < temp.length; i++) {
			for (int j = 0; j < temp[0].length; j++) {
				System.out.print(temp[i][j]+" ");
			}
			System.out.println();
		}
	}

	private static int min(int i, int j, int k) {
		if(i>j)
			i=j;
		if(i>k)
			i=k;
		return i;
	}
}
