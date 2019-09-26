package ok.beak;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/*
 * https://www.acmicpc.net/problem/4948
 */

public class Beak4948 {
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
            
            //int total   = sc.nextInt();
            
            int[] temp = getDecimal(123456*2);
            int nextNumber;
            while(sc.hasNextLine() && (nextNumber = sc.nextInt())  != 0) {
                int twiceNumber = nextNumber *2 ;
                int result = 0;
                for (int index = nextNumber+1; index <= twiceNumber; index++) {
                    if( temp[index] == 0 ) result++;
                }
                System.out.println(result);
            }
        } catch (Exception e) {
            System.out.println(e);
        } 
    }
    
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
