package prorammers.kakao;
// https://programmers.co.kr/learn/courses/30/lessons/12903
// 문자열 중간 추출 방법 
class Programmers12903Level1sMid {
  public String solution(String s) {
      String answer = "";
      
      int length = s.length();
      int index  = length/2; 
      if( length%2 == 0 ) {
          answer =  String.valueOf(s.charAt(index-1))+String.valueOf(s.charAt(index));
      } else {
          answer =  String.valueOf(s.charAt(index));
      }
      
      
      return answer;
  }
  
  // 좋은 남의 소스  substring >>  start위치 부터 end전까지 문자열 발췌
  public String solution2(String s) {
      
      int length = s.length();
      
      return s.substring((length-1) / 2, length/2 + 1);
  }
}