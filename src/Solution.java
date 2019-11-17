//https://programmers.co.kr/learn/courses/30/lessons/12911
class Solution {
  public int solution(int n) {
      int nOne = Integer.bitCount(n);
      int next = n+1;
      while(Integer.bitCount(next) != nOne) {
          next++;
      }
      return next;
  }
  
}