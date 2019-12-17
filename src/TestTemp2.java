
public class TestTemp2 implements AutoCloseable {
    
    public void doSometing() throws FirstErr{
        System.out.println("작업 진행중2");
        throw new FirstErr();
    }
    @Override
    public void close() throws SecondErr {
        System.out.println("자원을 회수합니다.2");
        throw new SecondErr();
    }

}
