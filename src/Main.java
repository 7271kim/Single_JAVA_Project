import java.util.Scanner;
import java.util.Stack;

/*
 * https://www.acmicpc.net/problem/17298
 * 로직에 대한 고민
 */

public class Main {
    public static void main(String args[]){
        //BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Scanner sc = new Scanner(System.in);
        try {
            int total       = sc.nextInt();
            int[] A     = new int[ 1000001]; // 구하는 A[i] 조합 인덱스가 inputNum
            int[] inputNum = new int[total]; // 들어오는 배열
            int min     = 0; // 업데이트해야할 최소값
            Stack<Integer> stack = new Stack<Integer>(); // 업데이트 해야할 것들
            
            for (int index = 0; index < total; index++) {
                int number = sc.nextInt();
                inputNum[index] = number;
                
                if(A[number] == 0) A[number] =-1;
                if( number > min && stack.size() > 0) {
                    for (int stackIndex = stack.size()-1; stackIndex >= 0; stackIndex--) {
                        int stackNumb = stack.get(stackIndex);
                        if( stackNumb < number ) {
                            A[stack.get(stackIndex)] = number;
                            stack.pop();
                            if(stack.size() == 0) break;
                        }
                    }
                }
                stack.add(number);
                min = number;
            }
            
            for (int index = 0; index < inputNum.length; index++) {
                System.out.print(A[inputNum[index]] + " ");
            }
        } catch (Exception e) {
            System.out.println(e);
        } 
    }
}
