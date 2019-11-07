import algorithm.DynamicProgramming.Combination;
import algorithm.DynamicProgramming.DynamicProgramming;

public class Main {
    public static void main(String args[]){
        //Solution temp = new Solution();
        //System.out.println(temp.solution(100));
        DynamicProgramming dp = new DynamicProgramming(5);
        
        System.out.println(dp.nomalFibonacci(5));
        // 1 , 1 ,  (1 + 1) = 2 , ( 1 + 1 ) + 1 = 3 , 3+2 =5 >> 5
        
        
        dp = new DynamicProgramming(5);
        System.out.println(dp.nomalFibonacciTopDown(5));
        
        dp = new DynamicProgramming(5);
        System.out.println(dp.nomalFibonacciBottomUp(5));
        
        Combination cb = new Combination(5, 3);
        System.out.println(cb.combinationTopDow(5, 3));
        
        cb = new Combination(5, 3);
        System.out.println(cb.combinationBottomUp(5, 3));
    }
}
