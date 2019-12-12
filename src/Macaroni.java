import java.util.EnumSet;
import java.util.Objects;

public class Macaroni extends Pizza {
    // 마카로니 피자에는 사이즈가 있다.
    
    public enum Size {
        SMALL, MEDEIUM, LARGE
    }
    
    // Pizza의 빌더를 상속하는 빌더
    public static class Builder extends Pizza.Builder<Builder>{
        private final Size size;
        private boolean sauseInside = false;
        
        //해당 빌더의 필수값을 세팅하는 영역
        public Builder( Size size ) {
            this.size = Objects.requireNonNull(size);
        }
        
        public Builder sauceInput() {
            sauseInside = true;
            return this;
        }
        
        @Override
        protected Builder self() {
            // 해당 상속받는(Macaroni.Builder) 객체를 리턴하여 addTopping하더라도 Macaroni.Builder를 바라보도록 하는 체이닝기법
            return this;
        }

        @Override
        Macaroni build() {
            // 빌드의 핵심. Pizza를 상속하는 해당 클래스 인스턴스를 새로 생성하여 리턴한다.
            return new Macaroni(this);
        }
    }
    
    
    // new Builder(  ) 를 통해 객체를 생성할 것임으로 빌더에서 생성된 객체의 size를 받아온다.
    private final Size size;
    private final boolean useSaurce;
    private final EnumSet<Topping> toppins;
    
    private Macaroni(Builder builder) {
        super(builder); // 토핑을 세팅
        size = builder.size;
        useSaurce = builder.sauseInside;
        toppins = builder.toppings;
    }
    public void print() {
        System.out.println("지금의 피자의 요청 크기는 : " + this.size +"입니다.");
        System.out.print("첨가된 토핑은 ");
        for (Topping topping : toppins) {
            System.out.print(topping.getKoreaName()+" ");
        }
        System.out.print("입니다.");
        System.out.println();
        System.out.println("또한 소스는 " + ( useSaurce ? "사용됩니다." : "사용하지 않습니다"));
    }
}
