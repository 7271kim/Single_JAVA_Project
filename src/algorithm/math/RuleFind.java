package algorithm.math;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/*
 * https://www.acmicpc.net/problem/2869
 * 달팽이
 */

public class RuleFind {
    public static void main(String args[]){
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 규칙찾기 
        // 낮기준 하루 올라가는 것  A1 = A
        // 그 다음날                   A2 = A + (A-B)
        // 그 다음날                   A3 = A + (A-B) + (A-B)
        // 일반적 반복                 An = A + (A-B)*(N-1)
        // 정리                         total < A + (A-B)*(N-1)
        // (total - B) / (A - B) < N 인 최소의 N을 구하는 것.
        // 올림응용
        try {
            String[] firstLine = br.readLine().split(" ");
            double up   = Integer.parseInt(firstLine[0]);
            double down    = Integer.parseInt(firstLine[1]);
            double height  = Integer.parseInt(firstLine[2]);
            int result  = (int)Math.ceil( ( height - down ) / ( up - down ) );
            System.out.println(result);
        } catch (Exception e) {
            System.out.println(e);
        } 
    }
}
