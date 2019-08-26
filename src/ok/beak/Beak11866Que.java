package ok.beak;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

/*
 * https://www.acmicpc.net/problem/11866
 * 큐 - 순회 돌기
 */

public class Beak11866Que {
    public static void main(String args[]){
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //Scanner sc = new Scanner(System.in);
        try {
            String[] input = br.readLine().split(" ");
            int total = Integer.parseInt(input[0]);
            int k = Integer.parseInt(input[1]);
            int tempt = 0;
            Queue<Integer> que = new LinkedList<Integer>();
            StringBuilder result = new StringBuilder();
            result.append("<");
            
            for (int index = 1; index <= total; index++) {
                que.add(index);
            }
            while(que.size() > 0) {
                // 큐의 순회 돌기
                for (int index = 0; index < k-1; index++) {
                    int temp = que.poll();
                    que.add(temp);
                }
                
                // 맨 위에 있는 친구 넣기
                result.append(que.poll());
                if( que.size() == 0 ) {
                    result.append(">");
                } else {
                    result.append(", ");
                }
            }
            System.out.println(result);
        } catch (Exception e) {
            System.out.println(e);
        } 
    }
}
