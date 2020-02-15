package prorammers.kakao;
import java.util.LinkedList;
import java.util.Queue;

/*

https://programmers.co.kr/learn/courses/30/lessons/17680?language=java

F solution ( 저장소 크기 cacheSize , 국가 배열 cities) 
    저장소를 FILO인 Que로 구성한 cache 
    결과를 더할 anser 변수 answer 변수 
    
    FOR 단순 인덱스 index < sities.length, ++
        현재 국가를 가르키는 변수 city
        answer += LRU를 구현하고 없을 시 5, 존재할시 1을 return는 함수 checkLRU( city );
    


LRU를 구현하고 없을 시 5, 존재할시 1을 return는 함수 checkLRU ( 들어온 국가명 city)
    변경할 값을 저장할 변수 temp = "";
    리턴변수 answer;
    
    FOR cache 크기 만큼
        피크를 가지고 온다 poll을 통해 마지지막을 가지고 온 변수 last 
        
        IF last와 들어온 국가명이 같다면 
            temp = city
            For문 지속 coutinue;
        ELSE 
            last를 다시 cache 에 넣는다.
        
    
    IF temp가 존재한다면 
        cache에 temp넣기
        anser = 1;
    ELSE IF cache 사이즈가 cacheSize 보다 작은경우
        cache에 city를 넣느다 
        anser = 5;
    ELSE 
        마지막을 poll 하고 현재 city를 넣는다 
        anser = 5;
    
    return anser;
            
*/
class Programmers20200215_17680_checkLinkedHashMap {
    Queue<String> cache = new LinkedList<>();
    int cacheSize;
    public int solution(int cacheSize, String[] cities) {
      int answer = 0;
      this.cacheSize = cacheSize;
      
      for (int index = 0; index < cities.length; index++) {
        String city = cities[index].toLowerCase();
        answer += checkLRU( city );
      }
      return answer;
    }
    
    private int checkLRU(String city) {
        int answer = 0;
        String temp = "";
        int cycle = cache.size();
        if ( cacheSize == 0 ) return 5;
        for (int index = 0; index < cycle; index++) {
            String last = cache.poll();
            if( last.equals( city ) ) {
                temp = city;
                continue;
            } else {
                cache.add(last);
            }
        }
        
        if( !temp.isEmpty() ) {
            cache.add(temp);
            answer = 1;
        } else if( cache.size() < cacheSize ) {
            cache.add(city);
            answer = 5;
        } else {
            cache.poll();
            cache.add(city);
            answer = 5;
        }
        
        return answer;
    }
}
