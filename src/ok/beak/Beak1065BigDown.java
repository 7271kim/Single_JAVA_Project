package ok.beak;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

/*
 * https://www.acmicpc.net/problem/5430
 * Deque 
 *
 */

public class Beak1065BigDown {
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
            
            
            ArithmeticSequence dt = new ArithmeticSequence(sc.nextInt());
            dt.print();
            //dt.print2();
            
        } catch (Exception e) {
            System.out.println(e);
        } 
    }
    
}

class ArithmeticSequence {
    int [] data; 
    int result = 0;
    ArithmeticSequence( int num ){
        data = new int[num+1];
        for (int index = 1; index <= num; index++) {
            double divide  = 10.0;
            int temp        = index;
            double arithmetic  = 0;
            double tempfirst   = 0;
            int count = 0;
            Boolean isArithmeticSequence = true;
            while( ( temp/divide ) > 0 ) {
                double lastNumber = temp % divide;
                if( count == 1 ){
                    arithmetic =  lastNumber - tempfirst;
                } else if( count > 1) {
                    double twoNum = tempfirst + arithmetic;
                    if( twoNum != lastNumber ) {
                        isArithmeticSequence = false;
                        break; 
                    }
                }
                tempfirst = lastNumber;
                temp /= 10;
                count++;
            }
            if( isArithmeticSequence ) {
                data[index] =1;
                result++;
            }
        }
    }
    public void print() {
        System.out.println(result);
    }
    public void print2() {
        for (int index = 0; index < data.length; index++) {
            if( data[index] == 1 ) {
                System.out.println(index);
            }
        }
        
    }
}