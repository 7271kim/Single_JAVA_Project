import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class GetLotto {

    public static void main(String[] args) {
        // 로또 사기 용
        int[] thisLotto = {}; // 금주 당첨번호 발표  후 작성 ( 몇개 맞았는지 확인용 )
        System.out.println("[11,17,28,30,33,35]");
        System.out.println("로또번호");
        ArrayList<Integer> result = new ArrayList<>();
        Map<String, ArrayList<Integer>> totalGetArray = new  HashMap<>();
        
        //구매갯수 
        int buy = 5;
        
        // 병경부 ! ------------------------------------- 1
        // 미리 선택해논 수 - 선택시 , No number에 추가 나머지에서 제거
        int[] beforePick         = {15,38};
        // 나오면 안되는 수
        int[] noNumber           = {10,15,25,32,37,38};
       
        
        Boolean checkOverLabDetail= true;
        Map<Integer, Integer> checkOverlay = new HashMap<>();
        
        checkOverlay.put(0, 0);
        checkOverlay.put(1, 2);
        checkOverlay.put(2, 0);
        checkOverlay.put(3, 2);
        checkOverlay.put(4, 0);
        
        wrapper : while( true ){
            result = new ArrayList<>();
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
            if( totalGetArray.size() >= buy ) break;
        }
        
        System.out.println("구매번호");
        
        // 로또 된것 * 표시
        for( String key : totalGetArray.keySet() ){
            ArrayList<Integer> child = totalGetArray.get(key);
            String outPut       = "[";
            String numberThis   = "";
            String loTtotNum    = "[";
            int howMnay      = 0;
            for( int innerChild = 0; innerChild < child.size(); innerChild++ ){
                numberThis = String.valueOf(child.get(innerChild));
                for( int thisLootoIndex = 0; thisLootoIndex < thisLotto.length; thisLootoIndex++ ){
                    if( thisLotto[thisLootoIndex] == child.get(innerChild) ) {
                        numberThis += "*";
                        loTtotNum += " " + String.valueOf(thisLotto[thisLootoIndex]) +" ";
                        howMnay++;
                        break;
                    }
                }
                outPut += " " + numberThis + " ";
            }
            loTtotNum += "]";
            outPut+= "]" + " 당첨 갯수 : " + String.valueOf(howMnay) + " "+ loTtotNum;
            System.out.println(outPut);
        }
        
    }
    
    public static Map<Integer, Integer> gerTempMap ( int inputNumber , Map<Integer, Integer> returnValue ) {
        int remainder = (int) Math.floor( ( inputNumber - 1 ) /10);
        int remainderValue = returnValue.containsKey(remainder) ? returnValue.get(remainder) + 1 : 1;
        returnValue.put(remainder, remainderValue);
        
        return returnValue;
    }
    public static int[] getCountNumber ( int[] income , int lange ) {
        int init = 10;
        if( lange == 40) init = 5;
        int[] result = new int[init];
        int count = 0;
        loopOutter:for( int index = 0; index < init; index++ ){
            lange++;
            for( int incomeIndex = 0; incomeIndex < income.length; incomeIndex++){
                if( lange == income[incomeIndex]){
                    continue loopOutter;
                } 
            }
            count++;
            result[index] = lange;
        }
        int[] newResult = new int[count];
        count = 0;
        for( int index = 0; index < result.length; index++){
            if(result[index] != 0) {
                newResult[count] = result[index];
                count++;
            }
        }
        
        return newResult;
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
