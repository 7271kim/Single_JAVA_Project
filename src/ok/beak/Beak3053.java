package ok.beak;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/*
 * https://www.acmicpc.net/problem/3053
 */

public class Beak3053 {
    public static void main(String args[]){
        //BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
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
            
            DecimalFormat df = new DecimalFormat("0.000000");
            int input   = sc.nextInt();
            double pi = Math.PI;
            
            System.out.println(df.format(input*input*pi));
            System.out.println(df.format(input*input*2));

        } catch (Exception e) {
            System.out.println(e);
        } 
    }
}
