public class TestClass {
    // 로컬 클래스
    private int temp = 1000;
    
    public void testMethod() {
        class NestedClass {
            int temp;
            NestedClass(int temp) {
                this.temp = temp;
            }
        }
        
        NestedClass nestedClass = new NestedClass(100);
        System.out.println("로컬 클래스 테스트 : " + ( TestClass.this.temp + nestedClass.temp ) );
    }
    
    // 중첩 인터페이스
    interface NestedInterface {
        
    }
}
