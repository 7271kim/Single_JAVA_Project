package algorithm.math;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Permutation {
    private int[][] returnArr;
    private int totalCount;
    public Permutation( int totalSize, int r ) {
        this.returnArr = new int[totalSize][r];
        totalSize = 0;
    }
    
    /* https://bcp0109.tistory.com/14
     * 순열  n 개의 값 중에서 r 개의 숫자를 모든 순서대로 뽑는 경우
     * Ex) [1, 2, 3] 배열에서 2개의 숫자를 뽑는 경우
     * 1. Swap을 이용 >> 함수를 만들어 배열의 값을 직접 변경 >> 변경된 데이터들에서 원하는 값을 추출한다.
     * >> Dept를 기준 인덱스로 하고 Dept보다 작은 값을을 고정 후 큰값들에 대해 다시 Swap을 진행한다.
     * 
     * 사용 예시: permutation(int[] 원본배열, int depth, int n, int r);
     * 
     * int[] input = { 1, 2, 3 };
     * int total = 3*2*1;
     * int r = 2; // 2개 뽑음
     * 
     * Permutation pe = new  Permutation( total, r );
     * pe.permutation( input, 0, input.length, r );
     * 
     * int[][] result = pe.getReturnArr();
     * Swap의 문제는 깔끔한 순서가 보장되지 않음
     *  
     */
    
    public void permutationSwap( int[] input, int depth, int n, int r ) {
        if(depth == r) {
            // 전체 값들 하나하나 담기 , returnArr이 최종 반환값
            // 싫으면 여기서 뭔가 해주기
            int[] temp = new int[r];
            for (int index = 0; index < r; index++) {
                temp[index] = input[index];
            }
            returnArr[totalCount++] = temp;
        }
        // depth부터 마지막까지 반복
        for(int i=depth; i<n; i++) {
            //Swap로직
            swap(input, depth, i);
            permutationSwap(input, depth + 1, n, r);
            // 원상 복구 로직
            swap(input, depth, i);
        }
    }
    
    public void swap(int[] input, int before, int after) {
        // before , after 값 변경 
        int temp = input[before];
        input[before] = input[after];
        input[after] = temp;
    }
    
    public int[][] getReturnArr() {
        return returnArr;
    }
    
    
    /* https://bcp0109.tistory.com/14
     * 사전식 출력을 위한 visited 배열 이용
     * input배열에서 순서대로 돌면서 방문하지 않은 갑들을 out풋에 넣어주면서 완성
     * 
     * int[] input = { 1, 2, 3 };
     * int total = 3*2*1;
     * int r = 2; // 2개 뽑음
     * 
     * Permutation pe = new  Permutation( total,input.length, r );
     * pe.permutation( input, 0, input.length, r );
     * 
     * int[][] result = pe.getReturnArr();
     * 
     */
    
    boolean[] visited;
    int[] output;
    
    public Permutation( int totalSize, int n, int r ) {
        this.returnArr = new int[totalSize][r];
        this.visited   = new boolean[n];
        this.output    = new int[n];
        totalSize = 0;
    }
    
    
    // output은 들어가면서 출력을 위한 값
    public void permutationDictionary(int[] input, int depth, int n, int r) {
        if(depth == r) {
            // 전체 값들 하나하나 담기 , returnArr을 최종 반환으로 목표한다.
            int[] temp = new int[r];
            for (int index = 0; index < r; index++) {
                temp[index] = output[index];
            }
            returnArr[totalCount++] = temp;
        }
        for(int i=0; i<n; i++) {
            if(visited[i] != true) {
                visited[i] = true; // 들렸다고 표시
                output[depth] = input[i]; // 값 넣어주기
                permutationDictionary(input, depth + 1, n, r);       
                visited[i] = false;; // 원상복구
                output[depth] = 0;   // 보기 쉽게 원상 복구 
            }
        }
    }
    
    public int totalSize( int input ) {
        if( input == 1 ) return 1;
        
        return input*totalSize(input-1);
    }
}
