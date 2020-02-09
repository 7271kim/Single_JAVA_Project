// TEST 클래스
public class Test {
    //클래스 필드
    public static int ONE       = 1;
    public static String TWO    = "TWO";
    public static String FOUR;
    
    // 상수
    public static final int FIVE    = 10;

    // 정적 블럭
    static {
        FOUR = "안녕하세요";
    }
    
    //인스턴스 필드
    private int three;
    
    public Test( int three ) {
        this.three = three;
    }
    
    // 클래스 메서드
    public static int sum( int a , int b ) {
        return a + b;
    }
    
    //인스턴스 메서드
    public int multiply( int a, int b ) {
        return a*b;
    }

    public int getThree() {
        return three;
    }
}
