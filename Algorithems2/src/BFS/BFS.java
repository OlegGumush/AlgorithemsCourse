package BFS;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ArrayBlockingQueue;

public class BFS {

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

    public BFS(ArrayList<Integer>[] g) {
        this.g = g;
        int n = g.length;
        parent = new int[n];
        color = new int[n];
        dis = new int[n];
        ways = new int[n];
        components = new int[n];
        q = new ArrayBlockingQueue<Integer>(g.length);

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

    public int[] getWays(int start) {
        bfs(start);
        return ways;
    }

    public String getPath(int s, int v) {
        bfs(s);

        if (dis[v] == -1) {
            return null;
        }
        String path = "";
        path = v + path;

        while (parent[v] != -1) {
            path = parent[v] + " " + path;
            v = parent[v];
        }
        return path;
    }

    public boolean ifConnective() {
        bfs(0);
        for (int i = 0; i < dis.length; i++) {
            if (dis[i] == -1) {
                return false;
            }
        }
        return true;
    }

    public int[] AmmountOfComponents() {
        int numOfCom = 0;
        boolean arr[] = new boolean[dis.length];
        int ans[] = new int[dis.length];

        for (int i = 0; i < g.length; i++) {
            if (dis[i] == -1 && !arr[i]) {
                numOfCom++;
                bfs(i);
                for (int j = 0; j < dis.length; j++) {
                    if (dis[j] != -1 && !arr[j]) {
                        ans[j] = numOfCom;
                        arr[j] = true;
                    }
                }
            }
        }
        ArrayList<ArrayList<Integer>> a = new ArrayList<>();
        for (int i = 0; i < numOfCom; i++) {
            a.add(new ArrayList());
        }
        for (int i = 0; i < dis.length; i++) {
            a.get(ans[i] - 1).add(i);
        }
        System.out.println(a);
        return ans;
    }

    public boolean isBiparete() {
        int partition[] = new int[g.length];
        int start = 0;
        ArrayBlockingQueue<Integer> q = new ArrayBlockingQueue<>(g.length);
        q.add(start);
        partition[start] = 1;

        while (!q.isEmpty()) {
            int v = q.poll();
            color[start] = gray;
            for (Integer nb : g[v]) {
                if (partition[v] == partition[nb]) {
                    return false;
                }
                if (color[nb] == white) {
                    color[nb] = gray;
                    parent[nb] = v;
                    dis[nb] = dis[v] + 1;
                    partition[nb] = 3 - partition[v];
                    q.add(nb);
                }
            }
            color[v] = black;
        }
        return true;
    }

    public int diameter() {
        int diam = 0;
        int index = -1;
        int dis = -1;
        if (g.length == 2) {
            return 1;
        } else if (g.length < 2) {
            return -1;
        }
        bfs(0);
        for (int i = 0; i < this.dis.length; i++) {
            if (this.dis[i] > dis) {
                dis = this.dis[i];
                index = i;
            }
        }
        bfs(index);
        for (int i = 0; i < this.dis.length; i++) {
            if (this.dis[i] > dis) {
                dis = this.dis[i];
                index = i;
            }
        }
        return dis;
    }
}
