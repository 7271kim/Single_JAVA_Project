import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

/*
 * https://www.acmicpc.net/problem/1966
 * 큐 - 순회 돌기
 * 메모리 초과
 */

public class Main {
    public static void main(String args[]){
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //Scanner sc = new Scanner(System.in);
        try {
            int total = Integer.parseInt(br.readLine());
            for (int index = 1; index <= total; index++) {
                String[] input = br.readLine().split(" ");
                String[] queSize = br.readLine().split(" ");
                int result       = 0;
                Queue<int[]> ques = new LinkedList<int[]>();
                int find    = Integer.parseInt(input[1]);
                int prioryMax   = 1;
                for (int indexQue = 0; indexQue < queSize.length; indexQue++) {
                    int findPri = Integer.parseInt(queSize[indexQue]);
                    int [] temp = { indexQue, findPri };
                    ques.add(temp);
                    if( prioryMax <= findPri ) prioryMax = findPri; // 찾으려는 친구보다 더 큰 우선순위를 갖는 친구 저장
                }
                Boolean isGo = true;
                int nextPriory = 1;
                while( isGo ) {
                    for (int cy = 0; cy < ques.size(); cy++) {
                        if(ques.peek()[1] == prioryMax) {
                            // 최고치부터 출력 그외는 순회
                            result++;
                            cy = 0;
                            if( ques.peek()[0] == find ) {
                                System.out.println(result);
                                isGo = false;
                                break;
                            }
                            ques.poll();
                        } 
                        if( nextPriory <= ques.peek()[1] ) nextPriory = ques.peek()[1]; // 그다음 최고치확인
                        int[] temp = ques.poll();
                        ques.add(temp);
                    }
                    prioryMax = nextPriory;
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        } 
    }
}
