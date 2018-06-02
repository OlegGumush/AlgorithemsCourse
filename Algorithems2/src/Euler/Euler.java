package Euler;

import java.util.ArrayList;
import java.util.Stack;

public class Euler {

    private ArrayList<Integer>[] graph;
    private int[] deg;
    private boolean isPath, isCycle;
    private int v_start;

    public Euler(ArrayList<Integer>[] g) {
        graph = new ArrayList[g.length];
        for (int i = 0; i < g.length; i++) {
            graph[i] = new ArrayList<Integer>();
            for (int j = 0; j < g[i].size(); j++) {
                graph[i].add(g[i].get(j));
            }
        }
        deg = new int[graph.length];
        isPath = isCycle = false;
        v_start = 0;
        int numOfOddVertexes = 0;
        for (int i = 0; i < graph.length; i++) {
            deg[i] = graph[i].size();
            if (deg[i] % 2 == 1) {
                numOfOddVertexes++;
                v_start = i;
            }
        }
        if (numOfOddVertexes == 0) {
            isPath = isCycle = true;
        } else if (numOfOddVertexes == 2) {
            isPath = true;
        }
    }

    public ArrayList<Integer> eulerPath() {
        if (!isPath) {
            return null;
        }
        return eulerAlgo();
    }

    public ArrayList<Integer> eulerCycle() {
        if (!isCycle) {
            return null;
        }
        return eulerAlgo();
    }

    private ArrayList<Integer> eulerAlgo() {
        //מסלול יתחיל מאי זוגי
        int v = v_start;
        Stack<Integer> st = new Stack<Integer>();
        ArrayList<Integer> path = new ArrayList<Integer>();
        st.add(v);

        while (!st.isEmpty()) {
            v = st.peek();
            if (deg[v] == 0) {
                path.add(st.pop());
            } else {
                int u = graph[v].get(0);
                st.push(u);
                deg[v]--;
                deg[u]--;
                graph[v].remove((Integer) u);
                graph[u].remove((Integer) v);
            }
        }
        return path;

    }

    public static void main(String[] args) {
        ArrayList<Integer> g[] = new ArrayList[4];
        for (int i = 0; i < g.length; i++) {
            g[i] = new ArrayList<>();
        }
        g[0].add(1);
        g[0].add(2);

        g[1].add(0);
        g[1].add(3);
        g[1].add(2);

        g[2].add(3);
        g[2].add(0);
        g[2].add(1);

        g[3].add(1);
        g[3].add(2);

        Euler e = new Euler(g);
        System.out.println(e.eulerPath());
    }
}
