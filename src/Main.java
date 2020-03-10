import java.awt.Taskbar.Feature;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import algorithm.math.Union_And_Intersection;
import study.CustomThread;
import study.ShareThread;

public class Main {
    public static void main(String args[]){
        // 스레드 풀 생성 
        //자동으로 스레드 수 생성
        ExecutorService executorServiceWithCached = Executors.newCachedThreadPool();
        
        // Runable 구현 객체 ( 익명구현객체 사용  )
        Runnable task1 = ()-> {
            for (int index = 0; index < 100; index++) {
                System.out.println("작업 중입니다.");
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }; 
        
        // Callable 구현 
        Callable<Boolean> task2 = () ->{
            Boolean isFinish = true;
            
            for (int index = 0; index < 100; index++) {
                System.out.println("작업 중입니다. Call");
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            
            return isFinish;
        };
        
        // 1. 리턴 값이 없는 단순 Runnable를 처리합니다.
        executorServiceWithCached.execute(task1);
        
        // 2. 리턴 가능한 Callable도 넣을 수 있는 메서드 입니다.
        Future<Boolean> returnBoolean =  executorServiceWithCached.submit(task2);
        
        // main스레드의 작업이 멈추지 않기 위해 새로운 스레드로 구성
         executorServiceWithCached.execute(()->{
            try {
                if( returnBoolean.get() ) {
                    System.out.println("작업이 완벽히 끝났습니다. ");
                } else {
                    System.out.println("작업이 끝나지 못했습니다.");
                }
            } catch (Exception e) {
            } 
        });
        
        // main스레드의 작업이 멈추지 않기 위해 새로운 스레드로 구성
        executorServiceWithCached.execute(()->{
            try {
                // 만약 특정 시간 내에 끝났는지 확인하려는 경우
                if( returnBoolean.get(1,TimeUnit.SECONDS) ) {
                    System.out.println("작업이 완벽히 끝났습니다. ");
                } 
            } catch (Exception e) {
                System.out.println("작업이 시간내에 끝나지 못했습니다.");
            } 
        });
        
        // 작업 큐에 대기하고 있는 모든 작업이 끝난 뒤 스레드를 종료한다. 
        executorServiceWithCached.shutdown();
        
        
        
        
        
        
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

