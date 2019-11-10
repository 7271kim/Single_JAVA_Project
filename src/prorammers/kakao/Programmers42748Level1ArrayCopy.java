package prorammers.kakao;
import java.util.Arrays;

//https://programmers.co.kr/learn/courses/30/lessons/42748
class Programmers42748Level1ArrayCopy {
    public int[] solution(int[] array, int[][] commands) {
        int[] answer = new int[commands.length];
        
        for (int index = 0; index < commands.length; index++) {
            int[] check = commands[index];
            int start = check[0]-1;
            int end   = check[1]-1;
            int find  = check[2];
            
            int[] temp = new int[end-start+1];
            int count  = 0;
            for (int getIndex = start; getIndex <= end; getIndex++) {
                temp[count++] = array[getIndex];
            }
            Arrays.sort(temp);
            answer[index] = temp[find-1];
        }
        return answer;
    }
    
    // 남들 소스 배열 복사 소스 copyOfRange( 원본 , 시작점 , 마지막 인덱스 + 1 )
    public int[] solution2(int[] array, int[][] commands) {
        int[] answer = new int[commands.length];
        
        for (int index = 0; index < commands.length; index++) {
            int[] temp = Arrays.copyOfRange(array, commands[index][0]-1, commands[index][1]);
            Arrays.sort(temp);
            answer[index] = temp[commands[index][2]-1];
        }
        return answer;
    }
}