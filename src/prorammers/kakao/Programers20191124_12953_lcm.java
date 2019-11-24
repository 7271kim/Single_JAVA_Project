package prorammers.kakao;
//https://programmers.co.kr/learn/courses/30/lessons/12953
class Programers20191124_12953_lcm {
  public int solution(int[] arr) {
      return lcm(arr);
  }
  
  public int lcm( int[] input ) {
      int lcm    = input[0];
      for (int index = 1; index < input.length; index++) {
          int b = input[index];
          lcm =  ( lcm * b ) / gcd( b , lcm % b );
      }   
      return lcm;
  }
   
  public int gcd( int a, int b ) {
      if( b == 0 ) return a;
      return gcd( b , a % b );
  }
  public int lcm( int a, int b ) {
      return ( a * b ) / gcd( b , a % b );
  }
}