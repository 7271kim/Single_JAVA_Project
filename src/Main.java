/*
 * https://www.acmicpc.net/problem/1436
 * 종말의 숫자란 어떤 수에 6이 적어도 3개이상 연속으로 들어가는 수
 * N번째 >> N-1에 666을 붙이기 >> X 
 * 문제가 16661도 종말의 수임.
 * 666 ~ 무한대로 while 돌면서 종말의 수 확인
 * 종말의 수 확인법 >> 해당이 문제 >> int형에서 연속된 숫자 확인하는 방법 >> 나는 String변경 후 matches수행
 * findCount가 받은 값이랑 같을 경우 해당 숫자를 출력
 * O( N(최대2,666,799 )*indexOf시간 복잡도(tartget 길이(7) * 찾는문자열 길이(3)) ) >> 56,002,779 >> 10억 안넘어서 괜춘
 * 
 */

public class Main {
    public static void main(String args[]){
      
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
        }
        
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("check");
        
        Solution3 temp = new Solution3();
        int[][] key2 = { 
                {1,0},
                {1,1}
            }; // 1이 열쇄
        int[][] lock2 = {
                {1,1,1},
                {1,1,0},
                {1,0,0}
               }; // 0이 홈
        
        System.out.println("True : "+temp.solution(key2, lock2));
    }
}

