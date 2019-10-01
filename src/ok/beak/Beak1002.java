package ok.beak;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/*
 * https://www.acmicpc.net/problem/1002
 */

public class Beak1002 {
    public static void main(String args[]){
        //BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
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
            for (int index = 0; index < total; index++) {
                int x1   = sc.nextInt();
                int y1   = sc.nextInt();
                int r1   = sc.nextInt();
                int x2   = sc.nextInt();
                int y2   = sc.nextInt();
                int r2   = sc.nextInt();
                double distance = Math.sqrt(Math.pow(x1-x2, 2) + Math.pow(y1-y2, 2));
                
                int sumR = r1 + r2;
                int absR = Math.abs(r1 - r2);
               
                // 무한대 케이스 
                if( distance == 0 && r1 == r2 ) {
                    System.out.println(-1);
                    continue;
                }
                
                // 1개만 만나는 케이스 
                // 외접 내접 
                if( r1 + r2 ==  distance || absR == distance  ) {
                    System.out.println(1);
                    
                } else if ( r1 + r2 > distance && absR < distance  ) {
                    // 2개 만나는 케이스 
                    System.out.println(2);
                } else {
                    System.out.println(0);
                }
                
                
            }
        } catch (Exception e) {
            System.out.println(e);
        } 
    }
}
