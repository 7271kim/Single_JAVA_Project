//https://programmers.co.kr/learn/courses/30/lessons/12900 >> 풀어보기
class Solution {
  public int solution(int n) {
      int answer = 0;
      int maxTwo = n /2;
      for (int index = 0; index < maxTwo; index++) {
          answer += combination( index + ( n - maxTwo), index );
      }
      return answer %1000000007;
  }
  
  //nCr = n-1Cr-1 + n-1Cr
  public int combination(long n, long r) { 
      
      if(n == r || r == 0) return 1; 
      
      long temp1 = combination(n - 1, r - 1);
      long temp2 = combination(n - 1, r);
      
      return (int)( temp1 + temp2 );
  }

}