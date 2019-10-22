import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;

/*
 * https://www.acmicpc.net/problem/7568
 * 들어온 수들 index로 하여 > 2차원 배열로 구성
 * [ [ x , y ] , [ x , y ] ] >> int[][] oriData = new int[N][2]; 
 * 우선순위 큐를 이용해 데이터가 들어오면 MaxHeap으로 구성
 * 우선순위에서 하나 빼서 원본데이터가 몇등인지 확인한다.
 * 공통 순위가 있을 수 있으니 이전 랭크의 x y와 지금 뽑은 x y 와 둘 사이를 비교해서 우선순위가 없다면 같은 등수이다.
 * O( NlogN + N^2 )
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
            PriorityQueue<xySet> priorityQueue = new PriorityQueue<>();
            for (int index = 0; index < total; index++) {
                String[] line = br.readLine().split(" ");
                int x         = Integer.parseInt(line[0]);
                int y         = Integer.parseInt(line[1]);
                oriData[index] =  new int [] { x, y }; // 오리지널 데이터 
                xySet temp = new xySet(x, y);
                priorityQueue.offer(temp);// 우선순위 큐에 담기
            }
            
            int beforeX = 201;
            int beforeY = 201;
            int beforeRank = 1;
            int rank[] = new int[total];
            int indexTemp  = 0;
            
            while (!priorityQueue.isEmpty()) {
                xySet temp = priorityQueue.poll();
                int tempX = temp.x;
                int tempY = temp.y;
                System.out.println(" X :  " + tempX + " Y : " + tempY);
                indexTemp++;
                for (int index = 0; index < oriData.length; index++) {
                    int oriX = oriData[index][0];
                    int oriY = oriData[index][1];
                    if( oriX == tempX && oriY == tempY ) {
                        if( beforeX > oriX && beforeY > oriY ) {
                            rank[index] = indexTemp;
                            beforeX = oriX;
                            beforeY = oriY;
                            beforeRank = indexTemp;
                        } else {
                            rank[index] = beforeRank;
                        }
                    }
                }
            }
            for (int index = 0; index < rank.length; index++) {
                System.out.println(rank[index]);
            }
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


