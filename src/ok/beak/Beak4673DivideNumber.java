package ok.beak;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Scanner;

/*
 * https://www.acmicpc.net/problem/4673
 * Deque 
 *
 */

public class Beak4673DivideNumber {
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
            GetSelfNum dt = new GetSelfNum(10000);
            dt.print();
            
        } catch (Exception e) {
            System.out.println(e);
        } 
    }
    
}

class GetSelfNum {
    int [] data; 
    GetSelfNum( int num ){
        data = new int[num+1];
        for (int index = 1; index <= num; index++) {
            if( data[index] > 1 ) continue;
            int temp = index;
            while( data[temp] < 2 ) {
                data[temp] += 1;
                int sum     = temp;

                //자기 자신의 자리수 합 로직
                double divide  = 10.0;
                while( ( sum/divide ) > 0 ) {
                    temp += sum%divide;
                    sum /= 10;
                }
                
                if( temp > num ) break;
            }
        }
    }
    public void print() {
        StringBuffer temp = new StringBuffer();
        for (int index = 0; index < data.length; index++) {
            if(data[index] == 1) temp.append(index+"\n");
        }
        System.out.println(temp);
    }
}