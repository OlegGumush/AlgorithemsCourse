package DFS;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

public class DFS {

    int black = 2, white = 0, gray = 1;
    int parent[];
    int color[];
    int first[];
    int last[];
    int components[];
    ArrayList<Integer>[] g;
    int time;
    int kashir;
    boolean ifHasCircle;
    String cyrcle;

    public DFS(ArrayList<Integer>[] graph) {
        g = new ArrayList[graph.length];
        components = new int[g.length];
        for (int i = 0; i < g.length; i++) {
            this.g[i] = new ArrayList<>();
            for (int j = 0; j < graph[i].size(); j++) {
                g[i].add(graph[i].get(j));
            }
        }
        kashir = 0;
        parent = new int[g.length];
        color = new int[g.length];
        first = new int[g.length];
        last = new int[g.length];
        time = 1;
        Arrays.fill(parent, -1);

        for (int i = 0; i < graph.length; i++) {
            if (color[i] == white) {
                kashir++;
                DFS(i);
            }
        }

        //כמה איברים יש בכל רכיב קשירות
        int each[] = new int[kashir];
        int counter = 0;
        int k = 0;
        for (int i = 1; i <= kashir; i++) {
            for (int j = 0; j < components.length; j++) {
                //באיזה צבע אתה לאיזה רכיב אתה שייך לפי זה נגד לאריי ליסט
                if (components[j] == i) {
                    counter++;
                }
            }
            each[k++] = counter;
            counter = 0;
        }
        //ממערך צבעים או מערך רכיבים למערך של אריי ליסטים
        ///////////////////////////////////////////////////
        //for(int i=0 , i < comp.length ; i++){
        //      g-->בגודל קשיר
        //      g[comp[i]-1].add(i)
        //////////////////////////////////////////////////
    }

    private void DFS(int v) {
        color[v] = gray;
        first[v] = time++;
        components[v] = kashir;

        for (int ng : g[v]) {
            if (color[ng] == white) {
                parent[ng] = v;
                DFS(ng);
            }
            //אם יש לי שכן אפור והוא לא אבא שלי
            if (color[ng] == gray && parent[v] != ng) {
                ifHasCircle = true;
                Stack s = new Stack();
                int w = v;
                cyrcle = "" + w;
                while (w != ng) {
                    w = parent[w];
                    cyrcle += "-" + w;
                }
            }
        }
        color[v] = black;
        last[v] = time++;
    }

    public boolean ifkashir() {
        return last[0] == g.length * 2;
    }

    public boolean ifCircle() {
        return ifHasCircle;
    }

    public String Circle() {
        return cyrcle;
    }
}
