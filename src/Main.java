import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/*
 * https://www.acmicpc.net/problem/1316
 * Deque 
 *
 */

public class Main {
    public static void main(String args[]){
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Scanner sc = new Scanner(System.in);
        StringBuilder resultString = new StringBuilder();
        try {
            //String[] firstLine = br.readLine().split(" ");
            //int total   = Integer.parseInt(firstLine[0]);
            //int compare = Integer.parseInt(firstLine[1]);
            //String[] secondeLine = br.readLine().split(" ");
            //int total = Integer.parseInt(br.readLine());
            //int total = sc.nextInt();
            //String result = "mixed";
            
            int total   = sc.nextInt();
            
            //계차수열 일반항 
            // a1 + n-1시그마 ak (등차 혹은 등비수열의 합)
            // 여기같은 경우 n이 1보다 클때  1+ n(n-1)/2 이 일반항임
            int result = 1;
            int up = 1;
            int down = 1;
            
            while( true ) {
                int temp =  1 + result * (result-1) / 2 ;
                if( total < temp ) break;
                result++;
                // total보다 큰 n을 찾고 
            }
            if( total != 1 ) {
                result--;
                // 시작점을 잡음 
                
                int temp = total - ( 1 + result * (result-1) /2 );
                // 시작점부터 몇 떨어져 있나 확인
                
                if( result % 2 == 0 ) {
                    up      = 1 + temp;
                    down    = result-temp;
                } else {
                    up      = result-temp;
                    down    = 1 + temp;
                }
            }
            System.out.println( up + "/" +down);
            
        } catch (Exception e) {
            System.out.println(e);
        } 
    }
}
