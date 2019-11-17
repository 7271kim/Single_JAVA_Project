package algorithm.math;

public class TenToBinaryOneCount {
    public int countOne( int number ) {
        int count = 0;
        // 101101 & (101101 - 1 ) = 101100 >> 마지막 1이 없어짐 
        while( number !=0 )  {
            number &= number - 1;
            count++;
        }
        return count;
    }
    // 간단한 것 존재 
    public int countOne2( int number ) {
        return Integer.bitCount(number);
    }
}
