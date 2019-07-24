import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
public class Main{
    public static void main(String args[]) throws IOException{
        /*
         * https://www.acmicpc.net/problem/7453
         * 알고리즘 문제
         */
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int totalRow =  Integer.parseInt(br.readLine());
        ArrayList<Integer> firstIndex = new ArrayList<Integer>();
        ArrayList<Integer> secondIndex = new ArrayList<Integer>();
        ArrayList<Integer> thirdIndex = new ArrayList<Integer>();
        ArrayList<Integer> fourIndex = new ArrayList<Integer>();
        for( int index = 0; index < totalRow; index++ ) {
        	String [] inputData  = br.readLine().split(" ");
        	 firstIndex.add(Integer.parseInt(inputData[0]));
             secondIndex.add(Integer.parseInt(inputData[1]));
             thirdIndex.add(Integer.parseInt(inputData[2]));
             fourIndex.add(Integer.parseInt(inputData[3]));
        }
        int result=0;
        for(int first : firstIndex ) {
        	for(int second : secondIndex) {
        		for(int third : thirdIndex) {
        			for( int four : fourIndex ) {
        				if(first+second+third+four == 0) result++;
        			}
        		}
        	}
        }
        System.out.println(result);
        
    }
}
