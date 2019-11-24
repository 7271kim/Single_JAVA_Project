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
    
    // 여러 수의 최소공배수 ( Least Common Multiple ) 각 수의 최소 공배수의 최소 공배수이다.
    public int lcm( int[] input ) {
        int lcm    = input[0];
        for (int index = 1; index < input.length; index++) {
            int b = input[index];
            lcm =  ( lcm * b ) / gcd( b , lcm % b );
        }   
        return lcm;
    }
    
   //여러 수의 최대 공약수는 각수의 최대공약수의 최대공약수이다.
    public int gcd( int[] input ) {
        int gcd    = input[0];
        for (int index = 1; index < input.length; index++) {
            gcd = gcd(gcd, input[index]);
        }   
        return gcd;
    }
}
