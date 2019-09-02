package algorithm.dataStructure;
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

public class BigDecimalCalc {
    public static void main(String args[]){
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Scanner sc = new Scanner(System.in);
        StringBuilder resultString = new StringBuilder();
        try {
            // 더하기 : add
            // 곱하기 : multiply
            // 나누기 : divide
            //BigDecimal.ROUND_UP : 올림
            //BigDecimal.ROUND_DOWN : 버림
            //BigDecimal.ROUND_HALF_UP : 반올림 ( 5 이상 올림 )
            //BigDecimal.ROUND_HALF_DOWN : 반내림 ( 5 이하 내림 )



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
