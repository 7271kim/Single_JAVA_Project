import java.util.Comparator;
import java.util.PriorityQueue;

//https://programmers.co.kr/learn/courses/30/lessons/42629
// 내 풀이 : 기간 안에 있는 수중 최고 supply를 구한다.
// 효율성 통과하지 못함 >> 계속 큐를 만들기 때문 
// 아... 기간이 오름차순 정렬되어 있음....ㅋ
// 기간을 보면서 최고 치를 상단에 넣어줌 
// 5 , 7 통과가 안됨 어떤케이스 인지 찾기
class Solution {
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
}