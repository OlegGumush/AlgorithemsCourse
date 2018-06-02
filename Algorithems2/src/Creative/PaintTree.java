package Creative;

/*
קלט עץ 
מצא בכמה צבעים אפשר לצבוע את העץ ותצבע אותו
 */
import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;

public class PaintTree {

    public static void main(String[] args) {
        ArrayList<Integer>[] a = new ArrayList[7];
        for (int i = 0; i < a.length; i++) {
            a[i] = new ArrayList<>();
        }
        a[0].add(1);
        a[0].add(2);

        a[1].add(0);
        a[1].add(3);
        a[1].add(4);

        a[2].add(0);
        a[2].add(5);
        a[2].add(6);

        a[3].add(1);
        a[4].add(1);
        
        a[5].add(2);
        a[6].add(2);

        Paint(a);
    }

    private static ArrayList<ArrayList<Integer>> Paint(ArrayList<Integer>[] a) {
        int color[] = new int[a.length];
        int paint[] = new int[a.length];
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        ArrayBlockingQueue<Integer> q = new ArrayBlockingQueue(a.length);
        if (a.length == 0) {
            return null;
        }
        if (a.length == 1) {
            ans.add(new ArrayList<>());
            ans.get(0).add(0);
            return ans;
        }
        ans.add(new ArrayList<>());
        ans.add(new ArrayList<>());

        q.add(0);
        color[0] = 1;
        paint[0] = 1;
        ans.get(0).add(0);

        while (!q.isEmpty()) {
            int v = q.poll();
            for (int nb : a[v]) {
                
                if (color[nb] == 0) {
                    color[nb] = 1;
                    paint[nb] = 3 - paint[v];
                    ans.get(paint[nb] - 1).add(nb);
                    q.add(nb);
                }
            }
            color[v] = 2;
        }
        System.out.println(ans);
        return null;
    }

}
