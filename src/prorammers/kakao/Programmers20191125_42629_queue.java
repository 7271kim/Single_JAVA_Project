package prorammers.kakao;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

//https://programmers.co.kr/learn/courses/30/lessons/42629
// 내 풀이 : 기간 안에 있는 수중 최고 supply를 구한다.
// 효율성 통과하지 못함 >> 계속 큐를 만들기 때문 
// 아... 기간이 오름차순 정렬되어 있음....ㅋ
// 기간을 보면서 최고 치를 상단에 넣어줌 
// 5 , 7 통과가 안됨 어떤케이스 인지 찾기
class Programmers20191125_42629_queue {
    public int solution(int stock, int[] dates, int[] supplies, int k) {
        int answer = 0;
        int needSupply = k - stock;
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2.compareTo(o1);
            }
        });
        for (int index = 0; index < dates.length; index++) {
            if( needSupply <= 0 ) break;
            
            int date = dates[index];
            if( date < stock ) {
                pq.add(supplies[index]);
            } else if( date > stock) {
                while(date > stock) {
                    int top = pq.poll();
                    needSupply -= top;
                    stock += top;
                    answer++;
                }
                pq.add(supplies[index]);
            } else {
                pq.add(supplies[index]);
                int top = pq.poll();
                needSupply -= top;
                stock += top;
                answer++;
            }
        }
       while( needSupply > 0) {
           int top = pq.poll();
           needSupply -= top;
           answer++;
       }
        return answer;
    }
    
    // 내 2번째 풀이 Greedy 풀이 응용 
/*
F int solution( 현재 재고량 stock, 임시 밀가루가 공급되는 날짜 dates, 임시 공급량 supplies, 최종 버틸날 k ) 
    현재 재고가 떨어지는 날 nextStock = stock
    dates중 사용 dates가 어디인지 index를 위한 변수 indexDates = 0;
    최대한 많은 공급을 해야 최소 변수가 나오기 때문에 정렬을 위한 que변수 sotedSupplies >> 최대 힙으로 구성하기 위해 
    new Comparator<Integer>()을 오버라이드 해서 사용
        F int compare ( 비교가 필요한 값 compare, 기존에 있던 값 orignal ) 함수 구현하기
                return orignal.compareTo(compare) // orignal > compare 면 +1을 반환(배열의 앞쪽에 와야함), 작으면 -1반환, 같으면 0을 반환 

    
    최종 결과를 위한 answer = 0;
    
    FOR index는 1부터 k까지 1씩 증가
        현재 dates값을 확인하기 위한 dates변수 thisDate = dates[indexDates] 다만 최대치 크기 확인
        IF thisDate와 index가 같은 경우 
            sotedSupplies에다가 supplies[indexDates]추가
            indexDates 1증가
        
        IF nextStock이 0 이라면 THEN
            supplies에서 하나 poll하기 peakSupplies
            nextStock에다가 peakSupplies더하기
            answer++;
    
    answer 리턴
*/
    public int solution2(int stock, int[] dates, int[] supplies, int k) {
        int nextStock = stock;
        int indexDates = 0;
        Queue<Integer> sotedSupplies = new PriorityQueue<Integer>(new Comparator<Integer>() {
            @Override
            public int compare(Integer compare, Integer orignal) {
                return orignal.compareTo(compare);
            }
        });
        
        int answer = 0;
        for (int index = 0; index < k; index++) {
            int thisDate = indexDates < dates.length ? dates[indexDates] : 0;
            
            if( thisDate == index ) {
                sotedSupplies.add(supplies[indexDates++]);
            }
            if( nextStock == 0 ) {
                int peakSupplies = sotedSupplies.poll();
                nextStock+=peakSupplies;
                answer++;
            }
            
            nextStock--;
        }
        return answer;
    }
}