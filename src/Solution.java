// https://programmers.co.kr/learn/courses/30/lessons/12900 >> 풀어보기
// https://coding-all.tistory.com/2
// https://do-rang.tistory.com/9
class Solution {
    int [][] memorization;
    
  public int solution(int n) {
      int answer = 0;
      int maxTwo = n /2;
      memorization = new int[n+1][n+1];
      
        /*for (int index = 0; index < maxTwo; index++) {
          answer += combination( n, index );
        }*/
      
      System.out.println(combination( 100, 2 ));
      return answer;
  }
  
  //nCr = n-1Cr-1 + n-1Cr , DP 응용
  public int combination(int n, int r) { 
      
      if( memorization[n][r] != 0 ) return memorization[n][r];
      
      if(n == r || r == 0) return 1;
      
      memorization[n][r]   = combination(n-1 , r-1) + combination(n-1 , r);
      memorization[n][n-r] = memorization[n][r]; 
      
      return memorization[n][r];
  }

}