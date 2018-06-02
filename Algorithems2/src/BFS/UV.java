package BFS;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;
import java.util.concurrent.ArrayBlockingQueue;

public class UV {

    int parent[];
    int color[];
    int dis[];
    int ways[];
    ArrayBlockingQueue<Integer> q;
    ArrayList<Integer> g[];
    int white = 0;
    int gray = 1;
    int black = 2;
    int components[];

    public UV(ArrayList<Integer>[] g) {
        this.g = g;
        int n = g.length;
        parent = new int[n];
        color = new int[n];
        dis = new int[n];
        ways = new int[n];
        components = new int[n];
        q = new ArrayBlockingQueue<Integer>(g.length);
    }

    public ArrayList<Integer> shortestPathUVV1V2(int a, int b, int v1, int v2) {
        int length_av1 = getPath1(a, v1);
        int length_bv2 = getPath1(v2, b);

        int length_av2 = getPath1(a, v2);
        int length_bv1 = getPath1(v1, b);

        int path1 = length_av1 + length_bv2 + 1;
        int path2 = length_bv1 + length_av2 + 1;

        ArrayList<Integer> ans = new ArrayList<Integer>();

        if (path1 < path2) {
            Stack s = getPathStack(a, v1);
            while (!s.isEmpty()) {
                ans.add((Integer) s.pop());
            }
            s = getPathStack(v2, b);
            while (!s.isEmpty()) {
                ans.add((Integer) s.pop());
            }
        } else {
            Stack s = getPathStack(a, v2);
            while (!s.isEmpty()) {
                ans.add((Integer) s.pop());
            }
            s = getPathStack(v1, b);
            while (!s.isEmpty()) {
                ans.add((Integer) s.pop());
            }
        }
        return ans;
    }

    public int getPath1(int s, int v) {
        bfs(s);
        int counter = 0;
        while (parent[v] != -1) {
            counter++;
            v = parent[v];
        }
        return counter;
    }

    public Stack<Integer> getPathStack(int s, int v) {
        bfs(s);
        Stack<Integer> stack = new Stack();
        stack.add(v);

        while (parent[v] != -1) {
            v = parent[v];
            stack.add(v);
        }
        
        return stack;
    }

    private void bfs(int start) {
        Arrays.fill(parent, -1);
        Arrays.fill(dis, -1);
        Arrays.fill(color, 0);
        q.add(start);
        color[start] = gray;
        dis[start] = 0;
        ways[start] = 1;

        while (!q.isEmpty()) {
            int v = q.poll();
            for (int ng : g[v]) {
                if (color[ng] == white) {
                    color[ng] = gray;
                    parent[ng] = v;
                    dis[ng] = dis[v] + 1;
                    ways[ng] = ways[v];
                    q.add(ng);
                } else if (dis[ng] == dis[v] + 1) {
                    ways[ng] += ways[v];
                }
            }
            color[v] = black;
        }
    }
}
