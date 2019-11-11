package algorithm.math;

public class CeilInt {
    public int intCeil( int dividend , int divisor ) {
        //double castDividen = (double) dividend;
        //double castDivisor = (double) divisor;
        //double result      = Math.ceil(castDividen/castDivisor);
        double reminder      = dividend / (double)divisor;
        int result           = (int)Math.ceil(reminder);
        return result;
    }
}
