class Solution {
    public int solution(Tree T) {
        
        int[] containNumber = new int[50001];
        int maxCount = 0;
        
        maxCount = check( T, maxCount , containNumber );
        
        return maxCount;
    }
    
    public int check( Tree T, int maxCount, int[]containNumber ) {
        int root = T.x;
        int leftMax  = 0;
        int rightMax = 0;
        
        if( containNumber[root] > 0 ) {
            containNumber[root] += 1;
            return maxCount;
        }
        containNumber[root] += 1;
        maxCount++;
        
        if(T.l != null) {
            leftMax = check( T.l, maxCount, containNumber );
            containNumber[T.l.x] -= 1;
        }
        
        if(T.r != null) {
            rightMax = check( T.r, maxCount, containNumber );
            containNumber[T.r.x] -= 1;
        }
        maxCount = maxCount < leftMax ? leftMax : maxCount;
        maxCount = maxCount < rightMax ? rightMax : maxCount;
        
        return maxCount;
    }
    
}
