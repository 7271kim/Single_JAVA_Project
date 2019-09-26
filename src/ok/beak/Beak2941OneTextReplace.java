package ok.beak;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/*
 * https://www.acmicpc.net/problem/2941
 * Deque 
 *
 */

public class Beak2941OneTextReplace {
    public static void main(String args[]){
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Scanner sc = new Scanner(System.in);
        StringBuilder resultString = new StringBuilder();
        String[] data = {"c=", "c-", "dz=", "d-", "lj", "nj", "s=", "z="};
        try {
            //String[] firstLine = br.readLine().split(" ");
            //int total   = Integer.parseInt(firstLine[0]);
            //int compare = Integer.parseInt(firstLine[1]);
            //String[] secondeLine = br.readLine().split(" ");
            //int total = Integer.parseInt(br.readLine());
            //int total = sc.nextInt();
            //String result = "mixed";
            
            String input = br.readLine().replaceAll(" ", "");
            int result = 0;
            for (int index = 0; index < data.length; index++) {
                while(input.indexOf(data[index]) > -1) {
                    result++;
                    input = input.replaceFirst(data[index], "0000");
                }
            }
            input = input.replaceAll("0000", "");
            result += input.length();
            
            System.out.println(result);
        } catch (Exception e) {
            System.out.println(e);
        } 
    }
}
