public interface MakePizza {
    public static final int CHECK_DEGREE = 400;
    String BASE = "핵 맛있는 피자";
    
    public abstract void makePizza();
    void makeBestPizza();
    
    public default void isBestPizza( String name ) {
        System.out.println( name + "이 가장 맛있는 피자입니다.");
    }
    
    default void isWorstPizza( String name ) {
        System.out.println( name + "이 가장 맛없는 피자입니다.");
    }
    
    static void sayHack() {
        System.out.println(BASE);
    }
}
