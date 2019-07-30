import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class Main{
    
    public static void main(String args[]) throws IOException{
        /*
         * https://www.acmicpc.net/problem/2910
         * 빈도수
         */
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] firstLow   = br.readLine().split(" ");
        String[] nextLow    = br.readLine().split(" ");
        Map<Integer, Integer> result          = new HashMap<Integer, Integer>();
        List<Integer> sort                    = new ArrayList<Integer>();
        int n = Integer.parseInt(firstLow[0]);
        int c = Integer.parseInt(firstLow[1]);
        int [] test = new int[c+1];
        //해당이슈는 배열으 최대 범위가 10억을 못넘음. 답은 맞지만 GGGGG
        for( int index = 0 ; index < n; index++ ) {
            int number = Integer.parseInt(nextLow[index]); 
            int mid    = (n-index)*10;
            if( test[number] == 0 ) {
                test[number] = 100 + mid + number;
            } else {
                test[number]+= 100;
            }
        }
        for( int item : test ) {
            sort.add(item);
        }
         Collections.sort(sort,Collections.reverseOrder());
         for( int key : sort ){
             int count  = key/100;
             int number = key%10;
            for( int index = 1 ; index <= count; index++ ) {
              System.out.println(number);
            }
        }
    }
}
