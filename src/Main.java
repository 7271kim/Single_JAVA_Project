import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class Main {
    public static void main(String args[]){
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            String[] input   = br.readLine().split(" ");
            ArrayList<Integer> sorted = new ArrayList<Integer>();
            for (int index = 0; index < input.length; index++) {
                sorted.add(Integer.parseInt(input[index]));
            }
            Collections.sort( sorted );
            System.out.println(sorted.get( (sorted.size())/2));
        } catch (Exception e) {
        } 
        
    }
}
    
