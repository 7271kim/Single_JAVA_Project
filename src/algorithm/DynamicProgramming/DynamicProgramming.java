package algorithm.DynamicProgramming;

// 동적 계획법이란 복잡한 문제를 간단한 여러 개의 문제로 나누어 푸는 방법 
// 일반적으로 주어진 문제를 풀기 위해서, 문제를 여러 개의 하위 문제(subproblem)로 나누어 푼 다음, 그것을 결합하여 최종적인 목적에 도달하는 것이다.
// EX ) 피보나치의 수 >> F1 = 1 , F2 = 1 , Fn = Fn-1 + Fn-2;
// EX ) 알고리즘에서 응용  1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89 .....

public class DynamicProgramming {
    
    // 단순 계산.
    public int nomalFibonacci( int n )  {
        if( n == 1 || n == 2 ) {
            return 1;
        } else {
            int before = nomalFibonacci(n-1);
            int second = nomalFibonacci(n-2);
            return before + second;
        }
    }
    // 위 알고리즘의 이슈는 f(7) = f(6) + f(5) >> f(6) = f(5) + f(4) >> f(5)반복 ... 무수한 반복을 해버린다.
    
    
    //중간에 중복 호출이 발생하기 때문에 Memoization 기법을 사용해줘야 합니다.
    // 1. Top - Down방식 >> 큰 문제(Main Problem)에서 작은 부분 문제(Sub Problem)를 재귀적으로 호출하여 리턴된 값으로 문제를 해결
    // 장 : 재귀함수를 통해서 구현되기 때문에 함수 호출에 대한 오버헤드가 발생
    // 단 : memoization을 잘 활용하면 Bottom-Up보다 훠어어얼씬 빠름
    
    // 큰 부분부터 답을 저장시켜간다.
    // memorization[1] 은 nomalFibonacciTopDown(1)의 결과이다.
    int[] memorization;
    
    public DynamicProgramming( int size) {
      memorization = new int[size+1];
    }
  
    public int nomalFibonacciTopDown( int n )  {
        // 기존 값이 있다면 리턴한다.
        if( memorization[n] != 0 ) return memorization[n];
        
        if( n == 1 || n == 2 ) {
            return 1;
        } else {
            int before = nomalFibonacci(n-1);
            int second = nomalFibonacci(n-2);
            
            // 기억하고 기억한걸 리턴한다.
            memorization[n] = before + second;
            
            return memorization[n];
        }
    }
    // 2. Bottom up 방식
    // 작은 부분부터 채워간다.
    public int nomalFibonacciBottomUp( int n )  {
        // 기존 값이 있다면 리턴한다.
        if( memorization[n] != 0 ) return memorization[n];
        
        // 처음 2개를 세팅해 놓는다.
        memorization[1] = 1;
        memorization[2] = 1;
        
        // 점차 나아가면서 답을 세팅해 놓는다.
        for( int index = 3; index <= n; index++ )
            memorization[index] = memorization[index-2] + memorization[index-1];
        
        return memorization[n];
    }
}
