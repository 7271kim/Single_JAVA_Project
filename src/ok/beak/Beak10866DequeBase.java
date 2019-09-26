package ok.beak;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

/*
 * https://www.acmicpc.net/problem/10866
 * Deque 
 *
 */

public class Beak10866DequeBase {
    public static void main(String args[]){
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //Scanner sc = new Scanner(System.in);
        //  1 : peak,poll
        //  2
        //  3
        try {
            int total = Integer.parseInt(br.readLine());
            Deque<Integer> deque   = new LinkedList<Integer>();
            int last    = -1;
            for (int index = 0; index < total; index++) {
                String line = br.readLine();
                if( line.indexOf("push_front") > -1 ) {
                    int temp =Integer.parseInt(line.split(" ")[1]); 
                    deque.addFirst(temp);
                    last = temp;
                } else if( line.indexOf("push_back") > -1 ) {
                    int temp =Integer.parseInt(line.split(" ")[1]); 
                    deque.addLast(temp);
                } else if( line.indexOf("pop_front") > -1 ) {
                    System.out.println(deque.isEmpty()?-1:deque.pop());
                } else if( line.indexOf("pop_back") > -1 ) {
                    System.out.println(deque.isEmpty()?-1:deque.removeLast());
                } else if( line.indexOf("size") > -1 ) {
                    System.out.println(deque.size());
                } else if( line.indexOf("front") > -1 ) {
                    System.out.println(deque.isEmpty()?-1:deque.peek());
                }  else if( line.indexOf("back") > -1 ) {
                    System.out.println(deque.isEmpty()?-1:deque.peekLast());
                } else if( line.indexOf("empty") > -1 ) {
                    System.out.println(deque.isEmpty()?1:0);
                } 
            }
        } catch (Exception e) {
            System.out.println(e);
        } 
    }
}
