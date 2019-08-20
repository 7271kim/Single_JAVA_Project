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
        Map<String, Integer> total = new HashMap<String, Integer>();
        try {
            int totalNumber = Integer.parseInt(br.readLine());
            Map<String, Integer> count = new HashMap<String, Integer>();
            for (int index = 0; index < totalNumber; index++) {
                int nextNumber = Integer.parseInt(br.readLine());
                for (int indexInner = 0; indexInner < nextNumber; indexInner++) {
                    String[] items = br.readLine().split(" ");
                    String item    = items[0];
                    String group   = items[1];
                    System.out.println(item);
                    System.out.println(group);
                    System.out.println(total.containsKey(item));
                    if( total.containsKey(item) ) {
                        count.put(group, count.get(group)+1);
                    } else {
                        total.put(item, 1);
                        count.put(group, 1);
                    }
                }
                int result = 1;
                for (String group : count.keySet()) {
                    result *= (count.get(group)+1);
                }
                System.out.println(result-1);
            }
        } catch (Exception e) {
        } 
        
    }
}
    
