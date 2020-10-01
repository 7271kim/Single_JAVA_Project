package LottoGet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.seokjin.kim.library.JsoupCustom;


public class GetLotto {
    static int count = 0;
    
    public static void main(String[] args) throws Exception {
        //updateTotalLottoPage(); 1page부터 93페이지까지 모든 로또 데이터 취합
        updateTotalLottoPage();
    }
    
    public static int randomRange(int n1, int n2) {
        return (int) (Math.random() * (n2 - n1 + 1)) + n1;
    }
    
    public static void updateTotalLottoPage () throws Exception {
        count = 0;
        ExecutorService executorServiceWithCached = Executors.newFixedThreadPool(30);
        LottoDB lottoDB = new LottoDB();
        int totalPage = 93;
        // 총 10페이지 업데이트
        for(int index = 1; index <= totalPage; index++) {
            TaskLotto<Integer> task = new TaskLotto<Integer>(index, lottoDB );
            Future<Boolean> returnBoolean = executorServiceWithCached.submit(task);
            executorServiceWithCached.execute(()->{
                try {
                    if( returnBoolean.get() ) {
                        count++;
                    }
                } catch ( Exception e ) {
                }
            });
            
        }
        boolean isLoop = true;
        while( isLoop ) {
            if( count == totalPage ) {
                isLoop = false;
                List<LottoModel> result = lottoDB.getLottoNumList("SELECT NUMBER FROM lotto_data", new TreeSet<String>(Arrays.asList("Number")));
                Map<String, Integer> totlaNumber = new HashMap<String, Integer>();
                for( int index = 1; index <= 45; index++ ) {
                    totlaNumber.put(String.valueOf(index), 0);
                }
                
                for( LottoModel item : result ) {
                    String[] numbers = item.getNumber().split(" ");
                    for( String number : numbers) {
                        int temp = totlaNumber.get(number);
                        totlaNumber.put(number, temp+1);
                    }
                }
                
                for( int index = 1; index <= 45; index++ ) {
                    TaskLotto2 task2 = new TaskLotto2(String.valueOf( index ), lottoDB);
                    executorServiceWithCached.submit(task2);
                }
            }
            Thread.sleep(1);
        }
        executorServiceWithCached.shutdown();
    }
}

class TaskLotto <T> implements Callable<Boolean> {
    private T input;
    private LottoDB lottoDB;

    public TaskLotto(T input, LottoDB lottoDB ) {
        this.input = input;
        this.lottoDB = lottoDB;
    }


    @Override
    public Boolean call() throws Exception {
        String parsingUrl = "https://www.dhlottery.co.kr/gameResult.do?method=statByColor&currentPage="+input;
        Document doc= JsoupCustom.getGetDocumentFromURL(parsingUrl);
        if(doc.select("table.tbl_data.tbl_data_col tbody")!= null){
            Element table  = doc.select("table.tbl_data.tbl_data_col tbody").get(0);
            Elements tr   = table.select("tr");
            for ( Element item : tr ) {
                Elements td = item.select("td");
                String date = td.get(0).text();
                String number = td.get(2).text();
                
                if(!lottoDB.getQurry("SELECT * FROM lotto_data WHERE DATE='"+date+"'") ) {
                    lottoDB.insertLotto(date, number);
                };
            }
        }
        
        return true;
    }
}

class TaskLotto2 implements Callable<Boolean> {
    private String input;
    private LottoDB lottoDB;

    public TaskLotto2(String input, LottoDB lottoDB ) {
        this.input = input;
        this.lottoDB = lottoDB;
    }

    @Override
    public Boolean call() throws Exception {
        if( !lottoDB.getQurry("SELECT * FROM lotto_number WHERE NUMBER='"+input+"'")) {
            lottoDB.insertLottoNum(input);
        } else {
            int getOne = lottoDB.getOne("SELECT SUM FROM lotto_number WHERE NUMBER="+input, "SUM" );
            lottoDB.updateLottoNum( input, String.valueOf( getOne+1 ));
        }
        return true;
    }
}

