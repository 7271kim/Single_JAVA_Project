package prorammers.kakao;
// https://programmers.co.kr/learn/courses/30/lessons/12901
class Programmers12901Level1Calender {
  public String solution(int a, int b) {
      String answer = "";
      
      int[] days    = { 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
      String[] week = { "FRI", "SAT", "SUN", "MON", "TUE", "WED", "THU" };
      
      int totalDays = 0;
      for (int index = 0; index <= a-2; index++) {
          totalDays += days[index];
      }
      
      totalDays += b-1;
      
      answer = week[totalDays%7];
      
      
      return answer;
  }
}