public class Main {
    public static void main(String args[]){
        /*Solution temp = new Solution();
        Solution3 temp2 = new Solution3();
        int[][]input = {{0, 0, 0, 1, 0},{1, 0, 0, 1, 1},{1, 0, 1, 0, 0},{0, 0, 0, 0, 0},{0, 0, 1, 1, 0}};
        
        int my  = temp.solution(input);
        int nam = temp2.solution(input);
        System.out.println(my);
        System.out.println(nam);*/
        
         while( true ) {
            Solution temp = new Solution();
            Solution3 temp2 = new Solution3();
            int[][]input;
            //int[][]input = {{0, 0, 0, 1, 1},{0, 0, 0, 1, 0},{0, 1, 0, 1, 1},{1, 1, 0, 0, 1},{0, 0, 0, 0, 0}};
            input = new int[][]{{0, 0, 1, 1, 1},{0, 0, 1, 1, 1},{0, 1, 1, 1, 1},{0, 0, 0, 0, 0},{0, 0, 0, 0, 0}};
            
            input = new int[5][5];
            
            for (int index = 0; index < 5; index++) {
               int[]teeemp = new int[5];
               for (int index2 = 0; index2 < 5; index2++) {
                   double random  = Math.random();
                   int value      = ( (int)(random*100) +1 ) % 2;
                   teeemp[index2] = value;
               }
               input[index] = teeemp;
            }
            
            int my  = temp.solution(input);
            int nam = temp2.solution(input);
            if( my != nam && my != 2147483647 ){ 
                System.out.println(my);
            }
        }
    }
}
