public class SingleTon {
    private static final SingleTon SINGLETON = new SingleTon ();
    
    private SingleTon () {}
    
    public static SingleTon getInstace() {
        return SINGLETON;
    }
}
