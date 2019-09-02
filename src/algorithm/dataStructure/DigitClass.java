package algorithm.dataStructure;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class DigitClass {
    public static void main(String args[]){
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Scanner sc = new Scanner(System.in);
        try {
            
            Digit dt = new Digit(sc.nextInt());
            dt.print();
            dt = new Digit(sc.nextInt(),1);
            dt.print();
            
        } catch (Exception e) {
            System.out.println(e);
        } 
    }
    
}

class Digit {
    int [] data; 
    int result = 0;
    
    /*
    
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
    
    */
    
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