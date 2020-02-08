public class Test {
    public static int ONE       = 1;
    public static String TWO    = "TWO";
    
    private int three;
    
    public Test( int three ) {
        this.three = three;
    }
    
    public static int sum( int a , int b ) {
        return a + b;
    }
    
    
    public int multiply( int a, int b ) {
        return a*b;
    }

    public int getThree() {
        return three;
    }
}
