package ok.beak;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

/*
 * https://www.acmicpc.net/problem/10845
 * 큐
 */

public class Beak10845Que {
    public static void main(String args[]){
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //Scanner sc = new Scanner(System.in);
        //  1 : peak,poll
        //  2
        //  3
        try {
            int total = Integer.parseInt(br.readLine());
            Queue<Integer> que  = new LinkedList<Integer>();
            int last    = -1;
            for (int index = 0; index < total; index++) {
                String line = br.readLine();
                if( line.indexOf("push") > -1 ) {
                    int temp =Integer.parseInt(line.split(" ")[1]); 
                    que.add(temp);
                    last = temp;
                } else if( line.indexOf("pop") > -1 ) {
                    System.out.println(que.isEmpty()?-1:que.poll());
                } else if( line.indexOf("size") > -1 ) {
                    System.out.println(que.size());
                } else if( line.indexOf("empty") > -1 ) {
                    System.out.println(que.isEmpty()?1:0);
                } else if( line.indexOf("front") > -1 ) {
                    System.out.println(que.isEmpty()?-1:que.peek());
                }  else if( line.indexOf("back") > -1 ) {
                    System.out.println(que.isEmpty()?-1:last);
                } 
            }
        } catch (Exception e) {
            System.out.println(e);
        } 
    }
}
