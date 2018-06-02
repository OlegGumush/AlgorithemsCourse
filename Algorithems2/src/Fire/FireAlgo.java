package Fire;

import java.util.ArrayList;

/**
 * Find the center of tree graph (connected without cycles) and the radius
 * Complexity: O(|V|) , (|E|=|V|-1)
 */
public class FireAlgo {

    private int center1, center2;
    private int radius;
    private ArrayList<Integer>[] tree;

    public FireAlgo(ArrayList<Integer>[] g) {
        tree = g;
        center1 = center2 = -1;
        radius = -1;
        fire();
    }

    private void fire() {
        radius = 0;
        //n ==1 || n==2 stop 
        int n = tree.length;
        //leaves arr
        ArrayList<Integer> leaves = new ArrayList<Integer>();
        //degrees arr
        int[] deg = new int[n];

        for (int i = 0; i < n; i++) {
            deg[i] = tree[i].size();
            if (deg[i] == 1) {
                leaves.add(i);
            }
        }

        while (n > 2) {
            radius++;
            int numberOfLeaves = leaves.size();
            for (int i = 0; i < numberOfLeaves; i++) {
                //take leave
                int index_of_leave = leaves.remove(0);
                //delete leave
                deg[index_of_leave] = 0;
                n--;
                //run all neghboor of leave
                for (int neg_index : tree[index_of_leave]) {
                    deg[neg_index]--;
                    if (deg[neg_index] == 1) {
                        leaves.add(neg_index);
                    }
                }
            }
        }

        if (leaves.size() > 1) {
            center1 = leaves.remove(0);
            center2 = leaves.remove(0);
            radius++;
        } else {
            center1 = center2 = leaves.remove(0);
        }
    }

    public int getRadius() {
        return radius;
    }

    public int getDiameter() {
        if (center1 == center2) {
            return radius * 2;
        }
        return (radius * 2) - 1;
    }

    public ArrayList<Integer> getCenters() {
        ArrayList<Integer> a = new ArrayList<>();
        a.add(center1);
        a.add(center2);
        return a;
    }

    public static void main(String[] args) {
        int n = 7;
        ArrayList<Integer> graph[] = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        graph[0].add(1);
        graph[0].add(2);
        graph[0].add(3);

        graph[1].add(0);
        graph[1].add(6);

        graph[2].add(0);

        graph[3].add(4);
        graph[3].add(0);

        graph[4].add(3);
        graph[4].add(5);

        graph[5].add(4);

        graph[6].add(1);

        FireAlgo fire = new FireAlgo(graph);
        System.out.println(fire.getRadius());
    }
}
