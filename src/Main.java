import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String args[]){
        Solution temp = new Solution();
        
        int n = 3;
       
        int count = 0;
        /*int[][] build_frame = new int[200][4];
        
         Map<String, String> tttaa = new HashMap<String, String>();
        
        
        while( count < 200 ) {
           int[] ttttt = new int[4];
           StringBuilder zzzzzzzz = new StringBuilder();
           for (int index = 0; index < 2; index++) {
               double random  = Math.random();
               int value2      = ( (int)(random*100) +1 ) % 10;
               ttttt[index] = value2;
           }
           for (int index = 2; index < 4; index++) {
               double random  = Math.random();
               int value2      = ( (int)(random*100) +1 ) % 2;
               ttttt[index] = value2;
           }
           
           for (int index = 3; index < 4; index++) {
               double random  = Math.random();
               //int value2      = ( (int)(random*100) +1 ) % 2;
               int value2      = 1;
               ttttt[index] = value2;
           }
           
           for (int index = 0; index < ttttt.length; index++) {
                zzzzzzzz.append(ttttt[index]);
           }
           
           if( !tttaa.containsKey(zzzzzzzz.toString()) ) {
               build_frame[count++] = ttttt;
               tttaa.put(zzzzzzzz.toString(), "aaa");
           } 
           
        }*/
        
        /*int[][] build_frame = {
                {0,0,1,1}
            };*/
        int[][] build_frame = new int[32][4];
        int xx = 0;
        while( xx < 18) {
            for (int indexY = 0; indexY < 4; indexY++) {
                for (int indexX = 0; indexX < 4;  indexX++) {
                    for (int check = 0; check < 2; check++) {
                        int[] ttt = { indexY , indexX , check ,1 };
                        build_frame[xx++] = ttt;
                    }
                }
            }
        }
        
        int[][] aa= temp.solution(n, build_frame);
        for (int index = 0; index < aa.length; index++) {
            for (int index2 = 0; index2 < aa[index].length; index2++) {
                System.out.print( aa[index][index2] + " " );
            }
            System.out.println();
        }
    }
}

