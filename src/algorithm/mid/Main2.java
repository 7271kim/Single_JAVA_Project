package algorithm.mid;


import java.util.Scanner;
public class Main2{
	public static void main(String args[]){
		Scanner sc = new Scanner(System.in);
		int a, b, c;
		double d;
		int result;
		a = sc.nextInt();
		b = sc.nextInt();
		c = sc.nextInt();
		if( a < b ) {
			if( b < c) {
				result= b;
			} else {
				if( a < c ) {
					result = c;
				} else {
					result = a;
				}
			}
		} else {
			if( a < c ) {
				result= a;
			} else {
				if( c < b ) {
					result = b;
				} else {
					result = c;
				}
			}
		}
		System.out.println(result);
	}
}