public class SingleTonBad {
    private static SingleTonBad SINGLETONBAD;
    
    private SingleTonBad() {}
    
    public static SingleTonBad getInstace() {
        if( SINGLETONBAD == null ) {
            SINGLETONBAD = new SingleTonBad();
        }
        return SINGLETONBAD;
    }
}
