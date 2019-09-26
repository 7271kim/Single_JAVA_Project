package ok.beak;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/*
 * https://www.acmicpc.net/problem/2839
 * Deque 
 *
 */

public class Beak2839Reminder {
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
            int maxFive = total / 5;
            int maxTree = total / 3;
            int min     = 100000000;
            
            for (int indexThree = 0; indexThree <= maxTree; indexThree++) {
                for (int indexFive = 0; indexFive <= maxFive; indexFive++) {
                    if( indexThree*3 + indexFive*5 == total ) {
                        int sum = indexThree + indexFive;
                        min = sum < min ? sum : min ;
                    }
                }
            }
            if( min == 100000000 ) min = -1;
            System.out.println(min);
            
        } catch (Exception e) {
            System.out.println(e);
        } 
    }
}
