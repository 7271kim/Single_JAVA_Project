// TEST 클래스
public class Test {
    @Myanotation
    public void testAnnotation() {
        System.out.println("절취선 어노테이션을 테스트 합니다.");
    }
    
    @Myanotation(value = "*")
    public void testAnnotation2() {
        System.out.println("**를 구분자로 가지는 어노테이션을 추적합니다.");
    }
}
