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
public class Shortest_Path_With_Weight_On_Verticals_AND_Edge {

    public static void main(String[] args) {
        double mat[][] = {{0, 1, 4, Double.MAX_VALUE},
                          {1, 0, Double.MAX_VALUE, 2},
                          {4, Double.MAX_VALUE, 0, 3},
                          {Double.MAX_VALUE, 2, 3, 0}};
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[0].length; j++) {
                if (mat[i][j] >= Double.MAX_VALUE) {
                    System.out.print("∞ ");
                } else {
                    System.out.print((int) mat[i][j] + " ");
                }
            }
            System.out.println("");
        }
        double arr[] = {1, 5, 8, 10};
        Shortest_Path(mat, arr);

    }

    public static void Shortest_Path(double[][] mat, double arr[]) {
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat.length; j++) {
                if (mat[i][j] != Double.MAX_VALUE) 
                //mat[i][j]  -->The cost of an edge*2  
                //Because I go further divide by 2
                //arr[i]+arr[j] The amount of cost vertices
                {
                    mat[i][j] = mat[i][j] * 2 + arr[i] + arr[j];
                }
            }
        }
        System.out.println("");
        System.out.println(Arrays.toString(arr));
        System.out.println("");
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[0].length; j++) {
                if (mat[i][j] >= Double.MAX_VALUE) {
                    System.out.print("∞");
                } else {
                    System.out.print((int) mat[i][j] + " ");
                }
            }
            System.out.println("");
        }
        //Floyed Varshel
        for (int k = 0; k < mat.length; k++) {
            for (int i = 0; i < mat.length; i++) {
                for (int j = 0; j < mat.length; j++) {
                    mat[i][j] = Math.min(mat[i][j], mat[i][k] + mat[k][j]);
                }
            }
        }
        //take off the Intermediary(מתווך) we'd counted twice 
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat.length; j++) {
                mat[i][j] = (mat[i][j] + arr[i] + arr[j]) / 2;
            }
        }
        System.out.println("");
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[0].length; j++) {
                if (mat[i][j] >= Double.MAX_VALUE) {
                    System.out.print("∞ ");
                } else if (i != j) {
                    System.out.print((int) mat[i][j] + "  ");
                } else {
                    System.out.print((int)( mat[i][j]/2) + "  ");
                }
            }
            System.out.println("");
        }
    }
}
