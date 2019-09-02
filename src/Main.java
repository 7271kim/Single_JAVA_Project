import java.io.BufferedReader;
import java.io.InputStreamReader;
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
            
            
            Digit dt = new Digit(sc.nextInt());
            dt.print();
            //dt.print2();
            
        } catch (Exception e) {
            System.out.println(e);
        } 
    }
    
}

class Digit {
    int [] data; 
    int result = 0;
    
    // position 0 이라면 data[0] = 1의자리부터
    // position 0 이라면 data[0] = 1의자리부터
    Digit( int num ){
        int totalLength = String.valueOf(num).split("").length;
        data = new int[totalLength];
        int count = 0;
        while( num / 10.0 > 0 ) {
            data[count] = num%10;
            num /= 10;
            count++;
        }
    }
    public void print() {
        for (int index = 0; index < data.length; index++) {
            System.out.println(data[index]);
        }
    }
}