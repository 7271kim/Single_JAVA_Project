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
            dt = new Digit(sc.nextInt(),1);
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
    
    //  123 position 0 이라면 data[0] = 1의자리부터  3 2 1 
    //  123 position 0 이라면 data[0] = 맨 윗차리부터 1 2 3 
    Digit( int num ){
        String[] spilt = String.valueOf(num).split("");
        data = new int[spilt.length];
        for ( int index = 0; index < spilt.length; index++ ) {
            data[index] = Integer.parseInt(spilt[spilt.length - index -1]);
        }
    }
    
    Digit( int num, int position ){
        String[] spilt = String.valueOf(num).split("");
        data = new int[spilt.length];
        if( position == 0) {
            for ( int index = 0; index < spilt.length; index++ ) {
                data[index] = Integer.parseInt(spilt[spilt.length - index -1]);
            }
        } else {
            for ( int index = 0; index < spilt.length; index++ ) {
                data[index] = Integer.parseInt(spilt[index]);
            }
        }
    }
    public void print() {
        for (int index = 0; index < data.length; index++) {
            System.out.println(data[index]);
        }
    }
}