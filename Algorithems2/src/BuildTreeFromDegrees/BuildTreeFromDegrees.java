package BuildTreeFromDegrees;

import java.util.ArrayList;
import java.util.Arrays;

public class BuildTreeFromDegrees {

    public static ArrayList<Integer>[] initTreeFromDegrees(int degrees[]) {
        Arrays.sort(degrees);
        reverseArray(degrees);
        int n = degrees.length;
        ArrayList<Integer>[] tree = new ArrayList[n];
        for (int i = 0; i < tree.length; i++) {
            tree[i] = new ArrayList<Integer>();
        }
        int vertex = 1;
        for (int i = 0; i < tree.length; i++) {
            for (int j = 0; j < degrees[i]; j++) {
                tree[i].add(vertex);
                tree[vertex].add(i);
                degrees[vertex]--;
                vertex++;
            }
            degrees[i] = 0;
        }
        return tree;
    }

    private static void reverseArray(int arr[]) {
        for (int i = 0; i < arr.length / 2; i++) {
            int temp = arr[i];
            arr[i] = arr[arr.length - i - 1];
            arr[arr.length - i - 1] = temp;
        }
    }

    public static void main(String[] args) {
        int[] degrees = {4, 4, 4, 2,2,2};
        ArrayList<Integer>[] tree = initTreeFromDegrees(degrees);
        System.out.println(Arrays.toString(tree));
        System.out.println(Arrays.toString(tree));
    }

}
