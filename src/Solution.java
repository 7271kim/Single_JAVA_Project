import java.util.Arrays;
import java.util.Stack;

/*
이전 값이 다음 값에 영향을 미칠 때 Stack이용이기 때문에 Stack으로 풀이 다시 
F int solution ( 들어오는 숫자배열 arr )
    이전 최종값이 무엇인지 확인하기 위한 stack배열 stackNumber 
    최종 반환을 위한 result배열 크기는 우선 arr과 같게 한다.  
    result배열의 인덱스를 위한 변수 next = 0;
    
    FOR index는 0부터 arr.length-1까지 1씩증가
        arr지금 위치의 값을 위한 변수 thisNum = arr[index]; 
        
        IF stackNumber가 비었거나 thisNum가 stackNumber의 맨 위의 값과 같지 않다면  THEN
            stackNumber에 thisNum 추가 
            result에 값 할당 result[next++] = thisNum
    
    마지막 빈 값을 제거하기 위한 배열 temp은 next -1 크기로 설정한다.
  temp는 reuslst를 next까지 copy한다. temp = Arrays.copyOf(result, next)
    
    temp 반환
*/
public class Solution {
    public int[] solution(int []arr) {
        Stack<Integer> stackNumber = new Stack<Integer>();
        int[] result    = new int[arr.length];
        int next        = 0;
        
        for (int index = 0; index < arr.length; index++) {
            int thisNum = arr[index];
            if( stackNumber.isEmpty() || thisNum != stackNumber.peek() ) {
                stackNumber.add(thisNum);
                result[next++] = thisNum;
            }
        }
        int[] temp = new int[next];
        temp = Arrays.copyOf(result, next);
        
        return temp;
    }
}