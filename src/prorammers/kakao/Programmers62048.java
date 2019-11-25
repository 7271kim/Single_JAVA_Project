package prorammers.kakao;
//https://programmers.co.kr/learn/courses/30/lessons/62048
// 최소 공배수를 통해 최하로 나눌 수 있는 케이스로 나누고 
// 최하의 케이스에서 w + h - 1 이 항상 걸치는 라인
// 즉 최대 케이스 w*h - ( w/min + w/min -1  )*min
// 문제는 1억이 넘어가기 때문에 w*h에서 이슈가 발생. >> long으로 처리 가능함.
class Programmers62048 {
    public long solution(int w,int h) {
        long answer = 1;
        int min = get(w, h);
        long wL = w;
        long hL = h;
        answer = wL*hL - ( wL + hL - min );
        
        return answer;
    }
    
     public int get( int b, int a ) {
        if( a == 0 ) return b;
        return get( a , b % a );
    }
}