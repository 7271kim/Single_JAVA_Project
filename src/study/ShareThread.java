package study;

public class ShareThread {
    private int value = 0;
    
    public  void setValue( int value ) {
        synchronized (this) {
            this.value = value;
            try {
                Thread.sleep(2000);
            } catch (Exception e) {
            }
            System.out.println(Thread.currentThread().getName() + "의 Value 값은 " + this.value +"입니다.");
        }
    }
    
    public int getValue() {
        return value;
    }
}
