package Best;

import java.util.ArrayList;
import java.util.Arrays;

public class Best_Array {

    /**
     * O(n^3)
     */
    public static int[] bestWholeSearch(int[] arr) {
        int n = arr.length;
        int max = Integer.MIN_VALUE;
        int start = -1;
        int end = -1;

        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                int sum = 0;
                if (j - i + 1 == 3) {
                    //עובר על כל התתי סדרות
                    for (int k = i; k <= j; k++) {
                        sum += arr[k];
                    }
                    if (sum >= max) {
                        max = sum;
                        start = i;
                        end = j;
                    }
                }
            }
        }
        return new int[]{max, start, end};
    }

    /*
     * Complexity: O(n)
     */
    public static int[] best(int[] arr) {
        int max = Integer.MIN_VALUE;
        int index = 0;
        int start = -1;
        int end = -1;
        int sum = 0;

        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
            if (sum > max) {
                max = sum;
                start = index;
                end = i;
            }
            if (sum == max && end - start + 1 > i - index + 1) {
                start = index;
                end = i;
            }

            if (sum < 0) {
                sum = 0;
                index = i + 1;
            }
        }
        return new int[]{max, start, end};
    }

    public static int[] bestCycle(int[] arr) {
        int[] ans1 = best(arr);
        int best1 = ans1[0];

        if (best1 <= 0) {
            return ans1;
        }

        int temp[] = new int[arr.length];
        int SumAll = 0;

        for (int i = 0; i < arr.length; i++) {
            SumAll += arr[i];
            temp[i] = -arr[i];
        }

        int[] ans2 = best(temp);

        int best2 = SumAll + ans2[0];

        if (best1 >= best2) {
            return ans1;
        } else {
            int ans[] = {best2, (ans2[2] + 1) % arr.length, ans2[1] - 1};
            return ans;
        }
    }

    /*
     * O(n^3)
     */
    public static ArrayList<int[]> AllBests(int[] arr) {

        ArrayList<int[]> ans = new ArrayList<>();

        int best = best(arr)[0];

        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j < arr.length; j++) {
                int sum = 0;
                //עובר על כל התתי סדרות
                for (int k = i; k <= j; k++) {
                    sum += arr[k];
                }
                if (sum == best) {
                    ans.add(new int[]{best, i, j});
                }
            }
        }
        return ans;
    }

    public static int bestStar(ArrayList<int[]> a) {
        int max = 0;

        for (int i = 0; i < a.size(); i++) {
            for (int j = i + 1; j < a.size(); j++) {
                ArrayList<Integer> temp = new ArrayList();
                for (int k = 0; k < a.get(i).length; k++) {
                    temp.add(a.get(i)[k]);
                }
                for (int k = a.get(j).length - 1; k >= 0; k--) {
                    temp.add(a.get(j)[k]);
                }
                int arr[] = new int[temp.size()];
                for (int k = 0; k < arr.length; k++) {
                    arr[k] = temp.get(k);
                }
                int best = best(arr)[0];
                if (best > max) {
                    max = best;
                }
            }
        }
        return max;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(bestWholeSearch(new int[]{1,2,3,4,5})));
    }

}
