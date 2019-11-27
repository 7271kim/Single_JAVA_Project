package prorammers.kakao;

import java.util.Stack;

//https://programmers.co.kr/learn/courses/30/lessons/60057 
class KAKAO60057DivideChar {
    
    // 내껏 
    public int solution(String s) {
        int size   = s.length();
        int min    = 10000;
        
        out: for ( int divide = 1; divide < size+1; divide++ ) {
            String[] temp = new String[10002];
            StringBuilder finalText      = new StringBuilder();
            
            int tempCount = 0;
            
           // N 별로 나눈다
            StringBuilder tempBulder = new StringBuilder();
            for (int sIndex = 1; sIndex < s.length()+1; sIndex++) {
                tempBulder.append(s.charAt(sIndex-1));
                if( sIndex  % divide == 0 ) {
                    temp[tempCount++] = tempBulder.toString();
                    tempBulder = new StringBuilder();
                } else {
                    if( sIndex == s.length() ) temp[tempCount++] = tempBulder.toString();
                }
            }
            
            // 압축한다.
            int beforeCount = 1;
            for (int index = 0; index < temp.length-1; index++) {
                String thisText     = temp[index];
                String nextText     = temp[index+1];
                
                if( thisText == null ) {
                    break;
                }
                    
                if( thisText.equals(nextText) ) {
                    beforeCount++;
                } else {
                    if( beforeCount > 1) {
                        finalText.append(beforeCount).append(thisText);
                    } else {
                        finalText.append(thisText);
                    }
                    beforeCount = 1;
                }
                
                if( finalText.length() > min ) continue out;
            }
            
            min = finalText.length();
        }
        return min;
    }
    
/*
남의 코드 의사코드 
F int solution ( 인풋스트링 s )
    최소값을 반환하기 위한 min변수 (min = s.legth)
    길이의 1/2까지만 for문 돌면 되기 때문에 바깥 for문을 위한 변수 설정 ( len = s.legth()+1)
    
    FOR index는 1부터 len까지 1씩 증가
        이전 글자가 무엇인지 확인하기 위할 변수 (before)
        몇개가 반복되어있는지 확인할 count변수 ( cnt = 1 )
        총 길이를 합산하기 위한 sum변수 ( sum = 0 )
        
        FOR index2는 0으로 시작하며 s.legth보다 작다면
            문자열을 자를때 사용할 시작인덱스를 나타내는 변수 ( start ) 는 index2이다.
            
            IF index2와 index1의 둘의 합이 s.legth보다 크다면 THEN
                index2는 s.legth
            ELSE
                index2는 index1+index2
            
            자른 문자열을 나타내는 변수 (temp ) = s.substring( start, index2 )
            
            IF 만약 before문자열과 temp문자열과 같다면 THEN
                cnt 1증가 
            ELSE
                IF cnt가 1이 아니라면 THEN
                    sum에 cnt의 길이만큼 더한다.
                
                cnt를 1로 초기화 한다.
                sum에 before길이만큼 붙인다.
                before를 temp로 세팅한다.
            
        마지막 값 처리를 위해 sum에 마지막 before의 길이만큼 더한다.
        IF cnt가 1이 아니라면 TEHN
            sum에 cnt만큼 더한다.
        
        min과 sum을 비교하여 작은것을 min으로 세팅

    min 리턴

 */
    public int solution2(String s) {
        int min = s.length();
        int len = s.length()/2+1; // 1/2 만큼만 하면 뒤에는 자동임
        for(int i = 1; i < len; i++) {
            String before = "";
            int sum = 0;
            int cnt = 1;
            for(int j = 0; j < s.length();) {               
                int start = j;
                j = (j+i > s.length()) ? s.length():j+i;
                String temp = s.substring(start, j); // 1. N으로 나누기 
                if(temp.equals(before)) {
                    cnt++;
                } else {
                    if(cnt != 1) {
                        // 길이를 반환하기
                        sum += (int)Math.log10(cnt)+1;
                    }
                    // 다를시 초기화부분
                    cnt = 1;
                    // 기존 것을 붙인다.
                    sum+=before.length();
                    before = temp;
                }
            }
            
            // 최종 나왔을 때 마지막값에 대해 한번 더 해주기 
            sum+=before.length();
            if(cnt != 1) {
                sum += (int)Math.log10(cnt)+1;
            }
            min = (min > sum) ? sum : min;
        }

        return min;
    }
    
    
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
    public int solution3(String s) {
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