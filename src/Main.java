import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
public class Main{
    static int TOTAL;
    
    public static void main(String args[]) throws IOException{
        /*
         * https://www.acmicpc.net/problem/1914
         * 재귀에 대한 이해
         */
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        TOTAL= 0;
        if(N <= 20 ) {
        moveCheck(N, 1, 2, 3, 0);
        System.out.println(TOTAL);
        showCheck(N, 1, 2, 3, 0);
        }
        
    }
    public static void moveCheck( int total,int from, int by, int to , int result ) {
        if( total == 1 ) {
            TOTAL++;
        } else {
            TOTAL++;
            moveCheck(total - 1, from, to, by, result);
            moveCheck(total - 1, by, from, to, result);
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
