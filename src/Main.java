import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.swing.JEditorPane;

/*
 * https://www.acmicpc.net/problem/2869
 *  
 */

public class Main {
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
            double up   = Integer.parseInt(firstLine[0]);
            double down    = Integer.parseInt(firstLine[1]);
            double height  = Integer.parseInt(firstLine[2]);
            int result  = (int)Math.ceil( ( height - down ) / ( up - down ) );
            System.out.println(result);
        } catch (Exception e) {
            System.out.println(e);
        } 
    }
}
