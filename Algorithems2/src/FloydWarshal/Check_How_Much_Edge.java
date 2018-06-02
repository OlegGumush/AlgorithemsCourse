/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FloydWarshal;

public class Check_How_Much_Edge {
     public static void main(String[] args) {
        int mat[][]={{0,1,1},{1,0,0},{1,0,0}};
        int ans =CheckHowMuchE(mat);
        System.out.println(ans);
    }
    public static int CheckHowMuchE(int mat[][]){
        int ones =0;
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat.length; j++) {
                if(mat[i][j]==1)
                    ones++;
            }
        }
        if(!Check_If_Graph_Direct.checkDirect(mat)){
            return ones/2;
        }else{
            return ones;
        }
    }
}
