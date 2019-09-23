package ok.beak;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/*
 * https://www.acmicpc.net/problem/10250
 */

public class Beak10250Math {
    public static void main(String args[]){
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Scanner sc = new Scanner(System.in);
        //StringBuilder resultString = new StringBuilder();
        try {
            //String[] firstLine = br.readLine().split(" ");
            //int total   = Integer.parseInt(firstLine[0]);
            //int compare = Integer.parseInt(firstLine[1]);
            //String[] secondeLine = br.readLine().split(" ");
            //int total = Integer.parseInt(br.readLine());
            //int total = sc.nextInt();
            //String result = "mixed";
            
            int total = Integer.parseInt(br.readLine());
            for (int index = 0; index < total; index++) {
                StringBuilder resultString = new StringBuilder();
                String[] line = br.readLine().split(" ");
                double height  = Integer.parseInt(line[0]);
                double wide    = Integer.parseInt(line[1]);
                double find    = Integer.parseInt(line[2]);
                int floor      = (int)(find%height);
                int ho         = (int)(Math.ceil(find/height));
                
                if( floor == 0 ) {
                    resultString.append((int)height);
                } else {
                    resultString.append(floor);
                }
                if( ho/10 > 0 ) {
                    resultString.append(ho);
                } else {
                    resultString.append("0");
                    resultString.append(ho);
                }
                System.out.println(resultString);
            }
            
        } catch (Exception e) {
            System.out.println(e);
        } 
    }
}
