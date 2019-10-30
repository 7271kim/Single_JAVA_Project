package algorithm.shortestPath;


import java.util.ArrayList;
import java.util.PriorityQueue;

import alorithm.dataStructureLow.MyGraphLinked;

/*
이 또한 ST이기 때문에 N-1번만 진행하면 된다.
시작지점을 선택하고 시작지점부터 각각의 구간까지 최소거리를 계산하기 위한 distance[]배열을 노드 크기만큼 세팅
시작지점과 연결된 노드중 비용이 최소값 변화가 있는 distance를 업데이트 한 후 우선순위 큐에 넣는다.
우선 순위 큐 맨 위에 있는 노드를 기준으로 반복한다.
*/
public class SpDijkstra {
    private PriorityQueue<NodeClass> prique;
    private MyGraphLinked orignal;
    private int nodeSize;
    private int[] distance;
    
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
    
    public SpDijkstra( int nodeCount ) {
        prique  = new PriorityQueue<NodeClass>();
        orignal = new MyGraphLinked(nodeCount);
        nodeSize = nodeCount;
        distance = new int[nodeSize+1];
        
        // distance 초기화
        for (int index = 0; index < distance.length; index++) {
            distance[index] = -1;
        }
        
    }
    
    public void pushOnly( int start, int end, int weight ) {
        orignal.pushOnly(start, end, weight);
    }
    
    public void push( int start, int end, int weight ) {
        orignal.push(start, end, weight);
    }
    public void getMinValue() {
        getMinValue(1);
    }
    public void getMinValue(int start) {
        int startData   = start;
        //시작지점과 연결된 노드중 비용이 최소값 변화가 있는 distance를 업데이트 한 후 우선순위 큐에 넣는다.
        distance[startData] = 0;
        putPri( startData );
        
        // N-1 진행
        for (int index = 0; index < nodeSize-1; index++) {
             // 우선순위 큐 맨 위에있는 것으로 확인
            if(prique.isEmpty()) break;
            startData = prique.poll().end;
            
            // 시작지점과 연결된 노드중 비용이 최소값 변화가 있는 distance를 업데이트 한 후 인접 정점을 우선순위 큐에 넣는다.
            ArrayList<int[]> temp = orignal.getData(startData);
            for (int innerIndex = 0; innerIndex < temp.size(); innerIndex++) {
                int endNode = temp.get(innerIndex)[0];
                int weight  = temp.get(innerIndex)[1];
                int changeValue = distance[startData] + weight;
                int nowValue    = distance[endNode];
                if( nowValue > changeValue || nowValue == -1 ) {
                    distance[endNode] = changeValue;
                    putPri ( endNode );
                }
            }
            
            
            
        }
    }
    
    public void putPri( int index ){
        ArrayList<int[]> temp = orignal.getData(index);
        
        for (int indexInner = 0; indexInner < temp.size(); indexInner++) {
            int startNode = index;
            int endNode   = temp.get(indexInner)[0];
            int weight    = temp.get(indexInner)[1];
            NodeClass tempNode = new NodeClass(startNode, endNode, weight);
            
            if( distance[endNode] == -1 ) {
                distance[endNode] = distance[startNode] + weight;
            }
            
            prique.add(tempNode);
        } 
    }
    
    public void print() {
        for (int index = 1; index < distance.length; index++) {
            System.out.println("index : " + index + " 거리 : " + distance[index]);
        }
    }
}

