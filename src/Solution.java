//https://programmers.co.kr/learn/courses/30/lessons/12924
class Solution {
  public int solution(int n) {
      int answer     = 0;
      for (int index = 1; index <= n; index++) {
          int tempSum   = 0;
          int tempIndex = index;
          while( tempSum < n ) {
              tempSum+=tempIndex;
              if( tempSum == n ) {
                  answer++;
              }
              tempIndex++;
          }
      }
      return answer;
  }
}