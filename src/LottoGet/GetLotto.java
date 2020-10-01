package LottoGet;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.seokjin.kim.library.JsoupCustom;
import com.seokjin.kim.library.MathAll;


public class GetLotto {
    static int count = 0;
    static final int TOTAL_PAGE = 93;
    static final int LOTTO_RANGE = 45;
    
    public static void main(String[] args) throws Exception {
        //updateTotalLottoPage(); 1page부터 93페이지까지 모든 로또 데이터 취합
        updateTotalLottoPage();
    }
    
    public static int randomRange(int n1, int n2) {
        return (int) (Math.random() * (n2 - n1 + 1)) + n1;
    }
    
    public static void updateTotalLottoPage () throws Exception {
        count = 0;
        ExecutorService executorServiceWithCached = Executors.newFixedThreadPool(40);
        LottoDB lottoDB = new LottoDB();
        // 총 10페이지 업데이트
        for(int index = 1; index <= TOTAL_PAGE; index++) {
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
            if( count == TOTAL_PAGE ) {
                isLoop = false;
                List<LottoModel> result = lottoDB.getLottoNumList("SELECT NUMBER FROM lotto_data", new TreeSet<String>(Arrays.asList("Number")));
                Map<String, Integer> totlaNumber = new HashMap<String, Integer>();
                Map<String, Integer> totlaTwoNumber = new HashMap<String, Integer>();
                Set<String> lottoCombi = MathAll.getCombination(45, 2);
                for( int index = 1; index <= 45; index++ ) {
                    totlaNumber.put(String.valueOf(index), 0);
                }
                for( String numberTwos : lottoCombi ) {
                    totlaTwoNumber.put(numberTwos, 0);
                }
                
                for( LottoModel item : result ) {
                    String[] numbers = item.getNumber().split(" ");
                    Set<String> numbersCombi = MathAll.getCombination(numbers, 2);
                    for( String number : numbersCombi) {
                        int temp = totlaTwoNumber.get(number);
                        totlaTwoNumber.put(number, temp+1);
                    }
                    for( String number : numbers) {
                        int temp = totlaNumber.get(number);
                        totlaNumber.put(number, temp+1);
                    }
                }
                count = 0;
                for( int index = 1; index <= 45; index++ ) {
                    String number = String.valueOf( index );
                    TaskLotto2 task2 = new TaskLotto2(number, String.valueOf(totlaNumber.get(number)), lottoDB );
                    Future<Boolean> returnBoolean = executorServiceWithCached.submit(task2);
                    executorServiceWithCached.execute(()->{
                        try {
                            if( returnBoolean.get() ) {
                                count++;
                            }
                        } catch ( Exception e ) {
                        }
                    });
                }
                
                for( String numberset : totlaTwoNumber.keySet() ) {
                    int value = totlaTwoNumber.get(numberset);
                    String[] temp = numberset.split(" ");
                    String firstNum = temp[0];
                    String seconNum = temp[1];
                    TaskLotto3 task3 = new TaskLotto3(firstNum, seconNum, String.valueOf(value), lottoDB );
                    executorServiceWithCached.submit(task3);
                }
            }
            Thread.sleep(1);
        }
        
        isLoop = true;
        while( isLoop ) {
            if( count == LOTTO_RANGE ) {
                isLoop = false;
                System.out.println("모든 작업이 끝났습니다.");
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
    private String number;
    private String value;
    private LottoDB lottoDB;

    public TaskLotto2(String number, String value, LottoDB lottoDB ) {
        this.number = number;
        this.value = value;
        this.lottoDB = lottoDB;
    }

    @Override
    public Boolean call() throws Exception {
        if( !lottoDB.getQurry("SELECT * FROM lotto_number WHERE NUMBER='"+number+"'")) {
            lottoDB.insertLottoNum( number, value );
        } else {
            lottoDB.updateLottoNum( number, value );
        }
        return true;
    }
}

class TaskLotto3 implements Callable<Boolean> {
    private String firstNum;
    private String secondNum;
    private String value;
    private LottoDB lottoDB;

    public TaskLotto3(String firstNum, String secondNum, String value, LottoDB lottoDB ) {
        this.firstNum = firstNum;
        this.secondNum = secondNum;
        this.value = value;
        this.lottoDB = lottoDB;
    }

    @Override
    public Boolean call() throws Exception {
        if( !lottoDB.getQurry("SELECT * FROM lotto_number_total WHERE NUMBER='"+firstNum+"' AND NUMBER_TWO = '"+secondNum+"'")) {
            lottoDB.insertLottoTwoNum( firstNum, secondNum, value );
        } else {
            lottoDB.updateLottoTwoNum( firstNum, secondNum, value );
        }
        return true;
    }
}
