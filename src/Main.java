import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/*
 * https://www.acmicpc.net/problem/6064https://ggmouse.tistory.com/317
 * https://ggmouse.tistory.com/317
 * https://sexycoder.tistory.com/26
 * https://mygumi.tistory.com/325
 * 시간초과 소스
 */

public class Main {
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
                int tempX = 1;
                int tempY = 1;
                int result = 1;
                while( !(tempX == x && tempY == y) && !( tempX == M && tempY == N ) ) {
                    result++;
                    if( tempX < M ) {
                        tempX++;
                    } else {
                        tempX = 1;
                    } 
                    if( tempY < N ) {
                        tempY++;
                    } else {
                        tempY = 1;
                    }
                }
                if( tempX == M && tempY == N && x != M &&  y != N ) result = -1;
                System.out.println(result);
            }
            
        } catch (Exception e) {
            System.out.println(e);
        } 
    }
}
