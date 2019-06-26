package LottoGet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class StaticClassLotto {

    public static void main(String[] args) {
        // 통계용 
        int wishGet = 5; // 로또 번호 5개가 맞을 때까지 계속 뽑음
        int wishTotalCount   = 1000; // 5번이 총 1000번 맞을 때 가지의 통계 작성
        
       //확인용 : 당첨된 숫자
        int[] thisLotto = {11,17,28,30,33,35};  // 금주 당첨번호 발표  후 작성 ( 몇개 맞았는지 확인용 )
        System.out.println("[11,17,28,30,33,35]");
        System.out.println("로또번호");
        ArrayList<Integer> result = new ArrayList<>();
        Map<String, ArrayList<Integer>> totalGetArray = new  HashMap<>();
        
        // 병경부 ! ------------------------------------- 1
        // 미리 선택해논 수 - 선택시 , No number에 추가 나머지에서 제거
        int[] beforePick         = {11,17};
        // 나오면 안되는 수
        int[] noNumber           = {11,17};
       
        Boolean checkOverLabDetail= true;
        Map<Integer, Integer> checkOverlay = new HashMap<>();
        checkOverlay.put(0, 0);
        checkOverlay.put(1, 2);
        checkOverlay.put(2, 0);
        checkOverlay.put(3, 2);
        checkOverlay.put(4, 0);
        
        // 해당 wishGet을 위해 총 몇번을 샀나
        double totalStaticCount = 0;
        
        // 통계 wishTotalCount번 돌리기
        for ( int staticIndex =0 ; staticIndex < wishTotalCount; staticIndex++ ){
            wrapper : while( true ){
                result = new ArrayList<>();
                totalGetArray = new  HashMap<>();
                Map<Integer, Integer> tempcheckOverlay = new HashMap<>();
                
                for( int indexBeforpick = 0; indexBeforpick < beforePick.length; indexBeforpick++ ){
                    int beforeValue = beforePick[indexBeforpick];
                    result.add(beforeValue);
                    tempcheckOverlay = gerTempMap( beforeValue , tempcheckOverlay);
                }
                
                // 로또번호 뽑기
                
                for( int index = 0; index < 6 - beforePick.length; index++ ){
                    int temp = 0;
                    
                    while( true ){
                        temp = randomRange(1, 45);
                        if( result.indexOf( temp ) > -1 ) continue;
                        int remainder = (int) Math.floor( (temp - 1 ) /10 );
                        tempcheckOverlay = gerTempMap( temp , tempcheckOverlay);
                        if( checkOverlay.get(remainder) != 0 && checkOverLabDetail && tempcheckOverlay.get(remainder) > checkOverlay.get(remainder) ) continue;
                        
                        break;
                    }
                    result.add(temp);
               }
                
                //checkOverlay 0 , 2, 2 등  맞추기 
                for( int key : checkOverlay.keySet() ){
                    if( checkOverlay.get(key) != 0 ){
                        if(!tempcheckOverlay.containsKey(key) || checkOverlay.get(key) != tempcheckOverlay.get(key) ){
                            continue wrapper;
                        } 
                    }
                }
                
                sortThis(result);
                
                Iterator iterator = result.iterator();
                String keyText= "";
                while (iterator.hasNext()) {
                    keyText += String.valueOf(iterator.next());
                }
                if( totalGetArray.containsKey(keyText) ) continue;
                totalGetArray.put( keyText, result );
                // 위 1회 구매 로직
                
                // 통계를 위해.
                int tempCount = 0;
                for(int lotto : thisLotto  ){
                    if(result.indexOf(lotto) > -1 ) tempCount++;
                }
                totalStaticCount++;
                if( tempCount >= wishGet ) break;
            }
        }
        
        System.out.println("통계");
        System.out.println("totalStatic : " + totalStaticCount);
        System.out.println(wishGet + "개를 맞추기 위해 통계적으로 몇 회 사야 하냐 : " +  Math.floor (totalStaticCount/wishTotalCount) + "번 사야한다.");
    }
    
    public static Map<Integer, Integer> gerTempMap ( int inputNumber , Map<Integer, Integer> returnValue ) {
        int remainder = (int) Math.floor( ( inputNumber - 1 ) /10);
        int remainderValue = returnValue.containsKey(remainder) ? returnValue.get(remainder) + 1 : 1;
        returnValue.put(remainder, remainderValue);
        
        return returnValue;
    }
    public static ArrayList<Integer> sortThis( ArrayList<Integer> inCome ){
        ArrayList<Integer> result = inCome;
        for( int cycleIndex = 0; cycleIndex < result.size(); cycleIndex++ ){
            int minIndex = cycleIndex;
            int minValue = result.get(cycleIndex);
            
            for( int index = cycleIndex+1 ; index < result.size(); index++ ){
                if(result.get(index) < minValue ){
                    minIndex = index;
                    minValue = result.get(index);
                }
            }
            
            int thisCycleValue = result.get(cycleIndex);
            result.set(cycleIndex, minValue);
            result.set(minIndex, thisCycleValue);
        }
        return result;
    }
    public static int randomRange(int n1, int n2) {
      return (int) (Math.random() * (n2 - n1 + 1)) + n1;
    }

}
