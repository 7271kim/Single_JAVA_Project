package study;

public class Order {
    private Customers customer;
    
    public Order() {}
    
    public Order( Customers customer ) {
        this.customer = customer;
        customer.setName("고객님");
    }
    
}
