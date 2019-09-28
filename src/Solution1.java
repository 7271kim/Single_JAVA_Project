// solution("aaaaa") , solution("aaaaabb") 등 인풋이 들어왔을 때 연속된 3글자가 나오는 케이스는 초기화 필요하다고 판단하고 코딩을 진행  
// 초기화는 연속된 3글자 이상인 경우는 2개로 모든것을 초기화
// 기존 max와 비교하여 최대값 구함

class Solution1 {
    public int solution(String S) {
       String[] temp  = S.split("");
       int max        = -1;
       int aCount     = 0;
       int bCount     = 0;
       int countText  = 0;
       
       for (int index = 0; index < temp.length; index++) {
           // 전체 카운트
           countText++;
           // 글자가 나오는 케이스는 2가지 밖에 없음 a 혹은 b
           if( temp[index].equals("a") ) {
               //연속된 a의 갯수 확인
               aCount++;
               if( aCount > 2 ) {
                   aCount    = 2;
                   // 초기화. a가 연속되도 나올수 있는 conText크기는 2임
                   countText = 2;
               }
               // 이전것이 b가 아니면 b 카운트는 초기화
               if( index != 0 && temp[index-1].equals("b") ) {
                   bCount     = 0;
               }
           } else {
               //b도 동일한 방법으로 구현
               // 반복 로직이라 더 효율적으로 구성할 수 있을것으로 보임
               bCount++;
               if( bCount > 2 ) {
                   bCount    = 2;
                   countText = 2;
               }
               if( index != 0 && temp[index-1].equals("a") ) {
                   aCount     = 0;
               }
           }
           // 기존 max카운트와 비교
           max = max < countText ? countText : max;
       }
       
       return max;
    }
}
