package ok.beak;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/*
 * https://www.acmicpc.net/problem/1085
 */

public class Beak1085 {
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
            
            //int total   = sc.nextInt();
            
            String[] firstLine = br.readLine().split(" ");
            int x = Integer.parseInt(firstLine[0]);
            int y = Integer.parseInt(firstLine[1]);
            int w = Integer.parseInt(firstLine[2]);
            int h = Integer.parseInt(firstLine[3]);
            int min = 1001;
            int comParX = w - x < x ? w - x : x;
            int comParY = h - y < y ? h - y : y;
            
            int compar = comParX < comParY ? comParX : comParY;
            min = min > compar ? compar : min;
            
            System.out.println(min);
        } catch (Exception e) {
            System.out.println(e);
        } 
    }
}
