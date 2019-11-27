import java.util.Stack;

/*
1씩 증가며 문자열을 잘랐을 때 최소값 구하기

의사코드
F int solution ( 인풋스트링 s )
    s크기에 대한 변수 (sSize = s.legth)
    가장짧은 수를 표현할 변수 (answer = sSize)
    
    FOR index는 1부터 sSize까지 1씩 증가
        각 index크기만큼 잘랐을때 길이가 몇인지 확인할 변수 ( temp ) = getCompressSize( 원본 s, 자를 index값 )
        temp와 answer와 비교하여 작은것을 answer로 할당한다.
    
    answer리턴

F int getCompressSize( 원본 s, 자를값 split )
    자른 크기만큼 넣을 Stack변수 (tempStack)
    하나하나 합칠 문자열 (tempText)
    리턴을 위한 값 ( result = 0 )
    
    FOR index는 0부터 s.lenth-1까지 1씩 증가
        현재 index가 가르키는 char를 나타내는 변수 ( thisChar ) 
        tempText에 thisChar추가 
        
        IF split -1 가 0이거나 index +1 % split 이 0이면 THEN 
            tempStack에 tempText를 집어 넣고 tempText ""로 만든다.
    
    
    IF tempText가 "" 이 아니라면 THEN
        tempStack에 tempText추가
    
    지금 수가 몇번나왔는지 카운트할 변수 (count = 1)
    while tempStack이 비지 않았다면 
        tempStack에 맨 위에것을 뽑는다.
        
        IF 다음 피키가 있고 다음 피크와 비교했을 때 같다 THEN
            count 1 증가
        ELSE
            현재 글자의 length를 더한 것을 result와 합친다
            IF count가 1이 아닐때 THEN
                count의 length를 더한 것을 result와 합친다
            
    
            count를 1으로 만든다
            
    
    result를 리턴한다.
*/

class Solution {
    public int solution(String s) {
        int sSize = s.length();
        int answer = sSize;
        for (int index = 1; index <= sSize; index++) {
            answer =   Math.min(answer, getCompressSize( s, index ) );
        }
        
        return answer;
    }

    private int getCompressSize(String s, int split) {
        Stack<StringBuilder> tempStack = new Stack<StringBuilder>();
        StringBuilder tempText = new StringBuilder();
        int result = 0;
        
        for (int index = 0; index < s.length(); index++) {
            char thisChar = s.charAt(index);
            tempText.append(thisChar);
            if (split - 1 == 0 || (index+1) % split == 0) {
                tempStack.add(tempText);
                tempText = new StringBuilder();
            }
        }
        
        if( !tempText.toString().equals("") ) {
            tempStack.add(tempText);
        }
        
        int count = 1;
        
        while ( !tempStack.isEmpty() ) {
            StringBuilder top = tempStack.pop();
            if( !tempStack.isEmpty() && top.toString().equals(tempStack.peek().toString()) ) {
                count++;
            } else {
                result+= top.length();
                if( count != 1 ) {
                    result += (int)Math.log10(count)+1;
                }
                count = 1;
            }
        }
        return result;
    }
}