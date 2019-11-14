package algorithm.math;

public class GcmLcm {
    //유클리드 호제법
    //최대공약수 ( Greatest Common Divisor )
    public int gcd( int a, int b ) {
        if( b == 0 ) return a;
        return gcd( b , a % b );
    }
    
   //최소공배수 ( Least Common Multiple )
    public int lcm( int a, int b ) {
        return ( a * b ) / gcd( b , a % b );
    }
}
