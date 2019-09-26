package ok.beak;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/*
 * https://www.acmicpc.net/problem/3009
 */

public class Beak3009 {
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
            
            //int total   = sc.nextInt();
            Map<String, String> resultX = new HashMap<String, String>();
            Map<String, String> resultY = new HashMap<String, String>();
            for (int index = 0; index < 3; index++) {
                String[] firstLine  = br.readLine().split(" ");
                String x = firstLine[0];
                String y = firstLine[1];
                
                if( resultX.containsKey(x) ) {
                    resultX.remove(x);
                } else {
                    resultX.put(x, x);
                }
                
                if( resultY.containsKey(y) ) {
                    resultY.remove(y);
                } else {
                    resultY.put(y, y);
                }
            }
            for (String string : resultX.keySet()) {
                System.out.print(string + " ");
            }
            for (String string : resultY.keySet()) {
                System.out.print(string);
            }
            
        } catch (Exception e) {
            System.out.println(e);
        } 
    }
}
