import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/*
 * https://www.acmicpc.net/problem/5430
 * Deque 
 *
 */

public class Main {
    public static void main(String args[]){
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Scanner sc = new Scanner(System.in);
        StringBuilder resultString = new StringBuilder();
        Map<String, Integer> data = new HashMap<String, Integer>();
        data.put("A", 3);
        data.put("B", 3);
        data.put("C", 3);
        data.put("D", 4);
        data.put("E", 4);
        data.put("F", 4);
        data.put("G", 5);
        data.put("H", 5);
        data.put("I", 5);
        data.put("J", 6);
        data.put("K", 6);
        data.put("L", 6);
        data.put("M", 7);
        data.put("N", 7);
        data.put("O", 7);
        data.put("P", 8);
        data.put("Q", 8);
        data.put("R", 8);
        data.put("S", 8);
        data.put("T", 9);
        data.put("U", 9);
        data.put("V", 9);
        data.put("W", 10);
        data.put("X", 10);
        data.put("Y", 10);
        data.put("Z", 10);
        
        int result = 0;
        try {
            String[] firstLine = br.readLine().split("");
            for (int index = 0; index < firstLine.length; index++) {
                result += data.get(firstLine[index]);
            }
            System.out.println(result);
        } catch (Exception e) {
            System.out.println(e);
        } 
    }
}
