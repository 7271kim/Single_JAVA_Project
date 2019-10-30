package algorithm.dataStructure;
import java.util.HashMap;

public class MyGraphHashMap {
    // 연결 리스트를 위한 Node클래스
    // 원하는 해쉬맵으로 우선 구현해보기 , 일반적이지 않은 방법
    // graph - A(Start)  - B (End) - 가중치
    //                   - C (End) - 가중치 
    
    //       - B(Start)  - A (End ) - 가중치
    //                   - C (End ) - 가중치
    // 이런식으로 저장할 생각
    private HashMap<Object, HashMap<Object, Integer>> graph;
    
    public MyGraphHashMap() {
        graph = new HashMap<Object, HashMap<Object,Integer>>();
    }
    public void push( Object start, Object end ) {
        push(start,  end, 0 ); 
    }
    
    public void push( Object start, Object end, int weight ) {
        if( !graph.containsKey(start) ) {
            HashMap<Object, Integer> temp = new HashMap<Object, Integer>();
            graph.put(start, temp);
        }
        graph.get(start).put(end, weight);
    }
    
    public void getVertex( Object vertex ) {
        if( graph.containsKey(vertex) ) {
            HashMap<Object, Integer> temp = graph.get(vertex);
            for (Object show : temp.keySet()) {
                System.out.println("연결된 노드 : "+ show + " 가중치 : " + temp.get(show));
            }
        }
    }
    
    public void print() {
        for (Object vertext : graph.keySet()) {
            for (Object show : graph.get(vertext).keySet()) {
                System.out.println("시작 노드 : "+ vertext + " >>연결된 노드 : " + show + " >>가중치 : " + graph.get(vertext).get(show));
            }
        }
    }
}
