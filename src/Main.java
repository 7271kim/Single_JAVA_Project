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
        StringBuilder resultString = new StringBuilder();
        try {
            //String[] firstLine = br.readLine().split(" ");
            //int total   = Integer.parseInt(firstLine[0]);
            //int compare = Integer.parseInt(firstLine[1]);
            //String[] secondeLine = br.readLine().split(" ");
            //int total = Integer.parseInt(br.readLine());
            //int total = sc.nextInt();
            //String result = "mixed";
            
            int total  = Integer.parseInt(br.readLine());
            int result = 0;
            
            for (int index = 0; index < total; index++) {
                String line = br.readLine();
                int[] temp = new int[26];
                int beforeText = -1;
                for (int indexInnner = 0; indexInnner < line.length(); indexInnner++) {
                    int inputIndex  = line.charAt(indexInnner) - 97;
                    if( indexInnner != 0 ) beforeText = inputIndex;
                    if( temp[inputIndex] == 0 ) {
                        temp[inputIndex] = 1;
                    } else {
                        
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        } 
    }
}
