import java.lang.reflect.Method;
import java.util.ArrayList;

import algorithm.math.Union_And_Intersection;

public class Main {
    public static void main(String args[]){
        TestClass testClass= new TestClass();
        
        testClass.testMethod();
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
        Solution2 temp2 = new Solution2();
        
        //int[][] baseball = {{123, 1, 1}, {356, 1, 0}, {327, 2, 0}, {489, 0, 1}};
        //int[][] baseball = {{345,1,1}};
        
        String[] cities    = {"Jeju", "city","byby","city","city"};
        System.out.println(temp.solution(3, cities));
       // System.out.println(temp2.solution(8, 5, words ));
        
        String[] temp11 = {"aa","bb","dcd","eee","aa"};
        String[] temp22 = {"aa","bb","ddcd","feee","aa","aa"};
        
        ArrayList<String> tttt = Union_And_Intersection.getUnion(temp11, temp22);
        ArrayList<String> tttt2 = Union_And_Intersection.getIntersection(temp11, temp22);
        
        
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

