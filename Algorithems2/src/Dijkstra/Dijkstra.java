package Dijkstra;

import java.util.Arrays;
import java.util.PriorityQueue;

class Node {

    int weight;
    int id;

    public Node(int id, int w) {
        this.weight = w;
        this.id = id;
    }

    public Node(Node n) {
        this.weight = n.weight;
        this.id = n.id;
    }
}

class Vertex implements Comparable<Vertex> {

    boolean isVisited;
    int parent;
    double distance;
    Node[] ngbors;
    int id;

    public Vertex(Node[] ngbors, int id) {
        this.ngbors = new Node[ngbors.length];
        for (int i = 0; i < ngbors.length; i++) {
            this.ngbors[i] = new Node(ngbors[i]);
        }
        this.id = id;
        this.isVisited = false;
        this.parent = -1;
        this.distance = Double.POSITIVE_INFINITY;
    }

    public Vertex(Vertex v) {
        this.isVisited = v.isVisited;
        this.id = v.id;
        this.distance = v.distance;
        this.parent = v.parent;
        this.ngbors = new Node[v.ngbors.length];
        for (int i = 0; i < v.ngbors.length; i++) {
            this.ngbors[i] = new Node(v.ngbors[i]);
        }

    }

    @Override
    public int compareTo(Vertex t) {
        return ((Double) (this.distance)).compareTo(t.distance);
    }

}

public class Dijkstra {

    Vertex[] graph;
    int start;
    PriorityQueue<Vertex> q = new PriorityQueue<>();
    int numPath[];

    public Dijkstra(Vertex[] g, int start) {
        this.graph = new Vertex[g.length];
        numPath = new int[graph.length];
        for (int i = 0; i < graph.length; i++) {
            this.graph[i] = new Vertex(g[i]);
        }
        this.start = start;
        q.add(graph[start]);
        graph[start].distance = 0;
        numPath[start] = 1;

        while (!q.isEmpty()) {
            Vertex v = q.poll();
            v.isVisited = true;
            //pass of all neighboor V 
            for (Node nb : v.ngbors) {
                if (!graph[nb.id].isVisited) {
                    //אם המרחק שרשום בשכן יותר גדול מ
                    //מהמרחק שעולה להגיע עד אליי פלוס המשקל שלי ממני אליו
                    if (v.distance + nb.weight < graph[nb.id].distance) {
                        //כל זה בשביל המיון בתוך התור
                        q.remove(graph[nb.id]);
                        graph[nb.id].distance = v.distance + nb.weight;
                        graph[nb.id].parent = v.id;
                        q.add(graph[nb.id]);
                        numPath[nb.id] = numPath[v.id];
                    } 
                    //לא חייב אם לא מחשבים מספר מסלולים
                    else if (v.distance + nb.weight == graph[nb.id].distance) {
                        numPath[nb.id] += numPath[v.id];
                    }
                }
            }
        }
    }

    public String getPath(int v) {
        if (v < 0 || v >= graph.length) {
            return null;
        }
        String ans = v + "->";

        while (graph[v].parent != -1) {
            ans = graph[v].parent + "->" + ans;
            v = graph[v].parent;
        }
        if (v == start) {
            return ans;
        }

        return null;
    }

    public String AllDistance() {
        String ans = "[";
        for (int i = 0; i < graph.length; i++) {
            ans += graph[i].distance + (i != graph.length - 1 ? "," : "]");
        }
        return ans;
    }

    public int[] pathes() {
        return numPath;
    }

    
    
    
    
    public static void main(String[] args) {
        Vertex ver[] = new Vertex[12];
        ver[0] = new Vertex(new Node[]{new Node(1, 5), new Node(2, 4), new Node(8, 1)}, 0);
        ver[1] = new Vertex(new Node[]{new Node(0, 5), new Node(5, 3), new Node(3, 3)}, 1);
        ver[2] = new Vertex(new Node[]{new Node(0, 4), new Node(9, 1), new Node(3, 5)}, 2);
        ver[3] = new Vertex(new Node[]{new Node(1, 3), new Node(7, 5), new Node(2, 5)}, 3);
        ver[4] = new Vertex(new Node[]{new Node(5, 20), new Node(6, 20), new Node(11, 20)}, 4);
        ver[5] = new Vertex(new Node[]{new Node(1, 3), new Node(4, 20), new Node(6, 2), new Node(7, 5)}, 5);
        ver[6] = new Vertex(new Node[]{new Node(4, 20), new Node(11, 5), new Node(7, 50), new Node(5, 2)}, 6);
        ver[7] = new Vertex(new Node[]{new Node(3, 5), new Node(5, 5), new Node(6, 50), new Node(11, 2)}, 7);
        ver[8] = new Vertex(new Node[]{new Node(0, 1), new Node(9, 1)}, 8);
        ver[9] = new Vertex(new Node[]{new Node(8, 1), new Node(10, 0), new Node(2, 1)}, 9);
        ver[10] = new Vertex(new Node[]{new Node(9, 0), new Node(11, 20)}, 10);
        ver[11] = new Vertex(new Node[]{new Node(4, 20), new Node(10, 20), new Node(6, 5), new Node(7, 2)}, 11);
        Dijkstra d = new Dijkstra(ver, 0);
        System.out.println(d.getPath(11));
        System.out.println(d.AllDistance());
        System.out.println(Arrays.toString(d.pathes()));
    }
}
