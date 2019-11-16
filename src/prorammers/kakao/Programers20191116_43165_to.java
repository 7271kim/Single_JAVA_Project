package prorammers.kakao;
// https://programmers.co.kr/learn/courses/30/lessons/42842
// red에 따라 브라운이 결정되는 케이스 red의 약수로 전체 탐색 해보자.
// 완전 탐색이란 한자기 조건을 고정시켜놓고 나머지 조건을 맞는지 확인하는 것도 존재
class Programers20191116_43165_to {
    public int  [] solution(int brown, int red) {
        int[] answer = new int[2];
        for (int index = 1; index <= red; index++) {
            if( red%index == 0 ) {
                int width  = red/index+2;
                int height = index+2;
                if( 2*(width+height)-4  == brown ) {
                    answer[0] = width;
                    answer[1] = height;
                    break;
                }
            }
        }
        return answer;
    }
}