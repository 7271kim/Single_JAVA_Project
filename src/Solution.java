//https://programmers.co.kr/learn/courses/30/lessons/12916 간단한거
class Solution {
    boolean solution(String s) {
        int[] data = new int[26];
        s = s.toLowerCase();
        for (int index = 0; index < s.length(); index++) {
            data[s.charAt(index) - 'a'] += 1;
        }
        return data[15] == data[24];
    }
}