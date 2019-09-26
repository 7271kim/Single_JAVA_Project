package algorithm.dataStructure;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class FrequencyCustom {
    public static void main(String args[]){
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //Scanner sc = new Scanner(System.in);
        try {
            String[] firstLow = br.readLine().split(" ");
            String[] toatalInput = br.readLine().split(" ");
            FrequencySortReal datas = new FrequencySortReal();
            for (int index = 0; index < toatalInput.length; index++) {
                int number = Integer.parseInt(toatalInput[index]);
                datas.update(number, index);
            }
            datas.sort();
            datas.sortPrint();
        } catch (Exception e) {
            System.out.println(e);
        } 
    }
}

class FrequencySortReal {
    // Map< 숫자,{인덱스,횟수 저장} >
    Map<Integer, int[]> datas = new HashMap<Integer, int[]>();
    ArrayList<Integer> sorted = new ArrayList<Integer>();
    
    public void update(int number , int index) {
        if(datas.containsKey(number)) {
            int[] temp = datas.get(number);
            temp[1] += 1;
            datas.replace(number, temp);
        }else {
            int[] temp = {index,1};
            datas.put(number, temp);
        }
    }
    public void sort() {
        sorted = new ArrayList<Integer>(datas.keySet());
        Collections.sort(sorted, new Comparator<Integer>() {
            @Override
            public int compare(Integer first, Integer second) {
                // {인덱스,횟수저장 } => 횟수가 높은것 먼저 저장, 그다음 인덱스가 먼저 나온것 상위
                // 리턴 1 : 오름 차순
                // 리턴 -1 : 내림차순
                // 리턴 0 : 같음 아무일 안함
                int firstSize       = datas.get(first)[1];
                int secondSize      = datas.get(second)[1];
                int firstShowIndex      =  datas.get(first)[0];
                int secondShowIndex     =  datas.get(second)[0];
                if( firstSize != secondSize ) {
                    return firstSize < secondSize ? 1 : -1;
                } else {
                    return firstShowIndex < secondShowIndex ? -1 : 1;
                }
            }
        });
    }
    
    public void sortPrint() {
        for (Integer key : sorted) {
            int count = datas.get(key)[1];
            for (int index = 0; index < count; index++) {
                System.out.println(key);
            }
        }
    }
}