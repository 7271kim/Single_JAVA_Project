package prorammers.kakao;
import java.util.ArrayList;

// https://programmers.co.kr/learn/courses/30/lessons/42840
class Programmers42840Level1 {
    public int[] solution(int[] answers) {
        int[] answer = {};
        // 정답 갯수
        int[] fisrstAnswer = { 1,2,3,4,5 };
        int[] secondAnswer = { 2,1,2,3,2,4,2,5 };
        int[] thirdAnswer  = { 3, 3, 1, 1, 2, 2, 4, 4, 5, 5 };
        
        int first  = 0;
        int second = 0;
        int third  = 0;
        
        for (int index = 0; index < answers.length; index++) {
            int thisAnswer = answers[index];
            if( fisrstAnswer[index % 5]  == thisAnswer ) first++;
            if( secondAnswer[index % 8]  == thisAnswer ) second++;
            if( thirdAnswer[index % 10]  == thisAnswer ) third++;
        }
        
        // 비교 
        int max = Math.max(first, Math.max(second, third));
        
        ArrayList<Integer> temp = new ArrayList<Integer>();
        int count = 0;
        if( first == max ) {
            temp.add(1);
            count++;
        }
        if( second == max ) {
            temp.add(2);
            count++;
        }
        if( third == max ) {
            temp.add(3);
            count++;
        }
        
        answer = new int[count];
        
        for (int index = 0; index < count; index++) {
            answer[index] = temp.get(index);
        }
        
        return answer;
    }

}