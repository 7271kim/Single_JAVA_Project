package ok.beak;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

/*
 * https://www.acmicpc.net/problem/1874
 * 스택 - JAVA에서 제공해주는 것이 있음
 * 시작과 종료조건에 대한 고민
 */

public class Beak1874Star {
    public static void main(String args[]){
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //Scanner sc = new Scanner(System.in);
        try {
            int number = Integer.parseInt(br.readLine());
            StringBuilder result = new StringBuilder();
            Stack<Integer> stack = new Stack<Integer>();
            int lastNumber       = 1;
            for (int index = 0; index < number; index++) {
               int line = Integer.parseInt(br.readLine());
               while(lastNumber <= line) {
                   result.append("+\n");
                   stack.add(lastNumber++);
               }
               if( stack.isEmpty() ) {
                   result = new StringBuilder("NO");
                   break;
               } else {
                   while(stack.peek() >= line) {
                       result.append("-\n");
                       stack.pop();
                       if(stack.isEmpty()) break;
                   }
               }
            }
            System.out.println(result);
        } catch (Exception e) {
            System.out.println(e);
        } 
    }
}
