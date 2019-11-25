import java.util.Stack;

/*
https://programmers.co.kr/learn/courses/30/lessons/12973

기준점과 비교점을 비교하여 모두가 제거 되었는지 확인하는 로직
기준점은 항상 stack에 넣어둔다.
고려 케이스
tcbaabcbaabt    무
aab     b
aabb    무
a       a
caab    cb

의사코드 작성

F int solustion( 스트링 인풋 )
    s를 ""로 나눈 배열을 준비한다.(sArr)
       마지막 인덱스를 확인하기 위한 Stack 변수 준비 (lastStack)
    
    FOR sArr의 인덱스(index) 0부터 s.length-2까지 1씩 증가 한다.
        IF 고 sArr인덱스값이 null이 아니다 THEN 
            IF index의 char와 index+1의 char가 같다 THEN
                       배열에서 index와 index+1위치의 char를 null으로 만든다
                       제거 된 후 다음 로직을 위한 함수 checkNext( sArr, lastStack, index+1 )실행
            ELSE
               lastStack에 index값 집어넣기
           
    FOR sArr의 인덱스 0부터 sArr.size()까지 돌면서 1씩 증가시킨다.
        IF sArr의 인덱스 값이 null이 아니면 THEN
            answer은 0이고 break
        ELSE 
            answer은 1
    
    answer을 리턴한다.


F void checkNext(원본 배열(ori), 비교를 위한 stack변수(lastStack) , 마지막 제거 인덱스(next))
    IF 스택이 비지 않았고 next+1이 ori크기보다 작다면 THEN
           스택에서 하나를 빼 비교를 위한 변수로 선언한다. ( benchMark )
      
      IF benchMark값과 next+1 값이 같을경우
                 원본 배열에서 benchMark next+1위치의 값을 null으로 만든다
          checkNext(원본 배열(ori), lastStack, next+1)
      ELSE
          Stack에 다시 index를 넣어준다.
    ELSE
        return;

*/
    
class Solution {
    public int solution(String s) {
        int answer = 1;
        String[] sArr = s.split("");
        Stack<Integer> lastStack = new Stack<Integer>();
        
        for (int index = 0; index < sArr.length-1; index++) {
            if(sArr[index] != null) {
                String befor = sArr[index];
                String next  = sArr[index+1];
                if( befor!= null && befor.equals(next) ) {
                    sArr[index] = null;
                    sArr[index+1] = null;
                    checkNext( sArr, lastStack, index+1 );
                } else {
                    lastStack.add(index);
                }
            }
        }
        
        for (int index = 0; index < sArr.length; index++) {
            if( sArr[index] != null ) {
                answer = 0;
                break;
            }
        }
        
        return answer;
    }
    
    private void checkNext(String[] sArr, Stack<Integer> lastStack, int next) {
        if( !lastStack.isEmpty() && next+1 < sArr.length ) {
            int benchMark = lastStack.pop();
            if( sArr[benchMark].equals(sArr[next+1]) ) {
                sArr[benchMark] = null;
                sArr[next+1] = null;
                checkNext( sArr, lastStack, next+1 );
            } else {
                lastStack.add(benchMark);
            }
            
        }
    }
}