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
            
            br.readLine();
            String[] secondeLine = br.readLine().split(" ");
            int[] temp = new int[secondeLine.length];
            int max    = -1; 
            BigDecimal sum      = new BigDecimal("0");
            BigDecimal length   = new BigDecimal(String.valueOf(secondeLine.length));
            for (int index = 0; index < secondeLine.length; index++) {
                int number = Integer.parseInt(secondeLine[index]);
                temp[index] = Integer.parseInt(secondeLine[index]);
                if(max <= number) max = number;
            }
            BigDecimal maxBix = new BigDecimal(String.valueOf(max));
            for (int index = 0; index < temp.length; index++) {
                BigDecimal tempNumber = new BigDecimal(String.valueOf(temp[index]));
                sum = sum.add( tempNumber.multiply(new BigDecimal("100")).divide(maxBix, 4, RoundingMode.HALF_EVEN ) );
            }
            System.out.println(sum.divide(length, 4, RoundingMode.HALF_EVEN));
            
        } catch (Exception e) {
            System.out.println(e);
        } 
    }
    
}
