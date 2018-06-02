package Bottle;

import java.util.Arrays;

public class BottleProblem {

    private int inf = Integer.MAX_VALUE;
    private int n, m, size;
    private int[][] mat;
    private String[][] paths;
    int arr[];

    public BottleProblem(int n, int m) {
        this.n = n;
        this.m = m;
        this.size = (n + 1) * (m + 1);
        arr = new int[size];
        buildMatrix();
        bestPath();
    }

    private void buildMatrix() {
        int arr[] = new int[size];
        mat = new int[size][size];
        paths = new String[size][size];
        for (int i = 0; i < size; i++) {
            Arrays.fill(mat[i], inf);
            Arrays.fill(paths[i], "");
        }
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= m; j++) {
                setPath(i, j, 0, j);
                setPath(i, j, i, 0);
                setPath(i, j, n, j);
                setPath(i, j, i, m);
                setPath(i, j, i + j - Math.min(m, i + j), Math.min(m, i + j));
                setPath(i, j, Math.min(n, i + j), i + j - Math.min(n, i + j));
            }
        }

    }

    private void setPath(int i1, int j1, int i2, int j2) {
        if (i1 != i2 || j1 != j2) {
            mat[index(i1, j1)][index(i2, j2)] = 1;
            arr[index(i2, j2)]++;
            arr[index(i1, j1)]++;
            System.out.println(Arrays.toString(arr));
            paths[index(i1, j1)][index(i2, j2)] = "->(" + i2 + "," + j2 + ")";
        }
    }

    private int index(int i, int j) {
        return i * (m + 1) + j;
    }

    private void bestPath() {
        for (int k = 0; k < size; k++) {
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    if (mat[i][k] != inf && mat[k][j] != inf && mat[i][k] + mat[k][j] < mat[i][j]) {
                        mat[i][j] = mat[i][k] + mat[k][j];
                        paths[i][j] = paths[i][k] + paths[k][j];
                    }
                }
            }
        }
    }

    public String getPath(int x, int y) {
        if (mat[index(0, 0)][index(x, y)] == inf) {
            return "no path!";
        } else {
            System.out.println(mat[index(0, 0)][index(x, y)]);
        }

        return "(0,0)" + paths[index(0, 0)][index(x, y)];
    }

    public void printMat() {
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat.length; j++) {
                System.out.print((mat[i][j] == Integer.MAX_VALUE ? "∞" : mat[i][j]) + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        BottleProblem b = new BottleProblem(2, 1);
    }
}
