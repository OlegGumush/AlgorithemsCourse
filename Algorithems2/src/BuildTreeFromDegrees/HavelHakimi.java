package BuildTreeFromDegrees;

import java.util.Arrays;

public class HavelHakimi {

    public static boolean isGraph(int[] degrees) {
        int n = degrees.length;
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += degrees[i];
        }
        if (sum % 2 != 0) {
            return false;
        }

        Arrays.sort(degrees);
        reverse(degrees);
        System.out.println(Arrays.toString(degrees));

        for (int i = 0; i < degrees.length; i++) {
            if (degrees[0] != 0) {
                if (degrees[0] > degrees.length - i - 1) {
                    return false;
                }
                for (int j = 1; j <= degrees[0]; j++) {
                    if (degrees[j] == 0) {
                        return false;
                    }
                    degrees[j]--;
                }
                degrees[0] = 0;
                Arrays.sort(degrees);
                reverse(degrees);
            }
        }
        return true;
    }

    public static boolean isTree(int[] degrees) {
        int sum = 0;
        for (int i = 0; i < degrees.length; i++) {
            if (degrees[i] == 0) {
                return false;
            }
            sum += degrees[i];
        }
        if (sum != 2 * (degrees.length - 1)) {
            return false;
        }
        return isGraph(degrees);
    }

    public static void main(String[] args) {
        int arr[] = {3, 2, 1, 1, 1, 1, 1};
        System.out.println(isGraph(arr));
    }

    private static void reverse(int[] arr) {
        for (int i = 0; i < arr.length / 2; i++) {
            int temp = arr[i];
            arr[i] = arr[arr.length - 1 - i];
            arr[arr.length - 1 - i] = temp;
        }
    }

}
