public class TestTemp1 implements AutoCloseable {
    public void doSometing() throws FirstErr {
        System.out.println("작업 진행중1");
        throw new FirstErr();
    }
    @Override
    public void close() throws SecondErr {
        System.out.println("자원을 회수합니다.1");
        throw new SecondErr();
    }

}
