package alorithm.dataStructureLow;
import java.util.ArrayList;

/* 저장 형식
   1  - [2 , 가중치]
      - [3 , 가중치]
   2  - 1
      - 3
*/
public class MyGraphLinked {
    private ArrayList<ArrayList<int[]>> graph;
    
    // 정점의 개수
    public MyGraphLinked( int size) {
        this.graph = new ArrayList<ArrayList<int[]>>(); // 그래프 생성
        
        // 각각의 정점들 초기화
        for(int i=0; i<size+1; i++) {
            graph.add(new ArrayList<int[]>());
        }
    }
    
    // 단방향 노드로 세팅
    public void pushOnly( int start, int end ) {
        int weight = 0;
        int[] temp = { end, weight };
        graph.get(start).add(temp);
    }
    public void pushOnly( int start, int end, int weight ) {
        int[] temp = { end, weight };
        graph.get(start).add(temp);
    }
    
    
    // 양방향 노드로 세팅
    public void push( int start, int end ) {
        pushOnly( start, end );
        pushOnly( end, start );
    }
    
    public void push( int start, int end, int weight ) {
        pushOnly( start, end, weight );
        pushOnly( end, start, weight );
    }
    
    
    public void print() {
        for (int index = 1; index < graph.size(); index++) {
            System.out.println("정점 " + index + "의 인접리스트");
            for (int indexInner = 0; indexInner < graph.get(index).size(); indexInner++) {
                int[] temp = graph.get(index).get(indexInner);
                System.out.print( temp[0]+"("+temp[1]+")" + " ");
            }
            System.out.println();
        }
    }
}
