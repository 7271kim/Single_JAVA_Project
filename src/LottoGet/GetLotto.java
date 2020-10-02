package LottoGet;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
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
    static final LottoDB lottoDB = new LottoDB();
    static final int GET_TOTAL = 7;
    static final Set<String> notPassible = new TreeSet<>();
    static final int RANGE1 = 1;
    static final int RANGE10 = 0;
    static final int RANGE20 = 0;
    static final int RANGE30 = 0;
    static final int RANGE40 = 0;
    // https://www.dhlottery.co.kr/gameResult.do?method=noViewNumber 15주간 안나온 번호
    static final int[] choiceNum = {3,15,17,29,36};
    static final int[] choiceNotNum = {37,40,45};
    static final int[] choiceNotNumArray = new int[46];
    static final int[] isCheckWhenTime = {29,30,36}; // 몇회째에 해당 번호가 나왔는지 찾기
    
    public static void main(String[] args) throws Exception {
        updateTotalLottoPage(); //1page부터 93페이지까지 모든 로또 데이터 취합
        //Set<String> lottoNumbers = getLottoNumber();
        //checkDBNumber();
    }
    
    /**
     * DB에서 isCheckWhenTime 나온 번호 추출
     */
    private static void checkDBNumber() {
        List<LottoModel> modelList = lottoDB.getLottoNumList("SELECT * FROM lotto_data", new TreeSet<String>(Arrays.asList("Number","Date")));
        for( LottoModel item : modelList ) {
            String value = item.getNumber();
            String[] valueList = value.split(" ");
            int count = 0;
            for( String numberDB : valueList ) {
                for( int chNum : isCheckWhenTime ) {
                    if( numberDB.equals(String.valueOf(chNum)) ) {
                        count++;
                    }
                }
            }
            if( count == isCheckWhenTime.length ) {
                System.out.println(item.getDate());
                System.out.println(value);
                System.out.println();
            }
        }
    }

    private static Set<String> getLottoNumber() {
        Set<String> lottoNumbers = new HashSet<>();
        double standard = getStandard();
        double mean = getMean();
        
        for( int noNum : choiceNotNum ) {
            choiceNotNumArray[noNum] = 1;
        }
        
        while ( true ) {
            if( lottoNumbers.size() == GET_TOTAL ) break;
            
            Set<Integer> sixNum = new TreeSet<>();
            for( int number : choiceNum ) {
                sixNum.add(number);
                
            }
            int temp1 = RANGE1;
            int temp10 = RANGE10;
            int temp20 = RANGE20;
            int temp30 = RANGE30;
            int temp40 = RANGE40;
            
            if( temp1 + temp10 + temp20 + temp30 + temp40 + choiceNum.length > 6 ) {
                System.out.println(" 총 뽑아야 하는 개수가 6개가 넘습니다. ");
                break;
            }
            
            while( true ) {
                int number = 0;
                // range별 번호 뽑기
                
                if( temp1 != 0 ) {
                    number  = randomRange(1,10);
                    if( !sixNum.contains(number) && choiceNotNumArray[number] != 1 ) {
                        sixNum.add(number);
                        temp1--;
                    }
                } else if( temp10 != 0 ) {
                    number  = randomRange(11,20);
                    if( !sixNum.contains(number) && choiceNotNumArray[number] != 1  ) {
                        sixNum.add(number);
                        temp10--;
                    }
                }else if( temp20 != 0 ) {
                    number  = randomRange(21,30);
                    if( !sixNum.contains(number) && choiceNotNumArray[number] != 1  ) {
                        sixNum.add(number);
                        temp20--;
                    }
                }else if( temp30 != 0 ) {
                    number  = randomRange(33,40);
                    if( !sixNum.contains(number) && choiceNotNumArray[number] != 1  ) {
                        sixNum.add(number);
                        temp30--;
                    }
                }else if( temp40 != 0 ) {
                    number  = randomRange(41,45);
                    if( !sixNum.contains(number) && choiceNotNumArray[number] != 1  ) {
                        sixNum.add(number);
                        temp40--;
                    }
                } else {
                    number = randomRange(1,45);
                    if( !sixNum.contains(number) && choiceNotNumArray[number] != 1  ) {
                        sixNum.add(number);
                    }
                }
                
                if( sixNum.size() == 6 ) {
                    break;
                }
            }
            boolean isAllTwoDeviation = isAllTwoDeviation( sixNum, standard, mean );
            if( isAllTwoDeviation ) {
                String temp = "";
                for( Integer number : sixNum ) {
                    temp += " " + number;
                }
                temp = temp.trim();
                lottoNumbers.add(temp);
            }
        }
        
        for( String text : lottoNumbers ) {
            System.out.println(text);
        }
        
        return lottoNumbers;
    }
    
    /**
     *  뽑은 번호들의 두 번호 사이의 value가 2표준편차 이내일때만 넣기  
     * @param mean 
     */
    private static boolean isAllTwoDeviation(Set<Integer> sixNum, double standard, double mean) {
        String[] temp = new String[sixNum.size()];
        int count = 0;
        for( Integer number : sixNum ) {
            temp[count++] = String.valueOf(number);
        }
        
        Set<String> combi =  MathAll.getCombination(temp, 2);
        boolean isPass = true;
        
        for( String numberText : combi ) {
            if( notPassible.contains(numberText) ) {
                isPass = false;
                break;
            }
            String[] numbers = numberText.split(" ");
            String numberOne = numbers[0];
            String numberTwo = numbers[1];
            List<LottoModel> modelList = lottoDB.getLottoNumList("SELECT * FROM lotto_number_total WHERE NUMBER = '"+numberOne+"' AND NUMBER_TWO = '"+numberTwo+"'", new TreeSet<String>(Arrays.asList("Number","Number_two","Value")));
            if( modelList.size() > 0 ) {
                LottoModel dbItem = modelList.get(0);
                int dbValue =  Integer.parseInt( dbItem.getValue() );
                if( dbValue < mean - standard*2 || dbValue > mean + standard*2 ) {
                    notPassible.add(numberText);
                    isPass = false;
                    break;
                }
            }
        }
        
        return isPass;
    }

    private static double getStandard() {
        List<LottoModel> modelList = lottoDB.getLottoNumList("SELECT * FROM lotto_number_total", new TreeSet<String>(Arrays.asList("Number","Number_two","Value")));
        int totalSize = modelList.size();
        double[] totalValue = new double[totalSize];
        for( int index = 0; index < totalSize; index++ ) {
            LottoModel item = modelList.get(index);
            totalValue[index] = Double.parseDouble( item.getValue() );
        }
        return MathAll.standardDeviation(totalValue);
    }
    
    private static double getMean() {
        List<LottoModel> modelList = lottoDB.getLottoNumList("SELECT * FROM lotto_number_total", new TreeSet<String>(Arrays.asList("Number","Number_two","Value")));
        int totalSize = modelList.size();
        double[] totalValue = new double[totalSize];
        for( int index = 0; index < totalSize; index++ ) {
            LottoModel item = modelList.get(index);
            totalValue[index] = Double.parseDouble( item.getValue() );
        }
        return MathAll.mean(totalValue);
    }

    public static int randomRange(int n1, int n2) {
        return (int) (Math.random() * (n2 - n1 + 1)) + n1;
    }
    
    public static void updateTotalLottoPage () throws Exception {
        count = 0;
        ExecutorService executorServiceWithCached = Executors.newFixedThreadPool(40);
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
