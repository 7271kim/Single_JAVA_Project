public enum SingleTonEnum {
    INSTANCE;
    
    private static String text;
    
    public String getText() {
        return this.text;
    }
    public static SingleTonEnum getInstance() {
        text = "안녕하세요";
        return INSTANCE;
    }
}
