package LCS;
//Longest ZigZag Sub
public class LongestAlternatingSubsequence {

	public static void main(String[] args) {
		int arr[]={2,1,2,1};
		int ans =Altarnating(arr);
		System.out.println(ans);
	}
	private static int Altarnating(int[] sequence) {


		int max_seq = 0;

	    if (sequence.length == 1) {
	        return 1;
	    }

	    if (sequence.length == 1) {
	        return 2;
	    }

	    int dp[] = new int[sequence.length];

	    dp[0] = 1;
	    dp[1] = 2;

	    for (int i = 2; i < sequence.length; i++) {
	        for (int j = i - 1; j > 0; j--) {
	            if (((sequence[i] > sequence[j] && sequence[j] < sequence[j - 1]) || (sequence[i] < sequence[j] && sequence[j] > sequence[j - 1])) && dp[i] < dp[j] + 1) {
	                dp[i] = dp[j] + 1;

	                if (dp[i] > max_seq) {
	                    max_seq = dp[i];
	                }
	            } 
	        }
	    }

	    return max_seq;
	}
}

