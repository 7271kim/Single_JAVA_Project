//https://programmers.co.kr/learn/courses/30/lessons/60059

import java.util.ArrayList;
// 확인을 어떻게 할것인가.
// 요약 및 자물쇠 제한 , 키보다 locker가 큰데 어떻게 할 것인가.
// 회전
// 상하 좌우 이동
// 무엇을 반복시킬것인가.
class Solution {
    private  int size        = -1 ;
    private  int lokerTotalLength = 0 ;
    private  int keyTotalLength = 0 ;
    private ArrayList<int[]> lockerPotision = new ArrayList<int[]>();
    private ArrayList<int[]> keyotision = new ArrayList<int[]>();
    
    public boolean solution( int[][] key, int[][] lock ) {
        boolean answer = false;
        lokerTotalLength      = lock.length;
        keyTotalLength        = key.length;
       
        // locker요약 및 제한(기준점 조정 )
        int leftStart = lokerTotalLength;
        int topStart  = lokerTotalLength;
        for (int line = 0; line < lock.length; line++) {
            for (int row = 0; row < lock[line].length; row++) {
                if( lock[line][row] == 0 ) {
                    leftStart = leftStart > row ? row : leftStart;
                    topStart  = topStart > line ? line : topStart;
                }
            }
        }
        // 기준점 조정한 locker 넣기
        for (int line = 0; line < lock.length; line++) {
            for (int row = 0; row < lock[line].length; row++) {
                if( lock[line][row] == 0 ) {
                    size = size == -1 ? 0 : size;
                    size++;
                    lockerPotision.add(new int[] {line-topStart,row-leftStart});
                }
            }
        }
        
        
        // key 요약 및 제한( 기준점 조정 문제는 기준점 조정 후 회전이슈  )
        leftStart = keyTotalLength;
        topStart  = keyTotalLength;
        
        for (int line = 0; line < key.length; line++) {
            for (int row = 0; row < key[line].length; row++) {
                if( key[line][row] == 1 ) {
                    keyotision.add(new int[] {line,row});
                }
            }
        }
        
        if( leftCheck(keyotision) || rightCheck(keyotision) ) {
            answer = true;
        }
        
        return answer;
    }
    
    // 돌려 가며 확인하기
    private  boolean repeatRotation( ArrayList<int[]> inputKey) {
        Boolean result = false;
        
        //4번 회전시켜 확인하는 로직
        ArrayList<int[]> rotation = new ArrayList<int[]>();
        rotation = roatation(inputKey);
        
        for (int index = 0; index < 4; index++) {
            rotation = roatation(rotation);
            if( check(rotation) ) {
                result =  true;
                break;
            }
        }
       
        return result;
    }
    
    // 90도 회전하기
    private  ArrayList<int[]>  roatation( ArrayList<int[]> inputKey) {
        ArrayList<int[]> toatation = new ArrayList<int[]>();
        
        for (int keyIndex = 0; keyIndex < inputKey.size(); keyIndex++) {
            int line = inputKey.get(keyIndex)[0];
            int low  = inputKey.get(keyIndex)[1];
            int changeLine = low;
            int changeLow = ( keyTotalLength -1 - line );
            
            toatation.add(new int[] {changeLine,changeLow} );
        }
        return toatation;
    }
    
    // 왼쪽으로 가기  
    private Boolean leftCheck( ArrayList<int[]> inputKey ) {
        Boolean result = false;
        ArrayList<int[]> temp = inputKey;
        while( !temp.isEmpty() ) {
            if( repeatRotation(temp) ) {
                result = true;
                break;
            }
            if(topCheck(temp)) {
                result = true;
                break;
            }
            if(downCheck(temp)) {
                result = true;
                break;
            }
            temp = leftMove(temp);
        }
        return result;
    }
    
    // 오른쪽으로 가기  
    private Boolean rightCheck( ArrayList<int[]> inputKey ) {
        Boolean result = false;
        ArrayList<int[]> temp = inputKey;
        while( !temp.isEmpty() ) {
            if( repeatRotation(temp) ) {
                result = true;
                break;
            }
            if(topCheck(temp)) {
                result = true;
                break;
            }
            if(downCheck(temp)) {
                result = true;
                break;
            }
            temp = rightMove(temp);
        }
        return result;
    }
    
    // 위로 가기
    private Boolean topCheck( ArrayList<int[]> inputKey ) {
        Boolean result = false;
        ArrayList<int[]> temp = inputKey;
        while( !temp.isEmpty() ) {
            if( repeatRotation(temp) ) {
                result = true;
                break;
            }
            temp = upMove(temp);
        }
        return result;
    }
    
    // 아래로 가기
    private Boolean downCheck( ArrayList<int[]> inputKey ) {
        Boolean result = false;
        ArrayList<int[]> temp = inputKey;
        while( !temp.isEmpty() ) {
            if( repeatRotation(temp) ) {
                result = true;
                break;
            }
            temp = downMove(temp);
        }
        return result;
    }
    
    private ArrayList<int[]> leftMove( ArrayList<int[]> inputKey ) {
        ArrayList<int[]> result = new ArrayList<int[]>();  
        
        for (int keyIndex = 0; keyIndex < inputKey.size(); keyIndex++) {
            int line = inputKey.get(keyIndex)[0];
            int low  = inputKey.get(keyIndex)[1];
            if( low - 1 > -1  ) {
                result.add(new int[] {line, low - 1} );
            }
        }
        return result;
    }
    
    // 오른쪽으로 가기
    private ArrayList<int[]> rightMove( ArrayList<int[]> inputKey ) {
       ArrayList<int[]> result = new ArrayList<int[]>();  
        
        for (int keyIndex = 0; keyIndex < inputKey.size(); keyIndex++) {
            int line = inputKey.get(keyIndex)[0];
            int low  = inputKey.get(keyIndex)[1];
            if( low + 1 < keyTotalLength  ) {
                result.add(new int[] {line, low + 1} );
            }
        }
        
        return result;
    }
    
    // 위로 올리기
    private ArrayList<int[]> upMove( ArrayList<int[]> inputKey ) {
        ArrayList<int[]> result = new ArrayList<int[]>();  
    
        for (int keyIndex = 0; keyIndex < inputKey.size(); keyIndex++) {
            int line = inputKey.get(keyIndex)[0];
            int low  = inputKey.get(keyIndex)[1];
            if( line - 1  > -1 ) {
                result.add(new int[] {line - 1, low} );
            }
        }
       
        return result;
    }
    
    // 아래로 내리기
    private ArrayList<int[]> downMove( ArrayList<int[]> inputKey ) {
        ArrayList<int[]> result = new ArrayList<int[]>();  
        
        for (int keyIndex = 0; keyIndex < inputKey.size(); keyIndex++) {
            int line = inputKey.get(keyIndex)[0];
            int low  = inputKey.get(keyIndex)[1];
            if( line + 1  < keyTotalLength ) {
                result.add(new int[] {line + 1, low} );
            }
        }
        
        return result;
    }
    
    
    // 확인로직 
    private boolean check( ArrayList<int[]> checkKeyPosition ) {
        int count      = 0;
        
        // 기준점 맞추기
        int leftStartKey = keyTotalLength+1;
        int topStartKey = keyTotalLength+1;
        
        ArrayList<int[]> temp = new ArrayList<int[]>();
        
        for (int index = 0; index < checkKeyPosition.size(); index++) {
            int line = checkKeyPosition.get(index)[0];
            int row  = checkKeyPosition.get(index)[1];
            leftStartKey = leftStartKey > row ? row : leftStartKey;
            topStartKey  = topStartKey > line ? line : topStartKey;
        }
        
        for (int index = 0; index < checkKeyPosition.size(); index++) {
            int line = checkKeyPosition.get(index)[0];
            int row  = checkKeyPosition.get(index)[1];
            temp.add(new int[] {line-topStartKey,row-leftStartKey});
        }
        
        
        if( temp.size() != size ) return false;
        
        for (int index = 0; index < temp.size(); index++) {
            int line = temp.get(index)[0];
            int low  = temp.get(index)[1];
            for (int lokerIndex = 0; lokerIndex < lockerPotision.size(); lokerIndex++) {
                if( lockerPotision.get(lokerIndex)[0] == line && lockerPotision.get(lokerIndex)[1] == low ) {
                    count++;
                }
            }
        }
        
        return count == size;
    }
}