import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String args[]){
        Solution temp = new Solution();
        
        int n = 3;
       
        int count = 0;
        int[][] build_frame = new int[399][4];
        
        Map<String, String> tttaa = new HashMap<String, String>();
        
        
        while( count < 399 ) {
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
           
           for (int index = 0; index < ttttt.length; index++) {
                zzzzzzzz.append(ttttt[index]);
           }
           
           if( !tttaa.containsKey(zzzzzzzz.toString()) ) {
               build_frame[count++] = ttttt;
               tttaa.put(zzzzzzzz.toString(), "aaa");
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

