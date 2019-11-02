import java.util.HashMap;
import java.util.Map;
// https://programmers.co.kr/learn/courses/30/lessons/60060
// 이전 기본은 >> 무식하게 검색쿼리를 하나 픽 후 전체를 돌아보는것.
// 10억 * 10억의 효율성이 나와버림
// 좋은 방법이 없을까 
// 찾는것이니 자료구조를 찾는것에 맞게 세팅을 한다면?
// 문자열과 ?의 갯수를 키로하고 개수를 카운팅하는 HashMap 자료구조 세팅
// 더 좋은것은 찾을것만 세팅하기
// 안되는 이유 Trie 자료구조를 알고 있어야함.
// Map구조로 불가


class Solution {
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