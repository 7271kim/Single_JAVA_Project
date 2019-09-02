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

public class BigDecimalCalc2 {
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
                String[] firstLine = br.readLine().split(" ");
                int[] totalData = new int[firstLine.length-1];
                int sum = 0 ;
                int average = 0;
                int upAverage = 0;
                for (int indexNumber = 1; indexNumber < firstLine.length; indexNumber++) {
                    int number = Integer.parseInt(firstLine[indexNumber]);
                    totalData[indexNumber-1] = number;
                    sum += number;
                }
                average = sum/totalData.length;
                for (int indexNumber = 0; indexNumber < totalData.length; indexNumber++) {
                    if( average < totalData[indexNumber]) upAverage++;
                }
                BigDecimal upBig        = new BigDecimal(String.valueOf(upAverage));
                BigDecimal totalLength  = new BigDecimal(String.valueOf(totalData.length));
                BigDecimal result       = upBig.multiply(new BigDecimal("100")).divide(totalLength, 3, BigDecimal.ROUND_HALF_UP );
                System.out.println(result+"%");
            }
        } catch (Exception e) {
            System.out.println(e);
        } 
    }
    
}
