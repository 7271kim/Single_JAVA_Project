public class Main {
    public static void main(String args[]){
        Solution temp  = new Solution();
        Solution3 temp3 = new Solution3();
        
          String[] words1   = new String[10000];
        String[] queries1 = new String[10000];
        
        for (int count = 1; count < 10001; count++) {
            StringBuilder tempB = new StringBuilder();
            double random  = Math.random();
            int value2      = ( (int)(random*100) +1 ) % 40+1;
            for (int index = 0; index < value2; index++) {
                random  = Math.random();
                int value      = ( (int)(random*100) +1 ) % 3 + 1 + 96;
                char aaa = (char)value;
                tempB .append(aaa);
            }
            words1[count-1] = tempB.toString();
        }
        
        for (int count = 1; count < 10001; count++) {
            StringBuilder tempB = new StringBuilder();
             if(count < 50) {
                tempB.append("?");
                double random3  = Math.random();
                int value      = ( (int)(random3*100) +1 ) % 5;
                for (int index = 0; index < value; index++) {
                    tempB.append("?");
                }
            }
            
            
            double random  = Math.random();
            int value2      = ( (int)(random*100) +1 ) % 5;
            
            for (int index = 0; index < value2; index++) {
                random  = Math.random();
                int value      = ( (int)(random*100) +1 ) % 5 + 1 + 96;
                char aaa = (char)value;
                tempB .append(aaa);
            }
            
            if(count >= 50) {
                tempB.append("?");
                double random3  = Math.random();
                int value      = ( (int)(random3*100) +1 ) % 5;
                for (int index = 0; index < value; index++) {
                    tempB.append("?");
                }
            }
            //if(tempB.toString() == "" || tempB.toString()== null ) tempB.append("?");
            queries1[count-1] = tempB.toString();
        }
        int[] second1 = temp3.solution(words1, queries1);
        int[] first1  = temp.solution(words1, queries1);
        
        
        for (int index = 0; index < queries1.length; index++) {
            if(first1[index] != second1[index]) {
                System.out.println( "인덱스 : "+ index );
                System.out.println( "문제 : "+ queries1[index] );
                System.out.println( "A결과 : "+ first1[index]  + " >> B 결과 : " + second1[index] );
                System.out.println("다릅니다.");
            }
        }
        
        String[] words = { "aaaabbaaa"};
        String[] queries = {"a????????"};
        int[] first  = temp.solution(words, queries);
        int[] second = temp3.solution(words, queries);
        
        for (int index = 0; index < first.length; index++) {
            if(first[index] != second[index]) {
                System.out.println( "인덱스 : "+ index );
                System.out.println( "문제 : "+ queries[index] );
                System.out.println( "A결과 : "+ first[index]  + " >> B 결과 : " + second[index] );
                System.out.println("다릅니다.");
            }
        }
    }
}

