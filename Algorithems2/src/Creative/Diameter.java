/*
מציאת קוטר
 */
package Creative;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ArrayBlockingQueue;

/**
 *
 * @author Oleg
 */
public class Diameter {

    public static void main(String[] args) {
        ArrayList<Integer>[] a = new ArrayList[5];
        for (int i = 0; i < a.length; i++) {
            a[i] = new ArrayList<>();
        }
        a[0].add(1);

        a[1].add(2);
        a[1].add(4);
        a[1].add(0);

        a[2].add(1);
        a[2].add(3);

        a[3].add(2);
        a[4].add(1);

        System.out.println(getD(a));
    }

    public static int getD(ArrayList<Integer>[] a) {
        int dis[] = BFS(a, 1);
        int max = Integer.MIN_VALUE;
        int index = 0;
        for (int i = 0; i < dis.length; i++) {
            if (dis[i] > max) {
                max = dis[i];
                index = i;
            }

        }
        dis = BFS(a, index);
        for (int i = 0; i < dis.length; i++) {
            if (dis[i] > max) {
                max = dis[i];
            }
        }
        return max;
    }

    private static int[] BFS(ArrayList<Integer>[] a, int start) {
        int color[] = new int[a.length];
        int dis[] = new int[a.length];
        Arrays.fill(dis,Integer.MAX_VALUE);
        Arrays.fill(color, 0);
        ArrayBlockingQueue<Integer> q = new ArrayBlockingQueue<Integer>(a.length);
        q.add(start);
        color[start] = 1;
        dis[start] = 0;

        while (!q.isEmpty()) {
            int v = q.poll();
            for (Integer nb : a[v]) {
                if (color[nb] == 0) {
                    color[nb] = 1;
                    dis[nb] = 1 + dis[v];
                    q.add(nb);
                }
            }
            color[v] = 2;
        }
        return dis;
    }

}
