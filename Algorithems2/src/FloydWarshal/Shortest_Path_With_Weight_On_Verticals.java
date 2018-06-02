/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FloydWarshal;

/**
 *
 * @author Oleg
 */
public class Shortest_Path_With_Weight_On_Verticals {

    public static void main(String[] args) {
        double mat[][] = {{0, 1, 1, Double.MAX_VALUE, Double.MAX_VALUE},
        {1, 0, Double.MAX_VALUE, Double.MAX_VALUE, Double.MAX_VALUE},
        {1, Double.MAX_VALUE, 0, 1, 1},
        {Double.MAX_VALUE, Double.MAX_VALUE, 1, 0, Double.MAX_VALUE},
        {Double.MAX_VALUE, Double.MAX_VALUE, 1, Double.MAX_VALUE, 0}};
        double arr[] = {7, 4, 3, 6, 8};
        shortestPath(mat, arr);
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[0].length; j++) {
                System.out.print((int) mat[i][j] + " ");
            }
            System.out.println("");
        }
        System.out.println(get(mat, 0, 3));
    }

    public static double get(double mat[][], int i, int j) {
        return mat[i][j];
    }

    private static void shortestPath(double[][] mat, double arr[]) {
        //Will create a matrix of amounts
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat.length; j++) {
                if (mat[i][j] == 1) {
                    mat[i][j] = arr[i] + arr[j];
                }
            }
        }
        //Will create a matrix of pathes
        for (int k = 0; k < mat.length; k++) {
            for (int i = 0; i < mat.length; i++) {
                for (int j = 0; j < mat.length; j++) {
                    mat[i][j] = Math.min(mat[i][j], mat[i][k] + mat[k][j]);
                }
            }
        }

        //Deduct the Intermediary  we'd counted twice(מתווך)   
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat.length; j++) {
                mat[i][j] = (mat[i][j] + arr[i] + arr[j]) / 2;
            }
        }
    }

}
