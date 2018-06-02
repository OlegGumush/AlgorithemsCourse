package FloydWarshal;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author Oleg
 */
public class Check_If_Graph_Connectivity {

    public static void main(String[] args) {
        int mat[][] = {{0,0,0,0,1,0},{0,0,0,1,0,0},{0,0,0,0,0,1},{0,1,0,0,0,0},{1,0,0,0,0,0},{0,0,1,0,0,0}};
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[0].length; j++) {
                System.out.print(mat[i][j] + " ");
            }
            System.out.println("");
        }
        System.out.println("");
        Check_If_Graph_Connectivity(mat);
    }
    public static void Check_If_Graph_Connectivity(int mat[][]) {
        for (int k = 0; k < mat.length; k++) {
            for (int i = 0; i < mat.length; i++) {
                for (int j = 0; j < mat.length; j++) {
                    if (mat[i][k] == 1 && mat[k][j] == 1) {
                        mat[i][j] = 1;
                    }
                }
            }
        }
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[0].length; j++) {
                System.out.print(mat[i][j] + " ");
            }
            System.out.println("");
        }

        //I mention this zero index
        int index = 0;
        //Arr set to mark number of components , and each Vertex belongs to a specific component 
        int arr[] = new int[mat.length];
        //if all arr full the ex done
        int endOfEx = 0;
        //value that arr get
        int components = 1;
        ArrayList <ArrayList> a = new <ArrayList>ArrayList();
        ArrayList temp = new ArrayList(mat.length);
        
        //Passing the matrix
        for (int i = 0; i < mat.length;) {
            for (int j = 0; j < mat.length; j++) {
                //if mat[i][j]==1 --> in my component
                if (mat[i][j] == 1) {
                    temp.add(j+1);
                    arr[j] = 1;
                    endOfEx++;
                }
            }
            a.add(temp);
            //if all sorted
            if (endOfEx == arr.length) {
                break;
            }
            components++;
            //i=k : next step i want to jump to k row ,Only if this component is not carping/לבקר
            for (int k = 0; k < mat.length; k++) {
                if (arr[k] == 0) {
                    i = k;
                    temp = new ArrayList();
                    break;
                }
            }
        }
        System.out.println(a.toString());
    }
}
