package Plane;

import java.util.ArrayList;

public class Node {
	int x=0;
	int y=0;
	int entry=0;
	int numOfPath2=0;
	int numOfpath=0;
	ArrayList<String> arr=new ArrayList();
	int entryFromTheEnd;
	int entry2=0;
	ArrayList<String> arr2=new ArrayList();

	public Node(int x,int y){
		this.x=x;
		this.y=y;
		
	}

	@Override
	public String toString() {
		return "Node [x=" + x + ", y=" + y + ", entry=" + entry
				+ ", numOfpath=" + numOfpath + "]";
	}
	
	
	
}
