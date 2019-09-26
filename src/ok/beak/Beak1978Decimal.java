package ok.beak;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/*
 * https://www.acmicpc.net/problem/1978
 */

public class Beak1978Decimal {
    public static void main(String args[]){
        //BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Scanner sc = new Scanner(System.in);
        //StringBuilder resultString = new StringBuilder();
        try {
            //String[] firstLine = br.readLine().split(" ");
            //int total   = Integer.parseInt(firstLine[0]);
            //int compare = Integer.parseInt(firstLine[1]);
            //String[] secondeLine = br.readLine().split(" ");
            //int total = Integer.parseInt(br.readLine());
            //int total = sc.nextInt();
            //String result = "mixed";
            
            int total   = sc.nextInt();
            int result  = 0;
            for (int index = 0; index < total; index++) {
                int number = sc.nextInt();
                for (int indexInner = 2; indexInner <= number; indexInner++) {
                    if( number%indexInner == 0 && indexInner!=number  ) break;
                    if( indexInner == number ) result++; 
                }
            }
            
            System.out.println(result);
            
        } catch (Exception e) {
            System.out.println(e);
        } 
    }
}
