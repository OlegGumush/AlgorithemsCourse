package Prim;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

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

public class Prim {

    private final int WHITE = 0;
    private final int GRAY = 1;
    private final int BLACK = 2;
    private final int infinity = Integer.MAX_VALUE;

    private ArrayList<Node>[] graph;
    private PriorityQueue<Node> queue;
    private Edge[] sTree;
    private int[] parent, color, weight;
    private int n;
    int root;

    public Prim(ArrayList<Node>[] g, int root) {
        this.root = root;
        graph = copy(g);
        n = g.length;
        sTree = new Edge[n - 1];
        parent = new int[n];
        color = new int[n];
        weight = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = -1;
            color[i] = WHITE;
            weight[i] = infinity;
        }
        queue = new PriorityQueue<Node>();
        findMST();
        makeMST();
    }

//bfs like
    private void findMST() {
        queue.add(new Node(root, 0));
        color[root] = GRAY;
        weight[root] = 0;
        while (!queue.isEmpty()) {
            Node v = queue.poll();
            for (Node nv : graph[v.id()]) {
                //עם לבן נסמן תמשקל של השכן כשמה שכתוב אצלו בנוד
                //כל השאר bfs רגיל
                if (color[nv.id()] == WHITE) {
                    color[nv.id()] = GRAY;
                    parent[nv.id()] = v.id();
                    weight[nv.id()] = nv.weigth();
                    queue.add(new Node(nv.id(), nv.weigth()));
                } //אם הוא אפשר אבל המשקל שכתוב אצלו במערך יותר גדול מהמשקל בינינו כלומר בנוד אז נעדכן משקל ונחליף אבא
                else if (color[nv.id()] == GRAY) {
                    if (weight[nv.id()] > nv.weigth()) {
                        queue.remove(nv);
                        weight[nv.id()] = nv.weigth();
                        parent[nv.id()] = v.id();
                        queue.add(nv);
                    }
                }
            }
            color[v.id()] = BLACK;
        }
    }

    private void makeMST() {
        int sizeOfTree = 0;
        for (int i = 0; i < graph.length && sizeOfTree < sTree.length; ++i) {
            if (parent[i] != -1) {
                sTree[sizeOfTree++] = new Edge(i, parent[i], weight[i]);
            }
        }
    }

    public Edge[] getTree() {
        return sTree;
    }

    private ArrayList<Node>[] copy(ArrayList<Node>[] g) {
        @SuppressWarnings("unchecked")
        ArrayList<Node>[] copy = new ArrayList[g.length];
        for (int i = 0; i < g.length; i++) {
            copy[i] = new ArrayList<Node>();
            for (int j = 0; j < g[i].size(); j++) {
                copy[i].add(new Node(g[i].get(j)));
            }
        }
        return copy;
    }
}

class Node implements Comparable<Node> {

    private int id, weight;

    public Node(int id, int weight) {
        this.id = id;
        this.weight = weight;
    }

    public Node(Node n) {
        this.id = n.id;
        this.weight = n.weight;
    }

    public void setWeight(int value) {
        weight = value;
    }

    public int weigth() {
        return weight;
    }

    public int id() {
        return id;
    }

    @Override
    public int compareTo(Node n) {
        return ((Integer) (this.weight)).compareTo((n.weight));
    }
}

class Main {

    public static void main(String[] args) {
        int numOfVertexes = 5;
        ArrayList<Node>[] graph = new ArrayList[numOfVertexes];
        for (int i = 0; i < numOfVertexes; i++) {
            graph[i] = new ArrayList<Node>();
        }
        graph[0].add(new Node(1, 1));
        graph[0].add(new Node(2, 1));
        graph[0].add(new Node(3, 1));
        graph[0].add(new Node(4, 1));
        graph[1].add(new Node(0, 1));
        graph[1].add(new Node(2, 1));
        graph[1].add(new Node(3, 1));
        graph[2].add(new Node(0, 1));
        graph[2].add(new Node(1, 1));
        graph[2].add(new Node(4, 1));
        graph[3].add(new Node(0, 1));
        graph[3].add(new Node(1, 1));
        graph[3].add(new Node(4, 1));
        graph[4].add(new Node(0, 1));
        graph[4].add(new Node(2, 1));
        graph[4].add(new Node(3, 1));
        Prim p = new Prim(graph, 0);
        System.out.println(Arrays.toString(p.getTree()));
    }
}
