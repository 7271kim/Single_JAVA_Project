package ok.beak;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

/*
 * https://www.acmicpc.net/problem/5430
 * Deque 
 *
 */

public class Beak11729HanowiTop2 {
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
            
            int total = sc.nextInt();
            System.out.println( ( 1 << total ) -1);
            System.out.println(showHawoi( total, 1,2,3 ));
            
        } catch (Exception e) {
            System.out.println(e);
        } 
    }
    
    public static StringBuilder showHawoi( int number, int first, int middle, int last ) {
        StringBuilder result = new StringBuilder();
        if( number == 1 ) {
            result.append(first + " " + last + "\n");
            return result;
        }
        --number;
        result.append(showHawoi( number, first, last, middle ));
        result.append(first + " " + last  + "\n");
        result.append(showHawoi( number, middle, first, last ));
        return result;
    }
    
}
