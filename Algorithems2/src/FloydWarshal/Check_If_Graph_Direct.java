/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FloydWarshal;

/**
 *
 * @author Oleg
 */
public class Check_If_Graph_Direct {
    public static void main(String[] args) {
        int mat[][]={{0,1,1},{1,0,0},{1,0,0}};
        boolean ans =checkDirect(mat);
        System.out.println(ans);
    }
    public static boolean checkDirect(int mat[][]){
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[0].length; j++) {
                if(mat[i][j]!=mat[j][i])
                    return true;
            }
        }
        return false;
    }
}
