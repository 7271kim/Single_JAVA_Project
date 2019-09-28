
/*
1 1 0 0 
1 1 0 0

 1행의 합은 U
 2행의 합은 L
  각 열의 합은 C[열]이 문제의 조건이다.
  
*/
import java.util.ArrayList;

class Solution3 {
    public String solution(int U, int L, int[] C) {
        String result = "";
        StringBuilder resultString = new StringBuilder();
        // 윗줄을 출력하기 위한 변수 선언
        int[] upper     = new int[C.length];
        // 아랫줄을 출력하기 위한 변수 선언
        int[] lower     = new int[C.length];
        
        // C의 값이 1인것의 index를 담아두는 변수
        ArrayList<Integer> putIndex = new ArrayList<Integer>();
        
        // C의 값이 1인 갯수 체크
        int oneCount  = 0;
        
        // 인풋 C에 대한 처리 
        for (int index = 0; index < C.length; index++) {
            int number = C[index];
            
            //C가 2일경우 upper 와 lower줄은 둘다 1이다
            if( number == 2) {
                upper[index] = 1;
                lower[index] = 1;
                U--;
                L--;
                // 위아래 합을 하나씩 빼줌
                if( U < 0 || L < 0 ) {
                    // 만약 U와 L이 0보다 작아지면 더이상의 로직이 필요없음  불가능함
                    result = "IMPOSSIBLE";
                    break;
                }
            } else if( number == 0 ) {
              //C가 2일경우 upper 와 lower줄은 둘다 0이다
                upper[index] = 0;
                lower[index] = 0;
            } else {
                // 1의 갯수 카운트
                oneCount++;
                putIndex.add(index);
            }
        }
        
        // 만약 1읠 갯수가 남은 U와 L의 합과 같아야 조건에 만족한다.
        if( oneCount != ( U+L ) ) {
            result = "IMPOSSIBLE";
        } else {
            // 이제 정답 출력을 위한 로직 추가
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
