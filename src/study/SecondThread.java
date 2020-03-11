package study;

public class SecondThread implements Runnable {
    
    ResultShare resultShare; 
    
    //공유객체 외부에서 주입.
    public SecondThread ( ResultShare resultShare ) {
        this.resultShare = resultShare;
    }
    
    @Override
    public void run() {
        int result = 0;
        for (int index = 101; index <= 200; index++) {
            result += index;
        }
        System.out.println("101~200까지의 합은 " + result +"입니다.");
        resultShare.sum(result);
        // 끝난 후 콜백 실행
        resultShare.getCompletionHandler().completed(resultShare, null);
    }

}
