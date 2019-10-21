package ok.beak;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

/*
 * https://www.acmicpc.net/problem/2231
 * input 을 1씩 줄여가서 하는 경우 
 * O( N * N의 자리수  ) >> 최대 1000000*6 = 6백만.괜찮
 * 
 * 방법
 * input에서 1을 줄인다.
 * 각각의 1 줄인수 + 자리수 합을 계산한다.
 * 합이 찾는 숫자일 경우 값을 바꾼다.
 */

public class Beak2231Digit2 {
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
            
            int total       = sc.nextInt();
            int findNumber  = total;
            int result      = 0;
            while( --total > 0 ) {
                int comp  = total;
                comp += digitSum ( total );
                if( comp == findNumber ) result = total;
            }
            System.out.println(result);
        } catch (Exception e) {
            System.out.println(e);
        } 
    }
    
    public static int digitSum( int input ) {
        int result = 0;
        int digit = input;
        while( digit!= 0 ) {
            result += digit%10;
            digit /= 10;
        }
        return result;
    }
}


