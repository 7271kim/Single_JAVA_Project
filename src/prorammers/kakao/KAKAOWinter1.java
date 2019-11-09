package prorammers.kakao;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

// 인형뽑기 문제 
// Stack과 Que를 얼마나 잘 이용하냐
class KAKAOWinter1 {
    public int solution(int[][] board, int[] moves) {

        int answer = 0;
        int boardSize = board.length;
        Queue<Integer>[] lowData = new LinkedList[boardSize+1];
        Stack<Integer> basket = new Stack<Integer>();
        
        for (int index = 0; index < boardSize; index++) {
            Queue<Integer> temp = new LinkedList<Integer>();
            lowData[index+1] = temp;
        }
        
        for (int line = 0; line < boardSize; line++) {
            for (int low = 0; low < boardSize; low++) {
                int value = board[line][low];
                if( value != 0 )
                    lowData[low+1].add(value);
            }
        }
        
        for (int index = 0; index < moves.length; index++) {
            int low         = moves[index];
            
            if( lowData[low].isEmpty() ) continue;
            
            int thisValue   = lowData[low].poll();
            
            if( basket.isEmpty() ) {
                basket.add(thisValue);
                continue;
            }
            
            int basketValue = basket.peek();
            
            if( basketValue != thisValue ) {
                basket.add(thisValue);
            } else {
                basket.pop();
                answer+=2;
            }
        }
        
        
        return answer;
    }
}