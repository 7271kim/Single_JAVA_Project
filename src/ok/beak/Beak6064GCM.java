package ok.beak;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/*
 * https://www.acmicpc.net/problem/6064
 * 시간초과 소스
 */

public class Beak6064GCM {
    public static void main(String args[]){
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //Scanner sc = new Scanner(System.in);
        //StringBuilder resultString = new StringBuilder();
        try {
            //String[] firstLine = br.readLine().split(" ");
            //int total   = Integer.parseInt(firstLine[0]);
            //int compare = Integer.parseInt(firstLine[1]);
            //String[] secondeLine = br.readLine().split(" ");
            //int total = Integer.parseInt(br.readLine());
            //int total = sc.nextInt();
            //String result = "mixed";
            
            int total = Integer.parseInt(br.readLine());
            for (int index = 0; index < total; index++) {
                String[] firstLine = br.readLine().split(" ");
                int M = Integer.parseInt(firstLine[0]);
                int N = Integer.parseInt(firstLine[1]);
                int x = Integer.parseInt(firstLine[2]);
                int y = Integer.parseInt(firstLine[3]);
                int big     = M < N ? N : M;
                int small   = M < N ? M : N;
                int lcm     = lcm(big, small);
                int result  = 0;
                int tempY   = 0;
                while( result < lcm ) {
                    if ( result == 0 ) {
                        result = x;
                        tempY  = x;
                    } else {
                        result += M;
                        tempY  += M;
                    }
                    tempY = tempY%N != 0 ? tempY%N : N;
                    
                    if( tempY == y ) break;
                }
                
                if( tempY != y ) result = -1;
                
                System.out.println(result);
            }
            
        } catch (Exception e) {
            System.out.println(e);
        } 
    }
    
    //최대공약수 ( Greatest Common Divisor )
    public static int gcd( int big, int small ) {
        
        if( small == 0 ) return big;
        
        return gcd( small , big % small );
    }
    
   //최소공배수 ( Least Common Multiple )
    public static int lcm( int big, int small ) {
        return ( big * small ) / gcd( small , big % small );
    }
}
