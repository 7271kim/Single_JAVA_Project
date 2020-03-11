package study;

import java.nio.channels.CompletionHandler;

public class ResultShare {
    private int result;
    //<결과 타입, 첨부타입>
    private static CompletionHandler<ResultShare, String> completionHandler = new CompletionHandler<ResultShare, String>() {
        
        //실패시 
        @Override
        public void failed(Throwable exc, String attachment) {
            System.out.println("실패하였습니다.");
        }
        
        //성공시 
        @Override
        public void completed(ResultShare result, String attachment) {
             System.out.println("지금까지의 저장된 합은 " + result.getResult() + "입니다.");
        }
    };
    
    public int sum( int number ) {
        return result+=number;
    }
    
    public void showSum() {
        System.out.println(" 지금 저장된 값은 : " + result + "입니다.");
    }
    
    public int getResult() {
        return result;
    }
    
    public static CompletionHandler<ResultShare, String> getCompletionHandler() {
        return completionHandler;
    }
}
