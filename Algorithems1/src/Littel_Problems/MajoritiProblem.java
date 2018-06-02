package Littel_Problems;

public class MajoritiProblem {

	public static void main(String[] args) {
		double d[]={1,1,2,2,1};
		int counter=1;
		double ans = d[0];
		for (int i = 1; i < d.length; i++) {
			if(d[i]!=d[i-1])
			{
				counter--;
				if(counter==0){
					counter=1;
					ans=d[i];
				}
			}else{
				counter++;
			}
		}
		counter=0;
		for (int i = 0; i < d.length; i++) {
			if(d[i]==ans)
				counter++;
			if(counter == d.length/2+1);
				return ;
		}
		
	}
}
