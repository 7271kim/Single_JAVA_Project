package prorammers.kakao;
import java.util.Arrays;
import java.util.Comparator;

/*
F int solution( 이동해야할 거리 n ) 
    최종 결과를 확인하기 위한 변수 result = 1;
    홀수를 확인하기 위한 변수 thisNumber = n
    WHILE thiNumber가 1이 아니라면
        IF thisNumber가 홀수라면 THEN 
            thiNumber를 1줄인다.
            result를 1 증가시킨다.
        ELSE 
            thiNumber를 절반으로 줄인다. 
    result 반환한다.
*/
public class Programmers20191222_12980 {
    public int solution(int n) {
        int result = 1;
        int thisNumber = n;
        while( thisNumber != 1 ) {
            if(thisNumber % 2 !=0 ) {
                thisNumber--;
                result++;
            } else {
                thisNumber /= 2;
            }
        }
        return result;
    }
}