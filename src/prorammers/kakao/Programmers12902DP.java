package prorammers.kakao;
import java.math.BigDecimal;

// https://programmers.co.kr/learn/courses/30/lessons/12902
// 길이가 2인경우 가지수는 3개
// 홀수의 경우 세로 3 * 5 = 15로 막대는 항상 짝수기 때문에 불가능하다.
// 즉 F( n+2 ) = 3*F(n)이 기본 + 예외 케이스를 추가해줘야 한다.
// 예외 케이스는  0 1 2 3 4 에서  1 ~ 3부분이 가로일때 즉 2와 그다음 4사이 경계가 가로인 경우가 예외 케이스 이다.
// 예외케이스 = f(n)까지의 전체 케이스 중 - 예외 케이스 가 발생하지 않을 케이스 f(n-2) 즉 n -2 ~ n까지가 전체 가로인 경우 n -2 ~ n 까지 전체 가로라고 가정 하면 f(n-2)까지의 경우의 수
// f( n +2 ) = 3*f(n) + f(n) - f(n-2)
// f ( n + 2 ) = 4*f(n) - f(n-2)
// 정답은 맞는데 문제는 나머지로 저장해야하다보니 큰수가 될때 - 때문에 음수가 나오는 함정이 존재 -_- 하아... 빅데시멀 연산사용
// 좀 설명이 논리적이지 않음 다시 확인이 필요


class Programmers12902DP {
  public int solution(int n) {
      BigDecimal[] remember = new BigDecimal[5 + n];
      remember[0] = new BigDecimal(0);
      remember[1] = new BigDecimal(0);
      remember[2] = new BigDecimal(3);
      remember[3] = new BigDecimal(0);
      remember[4] = new BigDecimal(11);
      
      for (int index = 5; index <= n; index++) {
          remember[index] = new BigDecimal(0);
      }
      
      for (int index = 6; index <= n; index++) {
          remember[index] = remember[index-2].multiply(new BigDecimal(4)).subtract(remember[index-4]); 
      }
      BigDecimal reminder = remember[n].remainder(new BigDecimal(1000000007));
      return reminder.intValue();
  }
}
