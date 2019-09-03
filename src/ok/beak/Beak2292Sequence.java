package ok.beak;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/*
 * https://www.acmicpc.net/problem/2292
 * Deque 
 *
 */

public class Beak2292Sequence {
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
            // 여기같은 경우 n이 1보다 클때 3n(n-1) + 1 이 일반항임
            // n와 n+1 사이의 해당 숫자를 찾는 문제
            int result = 1;
            
            while( true ) {
                int before = 3*result*result - 3*result + 1;
                int next   = 3*result*result + 3*result + 1;
                if( before < total &&  total <= next ) break;
                result++;
            }
            if( total == 1 ) {
                System.out.println( 1 );
            } else {
                System.out.println(result + 1);
            }
            
        } catch (Exception e) {
            System.out.println(e);
        } 
    }
}
