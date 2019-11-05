package algorithm.math;

public class Permutation2 {
    private int[][] returnArr;
    private int totalCount;
    boolean[] visited;
    int[] output;
    
    /*
     *사용성 쉽게 변화
     */
    
    public Permutation2( int[] input, int r ) {
        int orignalSize = input.length;
        int totalSize = totalSize( orignalSize ) ;
        this.returnArr = new int[totalSize][r];
        this.visited   = new boolean[orignalSize];
        this.output    = new int[orignalSize];
        this.totalCount = 0;
        
        //permutationDictionary( input, 0, orignalSize, r );
        permutationSwap( input, 0, orignalSize, r );
        
    }
    
    public void newSetting( int[] input, int r ) {
        int orignalSize = input.length;
        int totalSize = totalSize( orignalSize ) ;
        this.returnArr = new int[totalSize][r];
        this.visited   = new boolean[orignalSize];
        this.output    = new int[orignalSize];
        this.totalCount = 0;
        
        permutationDictionary( input, 0, orignalSize, r );
    }
    
    public void permutationDictionary(int[] input, int depth, int n, int r) {
        if(depth == r) {
            int[] temp = new int[r];
            for (int index = 0; index < r; index++) {
                temp[index] = output[index];
            }
            returnArr[totalCount++] = temp;
        }
        for(int i=0; i<n; i++) {
            if(visited[i] != true) {
                visited[i] = true; 
                output[depth] = input[i];
                permutationDictionary(input, depth + 1, n, r);       
                visited[i] = false; 
                output[depth] = 0;
            }
        }
    }
    
    public void permutationSwap( int[] input, int depth, int n, int r ) {
        if(depth == r) {
            int[] temp = new int[r];
            for (int index = 0; index < r; index++) {
                temp[index] = input[index];
            }
            returnArr[totalCount++] = temp;
        }
        // depth부터 마지막까지 반복
        for(int i=depth; i<n; i++) {
            //Swap로직
            swap(input, depth, i);
            permutationSwap(input, depth + 1, n, r);
            // 원상 복구 로직
            swap(input, depth, i);
        }
    }
    
    public void swap(int[] input, int before, int after) {
        int temp = input[before];
        input[before] = input[after];
        input[after] = temp;
    }
    
    public int totalSize( int input ) {
        if( input == 1 ) return 1;
        
        return input*totalSize(input-1);
    }
    
    public int[][] getReturnArr() {
        return returnArr;
    }
}
