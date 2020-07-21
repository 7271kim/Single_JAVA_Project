package alorithm.dataStructureLow;

import java.util.ArrayList;

public class GraphArrayList {
    private ArrayList<ArrayList<int[]>> graph;
    
    // 정점의 개수로 초기화
    public GraphArrayList( int nodeSize ) {
        this.graph = new ArrayList<ArrayList<int[]>>();
        
        // 각각의 정점들 초기화
        for( int index=0; index < nodeSize; index++ ) {
            graph.add( new ArrayList<int[]>() );
        }
    }
    
    public void push( int orignalNode, int adjacentNode, int weight ) {
        pushOnly( orignalNode, adjacentNode, weight );
        pushOnly( adjacentNode, orignalNode, weight );
    }
    
    private void pushOnly( int orignalNode, int adjacentNode, int weight ) {
        int[] temp = { adjacentNode, weight };
        graph.get(orignalNode).add(temp);
    }
    
    public void print() {
        for (int index = 0; index < graph.size(); index++) {
            System.out.println("정점 " + index + "의 인접리스트");
            for (int indexInner = 0; indexInner < graph.get(index).size(); indexInner++) {
                int[] temp = graph.get(index).get(indexInner);
                System.out.print( temp[0]+"("+temp[1]+")" + " ");
            }
            System.out.println();
        }
    }
}
