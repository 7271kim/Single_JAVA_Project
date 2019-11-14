package algorithm.math;
// 소수를 영어로 prim이라고함 잘못봄..
public class PrimNumber {

    //소수 구하기 getDecimal(10000) 1~ 10000번까지의 소수인 수를 0으로 구현 ex) result[61] = 0 이므로 61은 소수 
    public int[] getPrims( int maxSize ) {
        int[] result = new int[maxSize+1];
        
        result[1] = 1;
        
        for (int index = 2; index <= maxSize; index++) {
            if(result[index] == 1 ) continue;
            for (int innerIndex = index+index; innerIndex <= maxSize; innerIndex += index) {
                result[innerIndex] = 1;
            }
        }
        
        return result;
    }
    
   //단순 해당 수가 소수인지 판별
   // num의 제곱근까지 계산하여 줄임. ( 25의 제곱근 5 )

    public boolean isPrime( int number ) {
        if( number == 1 || number == 0 ) return false;
        boolean result = true;
        int last       = ( int )Math.sqrt ( number );
        
        for (int index = 2; index <= last; index++) {
            if( number%index == 0 ) {
                result = false;
                break; 
            }
        }
        
        return result;
    }
}
