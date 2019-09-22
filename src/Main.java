import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/*
 * https://www.acmicpc.net/problem/1011
 * 데칼 코마니는 N의 제곱승이다.
 * 1^1 = 1
 * 2^2 = 1 + 2 + 1
 * 3^2 = 1 + 2 + 3 + 2 + 1
 * 4^2 = 1 + 2 + 3 + 4 + 3 + 2 + 1
 * n^2 = 1+2+ ... + n + n-1 ... + 1
 * http://blog.naver.com/PostView.nhn?blogId=occidere&logNo=220982644540&categoryNo=0&parentCategoryNo=0&viewDate=&currentPage=1&postListTopCurrentPage=1&from=postView
 * https://jaimemin.tistory.com/1170
 *  
 */

public class Main {
    public static void main(String args[]){
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Scanner sc = new Scanner(System.in);
        //StringBuilder resultString = new StringBuilder();
        try {
            //String[] firstLine = br.readLine().split(" ");
            //int total   = Integer.parseInt(firstLine[0]);
            //int compare = Integer.parseInt(firstLine[1]);
            //String[] secondeLine = br.readLine().split(" ");
            //int total = Integer.parseInt(br.readLine());
            //int total = sc.nextInt();
            //String result = "mixed";
            
            int firstNumber     = sc.nextInt();
            int secondNumber    = sc.nextInt();
            int sum             = firstNumber*secondNumber;
            while( secondNumber / 10 > 0 ) {
                int digitOne = secondNumber %10;
                int temp = firstNumber*digitOne;
                System.out.println( temp );
                secondNumber /= 10;
            }
            System.out.println(firstNumber*secondNumber);
            System.out.println(sum);
            
            
            
        } catch (Exception e) {
            System.out.println(e);
        } 
    }
}
