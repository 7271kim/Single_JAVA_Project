package ok.beak;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Scanner;

/*
 * https://www.acmicpc.net/problem/1018
 * 하나하나 8*8 조각으로 잘라 몇개를 칠해야하는지 확인한다.
 * 64 - 결과는 반대로 칠할때
 * 준비 : [ N , N,...N ] >> char[][] oriData = new char[N][M]
 *  O(N^2*8*8) >> 50*50*8*8 >> 16만번 OK
 */

public class Beak1018 {
    public static void main(String args[]){
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Scanner sc = new Scanner(System.in);
        StringBuilder resultString = new StringBuilder();
        try {
            //String[] firstLine = br.readLine().split(" ");
            //int total   = Integer.parseInt(firstLine[0]);
            //int compare = Integer.parseInt(firstLine[1]);
            //String[] secondeLine = br.readLine().split(" ");
            //int total = Integer.parseInt(br.readLine());
            //int total = sc.nextInt();
            
            String[] firstLine = br.readLine().split(" ");
            int N = Integer.parseInt(firstLine[0]);
            int M = Integer.parseInt(firstLine[1]);
            char[][] oriData = new char[N][M];
            int min = 250;
            
            for (int index = 0; index < N; index++) {
                String line = br.readLine();
                for (int lineIndex = 0; lineIndex < line.length(); lineIndex++) {
                    oriData[index][lineIndex] = line.charAt(lineIndex);
                }
            }
            
            for (int index = 0; index <= N-8; index++) {
                for (int indexM = 0; indexM <= M-8; indexM++) {
                    int tempChile = 0;
                    int startInvers = 1; // 1: W 를 의미 , -1 : B를 의미
                    for (int startN = index; startN < index+8; startN++) {
                        for (int startM = indexM; startM < indexM+8; startM++) {
                            char thisData = oriData[startN][startM];
                            //WBWBWB로 칠할때의 가지수
                            if( startInvers == 1 && thisData=='B' ) {
                                tempChile++;
                            } else if(startInvers == -1 && thisData == 'W') {
                                tempChile++;
                            }
                            startInvers = startInvers == 1 ? -1 : 1; // 방향 플레그 꾸기
                        }
                        startInvers = startInvers == 1 ? -1 : 1; // 방향 플레그 꾸기
                    }
                    // BWBWBW로 칠할 때 가지수
                    int reverseCount = 64 - tempChile;
                    tempChile = reverseCount > tempChile ? tempChile : reverseCount; 
                    
                    //최소값 찾기 
                    min = min > tempChile ? tempChile : min;
                }
            }
            
            System.out.println(min);
            
            
        } catch (Exception e) {
            System.out.println(e);
        } 
    }
}


