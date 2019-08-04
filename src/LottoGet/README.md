### 1.GetLotto.JAVA [2019.08.04 전체로직 변경]
 - totalGetLotto 로또구매 갯수
 - noDate : 과거 noDate번 동안 나오지 않은 수
 - CheckNoDate : 과거 noDate번 동안 나오지 않은 수 제거 할것인가
 - pickBefore : 먼저 뽑아놓을 수 
 - noPick : 나오면 안되는 수
 - check_1_10, check_11_20, check_21_30, check_31_40, check_41_45 : 구간별 pick

### 1.StaticClassLotto.JAVA
- wishGet : 3이라면 3개의 번호를 맞출 때 까지 계속 뽑음 
- wishTotalCount : 1000이라면 wishGet(3)개의 번호가 맞는 것을  1 싸이클이라 치고 1000번 을 반복
- thisLotto : 당첨 번호 
- GetLotto.java와 나머지 동일 
- 결과 
: 5개를 맞추기 위해 통계적으로 몇 회 사야 하냐 : 211.0번 사야한다.
