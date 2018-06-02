package Creative;

/*
קלט מערך דרגות
השאלה כמה צלעות צריך להוסיך עד שנגיע למעגל אוילר
פלט הצלעות אם זה אפשרי
 */
import java.util.Arrays;

class Edge {

    int v1;
    int v2;

    public Edge(int v1, int v2) {
        this.v1 = v1;
        this.v2 = v2;
    }

    @Override
    public String toString() {
        return "Edge{" + "v1=" + v1 + ", v2=" + v2 + '}';
    }

}

public class HowManyEdgeToAddToEulerCyrcle {

    public static void main(String[] args) {
        int arr[] = {3, 3, 3, 1, 1, 1};
        System.out.println(Arrays.toString(f(arr)));
    }

    public static Edge[] f(int arr[]) {
        int numEdgetoAdd = 0;
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] % 2 != 0) {
                numEdgetoAdd++;
            }
            sum += arr[i];

        }
        if (sum % 2 != 0) {
            return null;
        }
        //מספר צלעות
        numEdgetoAdd = numEdgetoAdd / 2;
        Edge ans[] = new Edge[numEdgetoAdd];
        int k = 0;
        int e = -1;

        // בונה צלעות אני אחזיר אותם במקרה וזה גראפ תקין
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] % 2 != 0 && e == -1) {
                e = i;
            } else if (arr[i] % 2 != 0 && e != -1) {
                ans[k++] = new Edge(e, i);
                e = -1;
            }
        }

        //מוסיף לכל האי זוגיים אחד ושולח לבדיקה אם הגרף שקיבלתי אם הוא תקין
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] % 2 != 0) {
                arr[i]++;
            }
        }
        System.out.println(Arrays.toString(arr));

        //בודק תקינות של גרף 
        boolean b = checkifGraph(arr);
        if (b) {
            return ans;
        } else {
            return null;
        }
    }

    public static boolean checkifGraph(int arr[]) {
        //מחשב סכום דרגות
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
        }

        //אם הסכום לא זוגי לא יתן גרף כזה
        if (sum % 2 != 0) {
            return false;
        }

        //ממיין והופך כי אני רוצה לרוץ מהגדול לקטן 
        Arrays.sort(arr);
        reverse(arr);

        for (int i = 0; i < arr.length; i++) {
            //אם הראשון הוא אפס אחרי שמיון זה אומר שאני צריך לצאת
            if (arr[0] != 0) {
                //אם מה שאני הולך להוריד יותר גדול מהחברה שלי שבאים אחרי
                if (arr[0] > arr.length - 1 - i) {
                    return false;
                }
                for (int j = 1; j <= arr[0]; j++) {
                    //אם מי שאני בא להוריד ממנו הוא כבר אפס אנחנו נצא החוצה
                    if (arr[j] == 0) {
                        return false;
                    }
                    arr[j]--;
                }
                arr[0] = 0;
                Arrays.sort(arr);
                reverse(arr);
            }
        }
        return true;
    }

    private static void reverse(int[] arr) {
        for (int i = 0; i < arr.length / 2; i++) {
            int temp = arr[i];
            arr[i] = arr[arr.length - 1 - i];
            arr[arr.length - 1 - i] = temp;
        }
    }
}
