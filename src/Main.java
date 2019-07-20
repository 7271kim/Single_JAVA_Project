import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
public class Main{
    public static void main(String args[]){
        /*
         * https://www.acmicpc.net/problem/7785
         * 알고리즘 문제
         */
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Map<String, String> employName    = new HashMap<String, String>();
        ArrayList<String> emmployNameSort; 
        try {
            int totalInput = Integer.parseInt( br.readLine() );
            for ( int index = 0; index < totalInput; index++ ) {
                String [] inputData        = br.readLine().split(" ");
                String name = inputData[0];
                String recode = inputData[1];
                if(recode.equals("leave") && employName.get(name).equals("enter")) {
                    employName.remove(name);
                } else if(recode.equals("enter") ) {
                    employName.put( name, recode);
                }
            }
            
            
            /* 
                     내 방법인데 
             Iterator iterator = employName.keySet().iterator(); 
             while(iterator.hasNext()){ 
                  String name = (String) iterator.next(); 
                  emmployNameSort.add(name); 
             }
                         아래가 더 좋은 방법
             */
             
            emmployNameSort = new ArrayList<String>(employName.keySet());
            Collections.sort(emmployNameSort,Collections.reverseOrder());
            for( String name : emmployNameSort ) {
                System.out.println(name);
            }
        } catch (Exception e) {
        } 
        
    }
}
