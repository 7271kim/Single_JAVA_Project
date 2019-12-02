/*
https://programmers.co.kr/learn/courses/30/lessons/12979

F int solution ( 총 아파트 n, 기지국 설치 배열 stations, 좌우 도달 거리 w )
    아파트가 현재 전파가 도달하는지 확인하기 위한 배열 checked = new int[n+1]
    0은 무시하기 위해 항상 전파가 도달한다고 체크 checked[0] = 1;
    최종 결과를 위한 result = 0;
    
    FOR index는 1부터 stations.length보다 작을때까지 1씩 증가
        현재 인풋값을 위한 변수 thisStations = stations[index]
        checked에 현재 전파가 도착하는지 범위의 시작점을 정해주는 변수 start = thisStations - w < 0  ? 0 : thisStations - w;
        checked에 현재 전파가 도착하는지 범위의 마지막지점을 정해주는 변수 end = thisStations + w >= n+1 ? n+1 : thisStations + w;
        
        FOR writerOne변수는 start부터 end까지 1씩 증가
            checked배열을 1로 채워 현재 전파가 도달한다 체크하기 checked[writerOne] = 1
    
    FOR index는 0부터 n+1까지 1씩 증가
        현재 전파가 도달 하는지 확인하기위한 변수 thisStations = checked[index]
        
        IF  thisStations가 1이라면 다음 진행 continue; 
        
        1로 w크기 만큼 채우기 위한 변수 count = 2*w +1;
        while count가 0보다 크다면 
            IF index가 n+1 작고 checked[index] 0이라면 THEN
                checked[index] 1로 변경한다 checked[index] = 1;
                index를 하나 증가시킨다. index++
                count 하나 줄이기 count--;
            ELSE
                result++
                완번 바깥 계속 진행 continue;
        IF count가 0이라면 THEN
             while문이 벗어나는 순간 마지막 index가 1증가 된상태를 줄여야 한다. ( 이 다음 로직이 index++를 한번 더 하기 때문 ) index---
            result++;
        
    result 반환

정답이지만 시간 초가가 떠버림...캐쉬 전략 사용해도 시간 초과 ;;;;;;;;;;

*/

class Solution {
    public int solution(int n, int[] stations, int w) {
        int[] checked    = new int[n+1];
        int[] cache      = new int[n+1];
        int stationsSize = stations.length; 
        int answer       = 0;
        checked[0]       = 1;
        
        for (int index = 0; index < stationsSize; index++) {
            int thisStations = stations[index];
            int start = thisStations - w < 0  ? 0 : thisStations - w;
            int end = thisStations + w >= n+1 ? n : thisStations + w;
            
            for (int writerOne = start; writerOne <= end; writerOne++) {
                checked[writerOne]  = 1;
                cache[writerOne]    = end;
            }
        }
        
        out : for (int index = 0; index < n+1; index++) {
            int thisStations = checked[index];
            
            if( thisStations == 1 ) {
                index = cache[index];
                continue;
            }
            
            int count = 2*w +1;
            while( count > 0 ) {
                if( index < n+1  && checked[index] == 0 ) {
                    checked[index] = 1;
                    index++;
                    count--;
                } else {
                    answer++;
                    continue out;
                }
            }
            if( count == 0 ) {
                index--;
                answer++;
            }
        }
        return answer;
    }
}