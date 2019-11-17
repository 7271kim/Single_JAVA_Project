package prorammers.hard;
//https://programmers.co.kr/learn/courses/30/lessons/12905
// 효율성 검사 있음
// 효율성 검사만 안되는 소스 
// DP를 써야 해결 가능하다. 작은 부분으로 나누는 방법을 알아야함.
//
class Programers20191117_12905_DP {
    //DP소스
    // [1][1] 부터시작하여 각 점에서 정사각형의 한 면을 만드는 방법은 0이 아니라면 min( [0][0] , [0][1], [1][0] ) + 1 이다.
    // 즉 좌 좌상 상 현재 중 최소값의 + 1을 시키면 해당점에서의 최대 정사각형의 한 변을 구할 수있다.
    public int solution(int [][]board) {
        int max = 0;
        for (int index = 1; index < board.length; index++) {
            for (int indexInner = 1; indexInner < board[index].length; indexInner++) {
                if( board[index][indexInner] !=0 ) {
                    board[index][indexInner] =  Math.min(board[index][indexInner-1], Math.min(board[index-1][indexInner], board[index-1][indexInner-1]))+1;
                }
                
            }
        }
        
        for (int index = 0; index < board.length; index++) {
            for (int indexInner = 0; indexInner < board[index].length; indexInner++) {
                max = Math.max( board[index][indexInner] , max);
            }
        }
        
        return max*max;
        
    }
    
    // 내소스 그냥 생 탐색이라 불가능함
    public int solution2(int [][]board) {
        int max = 0;
        
        for (int index = 0; index < board.length; index++) {
            for (int indexInner = 0; indexInner < board[index].length; indexInner++) {
                
                int start    = board[index][indexInner];
                out: while( start > 0 ) {
                    //1 부터 한칸씩 증가시키면서 정사각형 이루는지 확인
                    //기존 max가 있다면 최대치부터 검사하는게 더 좋을듯.
                    start = Math.max(start, max);
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