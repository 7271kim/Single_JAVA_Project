package prorammers.hard;
import java.util.LinkedList;
import java.util.Queue;

// 1. 특정 지점에서 지점까지의 최단거리 문제
// 2. 각각의 정점에서 비용이 1을 응용한 DFS문제이다.
 

class KAKAO60063BFS {
    private int MAX = 2147483647;
    private int ISVERTICAL = 1;
    private int ISHORIZONTAL = 0;
    
    private int boardSize;
    int [][] verticalValue; // 수직인 상태에서 마지막까지 갔을때의 최소값
    int [][] horizontalValue; // 수평인 상태에서 마지마까지 갔을 때의 최소값
    
    public int solution(int[][] board) {
        boardSize = board.length;
        Queue<int[]> que = new LinkedList<int[]>();
        verticalValue = new int[boardSize][boardSize];
        horizontalValue = new int[boardSize][boardSize];
        // 최대값으로 초기화
        for (int index = 0; index < boardSize; index++) {
            for (int index2 = 0; index2 < boardSize; index2++) {
                verticalValue[index][index2]   = MAX;
                horizontalValue[index][index2] = MAX;
            }
        }
        
        // 시작점 정하기 y, x , count, 방향 넣어주기
        que.add(new int[]{ 0, 0, 0, ISHORIZONTAL });
        
        //큐가 빌때까지 진행
        while(!que.isEmpty()) {
            int[] temp   = que.poll();
            int y        = temp[0];
            int x        = temp[1];
            int count    = temp[2];
            int position = temp[3];
           
            if( position == ISHORIZONTAL ) {
                // 틀린 조건 명시 
                if( x+1 >= boardSize || board[y][x] == 1 || board[y][x+1] == 1 ) continue;
                
                // 최소값 비교 후 최소값이 변경된 건들에 대해 전체 업데이트
                
                if( horizontalValue[y][x] > count ) {
                    horizontalValue[y][x] = count;
   
                    // 회전 경우의 수에 대해 넣어주기 좌꼭지점 위 아래. 우꼭지점 위아래 총 4가지 , 최소 값이 변했으니 해당과 관련있는 모든 케이스를 넣는다.
                    // 좌꼭지 위로 회전
                    if( y - 1 > -1 && x + 1 < boardSize && board[y - 1][x + 1] !=1 )
                        que.add(new int[] { y-1, x, count+1, ISVERTICAL });
                    
                    // 좌꼭지 아래로 회전
                    if( y + 1 < boardSize && x + 1 < boardSize && board[y + 1][x + 1] !=1 )
                        que.add(new int[] { y, x, count+1, ISVERTICAL });
                    
                    //우꼭지 위로 회전
                    if( y - 1 > -1 && x + 1 < boardSize && board[y - 1][x] !=1 )
                        que.add(new int[] { y-1, x+1, count+1, ISVERTICAL });
                    
                   //우꼭지 아래로 회전
                    if( y + 1 < boardSize && x + 1 < boardSize && board[y + 1][x] !=1 )
                        que.add(new int[] { y, x+1, count+1, ISVERTICAL });
                    
                    // 상하좌우 경우의 수에 대해 넣어주기
                    // 상 케이스
                    if( y - 1 > -1 )
                        que.add(new int[] { y-1, x, count+1, ISHORIZONTAL });
                    // 하케이스
                    if( y + 1 < boardSize )
                        que.add(new int[] { y+1, x, count+1, ISHORIZONTAL });
                    // 좌케이스
                    if( x - 1 > -1 )
                        que.add(new int[] { y, x-1, count+1, ISHORIZONTAL });
                    // 우케이스
                    if( x + 1 < boardSize )
                        que.add(new int[] { y, x+1, count+1, ISHORIZONTAL });
                }
            } else {
                // 현 세로 상테 틀린 조건 명시 
                if( y+1 >= boardSize ||  board[y][x] == 1 || board[y+1][x] == 1 ) continue;
                
                // 최소값 비교 후 최소값이 변경된 건들에 대해 전체 업데이트
                
                if( verticalValue[y][x] > count ) {
                    verticalValue[y][x] = count;
   
                    // 회전 경우의 수에 대해 넣어주기위 아래 총 4가지 모든 케이스를 넣는다.
                    // 위꼭지 축 왼쪽 회전
                    if( y+ 1 < boardSize && x - 1 > -1 && board[y + 1][x - 1] !=1 )
                        que.add(new int[] {  y, x - 1, count+1, ISHORIZONTAL });
                    
                    // 위꼭지 축 오른쪽 회전
                    if( y + 1 < boardSize && x + 1 < boardSize && board[y + 1][x + 1] !=1 )
                        que.add(new int[] { y, x, count+1, ISHORIZONTAL });
                    
                    //아래꼭지 축 좌측 회전
                    if(  y + 1 < boardSize && x - 1 > -1 && board[y][x-1] !=1 )
                        que.add(new int[] { y+1, x-1, count+1, ISHORIZONTAL });
                    
                   //아래꼭지 오른쪽 회전
                    if( y + 1 < boardSize && x + 1 < boardSize && board[y][x+1] !=1 )
                        que.add(new int[] { y+1, x, count+1, ISHORIZONTAL });
                    
                    // 상하좌우 경우의 수에 대해 넣어주기
                    // 상 케이스
                    if( y - 1 > -1 )
                        que.add(new int[] { y-1, x, count+1, ISVERTICAL });
                    // 하케이스
                    if( y + 1 < boardSize )
                        que.add(new int[] { y+1, x, count+1, ISVERTICAL });
                    // 좌케이스
                    if( x - 1 > -1 )
                        que.add(new int[] { y, x-1, count+1, ISVERTICAL });
                    // 우케이스
                    if( x + 1 < boardSize )
                        que.add(new int[] { y, x+1, count+1, ISVERTICAL });
                }
            }
        }
        
        // 큐가 비었으면 모든 최소값은 verticalValue와 horizontalValue에 업데이트 되어있다.
        return Math.min(verticalValue[boardSize-2][boardSize-1], horizontalValue[boardSize-1][boardSize-2]);
    }
}