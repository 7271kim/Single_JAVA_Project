package algorithm.math;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
public class Sequence2{
	public static void main(String args[]){
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Scanner sc = new Scanner(System.in);
        try {
            // a1 = 0  + 1
            // a2 = a1 + 1 + 1
            // a3 = a2 + 1 + 1 + 1
            // a4 = a3 + 1 + 1 + 1 + 1
            
            
            int total   = sc.nextInt();
            
            int count  = 1;
            int first  = 0;
            while( true ) {
                first += count;
                if( total <= first ) break; // 각 구간의 끝 단계보다 작거나 같아야함 : 자기의 구간 
                count++;
            }
            
            int up      = 1;
            int down    = 1;
            int temp    = first - total; 
            if( count % 2 != 0 ) {
                up      = 1 + temp;
                down    = count-temp;
            } else {
                up      = count-temp;
                down    = 1 + temp;
            }
            
            System.out.println( up + "/" +down);
            
        } catch (Exception e) {
            System.out.println(e);
        } 
	}
}

