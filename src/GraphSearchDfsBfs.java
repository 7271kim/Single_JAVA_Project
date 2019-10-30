import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class GraphSearchDfsBfs {
    private ArrayList<ArrayList<int[]>> graph;
    private int size;
    
    // 정점의 개수
    public GraphSearchDfsBfs( int size) {
        this.graph = new ArrayList<ArrayList<int[]>>(); // 그래프 생성
        this.size  = size;
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

    /* 
    - 방문 여부를 확인하는 정점의 개수만큼 배열 준비
    - 원하는 정접을 Queue에 삽입한다.
    - Queue에서 하나 꺼내 방문여부를 확인한다.
    - 방문 안한 곳이라면 인접노드를 Queue에 삽입한다.
    - Queue가 빌 때 까지 반복한다.
    */
   
    public void searchBFS( int root ) {
        int[] check             = new int[size+1]; // 방문 여부 체크 
        StringBuilder result    = new StringBuilder();
        Queue<Integer> que      = new LinkedList<Integer>();
        que.add(root);
        while( !que.isEmpty() ){
            int top = que.poll();
            if( check[top] != 1 ) {
                check[top] = 1;
                result.append( top + " " );
                ArrayList<int[]> temp = graph.get( top );
                for (int index = 0; index < temp.size(); index++) {
                    que.add(temp.get(index)[0]);
                }
            }
        }
        System.out.println(result.toString());
    }
    
    /* 
     root 노드를 방문 후 방문 노드 추가 
     root 노드와 인접한 노드가 있다면 들어가기 
         방문 노드 추가 해당 기준점으로 root로 다시 시작
         방문 노드들이 DFS 결과로 표시
     loop
    */
    public void searchDFS( int root ) {
        int[] check             = new int[size+1]; // 방문 여부 체크 
        StringBuilder result    = new StringBuilder();
        result = dfs( root, check, result );
        
        System.out.println(result.toString());
    }
    
    public StringBuilder dfs( int root, int[] check, StringBuilder result ) {
        if( check[root] != 1 ) {
            check[root] = 1;
            ArrayList<int[]> temp = graph.get(root);
            result.append( root + " " );
            for (int index = 0; index < temp.size(); index++) {
                dfs( temp.get(index)[0], check, result );
            }
        }
        return result;
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
