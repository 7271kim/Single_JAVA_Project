package prorammers.kakao;
import java.util.ArrayList;
// https://programmers.co.kr/learn/courses/30/lessons/12906
public class Programmers12906Level1 {
    public int[] solution(int []arr) {
        int[] answer = {};
        ArrayList<Integer> temp = new ArrayList<Integer>();
        int before = arr[0];
        temp.add(before);
        for (int index = 1; index < arr.length; index++) {
            int thisNumber = arr[index];
            if( thisNumber != before ) {
                temp.add(thisNumber);
                before = thisNumber;
            }
        }
        answer = new int[temp.size()];
        for (int index = 0; index < temp.size(); index++) {
            answer[index] = temp.get(index);
        }
        
        return answer;
    }
}