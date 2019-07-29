import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
public class Main{
    
    public static void main(String args[]) throws IOException{
        /*
         * https://www.acmicpc.net/problem/1914
         * 재귀에 대한 이해
         */
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        BigInteger big = new BigInteger("1");

        if(N <= 20 ) {
            System.out.println((1<<N)-1);
            showCheck(N, 1, 2, 3, 0);
        } else {
            for( int index = 0 ; index < N; index++ ) {
                //big = big.multiply(BigInteger.TWO);
                big = big.multiply(new BigInteger("2"));
            }
            big = big.subtract(new BigInteger("1"));
            System.out.println(big);
        }
        
    }
    public static void showCheck( int total,int from, int by, int to , int result ) {
        if( total == 1 ) {
            System.out.println(from + " " + to);
        } else {
            showCheck(total - 1, from, to, by, result);
            System.out.println(from + " " + to);
            showCheck(total - 1, by, from, to, result);
        }
    }
}
