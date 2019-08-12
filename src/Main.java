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
         * https://matice.tistory.com/54
         */
        /*
        Index Tree란 (https://matice.tistory.com/54)
        - 구간 합을 구하는 트리의 종류로 인덱스 트리, 세그먼트 트리, 펜윅 트리가 있다. 세그먼트 트리는 인덱스 트리가 포함하고 있는 한 종류이다. 세그먼트 트리로 풀 수 있는 문제는 인덱스 트리로 모두 풀 수 있다.
        - 노드의 갯수는 최적으로 N * 2개지만, 인덱스 0을 쓰기위해서 N*2*2개 
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

class IndexTree {
    private int data[];
    
    public IndexTree( int[] orignal ) {
        data = new int[orignal.length*4];
        for( int index = 1; index < orignal.length; index++ ) {
            updateTree(orignal[index], 1);
        }
    }
    
    public void updateTree( int i, int x) {
        i += S;
        data[i] += x;
        while (i > 1) {
            i >>= 1;
                 data[i] = data[2 * i] + data[2 * i + 1];
        }

    }
}

