package ok.beak;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/*
 * https://www.acmicpc.net/problem/10773
 * 스택
 */

public class Beak10773 {
    public static void main(String args[]){
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //Scanner sc = new Scanner(System.in);
        try {
            int number = Integer.parseInt(br.readLine());
            Stack<Integer> stack = new Stack<Integer>();
            int sum    = 0;
            for (int index = 0; index < number; index++) {
                int line = Integer.parseInt(br.readLine());
                if( line == 0 ) {
                    sum -= stack.pop();
                } else {
                    sum += line;
                    stack.push(line);
                }
            }
            System.out.println(sum);
        } catch (Exception e) {
            System.out.println(e);
        } 
    }
}