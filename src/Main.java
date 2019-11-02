public class Main {
    public static void main(String args[]){
        Solution temp = new Solution();
        
       int n = 5;
        /*int[][] build_frame = {
            {0,0,0,1},{2,0,0,1},{4,0,0,1},{0,1,1,1},{1,1,1,1},{2,1,1,1},{3,1,1,1},{2,0,0,0},{1,1,1,0},{2,2,0,1}
        };*/
       
       int[][] build_frame = {
               {0,0,0,1}
          };
       
       int[][] aa= temp.solution(n, build_frame);
        for (int index = 0; index < aa.length; index++) {
            for (int index2 = 0; index2 < aa[index].length; index2++) {
                System.out.print( aa[index][index2] + " " );
            }
            System.out.println();
        }
    }
}

