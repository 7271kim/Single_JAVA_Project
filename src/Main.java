import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import algorithm.math.GcmLcm;
import alorithm.dataStructureLow.MyHeapArray;

public class Main {
    public static void main(String args[]){
        
        /*while( true ) {
            Solution temp = new Solution();
            Solution3 temp3 = new Solution3();
            int n;
            int[] lost;
            int[] reserve;
            
            Map<Integer, Integer> lostMap = new HashMap<Integer, Integer>();
            Map<Integer, Integer> lostReserve = new HashMap<Integer, Integer>();
            
            double random  = Math.random();
            
            n = ( (int)( random*100) + 1) % 30 + 1;
            
           
            
            
            int count = 0;
            int nextN = ( (int)( random*100) + 1) % n;
            lost     = new int[nextN];
            int[] lost2     = new int[nextN];
            int[] lost3     = new int[nextN];
            
            while ( count < nextN ) {
                random  = Math.random();
                int value      = ( (int)(random*100) + 1) % n;
                value = value == 0 ? value = 1 : value;
                
                if(!lostMap.containsKey(value)) {
                    lostMap.put(value,value);
                    lost[count] = value;
                    count++;
                } 
            }
            
            count = 0;
            nextN = ( (int)( random*100) + 1) % n;
            reserve  = new int[nextN];
            int[] reserve2     = new int[nextN];
            int[] reserve3     = new int[nextN];
            
            while ( count < nextN ) {
                random  = Math.random();
                int value      = ( (int)(random*100) + 1) % n;
                value = value == 0 ? value = 1 : value;
                
                if(!lostReserve.containsKey(value)) {
                    lostReserve.put(value,value);
                    reserve[count] = value;
                    count++;
                } 
            }
            
            Arrays.sort(lost);
            Arrays.sort(reserve);
            
            for (int index = 0; index < lost.length; index++) {
                lost2[index] = lost[index];
                lost3[index] = lost[index];
            }
            for (int index = 0; index < reserve.length; index++) {
                reserve2[index] = reserve[index];
                reserve3[index] = reserve[index];
            }
            
            int my  = temp.solution(n, lost2, reserve2);
            int nam = temp3.solution(n, lost3, reserve3);
            if( my != nam && n < 8) {
                System.out.println("다르다");
            }
        }*/
        
        Solution temp = new Solution();
        
        int[] scoville = {1, 2, 3, 9, 10, 12};
        
        System.out.println(temp.solution2(scoville, 7));
        
        /*    MyHeapArray aa = new MyHeapArray(5);
        aa.insertMaxHeap(1);
        aa.insertMaxHeap(2);
        aa.insertMaxHeap(3);
        aa.insertMaxHeap(4);
        aa.insertMaxHeap(5);
        aa.print();
        
        MyHeapArray aa2 = new MyHeapArray(5);
        aa2.insertMinHeap(1);
        aa2.insertMinHeap(2);
        aa2.insertMinHeap(3);
        aa2.insertMinHeap(4);
        aa2.insertMinHeap(5);
        aa2.print();*/
    }
}

