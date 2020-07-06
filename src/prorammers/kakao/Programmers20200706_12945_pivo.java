package prorammers.kakao;
import java.util.LinkedList;
import java.util.Queue;

/*

https://programmers.co.kr/learn/courses/30/lessons/12945?language=java

F solution ( n번째 피보나치 수 n ) 
    int[] 캐쉬 cached = new int[총 크기 100001];
    cached[0] = 0;
    cached[1] = 0;
    FOR index 2 부터 100000 까지 
       cached[index] = ( cached[index-2] + cached[index-1] ) % 1234567;
    return cached[n]
*/
class Programmers20200706_12945_pivo {
    public int solution(int n) {
        int[] cached = new int[100001];
        cached[0] = 0;
        cached[1] = 1;
        for (int index = 2; index < cached.length; index++) {
            cached[index] = ( cached[index-2] + cached[index-1] ) % 1234567;
        }
        return cached[n];
    }
}
