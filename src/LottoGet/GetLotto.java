package LottoGet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class GetLotto {
    public static void main(String[] args) {
        int totalGetLotto   = 10; // 로또 구매 갯수
        int[] pickBefore    = {1,2}; // 먼저 뽑아놓는 수
        int[] noPick        = {3,4,5,6,7,8,9,41,42,43,44}; // 나오면 안되는 수
        ArrayList<ArrayList<Integer>> totalLotto = new ArrayList<ArrayList<Integer>>();
        // 구간별 Pick
        int check_1_10  = 3;
        int check_11_20 = 0;
        int check_21_30 = 0;
        int check_31_40 = 0;
        int check_41_45 = 1;
        
        while(totalLotto.size() < totalGetLotto) {
            int totoal      = check_1_10+check_11_20+check_21_30+check_31_40+check_41_45;
            if( pickBefore.length < 7 && totoal < 7) {
                Map<Integer, Integer> result = new HashMap<Integer, Integer>();
                Map<Integer, Integer> lottoNumber = new HashMap<Integer, Integer>();
                ArrayList<Integer> check = new ArrayList<Integer>(Arrays.asList(check_1_10,check_11_20,check_21_30,check_31_40,check_41_45));
                
                for(int index = 1; index <= 45; index++) {
                    lottoNumber.put(index, index);
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
