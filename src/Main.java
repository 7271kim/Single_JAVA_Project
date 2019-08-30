import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Scanner;

/*
 * https://www.acmicpc.net/problem/5430
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
            
            int total = Integer.parseInt(br.readLine());
            for (int index = 0; index < total; index++) {
                String[] firstLine = br.readLine().split("");
                int temp = 0;
                int result = 0;
                for (int indexO = 0; indexO < firstLine.length; indexO++) {
                    if(firstLine[indexO].indexOf("O") > -1) {
                        temp++;
                    } else {
                        temp=0;
                    }
                    result+=temp;
                }
                System.out.println(result);
            }
        } catch (Exception e) {
            System.out.println(e);
        } 
    }
    
}
