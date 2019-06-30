package algorithm.mid;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
public class Main{
	public static void main(String args[]){
		/*
		 * https://www.acmicpc.net/problem/10817
		 * 알고리즘 문제
		 * http://localhost:4000/algorithm/java/2019/06/30/algorithm-3.html
		 * (헤석)
		 */
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			String [] arr = br.readLine().split(" ");
			int [] result = {Integer.parseInt(arr[0]),Integer.parseInt(arr[1]),Integer.parseInt(arr[2])};
			Arrays.sort(result);
			System.out.println(result[1]);
		} catch (Exception e) {
		}
		
	}
}