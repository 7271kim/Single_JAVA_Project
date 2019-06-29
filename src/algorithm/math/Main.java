package algorithm.math;


import java.util.*;
public class Main{
	public static void main(String args[]){
		/*
		 * https://www.acmicpc.net/problem/2588
		 * 알고리즘 문제
		 * https://7271kim.github.io/algorithm/2019/06/29/algorithm.html
		 * (헤석)
		 */
		
		Scanner sc = new Scanner(System.in);
		int a,b,c;
		a = sc.nextInt();
		b = sc.nextInt();
		System.out.println(a*(b%10));
		System.out.println(a*(b%100/10));
		System.out.println(a*(b%1000/100));
		System.out.println(a*b);
	}
}

