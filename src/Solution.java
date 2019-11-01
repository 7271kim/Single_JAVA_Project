// 2 4 12번 실패
class Solution {
    int[][] lockerBig;
    int[][] keyBig;
    int lockerSize;
    int lockerStart;
    int homeCount = -1;
    
    public boolean solution(int[][] key, int[][] lock) {
        boolean answer = false;
        lockerSize = 2*(key.length) + lock.length - 2;
        lockerBig = new int[lockerSize][lockerSize];
        lockerStart = key.length -1;
        
        // 큰 판 세팅
        for (int line = 0; line < lock.length; line++) {
            for (int low = 0; low < lock.length; low++) {
                int value = lock[line][low];
                lockerBig[lockerStart+line][lockerStart+low] = value;
                if( value == 0 ) {
                    homeCount = homeCount == -1 ? 0 : homeCount;
                    homeCount++;
                }
            }
        }
        
        // 전수조사 최대 60 * 60
        out: for (int lockerLine = 0; lockerLine < lockerSize-key.length+1; lockerLine++) {
            for (int lockerLow = 0; lockerLow < lockerSize-key.length+1; lockerLow++) {
                // 키 판데기 초기화 * 20
                keyBig    = new int[lockerSize][lockerSize];
                for (int line = 0; line < key.length; line++) {
                    for (int low = 0; low < key.length; low++) {
                        int value = key[line][low];
                        keyBig[lockerLine+line][lockerLow+low] = value;
                    }
                }
                
                for (int cycle = 0; cycle < 4; cycle++) {
                    if( check(lock) ) {
                        answer = true;
                        break out;
                    } else {
                        rotation( lockerLine, lockerLow, key );
                    }
                }
            }
        }
        
        return answer;
    }
    
    private Boolean check( int[][] lock ) {
        int count = 0;
        for (int line = 0; line < lock.length; line++) {
            for (int low = 0; low < lock.length; low++) {
                int lineIndex   = lockerStart + line;
                int lowIndex    = lockerStart + low;
                int lockerValue = lockerBig[lineIndex][lowIndex];
                int keyValue    = keyBig[lineIndex][lowIndex];
                if( keyValue == 1 ) {
                    if( lockerValue == 0 ) {
                        count++;
                    } else {
                        return false;
                    }
                }
            }
        }
        
        return homeCount == count;
    }
    
    private void rotation( int lockerLine , int lockerLow, int[][] key) {
        int [][] temp = new int [lockerSize][lockerSize];
        for (int line = 0; line < key.length; line++) {
            for (int low = 0; low < key.length; low++) {
                int tempValue = keyBig[lockerLine+line][lockerLow+low];
                temp[lockerLine+low][lockerLow + key.length -1 - line] = tempValue;
            }
        }
        keyBig = temp;
    }
}