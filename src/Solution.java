
import java.util.ArrayList;


/*
전체 루프 돌면서 케이스 확인 로직
int size = 3;
int total = (int)Math.pow(2, size*size);

int big[][][] = new int[total+1][size][size];
int  totalIndex = 0;
for ( int first = 0; first <= 1; first++ ) {
    for ( int second = 0; second <= 1; second++ ) {
        for ( int three = 0; three <= 1; three++ ) {
            for ( int four = 0; four <= 1; four++ ) {
                for ( int five = 0; five <= 1; five++ ) {
                    for ( int six = 0; six <= 1; six++ ) {
                        for ( int seven = 0; seven <= 1; seven++ ) {
                            for ( int eight = 0; eight <= 1; eight++ ) {
                                for ( int nine = 0; nine <= 1; nine++ ) {
                                    int[][] serch =  new int[size][size];
                                    //line , low
                                    serch[0][0] = nine;
                                    serch[0][1] = eight;
                                    serch[0][2] = seven;
                                    serch[1][0] = six;
                                    serch[1][1] = five;
                                    serch[1][2] = four;
                                    serch[2][0] = three;
                                    serch[2][1] = second;
                                    serch[2][2] = first;
                                    big[totalIndex++] = serch;
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
size = 2;
total = (int)Math.pow(2, size*size);
int key[][][] = new int[total+1][size][size];
int  totalIndex2 = 0;
for ( int first = 0; first <= 1; first++ ) {
    for ( int second = 0; second <= 1; second++ ) {
        for ( int three = 0; three <= 1; three++ ) {
            for ( int four = 0; four <= 1; four++ ) {
                int[][] serch =  new int[size][size];
                //line , low
                serch[0][0] = first;
                serch[0][1] = second;
                serch[1][0] = three;
                serch[1][1] = four;
                
                key[totalIndex2++] = serch;
            }
        }
    }
}

outer : for (int index = 0; index < big.length; index++) {
    for (int keyIndex = 0; keyIndex < key.length; keyIndex++) {
        Solution temp = new Solution();
        Solution3 temp2 = new Solution3();
        int[][] inputKey  = key[keyIndex]; // 1이 열쇄
        int[][] lock = big[index]; // 0이 홈
        
        Boolean solution1 = temp.solution(inputKey, lock);
        Boolean solution2 = temp2.solution(inputKey, lock);
        if( solution1 != solution2 ) {
            System.out.println("solution 1 : " + solution1);
            System.out.println("solution 2 : " + solution2);
            System.out.println("KEY : ");
            for (int x = 0; x < inputKey.length; x++) {
                for (int y = 0; y < inputKey[x].length; y++) {
                    System.out.print(inputKey[x][y] + " ");
                }
                System.out.println();
            }
            
            System.out.println("문제 : ");
            for (int x = 0; x < lock.length; x++) {
                for (int y = 0; y < lock[x].length; y++) {
                    System.out.print(lock[x][y] + " ");
                }
                System.out.println();
            }
        }
        
    }
}*/
class Solution {
    int[][] lockerBig;
    int[][] keyBig;
    int lockerSize;
    int lockerStart;
    int homeCount = -1;
    int keyCount  = -1;
    
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
        
        if( homeCount == -1 ) {
            return true;
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