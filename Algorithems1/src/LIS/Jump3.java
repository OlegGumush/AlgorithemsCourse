package LIS;

import java.util.HashMap;

public class Jump3 {
	public static void main(String[] args) {
		int arr[]= {1,2,-3,4,5,6,7,8,-9,-10,11};
		System.out.println(increasedBy3(arr));
	}

	public static int increasedBy3(int[] arr) {
		// Key - number from arr , Value - the length of the LIS icreased by 3
		// until him.
		HashMap<Integer, Integer> hm = new HashMap<>();
		int max_lis_length = 1;
		// we put in the hashmap arr[0] and the lis leagth until him is 1.
		hm.put(arr[0], 1);
		for (int i = 1; i < arr.length; i++) {
			Integer lis_length_until_him = hm.get(arr[i] - 4);
			if (lis_length_until_him == null)
				hm.put(arr[i], 1);
			else {
				hm.put(arr[i], lis_length_until_him + 1);
				if (max_lis_length < lis_length_until_him + 1)
					max_lis_length = lis_length_until_him + 1;
			}
		}
		return max_lis_length;
	}

}