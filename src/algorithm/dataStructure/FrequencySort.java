package algorithm.dataStructure;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
public class FrequencySort{
    
    public static void main(String args[]) throws IOException{
        /*
         * https://www.acmicpc.net/problem/2910
         * 빈도정렬
         * HashMap의 Key를 Vaulue 기준으로 정렬하기
         * Key만 List에 담는 신박한 방법
         * 오브젝트의 특정 변수를 기준으로 정렬하는 Comparator
         * 먼저나온것 부터 정렬 , Double 연산 (BigDecimal)
         */
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] firstLow   = br.readLine().split(" ");
        String[] nextLow    = br.readLine().split(" ");
        Map<Integer, int[]> result          = new HashMap<Integer, int[]>();
        int n = Integer.parseInt(firstLow[0]);
        for( int index = 0 ; index < n; index++ ) {
            int number = Integer.parseInt(nextLow[index]);
            if( result.containsKey(number) ) {
                int[] temp = {result.get(number)[0]+1,result.get(number)[1]};
                result.replace(number, temp);
            } else {
                int[] temp = {1,index};
                result.put(number, temp);
            }
        }
        //Key만 List에 담기 
        List<Integer> keys = new ArrayList<Integer>(result.keySet());
        
        // 비교하는데 비교하는 값들은 Integer이다.
        // 해당 List들을 원하는 형식으로 정렬할 수 있음.
        //Comparator<Integer>() >>> Integer은 키값의 형
         Collections.sort(keys, new Comparator<Integer>() {
            @Override
            public int compare(Integer a, Integer b) {
                int[] tempA = result.get(a);
                int[] tempB = result.get(b);
                int compare = Integer.compare(tempB[0], tempA[0]); // 오름차순 정렬
                if( compare == 0 ) {
                    return Integer.compare(tempA[1], tempB[1]); // 내림차순 정렬
                } else {
                    return compare;
                }
            }
            
        });
        
        for( int item : keys ) {
            for( int index = 0; index < result.get(item)[0]; index++ )
                System.out.print(item + " ");
        }
    }
    
    /*
     [ 숫자 , 회수]
     [ 숫자 , 회수]
     [ 숫자 , 회수]
     .
     .
     .
     .
     >> 이 이중 배열을 정렬을 횟수로 표현한 남의 코드 
      >> 
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
    }*/
}
