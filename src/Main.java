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
      Solution temp = new Solution();
      int[][] key = { 
              {1,1,1},
              {1,1,1},
              {1,1,1}
          }; // 1이 열쇄
      int[][] lock = {
              {1,0,0,1},
              {1,1,1,1},
              {1,1,1,1},
              {1,1,1,1}
             }; // 0이 홈
      
      System.out.println("true : "+temp.solution(key, lock));
      
      temp = new Solution();
      int[][] key1 = { 
              {0,0,0},
              {0,0,0},
              {0,0,1}
          }; // 1이 열쇄
      int[][] lock1 = {
              {1,1,1,1},
              {1,1,1,1},
              {1,1,1,1},
              {1,1,1,0}
             }; // 0이 홈
      System.out.println("true : "+temp.solution(key1, lock1));
      
      temp = new Solution();
      int[][] key2 = { 
              {0,0,0},
              {0,0,1},
              {0,0,1}
          }; // 1이 열쇄
      int[][] lock2 = {
              {1,1,1,1},
              {1,1,1,1},
              {1,1,1,1},
              {1,1,0,0}
             }; // 0이 홈
      System.out.println("true : "+temp.solution(key2, lock2));
      
      temp = new Solution();
      int[][] key3 = { 
              {0,0,0},
              {1,0,0},
              {0,1,1}
          }; // 1이 열쇄
      int[][] lock3 = {
              {1,1,1,1},
              {1,1,1,1},
              {1,1,1,0},
              {1,1,0,1}
             }; // 0이 홈
      System.out.println("true : "+temp.solution(key3, lock3));
      
      temp = new Solution();
      int[][] key4 = { 
              {0,1,0},
              {1,1,1},
              {0,1,0}
          }; // 1이 열쇄
      int[][] lock4 = {
              {1,1,1,1},
              {1,1,0,1},
              {1,0,0,1},
              {1,1,1,1}
             }; // 0이 홈
      System.out.println("false : "+temp.solution(key4, lock4));
      
      temp = new Solution();
      int[][] key5 = { 
              {0,1,0},
              {1,1,1},
              {0,1,0}
          }; // 1이 열쇄
      int[][] lock5 = {
              {1,1,1,1},
              {1,1,1,1},
              {1,1,1,0},
              {1,1,0,0}
             }; // 0이 홈
      System.out.println("true : "+temp.solution(key5, lock5));
      
      temp = new Solution();
      int[][] key6 = { 
              {0,0,0},
              {0,1,1},
              {0,1,0}
          }; // 1이 열쇄
      int[][] lock6 = {
              {1,1,1,1},
              {1,1,0,1},
              {1,0,0,1},
              {1,1,1,1}
             }; // 0이 홈
      System.out.println("true : "+temp.solution(key6, lock6));
      System.out.println();
      System.out.println();
      System.out.println();
      System.out.println();
      
      
        /*      
             temp = new Solution();
              int[][] key7 = { 
              {1,1,1},
              {1,1,1},
              {1,1,1}
          }; // 1이 열쇄
              int[][] lock7 = {
              {1,0,0,1},
              {1,1,1,1},
              {1,1,1,1},
              {1,1,1,1}
             }; // 0이 홈
              
              System.out.println("true : "+temp.solution2(key7, lock7));
              */
      
      temp = new Solution();
      int[][] key9 = { 
              {0,0,0},
              {0,0,1},
              {0,0,1}
          }; // 1이 열쇄
      int[][] lock9 = {
              {1,1,1,1},
              {1,1,1,1},
              {1,1,1,1},
              {1,1,0,0}
             }; // 0이 홈
      System.out.println("true : "+temp.solution2(key9, lock9));
      
      temp = new Solution();
      int[][] key10 = { 
              {0,0,0},
              {1,0,0},
              {0,1,1}
          }; // 1이 열쇄
      int[][] lock10 = {
              {1,1,1,1},
              {1,1,1,1},
              {1,1,1,0},
              {1,1,0,1}
             }; // 0이 홈
      System.out.println("true : "+temp.solution2(key10, lock10));
      
      temp = new Solution();
      int[][] key11 = { 
              {0,1,0},
              {1,1,1},
              {0,1,0}
          }; // 1이 열쇄
      int[][] lock11 = {
              {1,1,1,1},
              {1,1,0,1},
              {1,0,0,1},
              {1,1,1,1}
             }; // 0이 홈
      System.out.println("false : "+temp.solution2(key11, lock11));
      
      temp = new Solution();
      int[][] key12 = { 
              {0,1,0},
              {1,1,1},
              {0,1,0}
          }; // 1이 열쇄
      int[][] lock12 = {
              {1,1,1,1},
              {1,1,1,1},
              {1,1,1,0},
              {1,1,0,0}
             }; // 0이 홈
      System.out.println("true : "+temp.solution2(key12, lock12));
      
      temp = new Solution();
      int[][] key13 = { 
              {0,0,0},
              {0,1,1},
              {0,1,0}
          }; // 1이 열쇄
      int[][] lock13 = {
              {1,1,1,1},
              {1,1,0,1},
              {1,0,0,1},
              {1,1,1,1}
             }; // 0이 홈
      System.out.println("true : "+temp.solution2(key13, lock13));
      
    }
}

