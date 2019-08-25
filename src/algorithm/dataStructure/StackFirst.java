package algorithm.dataStructure;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;

/*
 * https://www.acmicpc.net/problem/10828
 * 스택 - JAVA에서 제공해주는 것이 있음
 */

public class StackFirst {
    public static void main(String args[]){
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Scanner sc = new Scanner(System.in);
        try {
            int number = Integer.parseInt(br.readLine());
            Stack<Integer> A = new Stack<Integer>();
            for(int T = sc.nextInt(); T > 0; T--) {
                String s = sc.next();
                if(s.equals("push"))
                    A.push(sc.nextInt());
                if(s.equals("pop"))
                    System.out.println(A.isEmpty()?-1:A.pop());
                if(s.equals("size"))
                    System.out.println(A.size());
                if(s.equals("empty"))
                    System.out.println(A.isEmpty()?1:0);
                if(s.equals("top"))
                    System.out.println(A.isEmpty()?-1:A.peek());
            }
            
            //내꺼 
            /* for (int index = 0; index < number; index++) {
                String order = br.readLine();
                if( order.indexOf("push") > -1 ) {
                    int inputNumber = Integer.parseInt(order.split(" ")[1]);
                    stack.push(inputNumber);
                } else if( order.indexOf("pop") > -1 ) {
                    stack.pop();
                } else if (order.indexOf("size") > -1) {
                    stack.size();
                } else if (order.indexOf("empty") > -1) {
                    stack.empty();
                } else if (order.indexOf("top") > -1) {
                    stack.top();
                }
            }*/
        } catch (Exception e) {
            System.out.println(e);
        } 
    }
}

// 이건 내꺼 
class StackMy{
    ArrayList<Integer> stack;
    
    public StackMy() {
        stack = new ArrayList<Integer>();
    }
    public void push( int input ) {
        stack.add(input);
    }
    public void pop() {
        if(stack.isEmpty()) {
            System.out.println(-1);
        } else {
            System.out.println(stack.get(stack.size()-1));
            stack.remove(stack.size()-1);
        }
        
    }
    public void size() {
        System.out.println(stack.size());
    }
    public void empty() {
        if(stack.isEmpty()) {
            System.out.println(1);
        } else {
            System.out.println(0);
        }
    }
    public void top() {
        if(stack.isEmpty()) {
            System.out.println(-1);
        } else {
            System.out.println(stack.get(stack.size()-1));
        }
    }
}