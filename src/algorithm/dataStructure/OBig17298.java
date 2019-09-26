package algorithm.dataStructure;
import java.util.Scanner;
import java.util.Stack;

/*
 * https://www.acmicpc.net/problem/17298
 * 로직에 대한 고민
 */

public class OBig17298 {
    public static void main(String args[]){
        //BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Scanner sc = new Scanner(System.in);
        try {
            int total       = sc.nextInt();
            int[] inputNum = new int[total]; 
            Stack<Integer> stack = new Stack<Integer>(); 
            StringBuilder result  = new StringBuilder();
            //O(N)
            for (int index = 0; index < total; index++) {
                int number = sc.nextInt();
                inputNum[index] = number;
            }
            //O(N)
            for ( int index = inputNum.length-1; index >= 0; index-- ) {
                int thisIndex = inputNum[index];
                //스택 맨 꼭대기에 있는 숫자가 자기보다 작거나 같으면 지우기
                while( !stack.isEmpty() && thisIndex >= stack.peek() ) {
                    stack.pop();
                }
                //꼭대기에 있는 숫자를 채우기
                if( stack.isEmpty() ) {
                    inputNum[index] = -1;
                } else {
                    inputNum[index] = stack.peek();
                }
                //다시 넣기
                stack.add(thisIndex);
            }
            for (Integer integer : inputNum) {
                result.append(integer+ " ");
            }
            System.out.println(result);
            
        } catch (Exception e) {
            System.out.println(e);
        } 
    }
}
