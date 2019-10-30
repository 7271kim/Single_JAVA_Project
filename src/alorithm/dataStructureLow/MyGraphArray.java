package alorithm.dataStructureLow;
public class MyGraphArray {
    private int[][] graph;

    public MyGraphArray( int size) {
        graph = new int [size+1][size+1];
    }
    
    public void push( int start, int end ) {
        graph[start][end] = 1;
    }
    
    public void print() {
        for (int index = 1; index < graph.length; index++) {
            System.out.println("정점 " + index + "의 인접리스트");
            for (int indexInner = 0; indexInner < graph[index].length; indexInner++) {
                if( graph[index][indexInner] == 1 ) {
                    System.out.print( indexInner + " ");
                }
            }
            System.out.println();
        }
    }
}
