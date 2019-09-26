package ok.beak;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

/*
 * https://www.acmicpc.net/problem/2164
 * 큐
 */

public class Beak2164 {
    public static void main(String args[]){
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //Scanner sc = new Scanner(System.in);
        try {
            int total = Integer.parseInt(br.readLine());
            Queue<Integer> que  = new LinkedList<Integer>();
            for (int index = 1; index <= total; index++) {
               que.add(index);
            }
            while( que.size() >= 2 ) {
                que.poll();
                int next  = que.poll();
                que.add(next);
            }
            System.out.println(que.peek());
        } catch (Exception e) {
            System.out.println(e);
        } 
    }
}
