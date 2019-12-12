import java.util.EnumSet;
import java.util.Objects;

public abstract class Pizza {
    
    // 피자에서 선택 사용가능한 모든 토핑을 가독성을 위해  열거 상수로 준비해 놓는 공간 
    public enum Topping{
        HAM("햄"), CHEESE("치즈"), DOUBLE_CHEESE("더블치즈"), ONION("양파"), SASAGE("쏘세지");
        private String koreaName;
        private Topping(String koreaName) {
            this.koreaName = koreaName;
        }
        public String getKoreaName() {
            return this.koreaName;
        }
    }
    
    // 제네릭을 이용하여 Builder안에서 사용할 T를 자기자신의 하위 타입의 빌더 구현하는 객체를 사용하겠다는 재귀적 타입의 매개변수를 의미한다.
    abstract static class Builder< T extends Builder<T> >{
        // 빌더 안에서는 토핑을 만든다.
        
        // 비어있는 EnumSet하나를 만든다.
        public EnumSet<Topping> toppings = EnumSet.noneOf(Topping.class);
        
        //토핑을 추가하기 위한 매서드
        public T addTopping( Topping topping ) {
            // null인경우 null을 아닐시 그대로를 반환합니다.
            toppings.add(Objects.requireNonNull(topping));
            return self();
        }
        
        //self()를 각각의 하위타입에서 구현함으로서 구현 객체를 리턴하도록 함으로서 매서드 체이닝이 가능하도록 하는 작업 
        protected abstract T self();
        
        // Convariant return type 즉 메서드를 오버라이딩 할 때 좀 더 좁은 타입으로 교체할 수 있게 하는 작업.
        abstract Pizza build();
    }
    
    //해당 Pizza클래스의 생성자
    
    final EnumSet<Topping> toppings;
    Pizza( Builder<?> builder){
        // 해당 클래스의 토핑은 각각의 빌더가 가지고 있는 토핑으로 세팅
        toppings = builder.toppings;
    }
}
