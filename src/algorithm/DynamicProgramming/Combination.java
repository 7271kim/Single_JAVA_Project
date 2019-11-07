package algorithm.DynamicProgramming;

// 동적 계획법의 일부 nCr = n-1Cr-1 + n-1Cr
public class Combination {
    
    // 메모이제이션을 위한 저장을 위한 공간
    int[][] memorization;
    
    public Combination( int n, int r ) {
      memorization = new int[n+1][r+1];
    }
    
    
    // 동적 계획법의 일부 nCr = n-1Cr-1 + n-1Cr
    public int combinationTopDow( int n, int r )  {
        // 기존 값이 있다면 리턴한다.
        if( memorization[n][r] != 0 ) return memorization[n][r];
        
        if( r == 0 || n == r ) {
            return 1;
        } else {
            int before = combinationTopDow( n - 1 , r - 1 );
            int second = combinationTopDow( n - 1 , r );
            memorization[n][r] = before + second;
            memorization[n][n-r] = before + second;
            
            return memorization[n][r];
        }
           
    }
    
    
    public int combinationBottomUp( int n, int r )  {
        // 기존 값이 있다면 리턴한다.
        if( memorization[n][r] != 0 ) return memorization[n][r];
        
        // 처음 디폴츠를 세팅해 놓는다 
        for ( int indexN = 1; indexN < n; indexN++ )
            for ( int indexR = 0; indexR <= indexN && indexR <= r; indexR++ )
                if( indexR == 0 || indexN == indexR ) memorization[ indexN ][ indexR ] = 1;
                        
        // 점차 나아가면서 답을 세팅해 놓는다.
        for (int indexN = 2; indexN <= n; indexN++)
            for (int indexR = 1; indexR  < indexN && indexR <= r; indexR++)
                memorization[indexN][indexR] = memorization[indexN-1][indexR-1] + memorization[indexN-1][indexR];
        
        return memorization[n][r];
    }
    
}
