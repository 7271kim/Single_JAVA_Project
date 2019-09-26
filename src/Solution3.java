import java.util.ArrayList;

class Solution3 {
    public String solution(int U, int L, int[] C) {
        String result = "";
        StringBuilder resultString = new StringBuilder();
        int[] upper     = new int[C.length];
        int[] lower     = new int[C.length];
        ArrayList<Integer> putIndex = new ArrayList<Integer>();
        
        int oneCount  = 0;
        for (int index = 0; index < C.length; index++) {
            int number = C[index];
            if( number == 2) {
                upper[index] = 1;
                lower[index] = 1;
                U--;
                L--;
                if( U < 0 || L < 0 ) {
                    result = "IMPOSSIBLE";
                    break;
                }
            } else if( number == 0 ) {
                upper[index] = 0;
                lower[index] = 0;
            } else {
                oneCount++;
                putIndex.add(index);
            }
        }
        if( oneCount != ( U+L ) ) {
            result = "IMPOSSIBLE";
        } else {
            for (Integer index : putIndex) {
                if( U > 0 ) {
                    upper[index] = 1;
                    U--;
                } else {
                    lower[index] = 1;
                }
            }
            for (int index = 0; index < upper.length; index++) {
                resultString.append(upper[index]);
            }
            resultString.append(",");
            for (int index = 0; index < lower.length; index++) {
                resultString.append(lower[index]);
            }
            result = resultString.toString();
        }
        
        
        return result;
    }
}
