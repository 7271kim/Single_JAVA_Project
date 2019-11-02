import java.util.HashMap;
import java.util.Map;


class Solution3 {
    public int[] solution(String[] words, String[] queries) {
        int[] answer = new int[queries.length];
        Map<String, Integer> dataSet = new HashMap<>();
        
        // ?를 이어 붙이기 위한 변수 1만
        StringBuilder questionMark = new StringBuilder();
        for (int index = 0; index < 10000; index++) {
            questionMark.append('?');
        }
        int min = 10000;
        int max = 0;
        //미리 찾을것만 만들어 놓음 , 마지막 효율성 2개가 문제가 걸림
        for (int index = 0; index < queries.length; index++) {
            String temp = queries[index];
            int size = temp.length();
            min = size < min ? size : min;
            max = size > max ? size : max;
            dataSet.put(temp,0);
            dataSet.put(String.valueOf(size), 0);
        }
        
        // 자료구조 만들기 10만
        for (int index = 0; index < words.length; index++) {
            String temp  = words[index];
            int tempSize = temp.length();
            if ( tempSize < min || tempSize > max || !dataSet.containsKey(String.valueOf(tempSize)) ) continue; 
            // 최대 1만
            for (int tempIndex = 1; tempIndex < temp.length()+1; tempIndex++) {
                StringBuilder addFirst     = new StringBuilder();
                StringBuilder addSecond    = new StringBuilder();
                
                // subString보다 이게 빠르다.
                for (int left = 0; left < tempIndex; left++) {
                    addFirst.append(temp.charAt(left));
                    addSecond.append('?');
                }
                for (int right = tempIndex; right < tempSize; right++) {
                    addFirst.append('?');
                    addSecond.append(temp.charAt(right));
                }
                String one = addFirst.toString();
                String two = addSecond.toString();
                if( dataSet.containsKey(one) ) {
                    dataSet.replace(one, dataSet.get(one)+1);
                } 
                if( dataSet.containsKey(two) ) {
                    dataSet.replace(two, dataSet.get(two)+1);
                }
            }
        }
        for (int index = 0; index < queries.length; index++) {
            String temp = queries[index];
            if(dataSet.containsKey(temp)) {
                answer[index] = dataSet.get(temp);
            } else {
                answer[index] = 0;
            }
        }
        
        return answer;
    }
}