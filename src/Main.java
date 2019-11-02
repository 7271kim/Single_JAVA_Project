public class Main {
    public static void main(String args[]){
        Solution temp = new Solution();
        
       int n = 3;
        /*int[][] build_frame = {
            {0,0,0,1},{2,0,0,1},{4,0,0,1},{0,1,1,1},{1,1,1,1},{2,1,1,1},{3,1,1,1},{2,0,0,0},{1,1,1,0},{2,2,0,1}
        };*/
       
       /*int[][] build_frame = {
               {1,0,0,1},{1,1,1,1},{2,1,0,1},{2,2,1,1},{5,0,0,1},{5,1,0,1},{4,2,1,1},{3,2,1,1},{5,2,1,1},{3,2,0,1},{3,3,0,1},{3,3,1,1},{0,0,0,1},{0,1,1,1},{1,0,0,0},{2,0,0,1},{1,0,0,0}
          };*/
       
       int count = 0;
       int[][] build_frame = new int[1000][4];
       while( count < 1000 ) {
           int[] ttttt = new int[4];
           for (int index = 0; index < 2; index++) {
               double random  = Math.random();
               int value2      = ( (int)(random*100) +1 ) % 10;
               ttttt[index] = value2;
           }
           for (int index = 2; index < 4; index++) {
               double random  = Math.random();
               int value2      = ( (int)(random*100) +1 ) % 2;
               ttttt[index] = value2;
           }
           build_frame[count++] = ttttt;
        }
       
       
        int[][] aa= temp.solution(n, build_frame);
        for (int index = 0; index < aa.length; index++) {
            for (int index2 = 0; index2 < aa[index].length; index2++) {
                System.out.print( aa[index][index2] + " " );
            }
            System.out.println();
        }
    }
}

