package algorithm.mst;
import java.util.PriorityQueue;

import alorithm.dataStructureLow.MyGraphLinked;

/*
 자료구조는 시작노드 끝노드 가중치의 조합의 노드로 구성
모든 간선들의 가중치의 오름차순으로 정렬 ( 우선순위 Que이용 )
pop()을 통해 가장 작은 비용의 간선 선택
Union-Find알고리즘으로 사이클 여부 확인 사이클이 아닐 경우 시작과 끝 노드를 union결합 후 비용을 더한다.
우선순위 큐가 빌 떄 까지 계속한다.
*/
public class MstKuskal {
    private PriorityQueue<NodeClass> prique;
    private MyGraphLinked graph; // MST만 따로 저장하기 위한 그래프 
    private int[] union; // 유니온 결합을 위해 만들어 놓음
    
    // 기본 자료구조를 위한 세팅
    private class NodeClass implements Comparable<NodeClass> { 
        private int start; 
        private int end;
        private int weight;
        
        private NodeClass( int start, int end, int weight ) {
            this.start  = start;
            this.end    = end;
            this.weight = weight;
        }

        // 우선순위 큐를위해 , 작은 친구를 맨 위로 올린다.
        @Override
        public int compareTo(NodeClass target) {
            return target.weight > weight ? -1 : 1;
        }
    } 
    
    public MstKuskal( int nodeCount ) {
        prique  = new PriorityQueue<NodeClass>();
        graph = new MyGraphLinked(nodeCount);
        
        //유니온 결합을 위해 세팅
        union   = new int[nodeCount+1];
        for (int index = 0; index < union.length; index++) {
            union[index] = index;
        }
    }
    
    public void push( int start, int end, int weight ) {
        NodeClass temp = new NodeClass(start, end, weight);
        prique.add(temp);
    }
    
    public int getMinValue() {
        int result = 0;
        while( !prique.isEmpty() ) {
            //poll()을 통해 가장 작은 비용의 간선 선택
            NodeClass temp = prique.poll();
            int start   = temp.start;
            int end     = temp.end;
            int weight  = temp.weight; 
            
            if( unionFind(start) != unionFind(end) ) {
                result += weight;
                union( start , end );
                graph.push(start, end, weight);
            }
            
        }
        
        return result;
    }
    
    //유니온 결합 Root 확인 
    private int unionFind( int start ){
        int result = union[start]; 
        
        if( start == result ) return result;
        result = unionFind(union[result]);
        
        return result;
    }
    
    // 유니온 결합
    private void union( int start, int end ){
        int startRoot = unionFind(start);
        int endRoot   = unionFind(end);
        union[endRoot] = startRoot; 
    }
    
    public void printGraph(){
        graph.print();
    }
    
}
