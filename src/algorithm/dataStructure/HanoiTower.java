package algorithm.dataStructure;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
public class HanoiTower{
    
    public static void main(String args[]) throws IOException{
        /*
         * https://www.acmicpc.net/problem/1914
         * 재귀에 대한 이해 , 큰 수에 대한 이해
         */
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        BigInteger big = new BigInteger("1");

        if(N <= 20 ) {
            System.out.println((1<<N)-1);
            // 답부터 제출 (1<<n)은= 2^n을 나타냄 답은  2^n -1 왜냐면 기존것 옮기는것 2번을 해야 가능 + 마지막 원판 1
            showCheck(N, 1, 2, 3);
        } else {
            // 20이 넘어버리면 2의 100승으로 long의 한계가 넘어감. 그때 사용하는 형
            // 곱셈 덧샘 방법이 따로 있음
            for( int index = 0 ; index < N; index++ ) {
                //big = big.multiply(BigInteger.TWO); >> BigInteger.TWO는 백준에서 못씀
                big = big.multiply(new BigInteger("2"));
            }
            big = big.subtract(new BigInteger("1"));
            System.out.println(big);
        }
        
    }
    
    // 반복을 찾는다 
    /*
     1. 1번 기둥에 위치한 N개의 원판 중, 위에 있는 N-1개의 원판을 2번 기둥으로 옮긴다.
     2. 1번 기둥에 남아있는 1개의 원판을 3번 기둥으로 옮긴다.
     3. 2번 기둥에 있는 N-1개의 원판을 3번 기둥으로 옮긴다.
     >> 개념을 옮긴다!!!
    */
    public static void showCheck( int total,int from, int by, int to ) {
        if( total == 1 ) {
            System.out.println(from + " " + to);
        } else {
            showCheck(total - 1, from, to, by);
            System.out.println(from + " " + to);
            showCheck(total - 1, by, from, to);
        }
    }
}
