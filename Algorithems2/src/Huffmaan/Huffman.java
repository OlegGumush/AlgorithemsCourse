package Huffmaan;

import java.util.PriorityQueue;

public class Huffman {
    

    private Node nodes[];
    private int numOfLeaves, numNodes, place;
    private final int nil = -1;
    String codes[];
    PriorityQueue<Node> queue;

    // constructor
    public Huffman(int freq[]) {//O(n)+O(n)+O(nlog2(n))
        numOfLeaves = freq.length;
        numNodes = numOfLeaves * 2 - 1;
        nodes = new Node[numNodes];
        codes = new String[numOfLeaves];
        place = numOfLeaves;
        for (int i = 0; i < numOfLeaves; i++) {//O(n)
            nodes[i] = new Node(i, freq[i],-1,-1,-1);
        }
        queue = new PriorityQueue<Node>(numOfLeaves);
        for (int i = 0; i < numOfLeaves; i++) {//O(nlog2(n))
            queue.add(nodes[i]);
        }
        for (int i = 0; i < numOfLeaves; i++) {//O(n)
            codes[i] = new String();
        }
    }

    public void HuffmanAlgorithm() {
        for (int i = 0; i < numOfLeaves - 1; i++) {
            Node n1 = queue.poll();
            Node n2 = queue.poll();
            Node node = new Node(place, n1.key + n2.key, n1.letterNumber, n2.letterNumber,-1);
            n1.setParent(place);
            n2.setParent(place);
            queue.add(node);
            nodes[place] = node;
            place++;
        }
        //// build the Huffman's Code for all letters
        for (int i = 0; i < numOfLeaves; i++) {
            Node child = nodes[i];
            Node parent = nodes[child.parent];
            while (child.parent != nil) {
                if (parent.left == child.letterNumber) {
                    codes[i] = "0" + codes[i];
                } else {
                    codes[i] = "1" + codes[i];
                }
                child = parent;
                if (parent.parent != nil) {
                    parent = nodes[parent.parent];
                }
            }
        }
    }

    public void printCode() {
        for (int i = 0; i < numOfLeaves; i++) {
            System.out.print((char) ('a' + i) + ": " + codes[i] + ";  ");
        }
    }

    public static void main(String[] args) {
        int freq1[] = {12,40,15,8,25};
        //int freq2[] = {45, 13, 12, 16, 9, 5};
        Huffman hh = new Huffman(freq1);
        hh.HuffmanAlgorithm();
        hh.printCode();
    }

}
/* 	freq1:  a: 1111;  b: 0;  c: 110;  d: 1110;  e: 10;  
	freq2:  a: 0;  b: 101;  c: 100;  d: 111;  e: 1101;  f: 1100;  
*/
