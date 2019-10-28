import java.util.ArrayList;

/* 저장 형식
   1  - 2
      - 3
   2  - 1
      - 3
*/
public class MyGraphLinked {
    private ArrayList<ArrayList<Integer>> graph;

    public MyGraphLinked( int size) {
        this.graph = new ArrayList<ArrayList<Integer>>(); // 그래프 생성
        
        // 각각의 정점들 초기화
        for(int i=0; i<size+1; i++) {
            graph.add(new ArrayList<Integer>());
        }
    }
    
    public void push( int start, int end ) {
        graph.get(start).add(end);
    }
    
    public void print() {
        for (int index = 1; index < graph.size(); index++) {
            System.out.println("정점 " + index + "의 인접리스트");
            for (int indexInner = 0; indexInner < graph.get(index).size(); indexInner++) {
                System.out.print( graph.get(index).get(indexInner) + " ");
            }
            System.out.println();
        }
    }
}
