package prorammers.kakao;
//https://programmers.co.kr/learn/courses/30/lessons/12909
class Programmers20191117_12909 {
    boolean solution(String s) {
        boolean answer = true;
        int openCount   = 0;
        for (int index = 0; index < s.length(); index++) {
            char temp = s.charAt(index);
            if( temp == '(') {
                openCount++;
            } else {
                openCount--;
                if( openCount < 0 ) {
                    answer = false;
                    break;
                }
            }
        }
        
        return answer && openCount == 0;
    }
}