public class TestClass {
    
    private OnclikListener listener;
    
    public void setOnclickLister( OnclikListener listener ) {
        this.listener = listener;
    }
    
    public void touch() {
        listener.onClick();
    }
    
    // 중첩 인터페이스
    interface OnclikListener {
        void onClick();
    }
}
