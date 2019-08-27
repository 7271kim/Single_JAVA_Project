import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

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
            StringBuilder temp  =  new StringBuilder();
            for (int index = 1; index <= total; index++) {
                String[] input      = br.readLine().split(" ");
                String[] queSize    = br.readLine().split(" ");
                Queue<Integer> que  = new LinkedList<Integer>();
                long result       = 0;
                int find    = Integer.parseInt(input[1]);
                int prioryMax   = 1;
                for (int indexQue = 0; indexQue < queSize.length; indexQue++) {
                    int findPri = Integer.parseInt(queSize[indexQue]);
                    que.add(findPri);
                    if( prioryMax <= findPri ) prioryMax = findPri; // 찾으려는 친구보다 더 큰 우선순위를 갖는 친구 저장
                }
                Boolean isGo = true;
                int nextPriory = 1; // 그 다음 우선순위 저장
                while( isGo ) {
                    for (int cy = 0; cy < que.size(); cy++) {
                        if(que.peek() == prioryMax) {
                            // 최고치부터 출력 그 외는 순회
                            result++;
                            cy = -1; // 출력되면 다시 시작
                            if( find == 0 ) {
                                temp.append(result+"\n");
                                isGo = false;
                                break;
                            }
                            que.poll();
                        } else {
                            if( nextPriory <= que.peek()) nextPriory = que.peek(); // 그다음 최고치확인
                            if(find == 0) find = que.size();
                            que.add(que.poll()); // 순회
                        }
                        find--; // find 한칸 올리기
                    }
                    prioryMax = nextPriory; // 그 다음 최고치 제거
                }
            }
            System.out.println(temp);
        } catch (Exception e) {
            System.out.println(e);
        } 
    }
}
