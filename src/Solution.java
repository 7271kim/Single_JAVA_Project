import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

// 1. 사각형에서 벽이 없을 때 회전 가능. 우측이동 아래측 이동 가능
// 2. N X N까지 가는데 최소 경우의 수
// 3. 보드는 100이하
// 4. 전수 조사 시도 >> 우측 끝 하단 끝 순서로 시도
// 좌 , 우 , 좌하, 우하 가준 판단.
//   i) 우로 갈 수 있냐 
//  ii) 아래로 갈 수 있냐
// iii) 좌측 축기준 회전
//  iv) 우측 기준 회전 
//   + 세로일때의 회전
//   v) 아래에 벽이 있냐 
//  >> true false로 리턴
//  가능 시 기준점 이동
// 이슈는 전수조사시 한 기준점당 할수 있는 경우의 수는 4가지 >> 최악 4^100
// 또한 중복가능 회전 후 우측이동시 이슈
// 좌기준 회전 후 우측 이동은 우측기준 회전이랑 같다. >> 한번 간 길은 더이상 작업하지 않는다.
 

class Solution {
    int boardSize;
    boolean[][] checkPath;
    int[] lastPosition;
    int min = 10000000;
    int[][] original;
    Queue<int[]> total = new LinkedList<int[]>();
    
    public int solution(int[][] board) {
        boardSize  = board.length;
        original = board;
        checkPath = new boolean[boardSize][boardSize];
        
        getAllPath( 0, 0 ,0, 1, 0);
        
        return min - 1;
    }
    
    private void getAllPath( int fisrtPointLine, int firstPointLow, int secondPoiontLine, int secondPointLow, int count ) {
        if( secondPoiontLine == boardSize-1 && secondPointLow == boardSize-1 ) {
            min = min > count ? count : min;
            while( !total.isEmpty() ) {
                int[] temp = total.poll();
                checkPath[temp[0]][temp[1]] = true;
            }
            return;
        }
        
        boolean checkValue = checkPath[fisrtPointLine][firstPointLow] && checkPath[secondPoiontLine][secondPointLow];
        
        if( !checkValue ) {
            total.add(new int[] { fisrtPointLine, firstPointLow });
            total.add(new int[] { secondPoiontLine, secondPointLow });
            if( canGoRight( fisrtPointLine, firstPointLow, secondPoiontLine, secondPointLow ) ) {
                getAllPath(fisrtPointLine, firstPointLow+1, secondPoiontLine, secondPointLow+1 , count+1 );
            }
            
            if( goDown( fisrtPointLine, firstPointLow, secondPoiontLine, secondPointLow ) ) {
                getAllPath(fisrtPointLine+1, firstPointLow, secondPoiontLine+1, secondPointLow, count+1 );
            }
            
            if( goLeftRotation( fisrtPointLine, firstPointLow, secondPoiontLine, secondPointLow ) ) {
                boolean isVertical = secondPointLow == firstPointLow;
               // 수직일 경우 
                if( !isVertical ) {
                    getAllPath(fisrtPointLine, firstPointLow, fisrtPointLine+1, firstPointLow, count+1 );
                } 
            }
            
            if( goRightRotation( fisrtPointLine, firstPointLow, secondPoiontLine, secondPointLow ) ) {
                boolean isVertical = secondPointLow == firstPointLow;
               // 수직일 경우 
                if( isVertical ) {
                    getAllPath(secondPoiontLine, secondPointLow, secondPoiontLine, secondPointLow+1, count+1 );
                } else {
                    getAllPath(fisrtPointLine, firstPointLow, secondPoiontLine, secondPointLow, count+1 );
                }
            }
            total.poll();
            total.poll();
        }
    }
    
    private boolean canGoRight( int fisrtPointLine, int firstPointLow, int secondPoiontLine, int secondPointLow ) {
        boolean result = false;
        boolean isVertical = secondPointLow == firstPointLow;
        
        // 범위 확인 
        if( secondPointLow + 1 >= boardSize ) return false;
        
        // 수직일 경우 
        if( isVertical ) {
            if( original[fisrtPointLine][firstPointLow+1] != 1 && original[secondPoiontLine][secondPointLow+1] != 1 ) {
                result = true;
            }
        } else {
            if( original[secondPoiontLine][secondPointLow+1] != 1 ) {
                result = true;
            }
        }
        
        return result; 
    }
    
    private boolean goDown( int fisrtPointLine, int firstPointLow, int secondPoiontLine, int secondPointLow ) {
        boolean result = false;
        boolean isVertical = secondPointLow == firstPointLow;
        
        // 범위 확인 
        if( secondPoiontLine + 1 >= boardSize ) return false;
        
        // 수직일 경우 
        if( isVertical ) {
            if(  original[secondPoiontLine+1][secondPointLow] != 1 ) {
                result = true;
            }
        } else {
            if( original[fisrtPointLine+1][firstPointLow] != 1 && original[secondPoiontLine+1][secondPointLow] != 1 ) {
                result = true;
            }
        }
        
        return result; 
    }
    
    private boolean goLeftRotation( int fisrtPointLine, int firstPointLow, int secondPoiontLine, int secondPointLow ) {
        boolean result = false;
        boolean isVertical = secondPointLow == firstPointLow;
        
        // 수직일 경우 
        if( !isVertical ) {
            if( secondPoiontLine + 1 < boardSize  && original[fisrtPointLine+1][firstPointLow] != 1 && original[secondPoiontLine+1][secondPointLow] != 1 ) {
                result = true;
            }
        } 
        
        return result; 
    }
    
    private boolean goRightRotation( int fisrtPointLine, int firstPointLow, int secondPoiontLine, int secondPointLow ) {

        boolean result = false;
        boolean isVertical = secondPointLow == firstPointLow;
        // 수직일 경우 
        if( isVertical ) {
            
            if( secondPointLow+1 < boardSize && original[secondPoiontLine][secondPointLow+1] != 1 ) {
                result = true;
            }
        } else {
            if( secondPoiontLine + 1 < boardSize && original[fisrtPointLine+1][firstPointLow] != 1 && original[secondPoiontLine+1][secondPointLow] != 1 ) {
                result = true;
            }
        }
        
        return result; 
    }
}