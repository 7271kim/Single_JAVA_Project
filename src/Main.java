import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/*
 * https://www.acmicpc.net/problem/9012
 * 스택
 */

public class Main {
    public static void main(String args[]){
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //Scanner sc = new Scanner(System.in);
        try {
            int number = Integer.parseInt(br.readLine());
            for (int index = 0; index < number; index++) {
                Stack<Integer> stack = new Stack<Integer>();
                String result        = "YES";
                //int line = Integer.parseInt(br.readLine());
                String[] next = br.readLine().split("");
                for (int inner = 0; inner < next.length; inner++) {
                    if( next[inner].indexOf("(") > -1 ) {
                        stack.push(1);
                    } else {
                        if( stack.isEmpty() ) {
                            result        = "NO";
                            break;
                        } else {
                            stack.pop();
                        }
                    }
                }
                if(!stack.isEmpty()) result = "NO";
                System.out.println(result);
            }
            
        } catch (Exception e) {
            System.out.println(e);
        } 
    }
}