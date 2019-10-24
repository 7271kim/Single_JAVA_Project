package ok.beak;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

/*
 * https://www.acmicpc.net/problem/10989
 * input 개수가 1000만개가 넘어가 메모리 초과가 발생 
 * 발생의 전환 >> N의 범위가 10000이므로 크기가 10000인 배열은 준비하고 정렬 되었다고 가정하고 count를 업해줌  
 * intput방식을 Scanner로 할 시 시간초과 발생
 */

public class Beak10989Sort3Special {
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
            int total = Integer.parseInt(br.readLine());
            int[] input =new int[10001];
            
            for (int index = 0; index < total; index++) {
                int number = Integer.parseInt(br.readLine());
                input[number]++;
            }
            
            for (int index = 0; index < input.length; index++) {
                int count = input[index];
                if( count != 0 ) {
                    for (int indexInner = 0; indexInner < count; indexInner++) {
                        resultString.append(index+"\n");
                    }
                }
            }
           System.out.println(resultString.toString());
            
        } catch (Exception e) {
            System.out.println(e);
        } 
    }
}