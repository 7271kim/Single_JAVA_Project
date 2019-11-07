package prorammers.kakao;
// https://programmers.co.kr/learn/courses/30/lessons/12900
// 나머지가 나왔다 >> DP일 확률이 높다고 함.
// 하나하나 케이스 정리해보면 됨
// 1 = 1
// 2 = 2
// 3 = 3
// 4 = 5
// 5 = 8 
// 일반식 = F(n) = F(n-1) + F(n-2); >> DP 사용 
class Programmers12900DP {
  public int solution(int n) {
     int[] remember = new int[n+1];
     remember[1] = 1;
     remember[2] = 2;
     int check = 3;
     while( check <= n && 2 < check ) {
         remember[check] = ( remember[check-1] + remember[check-2] ) %1000000007; 
         check++;
     }
         
     return remember[n];
  }

}