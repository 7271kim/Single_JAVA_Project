package ok.beak;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/*
 * https://www.acmicpc.net/problem/2775
 */

public class Beak2775 {
    public static void main(String args[]){
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
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
            
            int total = sc.nextInt();
            for (int index = 0; index < total; index++) {
                int k = sc.nextInt();
                int n = sc.nextInt();
                System.out.println(checkPeople(k,n));
            }
            
        } catch (Exception e) {
            System.out.println(e);
        } 
    }

    private static int checkPeople(int k, int n) {
        int result = 0;
        if(k == 0 ) return n;
        if( k != 0 ) {
            for (int index = 1; index <= n; index++) {
                result += checkPeople(k-1, index);
            }
        }
        return result;
    }
}
