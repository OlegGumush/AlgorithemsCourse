package Kraskal_Reverse;

import java.util.ArrayList;
import java.util.Arrays;

class Edge implements Comparable<Edge> {

    int v1, v2, w;

    public Edge(int v1, int v2, int w) {
        this.v1 = v1;
        this.v2 = v2;
        this.w = w;
    }

    public Edge(Edge e) {
        v1 = e.v1;
        v2 = e.v2;
        w = e.w;
    }

    @Override
    public String toString() {
        return "[" + v1 + "," + v2 + "," + w + "]";
    }

    @Override
    public int compareTo(Edge e) {
        return ((Integer) (e.w)).compareTo(this.w);
    }

}

class Kruskal_Reverse {

    ArrayList<Integer> g[];
    Edge[] graph;
    int numOfV;
    Edge[] ans;

    Kruskal_Reverse(Edge[] graph) {
        this.graph = graph;
        numOfV = numOfV();
        ans = new Edge[numOfV - 1];
        g = new ArrayList[numOfV];
        for (int i = 0; i < numOfV; i++) {
            g[i] = new ArrayList<>();
        }
        Hamara();
        Kraskal();
    }

    private int numOfV() {
        int max = -1000000;
        for (int i = 0; i < graph.length; i++) {
            if (graph[i].v1 > max) {
                max = graph[i].v1;
            }
            if (graph[i].v2 > max) {
                max = graph[i].v2;
            }
        }
        return max + 1;
    }

    private void Hamara() {
        for (int i = 0; i < graph.length; i++) {
            Edge e = graph[i];
            g[e.v1].add(e.v2);
            g[e.v2].add(e.v1);
        }
    }

    private void Kraskal() {
        Arrays.sort(graph);
        int treeS = 0;

        for (int i = 0; i < graph.length && treeS < ans.length; i++) {
            Edge e = graph[i];
            g[e.v1].remove((Integer) e.v2);
            g[e.v2].remove((Integer) e.v1);
            //if not kashir
            if (!IfKashir(g)) {
                g[e.v1].add(e.v2);
                g[e.v2].add(e.v1);
                ans[treeS++] = e;
            }
        }
        System.out.println(Arrays.toString(ans));

    }

    private boolean IfKashir(ArrayList<Integer>[] g) {
        int kashir = 0;
        int color[] = new int[g.length];
        for (int i = 0; i < g.length; i++) {
            if (color[i] == 0) {
                kashir++;
                Dfs(i, color, g);
            }
        }
        if (kashir == 1) {
            return true;
        }
        return false;
    }

    private void Dfs(int i, int color[], ArrayList<Integer>[] g) {
        color[i] = 1;
        for (int nb : g[i]) {
            if (color[nb] == 0) {
                Dfs(nb, color, g);
            }
        }
        color[i] = 2;
    }

    public static void main(String[] args) {
        Edge arr[] = new Edge[8];
        arr[0] = new Edge(0, 1, 1);
        arr[6] = new Edge(0, 4, 1);
        arr[7] = new Edge(0, 2, 5);

        arr[1] = new Edge(1, 2, 2);
        arr[3] = new Edge(1, 4, 4);
        arr[5] = new Edge(1, 3, 7);

        arr[2] = new Edge(2, 3, 3);

        arr[4] = new Edge(4, 3, 1);

        Kruskal_Reverse kr = new Kruskal_Reverse(arr);
    }

}
