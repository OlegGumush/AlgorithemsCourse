package Max_Min;

import java.util.ArrayList;

public class Max_Max_Induction_Recursion {

	public static void main(String[] args) {
		int arr[]={1,7,7,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,4};
		max_Max(arr);
		max_Max_Recursion(arr);
		//if(i == list.size() || i == list.size()-1) i = 0;
	}

	private static void max_Max_Recursion(int[] arr) {
		Node_MaxMax temp[]=new Node_MaxMax[arr.length];
		for (int i = 0; i < arr.length; i++) {
			temp[i]=new Node_MaxMax(arr[i]);
		}    
		Node_MaxMax ans = max_Max_Recursion(temp,0,arr.length-1);
		int max1=ans.data;
		int max2 = 0;

		if(!ans.st.empty())
			max2=ans.st.pop();
		while(!ans.st.isEmpty()){
			int n= ans.st.pop();
			if(max2<n){
				max2=n;
			}
		}
		System.out.println(max1);
		System.out.println(max2);
	}

	private static Node_MaxMax max_Max_Recursion(Node_MaxMax[] temp, int start,int end) {
		if(start==end)
			return temp[start];

		int middle = (start+end)/2;
		Node_MaxMax a = max_Max_Recursion(temp,start,middle);
		Node_MaxMax b = max_Max_Recursion(temp,middle+1,end);
		if(a.data>b.data){
			a.st.push(b.data);
			return a;
		}
		else{
			b.st.push(a.data);
			return b;
		}

	}

	private static void max_Max(int[] arr) {
		ArrayList<Node_MaxMax> temp=new ArrayList<Node_MaxMax>();
		for (int i = 0; i < arr.length; i++) {
			temp.add(new Node_MaxMax(arr[i]));
		}
		int i=0;
		while(temp.size()>1){
			if(temp.get(i%temp.size()).data>temp.get((i+1)%temp.size()).data){
				temp.get(i%temp.size()).st.push(temp.get((i+1)%temp.size()).data);
				temp.remove((i+1)%temp.size());
			}
			else{
				temp.get((i+1)%temp.size()).st.push(temp.get(i%temp.size()).data);
				temp.remove(i%temp.size());
			}
			i+=1;

		}
		int max1=temp.get(0).data;
		int max2 = 0;

		if(!temp.get(0).st.empty())
			max2=temp.get(0).st.pop();
		while(!temp.get(0).st.isEmpty()){
			int n= temp.get(0).st.pop();
			if(max2<n){
				max2=n;
			}
		}
		System.out.println(max1);
		System.out.println(max2);

	}

}
