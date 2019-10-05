import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;
/*
 * https://www.acmicpc.net/problem/1316
 * 종은 풀이
 */
public class Main {
    public static void main(String[] args) {
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
            int[] or = {1,4,5,9,7};
             IndexTree temp = new IndexTree(or);
             temp.printOriginal();
        } catch (Exception e) {
        }     
    }
}

class IndexTree {
    private int data[];
    private int originalStart;
    
    public IndexTree( int[] orignal ) {
        int originalSize = orignal.length;
        originalStart = 1;
        
        // 1 + 2 + 2^2 + 2^3 + 2^4....마지막 리프를 제외한 부모리프까지의 합을 구하는 방법 5개인 경우 2^3 보다 작으니 딱 2^3 = 8이 시작위치임 
        while (originalStart < originalSize)
            originalStart <<= 1;
        
        //data의 크기는 originalStart의 2배
        data = new int[originalStart*2];
        
        for( int index = 0; index < orignal.length; index++ ) {
            update( index, orignal[index] );
        }
    }
    
    public void update( int index, int value) {
        index += originalStart;
        int beforeValue = data[index];
        while(index > 0) {
            data[index] = data[index] - beforeValue + value;
            index>>=1;
        }
    }

   public int sumInterVal( int start, int end ) {
       start += originalStart;
       end += originalStart;
       int sum = 0;
       while ( start < end ) {
           if( start%2 == 1) {
               sum += data[start]; 
               start++;
           }
           if( end%2 == 0) {
               sum += data[end];
               end--;
           }
           start>>=1;
           end>>=1;
       }
       if( start == end ) {
           // 같은 노드내 탐색일 경우 합치기
           sum += data[start];
       }
       
       return sum;
   }
   public int sumTotal( int end ) {
       int start = 0;
       start += originalStart;
       end += originalStart;
       int sum = 0;
       while ( start < end ) {
           if( start%2 == 1) {
               sum += data[start]; 
               start++;
           }
           if( end%2 == 0) {
               sum += data[end];
               end--;
           }
           start>>=1;
           end>>=1;
       }
       if( start == end ) {
           sum += data[start];
       }
       
       return sum;
   }
    public void printOriginal() {
        for (int index = 0; index < data.length; index++) {
            System.out.print(data[index] + " ");
        }
        System.out.println();
    }
}
