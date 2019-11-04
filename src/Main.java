import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String args[]){
        
        /* while( true ) {
            Solution my    = new Solution();
            Solution3 nam  = new Solution3();
            
            int n        = 20;
            int aaa      = 5;
            int bbb      = 3;
            int[] weak = new int[aaa];
            int[] dist = new int[bbb];
            
            Map<Integer, String> aa = new HashMap<Integer, String>();
            
            for (int index = 0; index < aaa; index++) {
                double random  = Math.random();
                int value      = ( (int)(random*100)*2 ) % n;
                if(aa.containsKey(value)) {
                    index--;
                } else {
                    weak[index] = value;
                    aa.put(value, "aa");
                }
            }
            
            for (int index = 0; index < bbb; index++) {
                double random  = Math.random();
                int value      = ( (int)(random*100) +1 ) % 101;
                dist[index] = value;
            }
            
            IndexSort temp = new IndexSort(weak, 200);
            temp.ascendingSrot();
            weak = temp.getOr();
                    
            int myInt  = my.solution(n, weak, dist);
            int namInt = nam.solution(n, weak, dist);
            
            if(myInt != namInt) {
                System.out.println("다르다");
                break;
            }
        }*/
        
        /* Solution my    = new Solution();
        Solution3 nam  = new Solution3();
        int n        = 20;
        int[] weak   = new int []{ 0 , 4 , 6, 10, 14} ;
        int[] dist   = new int []{ 5, 2, 1  };
        
        System.out.println( "3: "+ my.solution(n, weak, dist));
        System.out.println( "3: "+ nam.solution(n, weak, dist));*/
        
        /*for (int index1 = 0; index1 < 2; index1++) {
            for (int index2 = 0; index2 < 2; index2++) {
                for (int index3 = 0; index3 < 2; index3++) {
                    for (int index4 = 0; index4 < 2; index4++) {
                        System.out.println(index1 + " " + index2+ " " + index3+ " " + index4+ " ");
                        System.out.println();
                    }
                }
            }
        }*/
        
        loopTest(0,"", 10);
        System.out.println(sum (5));
    }
    
    private static int sum( int cont ) {
        if(cont == 1) {
            return 1;
        }
        return cont+sum(cont-1);
    }
    
    
    private static void loopTest( int count, String next, int loopTotal) {
        if( count == loopTotal ) {
            System.out.println(next);
            System.out.println();
        } else {
            count++;
            for (int index = 0; index < 2; index++) {
                loopTest( count, next+String.valueOf(index),loopTotal );
            }
        }
    }
    
    private static String forAll( boolean [] aaa, int count, String result, int startIndex ) {
        if( aaa[startIndex] != true ) {
            aaa[startIndex] = true;
            result+= startIndex + " ";
            for (int index = 0; index < aaa.length; index++) {
                result += forAll( aaa, count, result, index );
            }
        }
        return result;
    }
    
}

class IndexSort {
    private int [] data;
    private int [] orignal;
   
    public IndexSort( int[] orignal, int maxRange ){
        this.orignal     = orignal;
        data = new int[maxRange+1];
        for (int index = 0; index < orignal.length; index++) {
            this.data[orignal[index]] += 1;
        }
    }
    public void ascendingSrot() {
        sortAcc();
    }
    public void sortAcc() {
        int count = 0;
        
        for (int index = 0; index < data.length; index++) {
            int thisNumber = data[index];
            if( thisNumber != 0) {
                for (int temp = 0; temp < thisNumber; temp++) {
                    orignal[count++] = index;
                }
            }
        }
    }
    
    
    public int[] getOr() {
        return orignal;
    }
}