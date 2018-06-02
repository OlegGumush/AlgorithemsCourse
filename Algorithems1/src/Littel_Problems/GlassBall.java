package Littel_Problems;

public class GlassBall {
	
	
	public static void main(String[] args) {
		int arr[]=new int[100];
		for (int i = 1; i <= arr.length; i++) {
			arr[i-1]=i;
		}
		GlassBall(arr,9);
	}
	/**
     * complexity: O(sqrt (2) * sqrt (n))
     */
    public static void GlassBall(int[] arr, int ball) {
      
    	int d = 1;
    	int floor =0;
    	//מוצאים את יחס הזהב
    	while(d*(d+1)/2 < arr.length)
    		d++;
    	
    	if(ball>arr[arr.length-1]){
    		System.out.println("ball Strong!!");
    		return;
    	}
    	floor = d;
    	
    	while(floor <=arr.length){
    		if(ball < arr[floor-1]){
    			floor = floor -d +1;
    			while(ball>arr[floor-1]){
    				floor++;
    				
    			}
    			break;
    		}else{
    			d--;
    			floor +=d;
    			if(floor>arr.length)
    				floor=arr.length;
    		}
    	}
    	System.out.println(floor);
    	
    }

}
