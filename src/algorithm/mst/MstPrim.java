package algorithm.mst;

import java.util.ArrayList;
import java.util.PriorityQueue;

import alorithm.dataStructureLow.MyGraphLinked;

/*
우선순위 큐, 그리고 정점 유무를 확인하는 변수가 필요, 기본 graph에다가 변수 삽입
원하는 root를 선택 후 해당 정점과 연결된 모든 간선들을 우선순위 큐에 넣는다.
우선순위 que에서 하나 뽑은 간선의 끝 노드가 기존 연결되었던 노드인지 확인
아니라면 check후 해당 노드와 연결된 간선 우선순위 큐에 넣기
N-1번 반복한다. » ST의 간선의 갯수는 N-1
*/
public class MstPrim {
    private PriorityQueue<NodeClass> prique;
    private int[] check; 
    private MyGraphLinked orignal;
    private MyGraphLinked graph; // MST만 따로 저장하기 위한 그래프
    private int nodeSize;
    
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
    
    public MstPrim( int nodeCount ) {
        prique  = new PriorityQueue<NodeClass>();
        graph   = new MyGraphLinked(nodeCount);
        orignal = new MyGraphLinked(nodeCount);
        check   = new int[nodeCount+1];
        nodeSize = nodeCount;
    }
    
    public void push( int start, int end, int weight ) {
        orignal.push(start, end, weight);
    }
    public int getMinValue() {
        return getMinValue(1);
    }
    public int getMinValue(int start) {
        int result      = 0;
        int startData   = start; 
        
        // N-1회 진행
        for (int index = 0; index < nodeSize-1; index++) {
            
            //원하는 root를 선택 후 해당 정점과 연결된 모든 간선들을 우선순위 큐에 넣는다.
            check[startData] = 1;
            putPri(start);
            
            //우선순위 que에서 하나 뽑은 간선의 끝 노드가 기존 연결되었던 노드인지 확인
            while( !prique.isEmpty() ) {
                NodeClass top = prique.poll();
                int startNode = top.start;
                int endNode = top.end;
                int weight = top.weight;
                
                if( check[endNode] == 0 ) {
                    check[endNode] = 1;
                    result += weight;
                    putPri(endNode);
                    graph.push(startNode, endNode, weight);
                }
            }
        }
        return result;
    }
    
    public void putPri( int index ){
        ArrayList<int[]> temp = orignal.getData(index);
        
        for (int indexInner = 0; indexInner < temp.size(); indexInner++) {
            int startNode = index;
            int endNode   = temp.get(indexInner)[0];
            int weight    = temp.get(indexInner)[1];
            NodeClass tempNode = new NodeClass(startNode, endNode, weight);
            prique.add(tempNode);
        } 
    }
    
    public void printGraph(){
        graph.print();
    }
    
}

