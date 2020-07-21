package alorithm.dataStructureLow;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class GraphMatrix {
    private int[][] graph;
    private int nodeSize = 0;

    public GraphMatrix( int nodeSize ) {
        graph = new int [nodeSize][nodeSize];
        this.nodeSize = nodeSize;
    }
    
    public void push( int orignalNode, int adjacentNode, int weight ) {
        graph[orignalNode][adjacentNode] = weight;
        graph[adjacentNode][orignalNode] = weight;
    }
    
    public void bfs() {
        if( nodeSize == 0 ) {
            throw new IndexOutOfBoundsException(" 노드가 비어있습니다.");
        }
        int[] cache = new int[nodeSize];
        Queue<Integer> nodesQue = new LinkedList<>();
        bfsLoopChecking(0, cache, nodesQue);
    }
    
    public void dfs() {
        if( nodeSize == 0 ) {
            throw new IndexOutOfBoundsException(" 노드가 비어있습니다.");
        }
        int[] cache = new int[nodeSize];
        Stack<Integer> nodesStack = new Stack<Integer>();
        dfsLoopChecking(0, cache, nodesStack);
    }
    
    private void bfsLoopChecking( int orignalNode, int[] cache , Queue<Integer> nodesQue ) {
        nodesQue.add( orignalNode );
        while ( !nodesQue.isEmpty() ) {
            Integer nextNodes = nodesQue.poll();
            if( cache[nextNodes] == 0 ){
                cache[nextNodes] = 1;
                System.out.println( nextNodes + " 탐색하였습니다.");
                for( int childNode = 0; childNode <  graph[nextNodes].length; childNode++ ) {
                    if( graph[nextNodes][childNode] != 0 && cache[childNode] == 0 ) {
                        nodesQue.add(childNode);
                    }
                }
            }
        }
    }
    
    private void dfsLoopChecking( int orignalNode, int[] cache , Stack<Integer> nodesStack ) {
        nodesStack.add( orignalNode );
        while ( !nodesStack.isEmpty() ) {
            Integer nextNodes = nodesStack.pop();
            if( cache[nextNodes] == 0 ){
                cache[nextNodes] = 1;
                System.out.println( nextNodes + " 탐색하였습니다.");
                for( int childNode = graph[nextNodes].length -1; childNode > 0; childNode-- ) {
                    if( graph[nextNodes][childNode] != 0 && cache[childNode] == 0 ) {
                        nodesStack.add(childNode);
                    }
                }
            }
        }
    }
}
