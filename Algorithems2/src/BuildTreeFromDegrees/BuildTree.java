package BuildTreeFromDegrees;

import java.util.ArrayList;
import java.util.Arrays;

class Node implements Comparable<Node> {

    int id;
    int deg;

    public Node(int id, int deg) {
        this.id = id;
        this.deg = deg;
    }

    @Override
    public int compareTo(Node t) {
        return ((Integer) (t.deg)).compareTo(this.deg);
    }
}

public class BuildTree {

    public static ArrayList<Integer>[] initTreeFromDegrees(int degrees[]) {

        Node nodes[] = new Node[degrees.length];
        ArrayList<Integer> ans[] = new ArrayList[degrees.length];

        for (int i = 0; i < degrees.length; i++) {
            ans[i] = new ArrayList<>();
        }

        for (int i = 0; i < nodes.length; i++) {
            nodes[i] = new Node(i, degrees[i]);
        }

        Arrays.sort(nodes);

        for (int i = 0; i < nodes.length; i++) {
            if (nodes[0].deg > 0) {
                if (nodes[0].deg > nodes.length - 1 - i) {
                    return null;
                }
                for (int j = 1; j <= nodes[0].deg; j++) {
                    ans[nodes[0].id].add(nodes[j].id);
                    ans[nodes[j].id].add(nodes[0].id);
                    if (nodes[j].deg == 0) {
                        return null;
                    }
                    nodes[j].deg--;
                }
            }
            nodes[0].deg = 0;
            Arrays.sort(nodes);
        }

        return ans;
    }

    public static void main(String[] args) {
        int[] degrees = {4,4,4,2,2,2};
        ArrayList<Integer>[] tree = initTreeFromDegrees(degrees);
        System.out.println(Arrays.toString(tree));
    }

}
