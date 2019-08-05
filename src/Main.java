import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class Main {
    public static void main(String[] args) throws NumberFormatException, IOException {
        /*
         * https://www.acmicpc.net/problem/1572
         * 중앙값 
         * 앞뒤 빼고 넣기
         */
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] fistInput = br.readLine().split(" ");
        int n = Integer.parseInt(fistInput[0]);
        int k = Integer.parseInt(fistInput[1]);
        int [] temp = new int[n+1];
        int result = 0;
        int mid = ((k+1)/2)-1;
         for( int index = 1; index <= n; index++ ) {
             int number = Integer.parseInt(br.readLine());
             temp[index] = number;
             if( k == index ) {
                 ArrayList<Integer> tempList = new ArrayList<Integer>();
                 for( int loop = 1; loop <= index; loop++ ) {
                     tempList.add(temp[loop]);
                 }
                 Collections.sort(tempList);
                 result += tempList.get(mid);
             } else if(k < index ) {
                 ArrayList<Integer> tempList = new ArrayList<Integer>();
                 for( int loop = index-k+1; loop <= index; loop++ ) {
                     tempList.add(temp[loop]);
                 }
                 Collections.sort(tempList);
                 result += tempList.get(mid);
             }
         }
         System.out.println(result);
    }
}

