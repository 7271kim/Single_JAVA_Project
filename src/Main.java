import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
public class Main{
    public static void main(String args[]) throws IOException{
        /*
         * https://www.acmicpc.net/problem/7453
         * 알고리즘 문제
         * 전체 탐색 및 시간 복잡도 계산 및 회귀 
         */
        /*
          1. 시간 복잡도 계산
           - O(1) : 입력되는 N과 상관없이 일정한 시행시간을 가진다.
           - O(logN) : 자료가 증가함에 따라 수렴 , 2진 검색
           - O(N) : 정비례 , 선형
           - O(N^2) : 따따블  - for문에 for문 
           - O(NlogN) : N배가 많아지면 N배보다 조금 증가 , 커다란 문제를 독립문제로 쪼개고 다시 하나로 모으는 경우
         */
        /*
             내가 못하는 것
             아래로직 회귀함수로 만들기..
        int result=0;
        for(int first : total ) {
            for(int second : total) {
                for(int third : total) {
                    for( int four : total ) {
                        if(first+second+third+four == 0) result++;
                    }
                }
            }
        }
        
        
        1. return 정하기
        : int result
        
        2. 반복되는 로직 => 함수화
        for문 +  first+second+third+four+.....
        
        
        public static void getResult( ArrayList<Integer> total, int result ){
             for( int data : total ) {
                first+second+third+four....
             }
             
             return result;
        }
        
        public static void getResult( ArrayList<Integer> total, int result, int parent ){
             for( int data : total ) {
                 int tempParent = parent + data;
                return getResult( total, tempParent, result, tempParent );
             }
             return result;
        }
        
        3. 종료조건 및 최종 데이터 확인
        public static void getResult( ArrayList<Integer> total, int result, int parent, int index ){
            if( index == 4 && parent == 0 ) result++;
            if( index == 4 ) return result;
            
            index++;
            
             for( int data : total ) {
                 int tempParent = parent + data;
                return getResult( total, tempParent, result, tempParent, index );
             }
             return result;
        }
        
         */ 
        
        /// 문제풀이!!!! 
        // 만약 진짜 전체를 하나하나 돌경우 O(N^4)되어 최대의 경우 : 256,000,000,000,000‬ 해까지 가는 말도 안되는 상황.
        // 방식을 바꿔야 한다! 
        // 합이 0인 네 정수!
        // 합치기기법
        // A와 B의 합을 1개의 그룹으로 , C와 D의 합을 1개의 그룹으로 HashMap< 숫자, 중복 갯수 > 로 담기
        // 2진탐색으로 절대값이 0이되는것 찾기
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int totalRow =  Integer.parseInt(br.readLine());
        ArrayList<Integer> firstIndex = new ArrayList<Integer>();
        ArrayList<Integer> secondIndex = new ArrayList<Integer>();
        ArrayList<Integer> thirdIndex = new ArrayList<Integer>();
        ArrayList<Integer> fourIndex = new ArrayList<Integer>();
        for( int index = 0; index < totalRow; index++ ) {
        	String [] inputData  = br.readLine().split(" ");
        	 firstIndex.add(Integer.parseInt(inputData[0]));
             secondIndex.add(Integer.parseInt(inputData[1]));
             thirdIndex.add(Integer.parseInt(inputData[2]));
             fourIndex.add(Integer.parseInt(inputData[3]));
        }
        int result=0;
        for(int first : firstIndex ) {
        	for(int second : secondIndex) {
        		for(int third : thirdIndex) {
        			for( int four : fourIndex ) {
        				if(first+second+third+four == 0) result++;
        			}
        		}
        	}
        }
        System.out.println(result);
        
    }
}
