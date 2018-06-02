package Plane;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Vector;


public class Plane {
	Node mat[][];
	boolean ifreturn =false;
	int teta;
	public Plane(Node mat[][],int teta){
		this.mat=new Node[mat.length][mat[0].length];
		this.teta=teta;
		for (int i = 0; i < mat.length; i++) {
			for (int j = 0; j < mat[0].length; j++) {
				this.mat[i][j]=new Node(mat[i][j].x,mat[i][j].y);
			}
		}

		BestPrice();
	}
	public int BestPrice(){

		for (int i = 1; i < mat.length; i++) {
			mat[i][0].entry=mat[i-1][0].entry+mat[i-1][0].y;
			mat[i][0].numOfpath=1;
		}

		for (int i = 1; i < mat[0].length; i++) {
			mat[0][i].entry=mat[0][i-1].entry+mat[0][i-1].x;
			mat[0][i].numOfpath=1;

		}

		for (int i = 1; i < mat.length; i++) {
			for (int j = 1; j < mat[0].length; j++) {
				int a=mat[i][j-1].entry+mat[i][j-1].x;
				int b=mat[i-1][j].entry+mat[i-1][j].y;
				if(a<b){
					mat[i][j].entry=a;
					mat[i][j].numOfpath=mat[i][j-1].numOfpath;
				}
				else if(a>b) {
					mat[i][j].entry=b;
					mat[i][j].numOfpath=mat[i-1][j].numOfpath;
				}else{
					mat[i][j].entry=b;
					mat[i][j].numOfpath=mat[i][j-1].numOfpath+mat[i-1][j].numOfpath;
				}
			}
		}
		return mat[mat.length-1][mat[0].length-1].entry;
	}
	public int numOfPathes(){
		BestPrice();
		return mat[mat.length-1][mat[0].length-1].numOfpath;

	}
	public String OneBestPath(){

		int i=mat.length-1;
		int j=mat[0].length-1;
		String path="";
		while(i>0 && j>0){
			int a=mat[i][j-1].entry+mat[i][j-1].x;
			int b=mat[i-1][j].entry+mat[i-1][j].y;
			if(a<b){
				path="0"+path;
				j--;
			}
			else{
				path="1"+path;	
				i--;
			}
		}
		while(i>0){
			path="1"+path;
			i--;
		}
		while(j>0){
			path="0"+path;
			j--;
		}
		return path;
	}
	public ArrayList<String> AllPathes(){
		String s1="";
		String s2="";
		//fill first col --> 1,11,111
		for (int i = 1; i < mat.length; i++) {
			s1+="1";
			mat[i][0].arr.add(s1);
		}
		//fill first row --> 0,00,000
		for (int i = 1; i < mat.length; i++) {
			s2+="0";
			mat[0][i].arr.add(s2);	
		}

		for (int i = 1; i < mat.length; i++) {
			for (int j = 1; j < mat[0].length; j++) {
				int a = mat[i-1][j].entry+mat[i-1][j].y;
				int b = mat[i][j-1].entry+mat[i][j-1].x;
				if(a<b){
					for (int k = 0; k < mat[i-1][j].arr.size(); k++) {
						String s = mat[i-1][j].arr.get(k);
						s+="1";
						if(!mat[i][j].arr.contains(s))
							mat[i][j].arr.add(s);
					}
				}else if(b<a){
					for (int k = 0; k < mat[i][j-1].arr.size(); k++) {
						String s = mat[i][j-1].arr.get(k);
						s+="0";
						if(!mat[i][j].arr.contains(s))
							mat[i][j].arr.add(s);
					}
				}else{
					for (int k = 0; k < mat[i-1][j].arr.size(); k++) {
						String s = mat[i-1][j].arr.get(k);
						s+="1";
						if(!mat[i][j].arr.contains(s))
							mat[i][j].arr.add(s);
					}
					for (int k = 0; k < mat[i][j-1].arr.size(); k++) {
						String s = mat[i][j-1].arr.get(k);
						s+="0";
						if(!mat[i][j].arr.contains(s))
							mat[i][j].arr.add(s);
					}
				}
			}
		}
		return mat[mat.length-1][mat[0].length-1].arr;

	}
	public int numOfOptimalPaths(){
		if(numOfPathes()>teta)
			return -1;

		ArrayList<String> ans = AllPathes();

		//System.out.println(counter);
		int min = Integer.MAX_VALUE;
		String ans1 ="";
		System.out.println(min);
		int counter=0;

		for (int i = 0; i < ans.size(); i++) {
			counter=0;
			for (int j = 1; j < ans.get(i).length(); j++) {
				if(ans.get(i).charAt(j)!=ans.get(i).charAt(j-1)){
					counter++;
				}
			}
			if(counter<min){
				min=counter;
				ans1 = ans.get(i);
			}

		}
		ArrayList<String> toto = new ArrayList<String>();
		int counter_ans=0;
		for (int i = 0; i < ans.size(); i++) {
			counter=0;
			for (int j = 1; j < ans.get(i).length(); j++) {
				if(ans.get(i).charAt(j)!=ans.get(i).charAt(j-1)){
					counter++;
				}
			}
			if(counter == min){
				counter_ans++;
				toto.add(ans.get(i));
			}

		}
		System.out.println(toto);


		return counter_ans;
	}
	//if point on the best path
	public boolean PathPoint(Node n){
		int ans = BestPrice();

		int up = up(n.x,n.y);
		System.out.println(up);
		int down = down(n.x,n.y);
		System.out.println(down);
		if(up==-1 || down==-1 ){
			return false;
		}
		if(ans == up+down)
			return true;
		else
			return false;
	}
	private int up(int n, int m) {
		if(n>=mat.length || m>=mat[0].length)
			return -1;
		//����� ���� ���� ���� ������
		Node temp[][]=new Node[mat.length-n][mat[0].length-m];

		for (int i = 0; i < temp.length; i++) {
			for (int j = 0; j < temp[0].length; j++) {
				temp[i][j]=new Node (mat[i+n][j+m].x,mat[i+n][j+m].y);
			}
		}

		for (int i = 1; i < temp.length; i++) {
			temp[i][0].entry=temp[i-1][0].entry+temp[i-1][0].y;
		}
		for (int i = 1; i < temp[0].length; i++) {
			temp[0][i].entry=temp[0][i-1].entry+temp[0][i-1].x;
		}


		for (int i = 1; i < temp.length; i++) {
			for (int j = 1; j < temp[0].length; j++) {
				int a = temp[i-1][j].entry+temp[i-1][j].y;
				int b = temp[i][j-1].entry+temp[i][j-1].x;
				if(a<b){
					temp[i][j].entry=a;
				}else{
					temp[i][j].entry=b;
				}
			}
		}
		return temp[temp.length-1][temp[0].length-1].entry;
	}
	private int down(int x, int y) {
		if(x>=mat.length || y>=mat[0].length)
			return -1;

		return mat[x][y].entry;
	}

	public boolean ArrayPointsOnTheOneShortestPath(Node[] p) {
		//check if all points correct
		for (int i = 0; i < p.length; i++) {
			if(p[i].x>=mat.length || p[i].y>=mat[0].length)
				return false;
		
		}
		int sum = mat[p[0].x][p[0].y].entry;
		System.out.println(sum);
		for (int i = 1; i < p.length; i++) {
			if(p[i].y < p[i-1].y || p[i].x<p[i-1].x)
				return false;
			sum += bestPathFromTo(p[i-1].x,p[i-1].y,p[i].x,p[i].y);
			System.out.println(sum);
		}

		sum += bestPathFromTo(p[p.length-1].x,p[p.length-1].y,mat.length-1,mat[0].length-1);
		System.out.println(sum);
		return sum == 20;
	}
	private int bestPathFromTo(int n1 ,int m1 , int n2 , int m2 ){


		Node temp[][]=new Node[n2-n1+1][m2-m1+1];

		for (int i = 0; i < temp.length; i++) {
			for (int j = 0; j < temp[0].length; j++) {
				temp[i][j]=new Node(mat[i+n1][j+m1].x,mat[i+n1][j+m1].y);
			}
		}			
		for (int i = 1; i < temp.length; i++) {
			temp[i][0].entry = temp[i-1][0].entry+temp[i-1][0].y;
		}

		for (int i = 1; i < temp[0].length; i++) {
			temp[0][i].entry = temp[0][i-1].entry + temp[0][i-1].x;
		}
		for (int i=1; i<temp.length; i++){
			for (int j=1; j<temp[0].length; j++){
				int a = temp[i-1][j].entry + temp[i-1][j].y;
				int b = temp[i][j-1].entry + temp[i][j-1].x;
				if(a<b)
					temp[i][j].entry=a;
				else
					temp[i][j].entry=b;
			}
		}
		return temp[temp.length-1][temp[0].length-1].entry;

	}
	public void SecondBestPrice(){
		BestPrice();
		for (int i = 1; i < mat.length; i++) {
			mat[i][0].entry2=mat[i-1][0].entry+ mat[i-1][0].y;
		}
		for (int i = 1; i < mat.length; i++) {
			mat[0][i].entry2 = mat[0][i-1].entry + mat[0][i-1].x;
		}

		for (int i = 1; i < mat.length; i++) {
			for (int j = 1; j < mat[0].length; j++) {

				int minUp = mat[i-1][j].entry + mat[i-1][j].y;
				int minUp2 = mat[i-1][j].entry2 + mat[i-1][j].y;

				int minRight = mat[i][j-1].entry + mat[i][j-1].x;
				int minRight2 = mat[i][j-1].entry2 + mat[i][j-1].x;

				if(minUp == minUp2 && minRight==minRight2)
					mat[i][j].entry2= Math.max(minUp, minRight);
				else if (minUp < minRight)
					mat[i][j].entry2 = Math.min(minUp2, minRight);
				else if (minRight < minUp)
					mat[i][j].entry2 = Math.min(minRight2, minUp);
				else
					mat[i][j].entry2 = Math.min(minRight2,minUp2 );
			}
		}
		System.out.println(mat[mat.length-1][mat[0].length-1].entry2);


	}
	public void secondBestAllPathes(){
		SecondBestPrice();
		String s1="";
		String s2="";
		//fill first col --> 1,11,111
		for (int i = 1; i < mat.length; i++) {
			s1+="1";
			mat[i][0].arr2.add(s1);
		}
		//fill first row --> 0,00,000
		for (int i = 1; i < mat.length; i++) {
			s2+="0";
			mat[0][i].arr2.add(s2);	
		}
		for (int i = 1; i < mat.length; i++) {
			for (int j = 1; j < mat[0].length; j++) {

				int minUp = mat[i-1][j].entry + mat[i-1][j].y;
				int minUp2 = mat[i-1][j].entry2 + mat[i-1][j].y;

				int minRight = mat[i][j-1].entry + mat[i][j-1].x;
				int minRight2 = mat[i][j-1].entry2 + mat[i][j-1].x;

				if(minUp == minUp2 && minRight==minRight2){
					if(minUp>minRight){
						for (int k = 0; k < mat[i-1][j].arr.size(); k++) {
							String s = mat[i-1][j].arr.get(k);
							s+="1";
							if(!mat[i][j].arr2.contains(s))
								mat[i][j].arr2.add(s);
						}
					}else if(minRight>minUp){
						for (int k = 0; k < mat[i][j-1].arr.size(); k++) {
							String s = mat[i][j-1].arr.get(k);
							s+="0";
							if(!mat[i][j].arr2.contains(s))
								mat[i][j].arr2.add(s);
						}
					}else{
						for (int k = 0; k < mat[i-1][j].arr.size(); k++) {
							String s = mat[i-1][j].arr.get(k);
							s+="1";
							if(!mat[i][j].arr2.contains(s))
								mat[i][j].arr2.add(s);
						}
						for (int k = 0; k < mat[i][j-1].arr.size(); k++) {
							String s = mat[i][j-1].arr.get(k);
							s+="0";
							if(!mat[i][j].arr2.contains(s))
								mat[i][j].arr2.add(s);
						}
					}

				}
				else if (minUp < minRight){
					if(minUp2<minRight){
						for (int k = 0; k < mat[i-1][j].arr2.size(); k++) {
							String s = mat[i-1][j].arr2.get(k);
							s+="1";
							if(!mat[i][j].arr2.contains(s))
								mat[i][j].arr2.add(s);
						}
					}else if (minRight<minUp2){
						for (int k = 0; k < mat[i][j-1].arr.size(); k++) {
							String s = mat[i][j-1].arr.get(k);
							s+="0";
							if(!mat[i][j].arr2.contains(s))
								mat[i][j].arr2.add(s);
						}
					}else{
						for (int k = 0; k < mat[i-1][j].arr2.size(); k++) {
							String s = mat[i-1][j].arr2.get(k);
							s+="1";
							if(!mat[i][j].arr2.contains(s))
								mat[i][j].arr2.add(s);
						}
						for (int k = 0; k < mat[i][j-1].arr.size(); k++) {
							String s = mat[i][j-1].arr.get(k);
							s+="0";
							if(!mat[i][j].arr2.contains(s))
								mat[i][j].arr2.add(s);
						}
					}
				}
				else if (minRight < minUp){
					if(minRight2<minUp){
						for (int k = 0; k < mat[i][j-1].arr2.size(); k++) {
							String s = mat[i][j-1].arr2.get(k);
							s+="0";
							if(!mat[i][j].arr2.contains(s))
								mat[i][j].arr2.add(s);
						}
					}else if (minUp<minRight2){
						for (int k = 0; k < mat[i-1][j].arr.size(); k++) {
							String s = mat[i-1][j].arr.get(k);
							s+="1";
							if(!mat[i][j].arr2.contains(s))
								mat[i][j].arr2.add(s);
						}
					}else{
						for (int k = 0; k < mat[i][j-1].arr2.size(); k++) {
							String s = mat[i][j-1].arr2.get(k);
							s+="0";
							if(!mat[i][j].arr2.contains(s))
								mat[i][j].arr2.add(s);
						}
						for (int k = 0; k < mat[i-1][j].arr.size(); k++) {
							String s = mat[i-1][j].arr.get(k);
							s+="1";
							if(!mat[i][j].arr2.contains(s))
								mat[i][j].arr2.add(s);
						}
					}
				}

				else
				{
					if(minRight2<minUp2)
						for (int k = 0; k < mat[i][j-1].arr2.size(); k++) {
							String s = mat[i][j-1].arr2.get(k);
							s+="0";
							if(!mat[i][j].arr2.contains(s))
								mat[i][j].arr2.add(s);
						}
					else if(minUp2<minRight2){

						for (int k = 0; k < mat[i-1][j].arr2.size(); k++) {
							String s = mat[i-1][j].arr2.get(k);
							s+="1";
							if(!mat[i][j].arr2.contains(s))
								mat[i][j].arr2.add(s);
						}
					}else{
						for (int k = 0; k < mat[i][j-1].arr2.size(); k++) {
							String s = mat[i][j-1].arr2.get(k);
							s+="0";
							if(!mat[i][j].arr2.contains(s))
								mat[i][j].arr2.add(s);
						}
						for (int k = 0; k < mat[i-1][j].arr2.size(); k++) {
							String s = mat[i-1][j].arr2.get(k);
							s+="1";
							if(!mat[i][j].arr2.contains(s))
								mat[i][j].arr2.add(s);
						}
					}

				}
			}


		}
		System.out.println(mat[mat.length-1][mat[0].length-1].arr2);

	}
	public void AllPathesRecursion(){
		int i = mat.length-1;
		int j = mat[0].length-1;
		ArrayList<String> a = new ArrayList<String>();

		AllPathesRecursion(i,j,"",a);
		System.out.println(a);
	}
	private void AllPathesRecursion(int i, int j, String s, ArrayList<String> arr) {

		if(i==0 || j==0){
			while(i>0){
				s = "1"+s;
				i--;
			}
			while(j>0){
				s = "0"+s;
				j--;
			}
			if(!arr.contains(s)){
				arr.add(s);
			}
			return;

		}
		int a=mat[i][j-1].entry+mat[i][j-1].x;
		int b=mat[i-1][j].entry+mat[i-1][j].y;

		if(a<b){
			AllPathesRecursion(i,j-1,"0"+s,arr);
		}else if(b<a){
			AllPathesRecursion(i-1,j,"1"+s,arr);
		}else{
			AllPathesRecursion(i,j-1,"0"+s,arr);
			AllPathesRecursion(i-1,j,"1"+s,arr);
		}
	}
	public void AllPathesSecondsRecursion(){
		//BestPrice();
		SecondBestPrice();
		int price = mat[mat.length-1][mat[0].length-1].entry2;

		int i = mat.length-1;
		int j = mat[0].length-1;
		ArrayList<String> k = new ArrayList<String>();

		AllPathesSecondsRecursion(i,j,"",k,price);
		System.out.println(k);
	}
	private void AllPathesSecondsRecursion(int i, int j, String s, ArrayList<String> arr,int price) {

		if(i==0 || j==0){
			while(i>0){
				s = "1"+s;
				i--;
			}
			while(j>0){
				s = "0"+s;
				j--;
			}
			if(!arr.contains(s)){
				arr.add(s);
			}
			return;

		}
		int right=mat[i][j-1].entry+mat[i][j-1].x;
		int right2 = mat[i][j-1].entry2+mat[i][j-1].x;
		
		int up=mat[i-1][j].entry+mat[i-1][j].y;
		int up2=mat[i-1][j].entry2+mat[i-1][j].y;

		if((  right == price || right2 == price) && (up==price || up2==price)){
			AllPathesSecondsRecursion(i-1,j,"1"+s,arr,price-mat[i-1][j].y);
			AllPathesSecondsRecursion(i,j-1,"0"+s,arr,price-mat[i][j-1].x);
		}else if(right == price || right2==price){
			AllPathesSecondsRecursion(i,j-1,"0"+s,arr,price-mat[i][j-1].x);
		}else{
			AllPathesSecondsRecursion(i-1,j,"1"+s,arr,price-mat[i-1][j].y);
		}
	}
	
	public void SecondNumOfPathes(){
		
		for (int i = 0; i < mat.length; i++) {
			mat[i][0].numOfPath2=mat[i][0].numOfpath;
		}
		for (int i = 0; i < mat[0].length; i++) {
			mat[0][i].numOfPath2=mat[0][i].numOfpath;
		}
		
		for (int i = 1; i < mat.length; i++) {
			for (int j = 1; j < mat.length; j++) {
				int up = mat[i-1][j].entry + mat[i-1][j].y;
				int up2 = mat[i-1][j].entry2 + mat[i-1][j].y;

				int right = mat[i][j-1].entry+mat[i][j-1].x;
				int right2 = mat[i][j-1].entry2+mat[i][j-1].x;
				
				if(up==up2 && right == right2){
					if(right>up)
						mat[i][j].numOfPath2 = mat[i][j-1].numOfpath;
					else if (up > right)
						mat[i][j].numOfPath2 = mat[i-1][j].numOfpath;
					else{
						mat[i][j].numOfPath2 = mat[i][j-1].numOfpath;
						mat[i][j].numOfPath2 = mat[i-1][j].numOfpath;
					}
						
				}else if(up<right){
					if(up2<right)
						mat[i][j].numOfPath2 = mat[i-1][j].numOfPath2;
					else if(right < up)
						mat[i][j].numOfPath2 = mat[i][j-1].numOfpath;
					else{
						mat[i][j].numOfPath2 = mat[i-1][j].numOfPath2;
						mat[i][j].numOfPath2 = mat[i][j-1].numOfpath;
					}
				}
				else if(right<up)
					if(up<right2)
						mat[i][j].numOfPath2 = mat[i-1][j].numOfpath;
					else if (right2 < up)
						mat[i][j].numOfPath2 = mat[i][j-1].numOfPath2;
					else{
						mat[i][j].numOfPath2 = mat[i-1][j].numOfpath;
						mat[i][j].numOfPath2 = mat[i][j-1].numOfPath2;
					}
						
				else{
					if(up2<right2)
						mat[i][j].numOfPath2 = mat[i-1][j].numOfPath2;
					else if(right2 < up2)
						mat[i][j].numOfPath2 = mat[i][j-1].numOfPath2;
					else{
						mat[i][j].numOfPath2 = mat[i-1][j].numOfPath2;
						mat[i][j].numOfPath2 = mat[i][j-1].numOfPath2;
					}

				}					
			}

		}
		System.out.println(mat[mat.length-1][mat[0].length-1].numOfPath2);
	}
	
}










