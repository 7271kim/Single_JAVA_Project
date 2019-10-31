package alorithm.dataStructureLow;
public class MyGraphArray {
    private int[][] graph;
    private int size;
    private int max = 10000000;

    public MyGraphArray( int size) {
        this.size = size+1;
        graph = new int [this.size][this.size];
        
        for (int start = 1; start < size; start++) {
            for (int end = 1; end < size; end++) {
                if( start == end ) {
                    graph[start][end] = 0;
                } else {
                    graph[start][end] = max;
                }
            }
        }
    }
    
    public void push( int start, int end ) {
        push(start,end,1);
    }
    
    public void push( int start, int end, int weight ) {
        graph[start][end] = weight;
    }
    
    public void print() {
        System.out.print("\t\t");
        for (int index = 1; index < size; index++) {
            System.out.print("Index : " + index+ "\t");
        }
        System.out.println();
        for (int start = 1; start < size; start++) {
            System.out.print("Index : " + start+ "\t");
            for (int end = 1; end < size; end++) {
                if( graph[start][end] == max) {
                    System.out.print("X" + "\t\t");
                } else {
                    System.out.print(graph[start][end] + "\t\t");
                }
            }
            System.out.println();
        }
    }
    
    public int[][] getGraph (){
        return graph;
    }
}
