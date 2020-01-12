package prorammers.kakao;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/*
https://programmers.co.kr/learn/courses/30/lessons/17677
자카드 유사도 - 하나의 방법론을 어떻게 개발에 적용하는지 보여주는 예제
배열의 교집합 합집합 구하기

F int solution( 들어온 첫번째 문자열 str1, 들어온 두번째 문자열 str2 ) 
    최종 결과 반환을 위한 int answer 변수
    str1의 특수문자를 변환한다.  str1 = replaceSpecialCharacters( str1 )
    str2의 특수문자를 변환한다.  str2 = replaceSpecialCharacters( str2 )
    str1을 들어온 문자열을 2개씩 잘라서 저장할 Deque 변수 Deque<String> str1Arr = getArr ( str1 )
    str2를 들어온 문자열을 2개씩 잘라서 저장할 Deque 변수 Deque<String> str2Arr = getArr ( str2 )
    
    교집합 갯수위한 변수 List<String> intersectionList = new LinkedList<>;
    합집합 갯수위한 변수 List<String> unionCount = new LinkedList<>;
    
    
    FOR indexStr1 은 0부터 길이까지
        str1Arr의 지금 값 thisAttr1 = str1Arr의 맨 위에것 뽑고 제거 pop  
        
        unionCount에 thisAttr1담기
        
        FOR indexStr2 0부터 길이까지
            str2Arr의 지금 값 thisAttr2 = str2Arr의 맨 위에것 뽑고 제거 pop 
            
            IF( thisAttr1 와 thisAttr2 이 같으면 )
                intersectionList에 thisAttr2 저장 
                break;
            ELSE
                thisAttr2 울 다시 str2Arr에 집어 넣기
            
        WHILE str2Arr 이 비지 않았다면
            str2Arr 하나 뽑는 것 thisStr2 = str2Arr pop 
            unionCount에 thisStr2 넣기
    
    answer 은 str1Arr 의 크기 나누기 str2Arr 크기 에서 65536 를 곱하고 나머지 절사
    
    answer 반환한다.
    
F String 특수문자를 *로 변환할 함수 replaceSpecialCharacters( String inputText )
    return inputText의 모든 특수문자를 *로 변환한다. inputText.replaceAll( "[ !@#$%^&*(),.?\":{}|<>]" , "*" ) 
    
F Deque<String> 들어온 문자열을 배여로 변환할 함수 getArr( String inputText )
    반환 변수 배열 Deque<String> answer = new LinkedList<>();
    
    FOR index 는 0부터 inputText.length()-2 까지 1씩 증가 
        지금 것과 다음 스트링을 결합한 변수 unionTwo = inputText.substring( index, index+2 );
        
        IF unionTwo에  *가 없는 경우
            answer에  소문자로 저장 ( thisChar+nextChar ) 하여 add하기
    
    return answer;



    
*/
class Programmers20200112_17677_intersection_union {
    public int solution(String str1, String str2) {
        int answer = 0;
        str1 = replaceSpecialCharacters( str1 );
        str2 = replaceSpecialCharacters( str2 );
        Deque<String> str1Arr     = getArr ( str1 );
        Deque<String> str2Arr    = getArr ( str2 );
        
        List<String> intersectionList = new LinkedList<>();
        List<String> unionList        = new LinkedList<>();
        
        int str1ArrSize = str1Arr.size();
        for (int i = 0; i < str1ArrSize; i++) {
            String thisAttr1 = str1Arr.poll();
            unionList.add(thisAttr1);
            
            int str2ArrSize = str2Arr.size();
            for (int index = 0; index < str2ArrSize; index++) {
                String thisAttr2 = str2Arr.poll();
                if( thisAttr1.equals(thisAttr2) ) {
                    intersectionList.add(thisAttr2);
                    break;
                } else {
                    str2Arr.add(thisAttr2);
                }
            }
        }
        
        while ( !str2Arr.isEmpty() ) {
            unionList.add(str2Arr.poll());
        }
        
        if( unionList.size() == 0  ) return 65536;
        
        double tempdouble = intersectionList.size() / ( unionList.size() + 0.0 ) * 65536;
        answer = (int) Math.floor(tempdouble);
        
        return answer;
  }

    private Deque<String> getArr(String inputText) {
        Deque<String> answer = new LinkedList<>();
        
        for (int index = 0; index < inputText.length()-1; index++) {
           String unionTwo = inputText.substring( index, index+2 );
           
           if( !unionTwo.contains("*") ) {
               answer.add(unionTwo.toLowerCase());
           }
        }
        return answer;
    }
    
    private String replaceSpecialCharacters(String inputText) {
        return inputText.replaceAll( "[^a-zA-Z]" , "*" );
    }
}