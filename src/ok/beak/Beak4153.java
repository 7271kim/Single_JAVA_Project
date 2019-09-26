package ok.beak;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/*
 * https://www.acmicpc.net/problem/4153
 */

public class Beak4153 {
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
            String inputData;
            
            while ( (inputData = br.readLine()) != null && !inputData.equals("0 0 0")) {  
                String[] get = inputData.split(" ");
                int first = Integer.parseInt(get[0]);
                int second = Integer.parseInt(get[1]);
                int third = Integer.parseInt(get[2]);
                int max   = 0;
                int sum   = 0;
                first   *= first;
                max = first > max ? first : max; 
                second  *= second;
                max = second > max ? second : max;
                third   *= third;
                max = third > max ? third : max;
                if( max ==  first) {
                    sum = second + third;
                } else if( max ==  second ) {
                    sum = first + third;
                } else {
                    sum = second + first;
                }
                System.out.println( sum == max ? "right" : "wrong" );
            }
        } catch (Exception e) {
            System.out.println(e);
        } 
    }
}
