class Solution1 {
    public int solution(String S) {
       String[] temp  = S.split("");
       int max        = -1;
       int aCount     = 0;
       int bCount     = 0;
       int countText  = 0;
       
       for (int index = 0; index < temp.length; index++) {
           countText++;
           if( temp[index].equals("a") ) {
               aCount++;
               if( aCount > 2 ) {
                   aCount    = 2;
                   countText = 2;
               }
               if( index != 0 && temp[index-1].equals("b") ) {
                   bCount     = 0;
               }
           } else {
               bCount++;
               if( bCount > 2 ) {
                   bCount    = 2;
                   countText = 2;
               }
               if( index != 0 && temp[index-1].equals("a") ) {
                   aCount     = 0;
               }
           }
           max = max < countText ? countText : max;
       }
       
       return max;
    }
}
