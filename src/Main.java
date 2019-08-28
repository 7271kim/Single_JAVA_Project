import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

/*
 * https://www.acmicpc.net/problem/5430
 * Deque 
 *
 */

public class Main {
    public static void main(String args[]){
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            //String[] firstLine = br.readLine().split(" ");
            //String[] secondLine = br.readLine().split(" ");
            //int total = Integer.parseInt(firstLine[0]);
            int total = Integer.parseInt(br.readLine());
            for (int totalIndex = 0; totalIndex < total; totalIndex++) {
                String[] order = br.readLine().split("");
                int secondLine = Integer.parseInt(br.readLine());
                String[] arrayNumber = br.readLine().split(",");
                AC acData = new AC(arrayNumber);
                Boolean result = true;
                if( order.length + secondLine <= 700000 ) {
                    for (int orderIndex = 0; orderIndex < order.length; orderIndex++) {
                        if( order[orderIndex].indexOf("R") > -1 ) {
                            acData.reverse();
                        } else {
                            if(!acData.destroy()) {
                                result = false;
                                break;
                            }
                            
                        }
                    }
                } else {
                    result = false;
                }
                if(result) {
                    acData.print();
                } else {
                    System.out.println("error");
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        } 
    }
}

class AC {
    Deque<Integer> data = new LinkedList<Integer>();
    
    public AC ( String[] arrayNumber ) {
        for (int index = 0; index < arrayNumber.length; index++) {
            String num  = arrayNumber[index].replaceAll("\\[", "").replaceAll("\\]", "");
            if(!num.equals("")) {
                data.add(Integer.parseInt(num));
            }
        }
    }
    
    public void reverse() {
        Deque<Integer> temp = new LinkedList<Integer>();
        for (int index = data.size()-1; index >= 0; index--) {
            temp.addLast(data.pollLast());
        }
        data = temp;
    }
    
    public Boolean destroy() {
       Boolean result = true;
       if(!data.isEmpty()) {
           data.pollFirst(); 
       } else {
           result = false;
       }
       return result;
    }
    
    public void print() {
        StringBuilder result = new StringBuilder();
        result.append("[");
        int totalSize = data.size();
        for (int index = 0; index < totalSize; index++) {
            result.append(data.pollFirst());
            if( data.size() != 0 ) {
                result.append( ",");
            } else {
                result.append( "]");
            }
        }
        System.out.println(result);
    }
}