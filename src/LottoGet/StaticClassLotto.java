package LottoGet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class StaticClassLotto {

    public static void main(String[] args) {
        // 통계용 
        int wishGet          = 4; // 로또 번호 wish개가 맞을 때까지 계속 뽑음
        int wishTotalCount   = 1000; // 5번이 총 1000번 맞을 때 가지의 통계 작성
        
       //확인용 : 당첨된 숫자
        int[] thisLotto = {21,25,30,32,40,42};  // 금주 당첨번호 발표  후 작성 ( 몇개 맞았는지 확인용 )
        
        int[] pickBefore    = {}; // 먼저 뽑아놓는 수
        int[] noPick        = {2,6,17,20,27,37,39,41,43,44,17}; // 나오면 안되는 수
        
        long totalBuy        = (long) 0.0; //총 산 횟수
        long thisCount        = (long) 0.0; // 지금까지 맞은 횟수
        
        // 구간별 Pick
        int check_1_10  = 0;
        int check_11_20 = 1;
        int check_21_30 = 2;
        int check_31_40 = 2;
        int check_41_45 = 1;
        
        while ( thisCount < wishTotalCount ) {
            int totoal      = check_1_10+check_11_20+check_21_30+check_31_40+check_41_45;
            if( pickBefore.length < 7 && totoal < 7) {
                Map<Integer, Integer> result = new HashMap<Integer, Integer>();
                Map<Integer, Integer> lottoNumber = new HashMap<Integer, Integer>();
                ArrayList<Integer> check = new ArrayList<Integer>(Arrays.asList(check_1_10,check_11_20,check_21_30,check_31_40,check_41_45));
                
                for(int index = 1; index <= 45; index++) {
                    lottoNumber.put(index, index);
                }
                LottoDataStatic lottoData = new LottoDataStatic(lottoNumber, check);
                
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
                totalBuy++;
                int temp = 0;
                for(int index = 0; index < thisLotto.length; index++) {
                    if( thisLotto[index] == orderedResult.get(index) ) {
                        temp++;
                    } 
                }
                if( temp == wishGet) thisCount++;
            }
            if( totalBuy > 50000000.0) {
                System.out.println("토탈이 5000만이 넘어 너무 오래걸려 로재 로직을 종료하겠습니다");
                break;
            }
        }
        if( thisCount > 0 ) {
        System.out.println("로또번호가 " + wishGet +"개 맞을때까지 평균적으로 " + totalBuy/thisCount + "회 사야한다.");
        System.out.println("총 " + thisCount +"회 맞음");
        } else {
            System.out.println("지금까지 맞은 케이스가 없습니다. Pick을 확인해보세요");
        }
    }
    
    public static int randomRange(int n1, int n2) {
      return (int) (Math.random() * (n2 - n1 + 1)) + n1;
    }
    
    public static LottoDataStatic removeLotto( int number ,LottoDataStatic input ) {
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
class LottoDataStatic {
    Map<Integer, Integer> lottoNumber;
    ArrayList<Integer> check;
    
    public LottoDataStatic( Map<Integer, Integer> lottoNumber, ArrayList<Integer> check ) {
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