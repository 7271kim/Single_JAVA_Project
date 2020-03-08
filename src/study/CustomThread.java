package study;

public class CustomThread extends Thread {
    
    @Override
    public void run() {
        int sum = 0;
        for (int index = 0; index < 10; index++) {
            sum += index;
            System.out.println(sum);
        }
        System.out.println( Thread.currentThread().getName() + "최종 합 : " + sum);

    }
}
