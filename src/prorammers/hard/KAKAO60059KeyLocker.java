package prorammers.hard;

import java.util.ArrayList;


/*
항상 전체 true와 전체 false는 의심해보자 
// 틀렷던 것은 자물쇠가 모두 1인 케이스

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
class KAKAO60059KeyLocker {
    /*
       카카오의 함정 
       solution , solution2 둘다 정답인데  
    KEY : 
        1 1 
        1 0 
        문제 : 
        1 1 1 
        1 0 0 
        1 1 1 
        
        해당케이스는 1번의 경우 true지만 아래는 false이다.
        정답은 당연 false 어떤 조합으로도 만들 수 없다. 
    */
    private  int size        = -1 ;
    private  int lokerTotalLength = 0 ;
    private  int keyTotalLength = 0 ;
    private ArrayList<int[]> lockerPotision = new ArrayList<int[]>();
    private ArrayList<int[]> keyotision = new ArrayList<int[]>();
    // 자물쇠에....내가 틀렸던 것은 홈이 없는 것은 성공이다. ( 롸커가 모두 1 로 채워진 경우! )
    
     // 확인을 어떻게 할것인가.
     // 요약 및 자물쇠 제한 , 키보다 locker가 큰데 어떻게 할 것인가.
     // 회전
     // 상하 좌우 이동
     // 무엇을 반복시킬것인가.
     // 문제는 좌물쇠 영역 내...에는 ...이라는 문구 
     // 3개가 통과가 안됨 2 4 12 
    public boolean solution( int[][] key, int[][] lock ) {
        boolean answer = false;
        lokerTotalLength      = lock.length;
        keyTotalLength        = key.length;
       
        // locker요약 및 제한(기준점 조정 )
        int leftStart = lokerTotalLength;
        int topStart  = lokerTotalLength;
        Boolean hasPerfect = true;
        
        for (int line = 0; line < lock.length; line++) {
            for (int row = 0; row < lock[line].length; row++) {
                if( lock[line][row] == 0 ) {
                    leftStart = leftStart > row ? row : leftStart;
                    topStart  = topStart > line ? line : topStart;
                    if( hasPerfect ) {
                        if( line == 0 || line == lokerTotalLength -1 ||  row == 0 || row == lokerTotalLength -1 ) {
                            hasPerfect = false;
                        }
                       
                    }
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
        
        if( size == -1 ) return true;
        
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
        
        // 키는 벗어난는데 열쇠의 영역 안 이라면 이슈가 있다.
        // 벽면에 하나라도 이슈가 붙은 것이 없다면 항상 꼭 맞아야 한다.
        if( hasPerfect) {
            if(repeatRotation(keyotision)) {
                answer = true;
            }
        } else {
            if( leftCheck(keyotision) || rightCheck(keyotision) ) {
                answer = true;
            }
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
     // 2 4 12번 실패
     // 자물쇠에....내가 틀렸던 것은 홈이 없는 것은 성공이다. ( 롸커가 모두 1 로 채워진 경우!)
     // 큰 판떼기 준비 크기는  2*(key.length) + lock.length - 2 >>  ( M + N + M 2개 겹치는거 제거)
     // 외부 큰 판떼기 하나하나 진행하면서 검사하기
    
    int[][] lockerBig;
    int[][] keyBig;
    int lockerSize;
    int lockerStart;
    int homeCount = -1;
    int keyCount  = -1;
    
    public boolean solution2(int[][] key, int[][] lock) {
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