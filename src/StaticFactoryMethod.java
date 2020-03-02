public abstract class StaticFactoryMethod {
    
    abstract void getName();
    
    static public StaticFactoryMethod getNewInstance( String code ) {
        StaticFactoryMethod staticFactoryMethod = null;
        if( code.indexOf("2") == 1 ) {
            staticFactoryMethod = new Point();
        } else {
            staticFactoryMethod = new Coupon();
        }
        return staticFactoryMethod;
    }
}
class Coupon extends StaticFactoryMethod {
    public void getName() {
        System.out.println("쿠폰을 발행합니다.");
    }
}

class Point extends StaticFactoryMethod {
    public void getName() {
        System.out.println("포인트 1000점을 적립합니다.");
    }
}
