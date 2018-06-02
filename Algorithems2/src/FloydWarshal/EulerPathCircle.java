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
public class EulerPathCircle {

    public static void main(String[] args) {
        int mat[][] = {{0, 1, 1, 1}, {1, 0, 0, 1}, {1, 0, 0, 1}, {1, 1, 1, 0}};
        EulerPath(mat);
    }

    public static void EulerPath(int[][] mat) {
        int deg[] = new int[mat.length];
        int start = 0;
        int numOfOdd = 0;

        for (int i = 0; i < deg.length; i++) {
            int counter = 0;
            for (int j = 0; j < mat.length; j++) {
                if (mat[i][j] == 1) {
                    counter++;
                }
            }
            if (counter % 2 == 1) {
                numOfOdd++;
                start = i;
            }

            deg[i] = counter;
        }

        String ans = "" + start;
        boolean flag = true;

        if (numOfOdd == 2 || numOfOdd == 0) {
            while (flag) {
                flag = false;
                for (int i = 0; i < deg.length; i++) {
                    if (mat[start][i] == 1) {
                        mat[start][i] = 0;
                        mat[i][start] = 0;
                        ans = i + "->" + ans;
                        start = i;
                        flag = true;
                        break;
                    }
                }
            }
        }
        System.out.println(ans);
    }

}
