import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String args[]){
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Map<String, String> total = new HashMap<String, String>();
        Map<String, String> count = new HashMap<String, String>();
        try {
            int totalNumber = Integer.parseInt(br.readLine());
            for (int index = 0; index < totalNumber; index++) {
                int nextNumber = Integer.parseInt(br.readLine());
                for (int indexInner = 0; index < nextNumber; indexInner++) {
                    String[] items = br.readLine().split(" ");
                    String item    = items[0];
                    String group   = items[1];
                    if( total.containsKey(items[0]) ) {
                        
                    }
                }
            }
        } catch (Exception e) {
        } 
        
    }
}
    
