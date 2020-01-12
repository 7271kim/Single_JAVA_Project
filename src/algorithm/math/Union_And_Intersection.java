package algorithm.math;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;

public class Union_And_Intersection {
    //배열의 교집합 구히기
    public static ArrayList<String> getIntersection( String[] input1 , String[] input2 ){
        ArrayList<String> intersectionList = new ArrayList<>();
        Deque<String> que1 = chageArrToDeque(input1);
        Deque<String> que2 = chageArrToDeque(input2);
        
        int que1Size = que1.size();
        for (int i = 0; i < que1Size; i++) {
            String thisAttr1 = que1.poll();
            
            int que2Size = que2.size();
            for (int index = 0; index < que2Size; index++) {
                String thisAttr2 = que2.poll();
                if( thisAttr1.equals(thisAttr2) ) {
                    intersectionList.add(thisAttr2);
                    break;
                } else {
                    que2.add(thisAttr2);
                }
            }
        }
        
        return intersectionList;
    }
    
  //배열의 합집합 구히기
    public static ArrayList<String> getUnion( String[] input1 , String[] input2 ){
        ArrayList<String> unionList = new ArrayList<>();
        Deque<String> que1 = chageArrToDeque(input1);
        Deque<String> que2 = chageArrToDeque(input2);
        
        int que1Size = que1.size();
        for (int i = 0; i < que1Size; i++) {
            String thisAttr1 = que1.poll();
            unionList.add(thisAttr1);
            
            int que2Size = que2.size();
            for (int index = 0; index < que2Size; index++) {
                String thisAttr2 = que2.poll();
                if( thisAttr1.equals(thisAttr2) ) {
                    break;
                } else {
                    que2.add(thisAttr2);
                }
            }
        }
        
        while ( !que2.isEmpty() ) {
            unionList.add(que2.poll());
        }
        
        return unionList;
    }
    
   private static Deque<String> chageArrToDeque ( String[] input1 ) {
       Deque<String> result = new LinkedList<>();
       
       for (int index = 0; index < input1.length; index++) {
           result.add(input1[index]);
       }
       
       return result;
   }
}
