/*
 * https://www.acmicpc.net/problem/1436
 * 종말의 숫자란 어떤 수에 6이 적어도 3개이상 연속으로 들어가는 수
 * N번째 >> N-1에 666을 붙이기 >> X 
 * 문제가 16661도 종말의 수임.
 * 666 ~ 무한대로 while 돌면서 종말의 수 확인
 * 종말의 수 확인법 >> 해당이 문제 >> int형에서 연속된 숫자 확인하는 방법 >> 나는 String변경 후 matches수행
 * findCount가 받은 값이랑 같을 경우 해당 숫자를 출력
 * O( N(최대2,666,799 )*indexOf시간 복잡도(tartget 길이(7) * 찾는문자열 길이(3)) ) >> 56,002,779 >> 10억 안넘어서 괜춘
 * 
 */

public class Main {
    public static void main(String args[]){
        int[] data = {254,3,213,64,75,56,4,324,65,78,9,5,76,3410,8,342,76};
        BubbleSort sortData = new BubbleSort(data);
        
        sortData.ascendingSrot();
        sortData.print();
        
        sortData.descendingSrot();
        sortData.print();
    }
}

