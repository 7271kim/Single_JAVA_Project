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
   int[] data;
   int reverseIndex = 0;
   int direction = 0; // 0은 오른쪽 , 1은 왼쪽
    
    public AC ( String[] arrayNumber ) {
        if(!arrayNumber[0].equals("[]")) {
            data = new int [arrayNumber.length];
        } else {
            data = new int[0];
        }
        for (int index = 0; index < arrayNumber.length; index++) {
            String num  = arrayNumber[index].replaceAll("\\[", "").replaceAll("\\]", "");
            if(!num.equals("")) {
                data[index] = Integer.parseInt(num);
            }
        }
    }
    
    public void reverse() {
        if(data.length !=0 ) {
            if( direction == 0 ) {
                if(--reverseIndex < 0 ) {
                    reverseIndex = data.length-1;
                    direction = direction == 0 ? 1 : 0;
                }
                
            } else {
                if(++reverseIndex >= data.length ) {
                    reverseIndex = 0;
                    direction = direction == 0 ? 1 : 0;
                }
            }
        }
    }
    
    public Boolean destroy() {
       Boolean result = true;
       if( data.length != 0 && data[reverseIndex] != -1 ) {
           data[reverseIndex] = -1;
           findStart();
       } else {
           result = false;
       }
       return result;
    }
    
    public void findStart() {
        int total = 0;
        while(data[reverseIndex] == -1 && total < data.length) {
            if( direction == 0 ) {
                if(++reverseIndex >= data.length ) {
                    reverseIndex = 0;
                }
            } else {
                if(--reverseIndex < 0 ) {
                    reverseIndex = data.length-1;
                }
            }
            total++;
        }
    }
    
    public void print() {
        StringBuilder result = new StringBuilder();
        result.append("[");
        int dataLength = data.length;
        if( data.length != 0 ) {
            result.append(data[reverseIndex]);
            result.append(",");
            for (int index = 1; index < data.length; index++) {
                if( direction == 0 ) {
                    if(++reverseIndex >= data.length ) {
                        reverseIndex = 0;
                    }
                    if( data[reverseIndex] != -1 ) {
                        result.append( data[reverseIndex]);
                        result.append(",");
                    }
                } else {
                    if(--reverseIndex < 0 ) {
                        reverseIndex = data.length-1;
                    }
                    if( data[reverseIndex] != -1 ) {
                        result.append( data[reverseIndex]);
                        result.append(",");
                    }
                }
            }
        }
        int lastIndex = result.lastIndexOf(",");
        result.replace(lastIndex, lastIndex+1, "");
        result.append("]");
        System.out.println(result);
    }
}