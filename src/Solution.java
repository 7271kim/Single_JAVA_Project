/*
https://programmers.co.kr/learn/courses/30/lessons/12973

기준점과 비교점을 비교하여 모두가 제거 되었는지 확인하는 로직
고려 케이스
tcbaabcbaabt    무
aab     b
aabb    무
a       a
caab    cb

의사코드 작성

F int solustion( 스트링 인풋 )
    s를 ""로 나눈 배열을 준비한다.(sArr)
    
    FOR sArr의 인덱스(index) 0부터 s.length-2까지 1씩 증가 
        IF index의 char와 index+1의 char가 같다 THEN
               배열에서 index와 index+1위치의 char를 null으로 만든다
               제거 된 후 다음 로직을 위한 함수 checkNext( sArr, index, index+1 )실행
    
    
    FOR sArr의 인덱스 0부터 sArr.size()까지 돌면서 1씩 증가시킨다.
        IF sArr의 인덱스 값이 null이 아니면 THEN
            answer은 0이고 break
        ELSE 
            answer은 1
    
    answer을 리턴한다.


F void checkNext(원본 배열(ori), 앞에 제거된 인덱스(before), 다음 제거된 인덱스(next))
    befor 이전의 null값이 아닌 값을 찾기 위한 변수 (temp = before-1)
    while 원본 배열의 temp인덱스 값이 null 이고 temp가 -1보다 클때 
       temp를 -1 줄인다.
     
    IF temp가  0보다 크고 next+1가 ori length보다 작고 원본에서 temp와 next+1 값이 값을 때 TEHN
            원본 배열에서 temp와 index+1위치의 char를 null으로 만든다
       checkNext(원본 배열(ori), temp, next+1)

*/
    
class Solution {
    public int solution(String s) {
        int answer = 1;
        String[] sArr = s.split("");
        for (int index = 0; index < sArr.length-1; index++) {
            String befor = sArr[index];
            String next  = sArr[index+1];
            if( befor!= null && befor.equals(next) ) {
                sArr[index] = null;
                sArr[index+1] = null;
                checkNext( sArr, index, index+1 );
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

    private void checkNext(String[] sArr, int before, int next) {
        int temp = before -1;
        int size = sArr.length;
        while( temp > -1 && sArr[temp] == null ) {
            temp--;
        }
        if( temp > -1 && next+1 < size && sArr[temp].equals(sArr[next+1]) ) {
            sArr[temp] = null;
            sArr[next+1] = null;
            checkNext( sArr, temp, next+1 );
        }
    }
}