package prorammers.kakao;
// https://programmers.co.kr/learn/courses/30/lessons/60061
// 로직은 맞는데 틀린이유 : Sort써서 일부로 틀렸다고 하게만듦
class KAKAONoSort60061 {
    int[][][] board;
    int totalSize; 
    int answer = 0;
    
    public int[][] solution(int n, int[][] build_frame) {
        int[][] answer = {};
        totalSize = n+1;
        board = new int[n+1][n+1][2]; // [y,x] = [ 0 기둥없음, 1 기둥존재  , 0 보업음 1 보존재 ]
        
        
        int buildSize= build_frame.length;
        
        for (int buildSizeIndex = 0; buildSizeIndex < buildSize; buildSizeIndex++) {
            int x = build_frame[buildSizeIndex][0];
            int y = build_frame[buildSizeIndex][1];
            int a = build_frame[buildSizeIndex][2];
            int b = build_frame[buildSizeIndex][3];
            
            //총 4케이스 검사
            if( a == 0 ) {
                if( b == 0) {
                    // a = 0 기둥  제거 케이스  >> 왼쪽 보가 ( 존재조건  - 시작지점이 기둥, 양츠이 보 ),  오늘쪽 보가( 존재조건을 만족해야함 )
                    removePillar( x, y );
                    
                } else {
                    makePillar( x, y );
                    // a = 0 기둥 설치케이스 >> 시작지점이 바닥( y점이 0 )이거나 , 기둥이( 아래가 0 1 )거나, 보( 좌측이 1 1 ) 여야한다.
                }
            } else {
               if( b == 0) {
                   // a = 1 보  제거 케이스  >> 왼쪽 보가 ( 존재조건  - 시작지점이 기둥, 양츠이 보 ),  오늘쪽 보가( 존재조건을 만족해야함 )
                   removeBo( x, y );
                } else {
                    // a = 1 보  설치케이스 >> 시작지점이 기둥( 아래가 0 1) 이거나 혹은 양츠이 보( 좌측이 1 1 , 시작지점이  1, 1 ) 여야 한다.
                    makeBo( x, y );
                }
            }
        }
        
        answer = new int[this.answer][3];
        int[][][] board2 = new int[n+1][n+1][2];
        int tempCount = 0;
        for (int y = 0; y < board.length; y++) {
            for (int x = 0; x < board[y].length; x++) {
                for (int index = 0; index <2; index++) {
                    if( board[y][x][index] == 1 ) {
                        board2[x][y][index] = 1;
                    }
                }
            }
        }
        
        for (int x = 0; x < board2.length; x++) {
            for (int y = 0; y < board2[x].length; y++) {
                if( board2[x][y][0] == 1 ) {
                    int[] temp = { x , y , 0 };
                    answer[tempCount++] = temp;
                    
                }
                if( board2[x][y][1] == 1 ) {
                    int[] temp = { x , y , 1 };
                    answer[tempCount++] = temp;
                } 
                
            }
        }
        
        return answer;
    }
    
    private void makeBo( int x, int y ) {
        //시작지점이 바닥( y점이 0 )이거나 , 기둥이( 아래가 0 1 )거나, 보( 좌측이 1 1 ) 여야한다.
        if( checkBo(x, y) ) {
            make(x, y, 1);
        }
    }
    
    private void makePillar( int x, int y ) {
        //시작지점이 바닥( y점이 0 )이거나 , 기둥이( 아래가 0 1 )거나, 보( 좌측이 1 1 ) 여야한다.
        if( checkPhill(x, y) ) {
            make(x, y, 0);
        }
    }
    
    private void removeBo( int x, int y ) {
        // 제거 부터 하고 양쪽 기둥 확인 영쪽 보 확인
        if( hasBo(x, y) ) {
            remove( x, y, 1 );
            
            Boolean isPossible = true;
            if( hasPillar(x, y)) {
                isPossible = checkPhill(x, y);
            }
            if( isPossible && hasPillar( x+1, y ) ) {
                isPossible = checkPhill( x+1, y );
            }
            if( isPossible && hasBo( x - 1, y ) ) {
                isPossible = checkBo( x - 1, y  );
            }
            if( isPossible && hasBo( x + 1, y ) ) {
                isPossible = checkBo( x + 1, y  );
            }
            if(!isPossible) {
                make( x, y, 1 );
            }
        }
    }
    
    private void removePillar( int x, int y ) {
        // 제거 부터 하고 위 좌 보가 존재 한다면 존재 조건, 위 보가 존재한다면 존재조건, 위 기둥이 존재한다면 존재조건을 만족해야 제거 가능
        if( hasPillar(x, y) ) {
            remove( x, y, 0 );
            Boolean isPossible = true;
            if( hasBo(x, y + 1) ) {
                isPossible = checkBo( x, y + 1 );
            }
            if( isPossible && hasBo(x-1, y + 1 ) ) {
                isPossible = checkBo( x-1, y + 1 );
            }
            if( isPossible && hasPillar(x, y + 1 ) ) {
                isPossible = checkPhill( x, y + 1 );
            }
            if(!isPossible) {
                make( x, y, 0 );
            }
        }
    }
    
    private void make( int x, int y, int check){
        if( x < totalSize && y < totalSize && x > -1 && y > -1 ) {
            board[y][x][check] = 1;
            answer++;
        }
    }
    
    private void remove( int x, int y, int check){
        if( x < totalSize && y < totalSize && x > -1 && y > -1 ) {
            board[y][x][check] = 0;
            answer--;
        }
    }
    private Boolean hasPillar( int x, int y ) {
        if( x < totalSize && y < totalSize && x > -1 && y > -1 ) {
            return board[y][x][0] == 1;
        } else {
            return false;
        }
        
    }
    private Boolean hasBo( int x, int y ) {
        if( x < totalSize && y < totalSize && x > -1 && y > -1 ) {
            return board[y][x][1] == 1;
        } else {
            return false;
        }
        
    }
    
    private Boolean checkBo( int x, int y ) {
        // 현재 보의 존재조건 - 왼쪽 또는 오른쪽이 기둥이거나 , 양측이 보  
        Boolean result = false;
        
        if( hasPillar(x, y-1) || hasPillar( x+1, y-1 ) || ( hasBo(x+1, y) && hasBo(x-1, y) ) ) {
            // 왼쪽 또는 오른쪽 아래 기둥이거나
            result = true;
        } 
        
        return result;
    }
    
    private Boolean checkPhill( int x, int y ) {
        // 현재 기둥의 존재 조건 - 아래가 바닥이거나 다른기둥 위거나 보 위일때 
        Boolean result = false;
        if( y == 0 || hasPillar(x, y-1) || hasBo(x, y) || hasBo(x-1, y) ) {
            result = true;
        }
        
        return result;
    }
}