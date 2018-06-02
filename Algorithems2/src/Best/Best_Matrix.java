package Best;

import static Best.Best_Array.best;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;

public class Best_Matrix {

    ///////////////////////////////////////////////////////////////////////////////////////
    //Best Matrix Whole Search - O((m*n)^3)                ////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////
    public static int[] bestMatrixWholeSearch(int[][] mat) {
        int max = Integer.MIN_VALUE;
        int si_index = -1, ei_index = -1, sj_index = -1, ej_index = -1;

        for (int iStart = 0; iStart < mat.length; iStart++) {
            for (int jStart = 0; jStart < mat[0].length; jStart++) {
                for (int iEnd = iStart; iEnd < mat.length; iEnd++) {
                    for (int jEnd = jStart; jEnd < mat[0].length; jEnd++) {

                        int sum = 0;
                        for (int i = iStart; i <= iEnd; i++) {
                            for (int j = jStart; j <= jEnd; j++) {
                                sum += mat[i][j];

                            }
                        }
                        if (sum > max) {
                            max = sum;
                            si_index = iStart;
                            ei_index = iEnd;
                            sj_index = jStart;
                            ej_index = jEnd;
                        }

                    }
                }
            }
        }
        return new int[]{max, si_index, sj_index, ei_index, ej_index};
    }

    ///////////////////////////////////////////////////////////////////////////////////////
    //Best Matrix Whole Search - O((m*n)^3) - Window //////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////
    public static int[] bestMatrixWindow(int[][] mat) {
        int max = Integer.MIN_VALUE;
        int si_index = -1, ei_index = -1, sj_index = -1, ej_index = -1;
        ArrayList<Mivne> a = new ArrayList<>();

        for (int iStart = 0; iStart < mat.length; iStart++) {
            for (int jStart = 0; jStart < mat[0].length; jStart++) {
                for (int iEnd = iStart; iEnd < mat.length; iEnd++) {
                    for (int jEnd = jStart; jEnd < mat[0].length; jEnd++) {
                        int temp[][] = new int[iEnd - iStart + 1][jEnd - jStart + 1];
                        int n = 0;
                        int m = 0;
                        for (int i = iStart; i <= iEnd; i++) {
                            for (int j = jStart; j <= jEnd; j++) {
                                temp[n][m++] = mat[i][j];
                            }
                            n++;
                            m = 0;
                        }
                        a.add(new Mivne(temp, iStart, iEnd, jStart, jEnd));
                    }
                }
            }
        }
        int sum = 0;
        Mivne ans = null;

        for (int i = 0; i < a.size(); i++) {
            for (int j = 0; j < a.get(i).mat.length; j++) {
                for (int k = 0; k < a.get(i).mat[0].length; k++) {
                    if (j == 0 || j == a.get(i).mat.length - 1) {
                        sum += a.get(i).mat[j][k];
                    } else if (k == 0 || k == a.get(i).mat[0].length - 1) {
                        sum += a.get(i).mat[j][k];
                    }
                }
            }
            if (sum > max) {
                max = sum;
                ans = a.get(i);
            }
            sum = 0;
        }
        return new int[]{max, ans.si_index, ans.ei_index, ans.sj_index, ans.ej_index};
    }

    ///////////////////////////////////////////////////////////////////////////////////////
    //Best Matrix - Rows - O(n^2*m) with help (base) matrix  ////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////
    public static int[] maxSumSubMatrixRows(int[][] mat) {
        int sum = 0, sumMax = 0;
        int iStart = 0, iEnd = 0, jStart = 0, jEnd = 0;
        int[] vec = new int[mat[0].length];
        int[][] help = new int[mat.length][mat[0].length];

        for (int i = 0; i < mat.length; i++) {
            for (int k = 0; k < mat[0].length; k++) {
                if (i == 0) {
                    help[i][k] = mat[i][k];
                } else {
                    help[i][k] = help[i - 1][k] + mat[i][k];
                }
            }
        }

        //כמה פעמים רצים
        for (int i = 0; i < mat.length; i++) {
            //איזו שורה סוכמים
            for (int j = i; j < mat.length; j++) {
                //מעתיק שורה של מטריצה
                for (int k = 0; k < mat[0].length; k++) {
                    vec[k] = help[j][k];
                }
                int res[] = best(vec);
                sum = res[0];
                //System.out.println(Arrays.toString(vec));
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
                    //i !!!!
                    help[n][k] -= help[i][k];
                }
            }
        }
        //print results
        System.out.println("jStart=" + jStart + ", iStart=" + iStart + ", jEnd=" + iEnd + ", iEnd=" + jEnd);
        System.out.println("sum max = " + sumMax);
        for (int i = iStart; i <= iEnd; i++) {
            for (int j = jStart; j <= jEnd; j++) {
                System.out.print(mat[i][j] + " ");
            }
            System.out.println();
        }
        return new int[]{sumMax, iStart, jStart, iEnd, jEnd};
    }

    ///////////////////////////////////////////////////////////////////////////////////////
    //Best Matrix - Cols - O(n^3) with help (base) matrix  ////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////
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
                int res[] = best(vec);
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
        System.out.println("jStart=" + jStart + ", iStart=" + iStart + ", jEnd=" + iEnd + ", iEnd=" + jEnd);
        System.out.println("sum max = " + sumMax);
        for (int i = iStart; i <= iEnd; i++) {
            for (int j = jStart; j <= jEnd; j++) {
                System.out.print(mat[i][j] + " ");
            }
            System.out.println();
        }
        return new int[]{sumMax, iStart, jStart, iEnd, jEnd};
    }

    ///////////////////////////////////////////////////////////////////////////////////////
    //Best Matrix - Rows - Black Points arr[]              ////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////
    public static int[] maxSumSubMatrixRowsBlackPoints(int[][] mat, Point p[]) {
        int sum = 0, sumMax = 0;
        int iStart = 0, iEnd = 0, jStart = 0, jEnd = 0;
        int[] vec = new int[mat[0].length];
        int[][] help = new int[mat.length][mat[0].length];

        for (int i = 0; i < p.length; i++) {
            if (p[i].x < mat[0].length && p[i].y < mat.length) {
                mat[p[i].y][p[i].x] = -100000;
            }
        }
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[0].length; j++) {
                System.out.print(mat[i][j] + " ");
            }
            System.out.println();
        }
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
                int res[] = best(vec);
                sum = res[0];
                //System.out.println(Arrays.toString(vec));
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
        System.out.println("jStart=" + jStart + ", iStart=" + iStart + ", jEnd=" + iEnd + ", iEnd=" + jEnd);
        System.out.println("sum max = " + sumMax);
        for (int i = iStart; i <= iEnd; i++) {
            for (int j = jStart; j <= jEnd; j++) {
                System.out.print(mat[i][j] + " ");
            }
            System.out.println();
        }
        return new int[]{sumMax, iStart, jStart, iEnd, jEnd};
    }

    public static void main(String[] args) {
        int[][] mat = {
            {1, 5, -124, 1, 5},
            {-128, -122, -124, -129, -122},
            {-8, -2, -121, -341, 5},
            {1, 1, 2, 1, 5}, {1, 1, 2, 1, 5}, {1, 1, 2, 1, 5}
        };
        int[][] mat1 = {{-2, 2, 3, 1, -9},
        {-2, -5, 3, -1, -9},
        {-2, 21, 3, 4, -1}};
        //System.out.println(Arrays.toString((mat1, new Point[]{new Point(1, 1), new Point(1, 0), new Point(0, 2)})));
        //System.out.println(Arrays.toString(bestMatrixWindow(mat)));
        //ArrayList<int[]> a = AllBests(mat);
        //for (int i = 0; i < a.size(); i++) {
        System.out.println(Arrays.toString(bestMatrixWindow(mat1)));
        //}
    }
}
