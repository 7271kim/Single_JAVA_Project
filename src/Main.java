import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Deque;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

import alorithm.dataStructureLow.DoubleLinkedList;
import alorithm.dataStructureLow.GraphArrayList;
import alorithm.dataStructureLow.GraphMatrix;
import alorithm.dataStructureLow.MaxHeap;
import alorithm.dataStructureLow.TreeWithArray;
import alorithm.dataStructureLow.TreeWithLinkedList;
import sun.tools.jconsole.MaximizableInternalFrame;

public class Main {
    private static void result( String[] people ) {
        // 1. 끝점
        // 2. 시작점
        // 3. 개별 임시 결과 저장
        // 4. 최종 결과
        // 5. 다음 뽑을 것을 위한 index
        
        int end = 3;
        int start  = 0;
        String[] tempResult = new String[end];
        int loopStartIndex = 0; 
        ArrayList<String[]> result = new ArrayList<String[]>();
        permutation( people, end, start, tempResult, result, loopStartIndex );
        for (String[] strings : result) {
            String temp = "";
            for (String strings2 : strings) {
                temp += " " + strings2;
            }
            System.out.println(temp);
        }
        System.out.println(result.size());
        
    }

    private static void permutation( String[] people, int end, int start, String[] tempResult, ArrayList<String[]> result, int loopStartIndex ) {
        if( end == start ) {
            result.add(tempResult.clone());
            return;
        }
        
        for( int index = loopStartIndex; index < people.length; index++ ) {
                tempResult[start] = people[index];
                permutation(people, end, start+1, tempResult, result, index+1);
                tempResult[start] = "";
        }
    }


    
    private static boolean sulotions( String[][] word, String findWord, int[][] checked, int findIndex, int x, int y ) {
        
        int totalSize = word[0].length;
        int[] dx = {-1,-1,-1,1,1,1,0,0};
        int[] dy = {-1,0,1,0,-1,0,1,-1,1};
        boolean result = false;
        
        if( x < 0 || x >= totalSize ) return false;
        if( y < 0 || y >= totalSize ) return false;
        if( checked[x][y] == 1 ) return false;
        if( findIndex == findWord.length() ) return true;
        
        String findOneWord = findWord.substring( findIndex, findIndex+1 );
        
        if( word[x][y].equals(findOneWord) ) {
            checked[x][y] = 1;
            for( int index = 0; index < 8; index++ ) {
                result = sulotions(word, findWord, checked, findIndex+1, x + dx[index], y + dy[index]);
                if( result ) {
                    return true;
                }
            }
            checked[x][y] = 0;
        }
        
        return result;
    }
    
    
    
    
    public static void main(String args[]){
        String[] people = {"1","2","3","4","5","6"};
        result(people);

     
        
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
                    lostReserve.put(value,value);0
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
        
        /*while( true ) {
            Solution temp = new Solution();
            Solution2 temp2 = new Solution2();
            int n;
            int m;
            double random  = Math.random();
            
            n = ( (int)( random*100) + 1) % 10 + 2;
            random  = Math.random();
            m = ( (int)( random*100) + 1) % 10 + 2;
            
            String[] words = new String[m];
            for (int index = 0; index < words.length; index++) {
                StringBuilder tempaa = new StringBuilder();
                for (int index2 = 0; index2 < n; index2++) {
                    random  = Math.random();
                    int tttt = ( (int)( random*100) + 1) % 26 +1;
                    char zzz = (char) (tttt + 'A' - 1 );
                    tempaa.append(zzz);
                }
                words[index] = tempaa.toString();
               
            }
            int aaaa = temp.solution(m, n, words);
            int adasd = temp2.solution(m, n, words);
            if( aaaa != adasd ) {
                System.out.println("다르다");
                break;
            }
        }*/
        
        
        Solution temp = new Solution();
        System.out.println( temp.solution(100000) );
        
        
        /*Thread thred1 = new Thread() {
            public void run(){ 
                try { 
                  
                  SingleTon SingleTon1 = SingleTon.getInstace();
                  SingleTon SingleTon2 = SingleTon.getInstace();
                  System.out.println("두 객체가 같습니까 ? : " + (SingleTon1 == SingleTon2));
                } catch (Exception e) { 
                  e.printStackTrace(); 
                } 
              } 
        };
        
        Thread thred2 = new Thread() {
            public void run(){ 
                try { 
                  SingleTon SingleTon1 = SingleTon.getInstace();
                  SingleTon SingleTon2 = SingleTon.getInstace();
                  System.out.println("두 객체가 같습니까 ? : " + (SingleTon1 == SingleTon2));
                } catch (Exception e) { 
                  e.printStackTrace(); 
                } 
              } 
        };
        thred1.start();
        thred2.start();*/
        
        /*  SingleTonMoreEffective aa = SingleTonMoreEffective.getInstace();
        SingleTonMoreEffective bb = SingleTonMoreEffective.getInstace();
        System.out.println(aa==bb);
        
        SingleTonEnum singleTonEnum  = SingleTonEnum.getInstance();
        System.out.println(singleTonEnum.getText());*/
    }
}

