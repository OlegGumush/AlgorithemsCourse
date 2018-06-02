package Kraskal;

import java.util.Arrays;

class DisjointSet {

    private int[] parent, rank;

    public DisjointSet(int n) {
        parent = new int[n];
        rank = new int[n];
        for (int i = 0; i < rank.length; i++) {
            parent[i] = i;
        }
    }

    public int find(int v) {
        while (v != parent[v]) {
            v = parent[v];
        }
        return v;
    }

    public boolean union(int v, int u) {
        int root_v = find(v);
        int root_u = find(u);
        if (root_v != root_u) {
            if (rank[root_v] > rank[root_u]) {
                parent[root_u] = root_v;
            } else if (rank[root_v] < rank[root_u]) {
                parent[root_v] = root_u;
            } else {
                parent[root_v] = root_u;
                rank[root_u]++;
            }
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return Arrays.toString(parent) + "\n" + Arrays.toString(rank);
    }
}

class Edge implements Comparable<Edge> {

    private int v1, v2, weight;

    public Edge(int v1, int v2, int weight) {
        this.v1 = v1;
        this.v2 = v2;
        this.weight = weight;
    }

    @Override
    public int compareTo(Edge e) {
        return ((Integer) (this.weight)).compareTo((Integer) (e.weight));
    }

    public int v1() {
        return v1;
    }

    public int v2() {
        return v2;
    }

    public int weight() {
        return weight;
    }

    @Override
    public String toString() {
        return "(" + v1 + "," + v2 + ", w = " + weight + ")";
    }
}

public class Kruskal {

    private Edge[] graph, sTree;
    private DisjointSet vgroup;
    private int vertices, treeSize;

    // O(|E|log|E|)
    public Kruskal(Edge[] g) {
        graph = copy(g);
        Arrays.sort(graph);
        numOfVertices();
        sTree = new Edge[vertices - 1];
        treeSize = 0;
        vgroup = new DisjointSet(vertices);
        makeMST();
    }

    private void makeMST() {
        int i = 0;
        while (i < graph.length && treeSize < vertices - 1) {
            if (vgroup.union(graph[i].v1(), graph[i].v2())) {
                sTree[treeSize++] = graph[i];
            }
            i++;
        }
    }

    private void numOfVertices() {
        vertices = -1;
        for (Edge e : graph) {
            vertices = Integer.max(Integer.max(e.v1(), e.v2()), vertices);
        }
        vertices++;
    }

    public static int getTree(Edge g[]) {
        int max = 0;
        for (int i = 0; i < g.length; i++) {
            if (g[i].v1() > max) {
                max = g[i].v1();
            }
            if (g[i].v2() > max) {
                max = g[i].v2();
            }
        }
        max++;

        if (max - 1 == g.length) {
            return max - 2 + 100;
        } else {
            return max - 1;
        }
    }

    private Edge[] copy(Edge[] g) {
        Edge[] temp = new Edge[g.length];
        for (int i = 0; i < g.length; i++) {
            temp[i] = new Edge(g[i].v1(), g[i].v2(), g[i].weight());
        }
        return temp;
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

        Kruskal k = new Kruskal(arr);
        k.makeMST();
        System.out.println(Arrays.toString(k.sTree));

    }
}
