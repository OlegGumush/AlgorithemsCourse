/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FloydWarshal;

import java.util.Arrays;

/**
 *
 * @author Oleg
 */
public class Shortest_Path_With_Weight_On_Edge {

    public static void main(String[] args) {
        double mat[][] = {{0, 3, Double.MAX_VALUE, 16,Double.MAX_VALUE},
                          {3, 0, 5, Double.MAX_VALUE,Double.MAX_VALUE},
                          {Double.MAX_VALUE,5,0, 4, 1},
                          {16, Double.MAX_VALUE, 4, 0,3},
                          {Double.MAX_VALUE, Double.MAX_VALUE,1, 3, 0}};

        Edge(mat);
        ifGraphHasNegativeCircle(mat);
    }

    //short path if Graph dont has negative circle
    public static void shortestPath(double[][] mat) {
        for (int k = 0; k < mat.length; k++) {
            for (int i = 0; i < mat.length; i++) {
                for (int j = 0; j < mat.length; j++) {
                    mat[i][j] = Math.min(mat[i][j], mat[i][k] + mat[k][j]);
                }
            }
        }
    }

    public static void ifGraphHasNegativeCircle(double[][] mat) {
        //If the graph is not directed, how to know if there is a negative circle .Enough to find a negative side
        boolean flag1 = false;
        for (int i = 0; i < mat.length && !flag1; i++) {
            for (int j = 0; j < mat.length && !flag1; j++) {
                if (mat[i][j] < 0) {
                    System.out.println("Not Directed , Graph has  a negative circle");
                    flag1 = true;
                }
            }
        }
        //If the graph is directed, how to know if there is a negative circle .Enough to find a negative side in diagonal in Floid Varshal matrix.
        //How to check if directed - we check if simetric.
        boolean flag = false;
        for (int i = 0; i < mat.length && !flag; i++) {
            for (int j = 0; j < mat.length && !flag; j++) {
                if (mat[i][j] != mat[j][i]) {
                    flag = true;
                }
            }
        }
        //Floid Varshal.
        for (int k = 0; k < mat.length; k++) {
            for (int i = 0; i < mat.length; i++) {
                for (int j = 0; j < mat.length; j++) {
                    mat[i][j] = Math.min(mat[i][j], mat[i][k] + mat[k][j]);
                }
            }
        }
        //Check diagonal.
        flag = false;
        for (int i = 0; i < mat.length && !flag; i++) {
            if (mat[i][i] < 0) {
                System.out.println( "Directed Graph , Graph has  a negative circle");
                flag = true;
            }
        }
    }

    public static void Edge(double mat[][]) {
        int n = mat.length;
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if(mat[i][j] > mat[i][k] + mat[k][j]){
                        mat[i][j] = mat[i][k] + mat[k][j];
                    }
                }
            }
        }
        
        for (int i = 0; i < mat.length; i++) {
            System.out.println(Arrays.toString(mat[i]));
        }
    }

}
