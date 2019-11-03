import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String args[]){
        
        Boolean aaaaaaaaa = true;
        
        while( aaaaaaaaa ) {
            Solution temp = new Solution();
            Solution3 anothor = new Solution3();
            
            int n = 5;
           
             int count = 0;
            int testing = n;
            int[][] build_frame = new int[testing*testing][4];
            
             Map<String, String> tttaa = new HashMap<String, String>();
            
            
            while( count < testing*testing ) {
               int[] ttttt = new int[4];
               StringBuilder zzzzzzzz = new StringBuilder();
               for (int index = 0; index < 2; index++) {
                   double random  = Math.random();
                   int value2      = ( (int)(random*100) +1 ) % (testing+1);
                   ttttt[index] = value2;
               }
               for (int index = 2; index < 4; index++) {
                   double random  = Math.random();
                   int value2      = ( (int)(random*100) +1 ) % 2;
                   ttttt[index] = value2;
               }
               
               for (int index = 3; index < 4; index++) {
                   double random  = Math.random();
                   //int value2      = ( (int)(random*100) +1 ) % 2;
                   int value2      = 1;
                   ttttt[index] = value2;
               }
               
               if( ttttt[1] == 0 && ttttt[2] == 1) {
                   ttttt[2] = 0;
               }
               
               if( ttttt[0] == n && ttttt[2] == 1) {
                   ttttt[2] = 0;
               }
               for (int index = 0; index < ttttt.length; index++) {
                   zzzzzzzz.append(ttttt[index]);
               }
               
               if( !tttaa.containsKey(zzzzzzzz.toString()) ) {
                   build_frame[count++] = ttttt;
                   tttaa.put(zzzzzzzz.toString(), "aaa");
               } 
            }
            
            /*int[][] build_frame = new int[32][4];
            int xx = 0;
            while( xx < 18) {
                for (int indexY = 0; indexY < 4; indexY++) {
                    for (int indexX = 0; indexX < 4;  indexX++) {
                        for (int check = 0; check < 2; check++) {
                            int zzz = check;
                            if( indexX == 0 ) {
                                zzz= 0;
                            }
                            int[] ttt = { indexY , indexX , zzz ,1 };
                            build_frame[xx++] = ttt;
                        }
                    }
                }
            }*/
            
            
            
            /*int[][] build_frame = {
                   {0,0,0,1},{0,1,0,1},{0,2,0,1},{0,3,0,1}
               };*/
            int[][] aa= temp.solution(n, build_frame);
            int[][] bb= anothor.solution(n, build_frame);
            
            for (int index = 0; index < aa.length; index++) {
                
                StringBuilder me = new StringBuilder();
                for (int index2 = 0; index2 < aa[index].length; index2++) {
                    me.append(aa[index][index2] + " " );
                }
                System.out.println();
                
                
                StringBuilder nam = new StringBuilder();
                
                for (int index2 = 0; index2 < aa[index].length; index2++) {
                    nam.append(bb[index][index2] + " " );
                }
                
                if(!me.toString().equals(nam.toString())) {
                    System.out.println("index :  " + index);
                    System.out.println("내 소스 ");
                    System.out.println(me.toString());
                    System.out.println("남소스 ");
                    System.out.println(nam.toString());
                    aaaaaaaaa = false;
                    break;
                }
                System.out.println();
            }
        }
        System.out.println("aa");
    }
}

