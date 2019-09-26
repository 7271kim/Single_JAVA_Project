package ok.beak;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/*
 * https://www.acmicpc.net/problem/2581
 */

public class Beak2581Decimal2 {
    public static void main(String args[]){
        //BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
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
            
            //int total   = sc.nextInt();
            
            int[] temp = getDecimal(10000);
            
            int start   = sc.nextInt();
            int end   = sc.nextInt();
            int sum   = 0;
            int first = -1;
            for (int index = start; index <= end; index++) {
                if(temp[index] == 0 ) {
                    sum+=index;
                    if( first == -1 ) first = index;
                } 
            }
            if( first != -1 ) {
                System.out.println(sum);
            }
            System.out.println(first);
        } catch (Exception e) {
            System.out.println(e);
        } 
    }
    
   //소수 구하기 getDecimal(10000) 1~ 10000번까지의 소수인 수를 0으로 구현 ex) result[61] = 0 이므로 61은 소수 
    public static int[] getDecimal( int maxSize ) {
        int[] result = new int[maxSize+1];
        
        result[1] = 1;
        
        for (int index = 2; index <= maxSize; index++) {
            if(result[index] == 1 ) continue;
            for (int innerIndex = index+index; innerIndex <= maxSize; innerIndex += index) {
                result[innerIndex] = 1;
            }
        }
        
        return result;
    }
}
