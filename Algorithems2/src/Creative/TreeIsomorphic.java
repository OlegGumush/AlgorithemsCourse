/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Creative;

import Fire.FireAlgo;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author Oleg
 */
public class TreeIsomorphic {

    public static void main(String[] args) {
        ArrayList<Integer>[] tree = new ArrayList[7];
        for (int i = 0; i < tree.length; i++) {
            tree[i] = new ArrayList<>();
        }
        tree[0].add(1);
        tree[0].add(2);

        tree[1].add(0);
        tree[1].add(3);
        tree[1].add(4);

        tree[2].add(5);
        tree[2].add(0);
        tree[2].add(6);

        tree[3].add(1);
        tree[4].add(1);
        tree[5].add(2);
        tree[6].add(2);

        ArrayList<Integer>[] tree1 = new ArrayList[7];
        for (int i = 0; i < tree1.length; i++) {
            tree1[i] = new ArrayList<>();
        }
        tree1[0].add(1);
        tree1[0].add(2);

        tree1[2].add(0);
        tree1[2].add(3);
        tree1[2].add(4);

        tree1[1].add(5);
        tree1[1].add(0);
        tree1[1].add(6);

        tree1[3].add(2);
        tree1[4].add(1);
        tree1[5].add(1);
        tree1[6].add(1);

        System.out.println(ifIsomorphic(tree, tree1));
    }

    public static boolean ifIsomorphic(ArrayList<Integer>[] tree1, ArrayList<Integer>[] tree) {
        int deg[] = new int[tree.length];
        int deg1[] = new int[tree1.length];

        //build degree array
        for (int i = 0; i < tree.length; i++) {
            deg[i] = tree[i].size();
        }
        for (int i = 0; i < tree1.length; i++) {
            deg1[i] = tree1[i].size();
        }
        //check num of vertex equals
        if (tree.length != tree1.length) {
            return false;
        }
        //if all degrees equals
        Arrays.sort(deg);
        Arrays.sort(deg1);

        //check if all degree equals
        for (int i = 0; i < deg.length; i++) {
            if (deg[i] != deg1[i]) {
                return false;
            }
        }
        //build fire for 2 graphs
        FireAlgo f = new FireAlgo(tree);
        FireAlgo f1 = new FireAlgo(tree1);

        //check if number of centers equals
        ArrayList<Integer> c1 = f.getCenters();
        ArrayList<Integer> c2 = f1.getCenters();
        if (c1.size() != c2.size()) {
            return false;
        }
        //check if diameter equals
        int d1 = f.getDiameter();
        int d2 = f1.getDiameter();
        if (d1 != d2) {
            return false;
        }
        //check if radius equals
        int r1 = f.getRadius();
        int r2 = f1.getRadius();
        if (r1 != r2) {
            return false;
        }
        //check both trees
        boolean ans = checkiftree(deg);
        boolean ans1 = checkiftree(deg1);

        if (ans == false || ans1 == false) {
            return false;
        }
        return true;
    }

    private static boolean checkiftree(int[] tree) {
        //sum degrees
        int sum = 0;
        for (int i = 0; i < tree.length; i++) {
            if (tree[i] == 0) {
                return false;
            }
            sum += tree[i];
        }
        //סכום הדרגות צריך להיות שווה לפעמיים הצלעות
        if (sum != 2 * (tree.length - 1)) {
            return false;
        }

        Arrays.sort(tree);
        reverse(tree);

        for (int i = 0; i < tree.length; i++) {
            if (tree[0] != 0) {
                //שלא נחסיר יותר מדי
                if (tree[0] > tree.length - 1 - i) {
                    return false;
                }
                for (int j = 1; j <= tree[0]; j++) {
                    if (tree[j] == 0) {
                        return false;
                    }
                    tree[j]--;
                }
                tree[0] = 0;
            }
            Arrays.sort(tree);
            reverse(tree);
        }
        return true;
    }

    private static void reverse(int[] arr) {
        for (int i = 0; i < arr.length / 2; i++) {
            int temp = arr[i];
            arr[i] = arr[arr.length - 1 - i];
            arr[arr.length - 1 - i] = temp;
        }
    }
}
