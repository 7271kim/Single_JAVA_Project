package algorithm.dataStructure;
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

public class AC5430 {
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
   int direction    = 0; // 0은 오른쪽 , 1은 왼쪽
   int left         = -1; // 왼쪽 시작점
   int right        = -1; // 오른쪽 시작점
    
    public AC ( String[] arrayNumber ) {
        if(!arrayNumber[0].equals("[]")) {
            data    = new int [arrayNumber.length];
            left    = 0;
            right   = data.length-1;
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
        direction = direction == 0 ? 1 : 0;
        findStart();
    }
    
    public Boolean destroy() {
       Boolean result = left != -1 && data[reverseIndex] != -1;
       if( result ) {
           data[reverseIndex] = -1;
           if(left != right) {
               if(direction == 0) {
                   left++;
               } else {
                   right--;
               }
           }
           findStart();
       } 
       return result;
    }
    
    public void findStart() {
        if( direction == 0 ) {
            if(++reverseIndex > right ) {
                reverseIndex = left;
            }
        } else {
            if(--reverseIndex < left ) {
                reverseIndex = right;
            }
        }
    }
    
    public void print() {
        StringBuilder result = new StringBuilder();
        result.append("[");
        if( left != -1 && data[reverseIndex] != -1 ) {
            if( direction == 0 ) {
                for (int index = left; index <= right; index++) {
                    result.append(data[index]);
                    if(index != right) result.append(",");
                }
            } else {
                for (int index = right; index >= left; index--) {
                    result.append(data[index]);
                    if(index != left) result.append(",");
                }
            }
        }
        result.append("]");
        System.out.println(result);
    }
}