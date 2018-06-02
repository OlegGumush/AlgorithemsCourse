package Best;

import static Best.Best_Array.bestCycle;
import java.util.Arrays;

public class Circle_Matrix {

    public static int[] maxSumSubMatrixRows(int[][] mat) {
        int sum = 0, sumMax = 0;
        int iStart = 0, iEnd = 0, jStart = 0, jEnd = 0;
        int[] vec = new int[mat[0].length];
        int[][] help = new int[mat.length][mat[0].length];

        //build help matrix  + sum rows
        for (int i = 0; i < mat.length; i++) {// prepare help matrix
            for (int k = 0; k < mat[0].length; k++) {
                if (i == 0) {
                    help[i][k] = mat[i][k];
                } else {
                    help[i][k] = help[i - 1][k] + mat[i][k];
                }
            }
        }

        for (int i = 0; i < mat.length; i++) {
            for (int j = i; j < mat.length; j++) {
                //מעתיק שורה של מטריצה
                for (int k = 0; k < mat[0].length; k++) {
                    vec[k] = help[j][k];
                }
                int res[] = bestCycle(vec);
                sum = res[0];
                //System.out.println(Arrays.toString(res));
                if (sum > sumMax) {
                    sumMax = sum;
                    jStart = res[1];
                    jEnd = res[2];

                    iStart = i;
                    iEnd = j;
                }

            }
            for (int n = i + 1; n < mat.length; n++) {
                for (int k = 0; k < mat[0].length; k++) {
                    help[n][k] -= help[i][k];
                }
            }
        }
        //print results
        System.out.println("jStart=" + jStart + ", iStart=" + iStart + ", jEnd=" + jEnd + ", iEnd=" + iEnd);
        System.out.println("sum max = " + sumMax);
        return new int[]{sumMax, iStart, iEnd, jStart, jEnd};
    }

    public static int[] maxSumSubMatrixCols(int[][] mat) {
        int sum = 0, sumMax = 0;
        int iStart = 0, iEnd = 0, jStart = 0, jEnd = 0;
        int[] vec = new int[mat.length];
        int[][] help = new int[mat.length][mat[0].length];

        //build help matrix  + sum rows
        for (int i = 0; i < mat[0].length; i++) {// prepare help matrix
            for (int k = 0; k < mat.length; k++) {
                if (i == 0) {
                    help[k][i] = mat[k][i];
                } else {
                    help[k][i] = help[k][i - 1] + mat[k][i];
                }
            }
        }

        for (int i = 0; i < mat[0].length; i++) {
            for (int j = i; j < mat[0].length; j++) {
                //מעתיק שורה של מטריצה
                for (int k = 0; k < mat.length; k++) {
                    vec[k] = help[k][j];
                }
                int res[] = bestCycle(vec);
                sum = res[0];
                //System.out.println(Arrays.toString(vec));
                if (sum > sumMax) {
                    sumMax = sum;
                    iStart = res[1];
                    iEnd = res[2];
                    jStart = i;
                    jEnd = j;
                }

            }
            for (int n = i + 1; n < mat[0].length; n++) {
                for (int k = 0; k < mat.length; k++) {
                    help[k][n] -= help[k][i];
                }
            }
        }
        //print results
        System.out.println("jStart=" + jStart + ", iStart=" + iStart + ", jEnd=" + jEnd + ", iEnd=" + iEnd);
        System.out.println("sum max = " + sumMax);
        return new int[]{sumMax, iStart, iEnd, jStart, jEnd};
    }

    public static int[] maxSumSubMatrix(int[][] mat) {
        int ans1[] = maxSumSubMatrixCols(mat);
        int ans2[] = maxSumSubMatrixRows(mat);

        if (ans1[0] > ans2[0]) {
            return ans1;
        }
        return ans2;
    }

    public static void main(String[] args) {

        int[][] mat1
                = {{1, -2, 3},
                {-4, -500,- 6},
                {-4, -500, -6},
                {7, -8, 9}};
        System.out.println(Arrays.toString(maxSumSubMatrix(mat1)));
    }

}
