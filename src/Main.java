import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

/*
 * https://www.acmicpc.net/problem/1021
 * Deque 
 *
 */

public class Main {
    public static void main(String args[]){
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            String[] firstLine = br.readLine().split(" ");
            String[] secondLine = br.readLine().split(" ");
            int total = Integer.parseInt(firstLine[0]);
            Deque<Integer> deque   = new LinkedList<Integer>();
            int result = 0;
            for (int index = 1; index <= total; index++) 
                deque.addLast(index);
            for (int index = 0; index < secondLine.length; index++) {
                int number = Integer.parseInt(secondLine[index]);
                int position = 0;
                while(number != deque.peek()) {
                    position++;
                    deque.addLast(deque.pop());
                }
                int backSize = deque.size()-position; // 앞으로 돌때, 뒤로 돌때 크기 확인
                result += position < backSize ? position : backSize;
                deque.pop();
            }
            System.out.println(result);
        } catch (Exception e) {
            System.out.println(e);
        } 
    }
}
