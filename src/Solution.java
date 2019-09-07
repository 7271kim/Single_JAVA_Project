class Solution {
    public int[] solution(String[] words, String[] queries) {
        int[] answer = new int[queries.length];
        for (int index = 0; index < queries.length; index++) {
            String input = queries[index];
            input = input.replaceAll("\\?", ".");
            int result = 0;
            for (int indexWord = 0; indexWord < words.length; indexWord++) {
                if( words[indexWord].matches(input) ) result++;
            }
            answer[index] = result;
        }
        return answer;
    }
}