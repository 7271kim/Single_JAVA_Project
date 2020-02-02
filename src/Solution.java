/*

F int solution( 판의 높이 m, 판의 너비 n, 배치정보 String 배열 board  ) 
    판을 배열로 만들기 위한 2차원 배열 arrayBoard = new int[m][n];
    지울 위치가 저장될 2차원 배열 remove = new int[m][n];
    결과를 위한 answer변수 int answer = 0;
    
    FOR indexLine는 0부터 m-1만큼 1씩 증가
        FOR indexRow는 0부터 n-1만큼 1씩 증가
            각 단어의 1개1개 받아서 int로 변환 시키는 변수 int thisChar = board[indexLine는].charAt(indexRow) - 'A' + 1;
            해당 int를 arrayBoard에 배치 arrayBoard[indexLine][indexRow] = thisChar;
    
    check( arrayBoard , m , n  )
    
    While checkRemove( remove , m , n  ) {
        answer 에다가 reArray( arrayBoard, remove ) 값을 더한다. answer += reArray( arrayBoard, remove );
        check( arrayBoard , m , n  )
    }
    
    FOR indexLine는 0부터 m-1만큼 1씩 증가
        FOR indexRow는 0부터 n-1만큼 1씩 증가
            IF arrayBoard[indexLine][indexRow]가 0이라면
                answer 1증가
                
    return answer;
    
    
F 지워질 변수를 찾기 findRemove ( 원본 배열 arrayBoard, 줄 indexLine, 열 indexRow, 리턴할 배열 remove );
    IF indexLine + 1이 m보다 크거나 같거나 또는 indexRow + 1이 n보다 크거나 같으면
        return;
    
    지금 숫자 number = arrayBoard[indexLine][indexRow];
    
    IF number와  arrayBoard[indexLine][indexRow +1 ], arrayBoard[indexLine+1][indexRow], arrayBoard[indexLine+1][indexRow+1] 모두 같을 시 
        remove에 지워져야 할 곳의 값을 1로 변경시킨다. 
        
        remove[indexLine][indexRow] = 1;
        remove[indexLine][indexRow+1] = 1;
        remove[indexLine+1][indexRow] = 1;
        remove[indexLine+1][indexRow+1]= 1;

F  arrayBoard에서 지워져야 할 전체를 훑는 함수 check ( arrayBoard, m, n ) 
    FOR indexLine는 0부터 m-1만큼 1씩 증가
        FOR indexRow는 0부터 n-1만큼 1씩 증가
            findRemove ( arrayBoard, indexLine, indexRow, remove );

F Boolean 지울것이 남아 있나 확인하는 함수 checkRemove ( remove , m , n  )
    Boolean result = false;
    
    FOR indexLine는 0부터 m-1만큼 1씩 증가
        FOR indexRow는 0부터 n-1만큼 1씩 증가
            IF  remove[indexLine][indexRow]인덱스 값이 1이라면  
                return true;

F reArray( arrayBoard 배열, remove 배열 )
    remove에서 1인것을 찾아 arrayBoard에서 지운다. 
    
    FOR indexLine는 0부터 m-1만큼 1씩 증가
        FOR indexRow는 0부터 n-1만큼 1씩 증가
            IF remove[indexLine][indexRow]가 1이라면 
                기존 위치 지울 값을 0으로 변경 arrayBoard[indexLine][indexRow] = 0;
                지울값도 0으로 변경 remove[indexLine][indexRow] = 0;
                result값 1증가 result++;
    
    자리교환
    
    FOR indexRow는 0부터 n-1만큼 1씩 증가
        교환할 위치를 나타내는 index는 맨 아래서부터 찾아 나갈것임 index = m-1;
        
        FOR indexLine는 m-1, indexLine이 0보다 크거나 같다면 
            지금 값을 나타내는 변수 number = arrayBoard[indexLine][indexRow];
            교체를 위한 값을 나타낼 변수 tempt = arrayBoard[index][indexRow];
            IF number 가 0이 라면 
                indexLine 1감소 indexLine--;
                다음 라인 확인 countinue;
                
            arrayBoard[index][indexRow] 값을 number로 변경시킨다
            arrayBoard[indexLine][indexRow] 값을 tempt로 변경시킨다.
            index 1 줄이기 
            indexLine 1 줄이기
            
    
    return result
            
*/
class Solution {
  private static int m = 0;
  private static int n = 0;
  
  public int solution(int m, int n, String[] board) {
      int[][] arrayBoard = new int[m][n];
      int[][] remove = new int[m][n];
      
      this.m = m;
      this.n= n;
      
      int answer = 0;
      
      for (int indexLine = 0; indexLine < m; indexLine++ ) {
          for (int indexRow = 0; indexRow < n; indexRow++ ) {
              int thisChar = board[indexLine].charAt(indexRow) - 'A' + 1;
              arrayBoard[indexLine][indexRow] = thisChar;
          }
      }
      check( arrayBoard , m , n, remove );
      
      while ( checkRemove( remove , m , n  ) ) {
          answer += reArray( arrayBoard, remove );
          check( arrayBoard , m , n, remove );
      }
      
      return answer;
    }

    private int reArray(int[][] arrayBoard, int[][] remove) {
        int result = 0;
        for (int indexLine = 0; indexLine < m; indexLine++ ) {
            for (int indexRow = 0; indexRow < n; indexRow++ ) {
                if( remove[indexLine][indexRow] == 1 ) {
                    arrayBoard[indexLine][indexRow] = 0;
                    remove[indexLine][indexRow] = 0;
                    result++;
                }
            }
        }
        
        for (int indexRow = 0; indexRow < n; indexRow++ ) {
            int index = this.m - 1;
            for (int indexLine = m-1; indexLine >= 0;) {
                int  number = arrayBoard[indexLine][indexRow];
                int  tempt  = arrayBoard[index][indexRow];
                if( number == 0 ) {
                    indexLine--;
                    continue;
                }
                
                arrayBoard[index][indexRow] = number;
                arrayBoard[indexLine][indexRow] = tempt;
                index--;
                indexLine--;
            }
        }
        
        return result;
    }

    private boolean checkRemove(int[][] remove, int m, int n) {
        Boolean result = false;
        for (int indexLine = 0; indexLine < m; indexLine++ ) {
            for (int indexRow = 0; indexRow < n; indexRow++ ) {
                if( remove[indexLine][indexRow] == 1 ) 
                    return true;
            }
        }
        
        return result;
    }

    private void check(int[][] arrayBoard, int m, int n, int[][] remove ) {
        for (int indexLine = 0; indexLine < m; indexLine++ ) {
            for (int indexRow = 0; indexRow < n; indexRow++ ) {
                findRemove ( arrayBoard, indexLine, indexRow, remove );
            }
        }
    }

    private void findRemove(int[][] arrayBoard, int indexLine, int indexRow, int[][] remove) {
        if( indexLine + 1 >= this.m || indexRow+1 >= this.n )
            return;
            
        int number = arrayBoard[indexLine][indexRow];
        if( number != 0 &&
            number == arrayBoard[indexLine][indexRow +1 ] &&
            number == arrayBoard[indexLine+1][indexRow]   && 
            number == arrayBoard[indexLine+1][indexRow+1]) {
            remove[indexLine][indexRow]     = 1;
            remove[indexLine][indexRow+1]   = 1;
            remove[indexLine+1][indexRow]   = 1;
            remove[indexLine+1][indexRow+1] = 1;
         }
    }
}