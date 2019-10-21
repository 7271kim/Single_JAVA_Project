package ok.beak;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

/*
 * https://www.acmicpc.net/problem/2798
 * O(N^3)총연산 100만번 1초 이내 간단 코드로 가능
 * 
 * 방법
 * 3개의 숫자를 뽑는다 
 * 연산 중 21을 넘어가면 다음 연산으로
 * 3개의 숫자의 합이 21 근사치인 경우 변경
 * 각각의 for문은 하위 for문과 연결되면 안된다. 독립적이여야 함.call of reference가 일어나면 안됨
 * 
 */

public class Beak2798 {
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
            //String result = "mixed";
            String[] firstLine = br.readLine().split(" ");
            String[] nextLine  = br.readLine().split(" ");
            int total   = Integer.parseInt(firstLine[0]);
            int find    = Integer.parseInt(firstLine[1]);
            int result  = 0; 
            int[] input = new int[total];
            
            for (int index = 0; index < nextLine.length; index++) {
                input[index] = Integer.parseInt(nextLine[index]);
            }
            outLoop : for (int index = 0; index < input.length; index++) {
                int temp = input[index];
                if( temp > find ) continue outLoop;
                
                outLoop2 : for (int index2 = index+1; index2 < input.length; index2++) {
                    int temp2 = temp + input[index2];
                    if( temp2 > find ) continue outLoop2;
                    
                    outLoop3 : for (int index3 = index2+1; index3 < input.length; index3++) {
                        int temp3 = temp2 + input[index3];
                        if( temp3 > find ) continue outLoop3;
                        result = result > temp3 ? result : temp3;
                    }
                }
            }
            System.out.println(result);
        } catch (Exception e) {
            System.out.println(e);
        } 
    }
}
