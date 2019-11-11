import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

// https://programmers.co.kr/learn/courses/30/lessons/42586
class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        int[] answer = {};
        ArrayList<Integer> temp = new ArrayList<Integer>();
        Queue<Integer> que = new LinkedList<Integer>();
        
        for (int index = 0; index < speeds.length; index++) {
            que.add( intCeil(100-progresses[index],speeds[index]) );
        }
        
        int topDay = que.poll();
        int count  = 1;
        while(!que.isEmpty()) {
            int top = que.poll();
            if( top > topDay ) {
                temp.add(count);
                count  = 1;
                topDay = top;
            } else {
                count++;
            }
        }
        temp.add(count);
        
        answer = new int [ temp.size() ];
        for (int index = 0; index < temp.size(); index++) {
            answer[index] = temp.get( index );
        }
        
        return answer;
    }
    
    public int intCeil( int dividend , int divisor ) {
        //double castDividen = (double) dividend;
        //double castDivisor = (double) divisor;
        //double result      = Math.ceil(castDividen/castDivisor);
        double reminder      = dividend / (double)divisor;
        int result           = (int)Math.ceil(reminder);
        return result;
    }
}