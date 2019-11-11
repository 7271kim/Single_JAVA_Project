package algorithm.math;

public class Decimal {
    public static void main(String args[]){
        int[] temp = getDecimal(10000);
        boolean isDecimal = temp[10] == 0;
    }
    
   //소수 구하기 getDecimal(10000) 1~ 10000번까지의 소수인 수를 0으로 구현 ex) result[61] = 0 이므로 61은 소수 
    public static int[] getDecimal( int maxSize ) {
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
}
