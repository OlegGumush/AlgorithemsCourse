package Plane;

import java.awt.Point;


public class temp {
	public static void main(String[] args) {

		int n=4;
		Node mat[][] = new Node[n][n];
		//the 1-st row
		mat[0][0] = new Node(1,3);
		mat[0][1] = new Node(8,4);
		mat[0][2] = new Node(3,8);
		mat[0][3] = new Node(0,4);
		//the 2-nd row
		mat[1][0] = new Node(2,5);
		mat[1][1] = new Node(5,11);
		mat[1][2] = new Node(3,1);
		mat[1][3] = new Node(0,2);
		//the 3-d row
		mat[2][0] = new Node(4,10);
		mat[2][1] = new Node(3,1);
		mat[2][2] = new Node(1,4);
		mat[2][3] = new Node(0,8);
		//the 4-th row
		mat[3][0] = new Node(2,0);
		mat[3][1] = new Node(3,0);
		mat[3][2] = new Node(5,0);
		mat[3][3] = new Node(0,0);

		Node k = new Node(3,1);
		onePoint(mat,k);
	}

	private static void onePoint(Node mat[][],Node k) {
		for (int i = 0; i < mat.length; i++) {
			for (int j = 0; j < mat[0].length; j++) {
				if(mat[i][j].x==k.x && mat[i][j].y==k.y)
					System.out.println(i+" "+j);
			}
		}

	}




}
