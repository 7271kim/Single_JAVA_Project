package study;

public class FirstThread implements Runnable {
    
    ResultShare resultShare; 
    
    //공유객체 외부에서 주입.
    public FirstThread ( ResultShare resultShare ) {
        this.resultShare = resultShare;
    }
    
    @Override
    public void run() {
        int result = 0;
        for (int index = 1; index <= 100; index++) {
            result += index;
        }
        System.out.println("1~100까지의 합은 " + result +"입니다.");
        resultShare.sum(result);
        
        // 끝난 후 콜백 실행
        resultShare.getCompletionHandler().completed(resultShare, null);
    }
}
