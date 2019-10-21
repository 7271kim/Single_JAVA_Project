package algorithm.math;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/*
 * https://www.acmicpc.net/problem/1011
 * 데칼 코마니는 N의 제곱승이다.
 * 1^1 = 1 >> 1회 작동
 * 2^2 = 1 + 2 + 1 >> 3회 작동
 * 3^2 = 1 + 2 + 3 + 2 + 1 >> 5회 작동 
 * 4^2 = 1 + 2 + 3 + 4 + 3 + 2 + 1 >> 7회 작동 
 * n^2 = 1+2+ ... + n + n-1 ... + 1 >> 2n -1회 작동
 * http://blog.naver.com/PostView.nhn?blogId=occidere&logNo=220982644540&categoryNo=0&parentCategoryNo=0&viewDate=&currentPage=1&postListTopCurrentPage=1&from=postView
 * https://jaimemin.tistory.com/1170
 *  
 */

public class Decalcomani {
    public static void main(String args[]){
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Scanner sc = new Scanner(System.in);
        StringBuilder resultString = new StringBuilder();
        try {
            //String[] firstLine = br.readLine().split(" ");
            //int total   = Integer.parseInt(firstLine[0]);
            //int compare = Integer.parseInt(firstLine[1]);
            //String[] secondeLine = br.readLine().split(" ");
            //int total = Integer.parseInt(br.readLine());
            //int total = sc.nextInt();
            //String result = "mixed";
            
            int total   = sc.nextInt();
            
            for (int index = 0; index < total; index++) {
                int first    = sc.nextInt();
                int second   = sc.nextInt();
                int interval = second - first;
                int n        = 1;
                
                /*
                Before 아래 로직 줄이기
                while( true ) {
                    if( interval <= n*n ) break;
                    n++;
                }
                */
                double sqrt = Math.sqrt(interval); // 제곱근 interval <= n*n 의 최소를 구하기 위한 제곱근
                double ceil = Math.ceil(sqrt); // 올림
                
                // 그룹 찾기
                n = (int) ceil; // n에 가장 근사한 값부터 시작하기
                
                
                
                // n을 찾으면 최소 워프횟수는 아래로 n번 반복된다.
                // total <= n*n - n 작을 시 한단계 아래 번호 ( 2*n -2 ) , 아닐시 그냥 번호  ( 2*n -1 )
                if( interval <= n*(n-1) ) {
                    resultString.append(2*n -2 + "\n");
                } else {
                    resultString.append(2*n -1  + "\n");
                }
            }
            System.out.println(resultString);
            
        } catch (Exception e) {
            System.out.println(e);
        } 
    }
}
