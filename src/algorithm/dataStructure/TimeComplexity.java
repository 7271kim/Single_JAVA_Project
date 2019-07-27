package algorithm.dataStructure;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
public class TimeComplexity{
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
        // 2진탐색으로 절대값이 0이되는것 찾기 Or 그냥 해쉬맵 데이터로 찾기
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
        //1. 2개의 그룹으로 묶기  HashMap< 숫자, 중복 갯수 > 로 담기
        Map<Integer, Integer> firstGroup = new HashMap<Integer, Integer>();
        Map<Integer, Integer> secondGroup = new HashMap<Integer, Integer>();
        
        for( int firstData = 0; firstData < totalRow; firstData++   ) {
            for( int secondData = 0; secondData < totalRow; secondData++ ) {
                int totalSumFirstSecond    = firstIndex.get(firstData) + secondIndex.get(secondData);
                int totalSumThirdFour    = thirdIndex.get(firstData) +  fourIndex.get(secondData);
                
                if( firstGroup.containsKey( totalSumFirstSecond ) ) {
                    firstGroup.replace(totalSumFirstSecond, firstGroup.get(totalSumFirstSecond)+1);
                } else {
                    firstGroup.put(totalSumFirstSecond, 1);
                }
                if( secondGroup.containsKey( totalSumThirdFour ) ) {
                    secondGroup.replace(totalSumThirdFour, secondGroup.get(totalSumThirdFour)+1);
                }else {
                    secondGroup.put(totalSumThirdFour, 1);
                }
            }
        }
        
        // 찾기 
        int sizeFisrt   = firstGroup.size();
        int sizeSecond  = secondGroup.size();
        int result      = 0;
        Map<Integer, Integer> loop;
        Map<Integer, Integer> seek;
        if( sizeFisrt < sizeSecond ) {
            loop = firstGroup;
            seek = secondGroup;
        } else {
            loop = secondGroup;
            seek = firstGroup;
        }
        System.out.println(firstGroup);
        System.out.println(secondGroup);
        
        for( int key :  loop.keySet()) {
            int temt = -(key);
            if(seek.containsKey(temt)) {
                result += (loop.get(key) *seek.get(temt) );
            }
        }
        System.out.println(result);
        
        /*
        // 제일 보기 깔끔했던 코드
        public static void main(String[] args) {
            Scanner sc = new Scanner(System.in);
            int n = sc.nextInt();
            int[][] arr = new int[n][4];
            long answer = 0;
            Map<Integer,Integer> map = new HashMap<Integer,Integer>();
            for(int i=0;i<n;i++)
                for(int j=0;j<4;j++)
                    arr[i][j] = sc.nextInt();
            
            for(int i=0;i<n;i++)
                for(int j=0;j<n;j++) {
                    int tmp = arr[i][0] + arr[j][1];
                    if(map.containsKey(tmp))
                        map.replace(tmp, map.get(tmp)+1);
                    else
                        map.put(tmp, 1);
                }
            for(int i=0;i<n;i++)
                for(int j=0;j<n;j++) {
                    int tmp = (arr[i][2]+arr[j][3])*-1;
                    if(map.containsKey(tmp)) //AB + CD == 0?
                        answer += map.get(tmp);
                }
            System.out.println(answer);
        }
        */
    }
}
