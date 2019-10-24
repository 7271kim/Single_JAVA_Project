package ok.beak;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Scanner;

/*
 * https://www.acmicpc.net/problem/2750
 * 단순 정렬 , 1000개니 O(N^2)이용해도 상관없음   
 */

public class Beak2750Sort1 {
    public static void main(String args[]){
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Scanner sc = new Scanner(System.in);
        StringBuilder resultString = new StringBuilder();
        try {
            //String[] firstLine = br.readLine().split(" ");
            //int total   = Integer.parseInt(firstLine[0]);
            //int compare = Integer.parseInt(firstLine[1]);
            //String[] secondeLine = br.readLine().split(" ");
            //int total = Integer.parseInt(br.readLine());
            //int total = sc.nextInt();
            int total = sc.nextInt();
            ArrayList<Integer> data = new ArrayList<>();
            for (int index = 0; index < total; index++) {
                int number = sc.nextInt();
                data.add(number);
            }
            Collections.sort(data);
            for (Integer number : data) {
                System.out.println(number);
            }
            
        } catch (Exception e) {
            System.out.println(e);
        } 
    }
}

