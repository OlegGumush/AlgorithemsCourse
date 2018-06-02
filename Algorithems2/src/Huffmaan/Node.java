package Huffmaan;

public class Node implements Comparable<Node>{
	/** @param:
	 * number - a letter number
	 * key - a letter frequency (probability)
	 */ 
	private final int nil = -1;
	int letterNumber, key, parent;
	int left, right;
	/** constructor of Node*/
	public Node(int letterNumber, int value, int left, int right,int parent){
		this.letterNumber = letterNumber;
		this.key = value;
		this.parent = parent;
		this.left = left;
		this.right = right;
	}
	public void setParent(int parent){
		this.parent = parent;
	}
	public String toString(){
		return "("+letterNumber+","+key+" "+","+left+","+right+","+parent +" )";
	}
	@Override
	public int compareTo(Node n) {
		int ans = this.key - n.key;
		return ans;
	}
}
