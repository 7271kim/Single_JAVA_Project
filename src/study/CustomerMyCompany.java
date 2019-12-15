package study;

public enum CustomerMyCompany implements Customers {
    INSTANCE;
    
    private static String name;
    private static String address;
    
    public static CustomerMyCompany getIncetance() {
        return INSTANCE;
    }
    
    @Override
    public String getName() {
        return this.name;
    }
    
    @Override
    public void setName( String name ) {
        this.name = name;
    }
}
