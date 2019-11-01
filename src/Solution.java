//https://programmers.co.kr/learn/courses/30/lessons/60059

import java.util.ArrayList;
// 확인을 어떻게 할것인가.
// 요약 및 자물쇠 제한 , 키보다 locker가 큰데 어떻게 할 것인가.
// 회전
// 상하 좌우 이동
class Solution {
    private  int size        = 0 ;
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
                    size++;
                    lockerPotision.add(new int[] {line-topStart,row-leftStart});
                }
            }
        }
        
        
        // key 요약 및 제한(기준점 조정 )
        int leftStartKey = keyTotalLength;
        int topStartKey  = keyTotalLength;
        
        for (int line = 0; line < key.length; line++) {
            for (int row = 0; row < key[line].length; row++) {
                if( key[line][row] == 1 ) {
                    leftStartKey = leftStartKey > row ? row : leftStartKey;
                    topStartKey  = topStartKey > line ? line : topStartKey;
                }
            }
        }
        
        for (int line = 0; line < key.length; line++) {
            for (int row = 0; row < key[line].length; row++) {
                if( key[line][row] == 1 ) {
                    keyotision.add(new int[] {line-topStartKey,row-leftStartKey});
                }
            }
        }
        
        // 90도씩  회전 
        ArrayList<int[]> rotation = rotation( keyotision );
        for (int index = 0; index < 4; index++) {
            rotation = rotation(rotation);
            if( leftRightMove(rotation) ) {
                answer = true;
                break;
            } 
        }
        
        return answer;
    }
    
    // 90도 회전하기
    private  ArrayList<int[]>  rotation( ArrayList<int[]> inputKey) {
        ArrayList<int[]> toatation = new ArrayList<int[]>();
        
        for (int keyIndex = 0; keyIndex < inputKey.size(); keyIndex++) {
            int line = inputKey.get(keyIndex)[0];
            int low  = inputKey.get(keyIndex)[0];
            int changeLow = line;
            int changeLine = ( low + keyTotalLength -1 ) % keyTotalLength;
            
            toatation.add(new int[] {changeLine,changeLow} );
        }
        return toatation;
    }
    
    // 오리지날 건드리지 좌우 움직임
    private boolean leftRightMove(  ArrayList<int[]> inputKey ) {
        boolean answer = true;
        
        // 우로 이동
        for (int lowMove = 0; lowMove < keyTotalLength; lowMove++) {
            //저장된 키값들 좌로 이동시켜서 새로 저장
            ArrayList<int[]> checkKeyPosition = new ArrayList<int[]>();
            
            for (int keyIndex = 0; keyIndex < inputKey.size(); keyIndex++) {
                int line = inputKey.get(keyIndex)[0];
                int low  = inputKey.get(keyIndex)[0];
                if( low+lowMove < keyTotalLength  ) {
                    low = low+lowMove;
                    checkKeyPosition.add(new int[] {line,low} );
                }
            }
            
            // 우로 이동한 키값들 확인
            if( check(checkKeyPosition) ) {
                answer = true;
                break;
            }
            
            // 아래 이동 
            ArrayList<int[]> checkKeyPositionDown = new ArrayList<int[]>();
            for (int lineMove = 0; lineMove < keyTotalLength; lineMove++) {
                for (int keyIndex = 0; keyIndex < checkKeyPosition.size(); keyIndex++) {
                    int line = checkKeyPosition.get(keyIndex)[0];
                    int low  = checkKeyPosition.get(keyIndex)[0];
                    
                    if( line+lineMove < keyTotalLength  ) {
                        line = line+lineMove;
                        checkKeyPositionDown.add(new int[] {line,low} );
                    }
                }
            }
            
            //아래 이동 키값들 확인
            if( check(checkKeyPositionDown) ) {
                answer = true;
                break;
            }
            
           // 위 이동 
            checkKeyPositionDown = new ArrayList<int[]>();
            for (int lineMove = 0; lineMove < keyTotalLength; lineMove++) {
                for (int keyIndex = 0; keyIndex < checkKeyPosition.size(); keyIndex++) {
                    int line = checkKeyPosition.get(keyIndex)[0];
                    int low  = checkKeyPosition.get(keyIndex)[0];
                    
                    if( line-lineMove > -1  ) {
                        line = line-lineMove;
                        checkKeyPositionDown.add(new int[] {line,low} );
                    }
                }
            }
            
            //위로 이동 키값들 확인
            if( check(checkKeyPositionDown) ) {
                answer = true;
                break;
            }
        }
        
         // 좌로 이동
        for (int lowMove = 0; lowMove < keyTotalLength; lowMove++) {
            //저장된 키값들 좌로 이동시켜서 새로 저장
            ArrayList<int[]> checkKeyPosition = new ArrayList<int[]>();
            
            for (int keyIndex = 0; keyIndex < inputKey.size(); keyIndex++) {
                int line = inputKey.get(keyIndex)[0];
                int low  = inputKey.get(keyIndex)[0];
                if( low-lowMove > -1  ) {
                    low = low-lowMove;
                    checkKeyPosition.add(new int[] {line,low} );
                }
            }
            
            // 좌로 이동한 키값들 확인
            if( check(checkKeyPosition) ) {
                answer = true;
                break;
            }
            // 아래 이동 
            ArrayList<int[]> checkKeyPositionDown = new ArrayList<int[]>();
            for (int lineMove = 0; lineMove < keyTotalLength; lineMove++) {
                for (int keyIndex = 0; keyIndex < checkKeyPosition.size(); keyIndex++) {
                    int line = checkKeyPosition.get(keyIndex)[0];
                    int low  = checkKeyPosition.get(keyIndex)[0];
                    
                    if( line+lineMove < keyTotalLength  ) {
                        line = line+lineMove;
                        checkKeyPositionDown.add(new int[] {line,low} );
                    }
                }
            }
            
            //아래 이동 키값들 확인
            if( check(checkKeyPositionDown) ) {
                answer = true;
                break;
            }
            
           // 위 이동 
            checkKeyPositionDown = new ArrayList<int[]>();
            for (int lineMove = 0; lineMove < keyTotalLength; lineMove++) {
                for (int keyIndex = 0; keyIndex < checkKeyPosition.size(); keyIndex++) {
                    int line = checkKeyPosition.get(keyIndex)[0];
                    int low  = checkKeyPosition.get(keyIndex)[0];
                    
                    if( line-lineMove > -1  ) {
                        line = line-lineMove;
                        checkKeyPositionDown.add(new int[] {line,low} );
                    }
                }
            }
            
            //위로 이동 키값들 확인
            if( check(checkKeyPositionDown) ) {
                answer = true;
                break;
            }
        }
        
        
        return answer;
    }
    
    // 확인로직 
    private boolean check( ArrayList<int[]> checkKeyPosition ) {
        int count      = 0;
        
        if( checkKeyPosition.size() != size ) return false;
        
        for (int index = 0; index < checkKeyPosition.size(); index++) {
            int line = checkKeyPosition.get(index)[0];
            int low  = checkKeyPosition.get(index)[0];
            for (int lokerIndex = 0; lokerIndex < lockerPotision.size(); lokerIndex++) {
                if( lockerPotision.get(lokerIndex)[0] == line && lockerPotision.get(lokerIndex)[1] == low ) {
                    count++;
                }
            }
        }
        
        return count == size;
    }
}