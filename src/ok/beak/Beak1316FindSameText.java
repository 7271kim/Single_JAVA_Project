package ok.beak;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/*
 * https://www.acmicpc.net/problem/1316
 * Deque 
 *
 */

public class Beak1316FindSameText {
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
            
            int total = Integer.parseInt(br.readLine());
            int result = 0;
            for (int index = 0; index < total; index++) {
                Map<String, Integer> data = new HashMap<String, Integer>();
                String[] temp = br.readLine().split("");
                data.put(temp[0], 1);
                Boolean isCount = true;
                for (int indexInner = 1; indexInner < temp.length; indexInner++) {
                    if( data.containsKey(temp[indexInner]) && !temp[indexInner-1].equals(temp[indexInner]) ) {
                        isCount = false;
                        break;
                    } else {
                        data.put(temp[indexInner], 1);
                    }
                }
                if(isCount) result++;
            }
            System.out.println(result);
        } catch (Exception e) {
            System.out.println(e);
        } 
    }
}
