package ok.beak;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;
/*
 * https://www.acmicpc.net/problem/1316
 * 종은 풀이
 */
public class Beak1316 {
    public static void main(String[] args) {
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
            int total = Integer.parseInt(br.readLine());
            
            int result = 0;
            for (int index = 0; index < total; index++) {
                String inputText = br.readLine();
                // 유니코드 응용
                // 대문자는 65 ~ 90
                // 소문자는 97 ~ 122까지
                // 26개
                int[] data = new int[26];
                Boolean isGroup    = true;
                for (int i = 1; i < inputText.length(); i++) {
                    // 해당 형식으로 하면 유니코드로 변환된 숫자가 나온다. 
                    int inputIndex  = inputText.charAt(i) - 97;
                    int beforeIndex = inputText.charAt(i-1) - 97;
                    if( data[inputIndex] == 1 && beforeIndex != inputIndex  ) {
                        isGroup = false;
                        break;
                    } else {
                        data[inputIndex] = 1;
                    }
                }
                if( isGroup ) result++;
            }
            System.out.println(result);
        } catch (Exception e) {
        }     
    }
}
