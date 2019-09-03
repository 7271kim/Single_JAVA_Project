package algorithm.math;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
public class Main{
	public static void main(String args[]){
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Scanner sc = new Scanner(System.in);
        try {
            
            
            int total   = sc.nextInt();
             // 계차 수열의 일반항 따위는 필요없음. 말 그대로 표현만 하면 된다.
             // a의 n은  n방의 최대로 들어갈 수 있는 수.
             // a1 = 1 
             // a2 = a1 + 6 
             // a3 = a2 + 6 + 6
             // a4 = a3 + 6 + 6 + 6
             // . . . . .
            int count  = 1;
            int first  = 1;
            int d      = 6;
            while( true ) {
                first += d*( count -1 );
                if( total <= first ) break; // 각 구간의 끝 단계보다 작거나 같아야함 : 자기의 구간 
                count++;
            }
            System.out.println(count);
        } catch (Exception e) {
            System.out.println(e);
        } 
	}
}

