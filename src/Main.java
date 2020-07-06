import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.function.Consumer;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

import algorithm.math.Union_And_Intersection;
import study.BeforeTest;
import study.FirstThread;
import study.Ramda;
import study.ResultShare;
import study.SecondThread;

public class Main {
    
    
    public static void main(String args[]){
        try {
            Stream<String> file = Files.lines( Paths.get("D:/over_the_dream/NaverCloud/3.Programming/1.개발 요청 사항들.txt"),Charset.defaultCharset());
            file.forEach(System.out :: println);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        
        
        List<Student> students = Arrays.asList( 
                    new Student("김석진", 10),
                    new Student("이석진", 11),
                    new Student("박석진", 13)
                );
        
        for (Student student : students) {
            System.out.println(student.getName() + " " + student.getAge()+"살.");
        }
        
        System.out.println();
        
        Stream<Student> studentStream = students.stream();
        studentStream.forEach( student -> System.out.println(student.getName() + " " + student.getAge()+"살.") );
        
        Ramda ramda = BeforeTest :: new;
        
        System.out.println( ramda.justOne(10, 11) );
        
        // 스레드 풀 생성 
        //자동으로 스레드 수 생성
        ExecutorService executorServiceWithCached = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        
        ResultShare resultShare = new ResultShare();
        
        Runnable task1 = new FirstThread(resultShare);
        Runnable task2 = new SecondThread(resultShare);
                
        Future<ResultShare> future1 =  executorServiceWithCached.submit( task1, resultShare );
        Future<ResultShare> future2 =  executorServiceWithCached.submit( task2, resultShare );
        
        executorServiceWithCached.shutdown();
        
        List<String> test = new LinkedList<String>();
        test.add("1.");
        test.add("2.");
        test.add("3.");
        
        Stream<String> zzz= test.stream();
        Consumer<String> dasd = zzzzz -> { 
            System.out.println(zzzzz);
        };
        
        zzz.forEach( dasd.andThen(ssss -> System.out.println("aaaaaaa")));
        
        
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

