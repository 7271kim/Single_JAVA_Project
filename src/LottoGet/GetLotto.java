package LottoGet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;


public class GetLotto {
    
    public static void main(String[] args) throws Exception {
        LottoDB lottoDB = new LottoDB();
        String noDate = "12"; // 과거 noDate번 동안 나오지 않은 수
        Boolean CheckNoDate = true; // 과거 noDate번 동안 나오지 않은 수 제거 할것인가
        
        int totalGetLotto   = 10; // 로또 구매 갯수
        int[] pickBefore    = {}; // 먼저 뽑아놓는 수
        int[] noPick        = {2,6,20,27,37,39,41,43,44,17}; // 나오면 안되는 수
        
        // 구간별 Pick
        int check_1_10  = 0;
        int check_11_20 = 0;
        int check_21_30 = 0;
        int check_31_40 = 2;
        int check_41_45 = 0;
        
        Map<Integer, Integer> emptyNumber = new HashMap<Integer, Integer>();
        Map<Integer, String> befereNumbers = new HashMap<Integer, String>();
        Map<String, String> lists = lottoDB.getQurry("SELECT * FROM lotto_data ORDER BY `DATE` DESC limit "+noDate);
        for(int index = 1; index <= 45; index++) {
            emptyNumber.put(index, index);
        }
        
        ArrayList<Integer> oderedData = new ArrayList<Integer>();
        for(String item : lists.keySet())
            oderedData.add(Integer.parseInt(item));
        Collections.sort(oderedData, Collections.reverseOrder());
        
        System.out.println("과거 " + noDate+"회 데이터");
        for( int item : oderedData ) {
            String [] splitText =  lists.get(String.valueOf(item)).split(" ");
            System.out.println(item +"회");
            String temp = "[";
            for( String number : splitText ) {
                temp += number + " ";
                int castNumber = Integer.parseInt(number);
                if(befereNumbers.containsKey(castNumber)){
                    befereNumbers.replace(castNumber, befereNumbers.get(castNumber)+"*");
                } else {
                    befereNumbers.put(castNumber,"*");
                }
                emptyNumber.remove(castNumber);
            }
            temp += "]";
            System.out.println(temp);
        }
        
        System.out.println("과거 " + noDate+"회동안 나오지 않은 수");
        for( int item : emptyNumber.keySet() ) {
            System.out.print(item + " ");
        }
        System.out.println();
        System.out.println("과거 " + noDate+"회동안 나온 수 세트");
        for( int item : befereNumbers.keySet() ) {
            System.out.println(item + " "+befereNumbers.get(item));
        }
        
        /*
        // 각 회의 합계를 구하고 싶은 경우
        Map<String, String> test = lottoDB.getAllData();
        ArrayList<Integer> tttt = new ArrayList<Integer>();
        for(String item : test.keySet())
            tttt.add(Integer.parseInt(item));
        Collections.sort(tttt);
        
        for( int item : tttt ) {
            String [] splitText =  test.get(String.valueOf(item)).split(" ");
            System.out.println(item +"회의 합");
            int temttt = 0;
            for( String number : splitText )
                temttt = temttt + Integer.parseInt(number);
            System.out.println(temttt);
        }
        */
        lottoDB.close();
        
        ArrayList<ArrayList<Integer>> totalLotto = new ArrayList<ArrayList<Integer>>();
        
        while(totalLotto.size() < totalGetLotto) {
            int totoal      = check_1_10+check_11_20+check_21_30+check_31_40+check_41_45;
            if( pickBefore.length < 7 && totoal < 7) {
                Map<Integer, Integer> result = new HashMap<Integer, Integer>();
                Map<Integer, Integer> lottoNumber = new HashMap<Integer, Integer>();
                ArrayList<Integer> check = new ArrayList<Integer>(Arrays.asList(check_1_10,check_11_20,check_21_30,check_31_40,check_41_45));
                
                for(int index = 1; index <= 45; index++) {
                    if(CheckNoDate) {
                        if(!emptyNumber.containsKey(index)) lottoNumber.put(index, index);
                    } else {
                        lottoNumber.put(index, index);
                    }
                }
                
                LottoData lottoData = new LottoData(lottoNumber, check);
                
                for( int before : pickBefore ) {
                    result.put(before, before);
                    lottoData.removeLotto(before);
                    removeLotto(before, lottoData);
                }
                for( int no : noPick ) {
                    lottoData.removeLotto(no);
                }
                while( result.size() < 6 ){
                    for( int item = 0; item < lottoData.getCheck().size(); item++ ) {
                        while( lottoData.getCheck().get(item) > 0 ) {
                            int tempNumber = randomRange(item*10+1, item*10+10);
                            int index = (tempNumber-1)/10;
                            
                            if(item != index) continue;
                            
                            if(lottoNumber.containsKey(tempNumber)) {
                                removeLotto(tempNumber,lottoData);
                                result.put(tempNumber, tempNumber);
                                lottoNumber.remove(tempNumber);
                            }
                        }
                    }
                    if( result.size() >= 6) break;
                    int tempNumber = randomRange(1, 45);
                    if(lottoData.getLottoNumber().containsKey(tempNumber)) {
                        removeLotto(tempNumber, lottoData);
                        result.put(tempNumber, tempNumber);
                        lottoData.removeLotto(tempNumber);
                    }
                }
                
                ArrayList<Integer> orderedResult = new ArrayList<Integer>(result.keySet());
                Collections.sort(orderedResult);
                totalLotto.add(orderedResult);
            } else {
                System.out.println("조건이 이상합니다. 조건을 확인해주세요");
            }
        }
        String show = "";
        for( ArrayList<Integer> item : totalLotto ) {
            show += "[ ";
            for( int inner : item )
                show += inner + " ";
            show+="] \n";
        }
        System.out.println(show);
    }
    
    public static int randomRange(int n1, int n2) {
        return (int) (Math.random() * (n2 - n1 + 1)) + n1;
    }
    
    public static LottoData removeLotto( int number ,LottoData input ) {
        int index = (number-1)/10;
        ArrayList<Integer> check = input.getCheck();
        Map<Integer, Integer> lottoNumber = input.getLottoNumber();
        
        int temp = check.get(index);
        if( temp > 0 ) {
            check.set(index, temp-1);
            if( check.get(index) == 0 ) {
                for(int removeIndex = 1; removeIndex <= 10; removeIndex++) {
                    lottoNumber.remove(index*10 + removeIndex);
                }
            }
        }
        input.setCheck(check);
        input.setLottoNumber(lottoNumber);
        
        return input;
    }
}

class LottoData {
    Map<Integer, Integer> lottoNumber;
    ArrayList<Integer> check;
    
    public LottoData( Map<Integer, Integer> lottoNumber, ArrayList<Integer> check ) {
        this.lottoNumber = lottoNumber;
        this.check = check;
    }
    
    public Map<Integer, Integer> getLottoNumber() {
        return lottoNumber;
    }
    public void setLottoNumber(Map<Integer, Integer> lottoNumber) {
        this.lottoNumber = lottoNumber;
    }
    public ArrayList<Integer> getCheck() {
        return check;
    }
    public void setCheck(ArrayList<Integer> check) {
        this.check = check;
    }
    
    public void removeLotto( int number) {
        lottoNumber.remove(number);
    }
     
}

