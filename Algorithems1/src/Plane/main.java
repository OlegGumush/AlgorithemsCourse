package Plane;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class main {

	public static void main(String[] args) {

		int n=4;
		Node mat[][] = new Node[n][n];
		mat[0][0] = new Node(1,3);
		mat[0][1] = new Node(8,4);
		mat[0][2] = new Node(3,8);
		mat[0][3] = new Node(0,4);
		mat[1][0] = new Node(2,5);
		mat[1][1] = new Node(5,11);
		mat[1][2] = new Node(3,1);
		mat[1][3] = new Node(0,2);
		mat[2][0] = new Node(4,10);
		mat[2][1] = new Node(3,1);
		mat[2][2] = new Node(1,4);
		mat[2][3] = new Node(0,8);
		mat[3][0] = new Node(2,0);
		mat[3][1] = new Node(3,0);
		mat[3][2] = new Node(5,0);
		mat[3][3] = new Node(0,0);


//				for (int i = 0; i < mat.length; i++) {
//					for (int j = 0; j < mat[0].length; j++) {
//						mat[i][j] = new Node((int)(10*Math.random()),(int)(10*Math.random()));
//					}
//				}
//				



		Plane p = new Plane(mat,4000);
		//////////////////////////////////////////////////////
		int ans = p.BestPrice();
		System.out.println("best price "+ans);
		//////////////////////////////////////////////////////
		int ans1=p.numOfPathes();
		System.out.println("num of best pathes "+ans1);
		//////////////////////////////////////////////////////
		String s = p.OneBestPath();
		System.out.println("One Optimal Path by price  "+s);
		//////////////////////////////////////////////////////

		ArrayList<String> arr =p.AllPathes();
		System.out.println("All Pathes"+arr.toString());
		//////////////////////////////////////////////////////
		int s1 = p.numOfOptimalPaths();
		System.out.println("Best Pathes for the Plane "+s1);
		//////////////////////////////////////////////////////
		Node p1 = new Node(2,1);
		boolean b = p.PathPoint(p1);

		//Gil-boolean b1 = p.isOnBestPath(p1);
		System.out.println("Point p("+p1.x+","+p1.y+") if on the optimal path????: "+b);
		//////////////////////////////
		///////////////////////
		Node arr1[]=new Node[3];
		arr1[0] = new Node(0,2);
		arr1[1] = new Node(2,0);
		arr1[2] = new Node(2,2);


		//פה אנחנו בודקים אם כל נקודה במערך נמצאת על איזשהו מסלול קצר
		boolean allpoint = p.ArrayPointsOnTheOneShortestPath(arr1);
		System.out.println("if arr of Points on optimal Path ??:"+allpoint);

		boolean allpoint1 = p.ArrayPointsOnTheOneShortestPath(arr1);
		System.out.println(allpoint1);

		System.out.println("First "+p.BestPrice());
		System.out.print("SECOND ");p.SecondBestPrice();
		p.secondBestAllPathes();
		p.AllPathesRecursion();
		p.SecondNumOfPathes();
		p.AllPathesSecondsRecursion();
	}
}

