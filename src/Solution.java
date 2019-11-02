import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

// https://programmers.co.kr/learn/courses/30/lessons/60061
// 24점 소스 
class Solution {
    int[][][] board;
    int totalSize; 
    private class TempNode implements Comparator<TempNode> {
        private int x;
        private int y;
        private int check;
        
        private TempNode() {
        }
        private TempNode( int x , int y, int check) {
            this.x = x;
            this.y = y;
            this.check = check;
        }
        
        @Override
        public int compare(TempNode before, TempNode after) {
            int result = 0;
            if( after.x > before.x ) {
                result = -1;
            } else if (after.x == before.x ){
                if (after.y > before.y ) {
                    result = -1;
                } else if( after.y == before.y ){
                    if( after.check > before.check ) {
                        result = -1;
                    }
                }
            }
            return result;
        }
    }
    public int[][] solution(int n, int[][] build_frame) {
        
        List<TempNode> list = new ArrayList<>();
        
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
        
        for (int y = 0; y < board.length; y++) {
            for (int x = 0; x < board[y].length; x++) {
                for (int index = 0; index <2; index++) {
                    if( board[y][x][index] == 1 ) {
                        TempNode tempNode = new TempNode(x, y, index);
                        list.add(tempNode);
                        /* String temp =  index == 0 ? "기둥존재" : "보 존재";
                        System.out.println("( "+x+ " , "+y+" ) " + temp );*/
                    }
                }
            }
        }
        
        Collections.sort(list, new TempNode());
        answer = new int[list.size()][3];
        for (int index = 0; index < list.size(); index++) {
            TempNode temp = list.get(index);
            int[] inner = { temp.x, temp.y, temp.check };
            answer[index] = inner; 
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
    
    private void removePillar( int x, int y ) {
        // 제거 부터 하고 위 좌 보가 존재 한다면 존재 조건, 위 보가 존재한다면 존재조건, 위 기둥이 존재한다면 존재조건을 만족해야 제거 가능
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
    
    private void make( int x, int y, int check){
        if( y < totalSize && x < totalSize ) {
            board[y][x][check] = 1;
        }
    }
    
    private void remove( int x, int y, int check){
        board[y][x][check] = 0;
    }
    private Boolean hasPillar( int x, int y ) {
        return board[y][x][0] == 1;
    }
    private Boolean hasBo( int x, int y ) {
        return board[y][x][1] == 1;
    }
    
    private Boolean checkBo( int x, int y ) {
        // 현재 보의 존재조건 - 왼쪽 또는 오른쪽이 기둥이거나 , 양측이 보  
        Boolean result = false;
        
        if( hasPillar(x, y-1) || hasPillar( x+1, y-1 ) ) {
            // 왼쪽 또는 오른쪽 아래 기둥이거나
            result = true;
        } else {
            // 좌우 둘다 보
            if( hasBo(x+1, y) && hasBo(x-1, y) ) {
                result = true;
            }
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