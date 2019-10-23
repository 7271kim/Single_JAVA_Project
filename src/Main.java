import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Scanner;

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
            /*MyQueueLinked test = new MyQueueLinked();
            test.add(1);
            test.add(2);
            test.add(3);
            test.add("dd");
            test.add("ee");
            
            while(!test.isEmpty()) {
                System.out.println(test.remove());
            }*/
            
            MyQueueArray test2 = new MyQueueArray(5);
            test2.add(1);
            test2.add(2);
            test2.add(3);
            test2.add("dd");
            test2.add("ee");
            
            while(!test2.isEmpty()) {
                System.out.println(test2.remove());
                
            }
            
        } catch (Exception e) {
            System.out.println(e);
        } 
    }
}

