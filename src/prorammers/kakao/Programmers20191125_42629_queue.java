package prorammers.kakao;
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

의사코드 작성 >>>>>>>> 틀림...... 효율성에서 

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


의사 코드 2
F int solustion( 스트링 인풋 (s) )
       마지막 인덱스를 확인하기 위한 Stack 변수 준비 (lastStack)
    
    FOR s를 index으로 해서 0부터 s.lentgh -1까지 1씩 증가 한다.
        IF lastStack이 비지 않을 경우 THEN
            lastStack에서 맨 위 변수를 준비한다 ( lastString )
            IF lastString과 index값의 스트링을 비교하여 다르다 THEN
                lastStack에 indexString값을 넣는다.
            ELSE
                lastStack에 1개를 제거한다.
        ELSE 
            lastStack에 index값의 스트링을 집어넣는다.
           
    IF lastStack이 비지 않았다면 THEN
        answer은 0
    ELSE 
        answer은 1
    
    answer을 리턴한다.
    

*/
    
class Programmers20191125_42629_queue {
    public int solution(String s) {
        int answer;
        Stack<Character> lastStack = new Stack<Character>();
        
        for (int index = 0; index < s.length(); index++) {
            char indexString = s.charAt(index);
            if( !lastStack.isEmpty() ) {
                char lastChar = lastStack.peek();
                if( lastChar != indexString ) {
                    lastStack.add(indexString);
                } else {
                    lastStack.pop();
                }
            } else {
                lastStack.add(indexString);
            }
        }
        
        answer = !lastStack.isEmpty()? 0 : 1;
        
        return answer;
    }
}