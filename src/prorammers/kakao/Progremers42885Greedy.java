package prorammers.kakao;
import java.util.Arrays;

// https://programmers.co.kr/learn/courses/30/lessons/42885
// 2명을 태울 수 있을 때의 조합.
class Progremers42885Greedy {
    public int solution(int[] people, int limit) {
        Arrays.sort(people);
        // 짝을 이룰 수 있는 경우를 확인 
        // 최대 2명이 탈 수 있기 때문에 가장 무거운 사람과 가장 가벼운 사람 두 조합이 가장 많이 테울 수 있는 경우다.
        
        int compareIndex = 0;
        int together  = 0;
        
        for (int index = people.length-1; index > compareIndex; index--) {
            if(people[index] + people[compareIndex] <= limit) {
                together++;
                compareIndex++;
            }
        }
        
        return people.length - together;
    }
}