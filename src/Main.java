import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Scanner;

/*
 * https://www.acmicpc.net/problem/7568
 * 기본이 잘안됨.
 * 그냥 쉽게 생각하면 자기 데이터 기준으로 rank를 구한다.
 * [ [x,y] , [ x,y ] ..] 우선 데이터를 다 담고 1개 1개의 우선순위 rank를 구한다.
 * 시간복잡도 
 * O( N^2 )
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
            
            int total = Integer.parseInt(br.readLine());
            int[][] oriData = new int[total][2];
            for (int index = 0; index < total; index++) {
                String[] line = br.readLine().split(" ");
                int x         = Integer.parseInt(line[0]);
                int y         = Integer.parseInt(line[1]);
                oriData[index] =  new int [] { x, y }; // 오리지널 데이터 
            }
            for (int index = 0; index < oriData.length; index++) {
                int rank = 1;
                for (int indexInner = 0; indexInner < oriData.length; indexInner++) {
                    int aa  = oriData[index][0];
                    int bb  = oriData[indexInner][0];
                    int cc  = oriData[index][1] ;
                    int dd  = oriData[indexInner][1];
                    if( oriData[index][0] < oriData[indexInner][0] && oriData[index][1] < oriData[indexInner][1] ) {
                        rank++;
                    }
                }
                resultString.append(rank + " ");
            }
            System.out.println(resultString.toString());
        } catch (Exception e) {
            System.out.println(e);
        } 
    }
}

class xySet implements Comparable<xySet>{
    int x;
    int y;
    public xySet( int x, int y ) {
        this.x = x;
        this.y = y;
        
    }
    @Override
    public int compareTo(xySet target) {
        // return : 1 이면 지금 객체를 우선순위 큐 아래로 내린다.
        // return : 0 이면 지금 객체는 움직이지 않는다.
        // return : -1 이면 지금 객체를 우선순위 큐 위로 올린다.
        
        int result = 1; // 1이면 지금 객체를 우선순위 큐 아래로 내린다. 
        if ( this.x >= target.x && this.y >= target.y ) {
            result = -1; // -1이면 지금 지금 객체를 우선순위 큐 위로 올린다.
        } 
        return result;
    }
}


