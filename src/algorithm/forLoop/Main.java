package algorithm.forLoop;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
public class Main{
    public static void main(String args[]){
        /*
         * https://www.acmicpc.net/problem/2408
         * 알고리즘 문제
         * hasMap loop도는 간편한 방법
         */
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            int totalExam = Integer.parseInt( br.readLine() );
            for ( int index = 0; index < totalExam; index++ ) {
                int inputExam = Integer.parseInt( br.readLine() );
                Map<String, ArrayList<String>> totalCase    = new HashMap<String, ArrayList<String>>();
                int result = 1;
                
                for ( int indexExam = 0; indexExam < inputExam; indexExam++ ) {
                    String [] inputData        = br.readLine().split(" ");
                    String name = inputData[0];
                    String group = inputData[1];
                    ArrayList<String> getValue = totalCase.get(group) != null ? totalCase.get(group) : new ArrayList<String>();
                    getValue.add(name);
                    totalCase.put(group, getValue);
                }
                
                for( String key : totalCase.keySet() ){
                    result *= ( totalCase.get(key).size() +1);
                }
                System.out.println(result - 1);
            }
            
           
        } catch (Exception e) {
        } 
        
    }
}
