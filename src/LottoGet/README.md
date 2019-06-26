### 1.GetLotto.JAVA
- thisLotto : 구매 후 금주 당첨 번호 작성란 > 작성하면  금주 몇개 맞았는지 확인 가능
- beforePick : 항상 들어갈 번호 선택
- noNumber : 나오면 안되는 번호 선택 ( beforePick에 들어간 숫자 적어주기 )
- checkOverlay.put(0, 2) : 1 ~ 10번까지 숫자중 2개의 숫자를 골라야 한다면 checkOverlay.put(0, 2)
- checkOverlay.put(1, 1) : 11 ~ 20번까지 숫자중 1개의 숫자를 골라야 한다면 checkOverlay.put(1, 1)
- checkOverlay.put(2, 0) : 21 ~ 30번까지 숫자 랜덤 checkOverlay.put(2, 0)
- checkOverlay.put(3, 0) : 31 ~ 40 번까지 숫자 랜덤 checkOverlay.put(3, 0)
- checkOverlay.put(4, 0) : 41 ~ 45 번까지 숫자 랜덤 checkOverlay.put(4, 0)

### 1.StaticClassLotto.JAVA
- wishGet : 3이라면 3개의 번호를 맞출 때 까지 계속 뽑음 
- wishTotalCount : 1000이라면 wishGet(3)개의 번호가 맞는 것을  1 싸이클이라 치고 1000번 을 반복
- thisLotto : 당첨 번호 
- GetLotto.java와 나머지 동일 
- 결과 
: 5개를 맞추기 위해 통계적으로 몇 회 사야 하냐 : 211.0번 사야한다.
