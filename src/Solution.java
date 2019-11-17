import java.util.PriorityQueue;

//https://programmers.co.kr/learn/courses/30/lessons/42629
// 효율성 통과하지 못함 >> 계속 큐를 만들기 때문 
class Solution {
    public int solution(int stock, int[] dates, int[] supplies, int k) {
        int answer = 0;
        int needSupply = k - stock;
        while( needSupply > 0) {
            PriorityQueue<Delivery> pq = new PriorityQueue<Delivery>();
            for (int index = 0; index < dates.length; index++) {
                if( dates[index] != 0 ) {
                    int date     = dates[index];
                    int supply   = supplies[index];
                    Delivery temp = new Delivery(date, supply, stock, index);
                    pq.add(temp);
                }
            }
            Delivery temp = pq.poll();
            needSupply -= temp.supply;
            dates[temp.index] = 0;
            stock += temp.supply;
            answer++;
        }
        
       
        return answer;
    }
    
    private class Delivery implements Comparable<Delivery> {
        int day;
        int supply;
        int stock;
        int index;
        
        private Delivery( int day, int supply, int stock, int index) {
            this.day    = day;
            this.supply = supply;
            this.stock  = stock;
            this.index  = index;
        }
        @Override
        public int compareTo( Delivery target ) {
            int result = 0;
            if( day <= stock ) {
                if( target.supply < supply ) {
                    result = -1;
                }
            } 
            return result;
        }
    }
}