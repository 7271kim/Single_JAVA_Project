
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;


class DigitClass {
    int [] data; 
    StringBuilder result = new StringBuilder();
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
    
    DigitClass(){
    }
   
    DigitClass( int num ){
        String[] spilt = String.valueOf(num).split("");
        data = new int[spilt.length];
        for ( int index = 0; index < spilt.length; index++ ) {
            data[index] = Integer.parseInt(spilt[spilt.length - index -1]);
        }
    }
    //  123 position 0 이라면 data[0] = 1의자리부터  3 2 1 
    //  123 position 1 이라면 data[0] = 맨 윗차리부터 1 2 3 
    DigitClass( int num, int position ){
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
    
    // 단순 각각의 자리수 합만을 원할 경우
    public int digitSum( int input ) {
        int result = 0;
        int digit = input;
        while( digit!= 0 ) {
            result += digit%10;
            digit /= 10;
        }
        return result;
    }
    
   
    public int getData() {
        for (int index = 0; index < data.length; index++) {
            int temp = data[index];
            result.append(temp);
        }
        return Integer.parseInt(result.toString());
    }
    
    public void update(int num) {
        String[] spilt = String.valueOf(num).split("");
        data = new int[spilt.length];
        for ( int index = 0; index < spilt.length; index++ ) {
            data[index] = Integer.parseInt(spilt[spilt.length - index -1]);
        }
    }
    
}