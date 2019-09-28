import java.util.*;

class Solution2Code {
    public int solution(String[] bishops) {
        int answer = 0;
        int[][] board = new int[8][8];
        for (int index = 0; index < bishops.length; index++) {
            String[] temp = bishops[index].split("");
            int x = 0;
            String xString = temp[0];
            if( xString.equals("A")) {
                x = 0;
            } else if( xString.equals("B")) {
                x = 1;
            } else if( xString.equals("C")) {
                x = 2;
            } else if( xString.equals("D")) {
                x = 3;
            } else if( xString.equals("E")) {
                x = 4;
            } else if( xString.equals("F")) {
                x = 5;
            } else if( xString.equals("G")) {
                x = 6;
            } else if( xString.equals("H")) {
                x = 7;
            } 
            int y = Integer.valueOf(temp[1])-1;
            board[x][y] = 1;
            
            check( board, x+1, y+1 , "upup");
            check( board, x+1, y-1 , "updown");
            check( board, x-1, y+1 , "downup");
            check( board, x-1, y-1 , "downdown");
        }
        
        for (int index = 0; index < board.length; index++) {
            for (int indexInner = 0; indexInner < board[index].length; indexInner++) {
                if(board[index][indexInner] == 0) answer++;
            }
        }
        
        return answer;
    }
    
    public int[][] check( int[][] board, int x, int y , String position) {
        if(x < 0 || y < 0 || x > 7 || y > 7 ||  board[x][y] == 1 ) return board;
        
        board[x][y] = 1;
        
        if(position.equals("upup")) board = check( board, x+1, y+1, "upup" );
        if(position.equals("updown")) board = check( board, x+1, y-1, "updown" );
        if(position.equals("downup")) board = check( board, x-1, y+1, "downup" );
        if(position.equals("downdown")) board = check( board, x-1, y-1, "downdown" );
        
        return board;
    }
    
}