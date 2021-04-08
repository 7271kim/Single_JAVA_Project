package algorithm.math;

import java.util.ArrayList;
import java.util.List;

public class CombinationOriginal<T> {
    
    private List<int[]> result;
    private int start;
    private boolean[] visited;
    private int[] orignal;
    private int n;
    private int r;
    
    public CombinationOriginal ( int[] orignal, int n, int r ) {
        this.orignal = orignal;
        this.n = n;
        this.r = r;
        result = new ArrayList<>();
        visited = new boolean[n];
        setCombination( orignal, n, r, 0 );
    }
    
    public List<int[]> getCombination() {
        return result;
    };
    
    private void setCombination( int[] input, int n, int r, int next ) {
        if( r == 0 ) {
            oneDataIntoResult();
        } else {
            for( int index = next; index< input.length; index++ ) {
                visited[index] = true; // 1개의 수를 뽑는다. 뽑은 수를 다음 단계로 전달하는 방벙중 하나.
                setCombination( input, index, r-1, index+1 );
                visited[index] = false;
                
            }
        }
    };
    
    private void oneDataIntoResult() {
        int[] one = new int[r];
        int temp = 0;
        for( int index = 0; index< visited.length; index++ ) {
            if( visited[index] ) {
                one[temp++] = orignal[index];
            }
        }
        result.add(one);
    }
}
