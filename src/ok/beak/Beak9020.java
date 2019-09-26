package ok.beak;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/*
 * https://www.acmicpc.net/problem/9020
 */

public class Beak9020 {
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
            
            int total   = sc.nextInt();
            int[] temp = getDecimal(10000);
            
            for (int index = 0; index < total; index++) {
                int number = sc.nextInt();
                int compareNumber;
                if( number == 4 ) {
                    compareNumber = 2;
                } else {
                    compareNumber = ( number/2 ) % 2 == 0 ? number/2+1 : number/2;
                }
                while( compareNumber <= number ) {
                    int tempMinus = number - compareNumber;
                    if( temp[tempMinus] == 0 && temp[compareNumber] == 0 ) {
                        System.out.println(tempMinus + " " + compareNumber);
                        break;
                    } else {
                        compareNumber +=2;
                    }
                }
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
