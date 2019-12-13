public class SingleTonMoreEffective {
    
    private SingleTonMoreEffective () {};
    private static class SigleTonInner {
        private static final SingleTonMoreEffective instance = new SingleTonMoreEffective();
        
    }
    
    public static SingleTonMoreEffective getInstace() {
        System.out.println("객체를 생성합니다.");
        return SigleTonInner.instance;
    }
}
