import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		int n=sc.nextInt();
		int[][] arr = new int[n][2];
		int scale =sc.nextInt();
		
		for(int i=0;i<n;i++) {
			arr[i][1]=1;
		}
		
		for(int i=0;i<n;i++) {
			arr[i][0]=sc.nextInt();
			for(int k=0;k<i;k++) {
				if(arr[k][0]==arr[i][0]) {
					arr[k][1]++;
					arr[i][0]=0;
				}
			}
		}
		String tmep = "";
		for( int index = 0; index< arr.length; index++ ) {
		    tmep +="[";
		    for( int index2 = 0; index2 < arr[index].length; index2++)
		        tmep = tmep +" [" +arr[index][index2] + "] ";
		    tmep +="]\n";
		}
		System.out.println(tmep);
		//빈도순으로 내림차순정렬
		for(int k=0;k<n;k++) {
			for(int i=0;i<n-1;i++) {
				if(arr[i][1]<arr[i+1][1]) {
					int temp1=arr[i][1];
					int temp2=arr[i][0];
					arr[i][1]=arr[i+1][1];
					arr[i][0]=arr[i+1][0];
					arr[i+1][1]=temp1;
					arr[i+1][0]=temp2;
				}
			}
		}
		
		for(int i=0;i<n;i++) {
			for(int k=0;k<arr[i][1];k++) {
				if(arr[i][0]!=0) {
					System.out.print(arr[i][0]+" ");
				}
				
			}
			
		}
	}

}

