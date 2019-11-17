//https://programmers.co.kr/learn/courses/30/lessons/12905
// 효율성 검사 있음
// 효율성 검사만 안되는 소스 
class Solution {
    public int solution(int [][]board) {
        int max = 0;
        
        for (int index = 0; index < board.length; index++) {
            for (int indexInner = 0; indexInner < board[index].length; indexInner++) {
                
                int start    = board[index][indexInner];
                out: while( start > 0 ) {
                    //1 부터 한칸씩 증가시키면서 정사각형 이루는지 확인
                   for (int indexCheckY = index; indexCheckY < index+start; indexCheckY++) {
                        for (int indexCheckX = indexInner; indexCheckX < indexInner+start; indexCheckX++) {
                            if( board[indexCheckY][indexCheckX] == 0 || index+start > board.length || indexInner+start > board[0].length ) {
                                start--;
                                break out;
                            }
                        }
                    }
                    start++;
                }
                max = Math.max(max, start);
            }
        }
        return max*max;
    }
}