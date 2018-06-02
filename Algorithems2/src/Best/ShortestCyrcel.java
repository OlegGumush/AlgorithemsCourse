package Best;

import java.util.Arrays;

public class ShortestCyrcel {

    private static int[] buildRegular(int lengthBest, int[] arr, int start, int end) {
        int t[] = new int[lengthBest];
        for (int i = 0; i < lengthBest; i++) {
            t[i] = (arr[(start + i) % arr.length]);
        }
        System.out.println("Path " + Arrays.toString(t));
        return null;
    }

    int arr[];

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

            if (sum <= 0) {
                sum = 0;
                index = i + 1;
            }
        }
        return new int[]{max, start, end};
    }

    public static int[] ShortestCyrcle(int[] arr) {
        int[] best1 = best(arr);

        if (best1[0] <= 0) {
            return best1;
        }

        int temp[] = new int[arr.length];
        int SumAll = 0;

        for (int i = 0; i < arr.length; i++) {
            SumAll += arr[i];
            temp[i] = -arr[i];
        }
        
        //min arr
        int[] best2 = best(temp);

        //Length
        int lengthBest1 = best1[2] - best1[1] + 1;
        //Cyrcel Length
        int lengthBest2 = arr.length - best2[2] + best2[1] - 1;

        int sumBestCyrcle = SumAll + best2[0];

        //אם הסכום שווה אבל האורך של הרגיל יותר קצר
        if (best1[0] == sumBestCyrcle && lengthBest1 <= lengthBest2) {
            buildRegular(lengthBest1, arr, best1[1], best1[2]);
            return best1;
            //אם הסכום שווה אבל האורך של המעגלי יותר קצר יותר קצר
        } else if (best1[0] == sumBestCyrcle) {
            int start = (best2[2] + 1);
            int end = best2[1] - 1;
            buildRegular(lengthBest2, arr, start, end);
            int ans[] = {sumBestCyrcle, start, end};
            return ans;
        }

        //אם עדיף רגיל אבל הכי קצר מהרגילים
        if (best1[0] >= sumBestCyrcle) {
            buildRegular(lengthBest1, arr, best1[1], best1[2]);
            return best1;
            //אם עדיף מעגלי אבל הכי קצר מהמעגליים
        } else {
            int start = (best2[2] + 1);
            int end = best2[1] - 1;
            buildRegular(lengthBest2, arr, start, end);
            return new int[]{sumBestCyrcle, start, end};
        }
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(ShortestCyrcle(new int[]{2, 2, -90, 8, -90, 6,1,-7,0,0, 1, -90, 1, 1})));
    }
}
