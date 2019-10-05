package algorithm.dataStructure;
import java.io.IOException;
import java.util.Scanner;

//Index Tree 응용
// 
public class Beak1572Mid {
    static int N, K, Mid, Len;
    static long SUM;    
    static int Tree[], Data[];
    static void update(int i, int n)
    {
        int idx = Len + n; // low데이터 시작위치 확인 : index자체를 값으로 응용하기 위해 들어온 수 만큼 + n 시킨 위치의 값을 1로 변경
        
        while(idx > 0)
        {
            Tree[idx] += i; // 해당 설계를 가지고 인덱스 트리 만들기
            idx /= 2;
        }
    }
    static int search(int k)
    {
        int i = 1;
        int l = 0;
        while(i < (1<<16))
        {
            //tree 루트인 index 1부터 검색 (맨 처음에는 K갯수 만큼 저장되어있을 것임 >> 이것을 mid인 (K+1)/2 위치의 숫자를 찾아가는 과정)
            l = Tree[i*2]; 
            // 자식노드 확인 자식노드에 저장된 갯수 확인
            
            // 자식노드에 저장된 수가  ( ex) 5 ) 중간값 (ex ) 2 )보다 크거나 같다면 계속 자식 탐색
            if(k <= l)
            {
                i = i * 2; // 자식 노드의 왼쪽 값
            }
            else
            {
                // 작아버리면  ex) k = 2 , ㅣ = 1
                // 마지막값을 찾기위해  k를 줄이고
                k = k - l;
                // 자식의 오른쪽 노드를 탐색한다.
                i = i * 2 + 1; //자식 노드의 오른쪽 값
            }
        }

        // 최종 index의 위치인 i에서 시작점을 빼면 중간 값을 추출할 수 있음
        return i - Len;
    }
    public static void main(String[] args) throws NumberFormatException, IOException {
        /*
         * https://www.acmicpc.net/problem/1572
         * 중앙값 
         * 앞뒤 빼고 넣기
         * https://matice.tistory.com/54
         */
        Scanner sc = new Scanner(System.in);
        
        N = sc.nextInt();
        K = sc.nextInt();
        Mid = (K+1)/2;
        Len = 1<<16; // 트리의 로우데이터 시작위치는 N의 최대크기인 2의16승 = 65536
        Tree = new int[2*Len+1];
        Data = new int[N+1];
        for(int i = 1 ; i <= N ; i++)
        {
            Data[i] = sc.nextInt();
            if(i < K)
            {
                update(1, Data[i]);
                // k가 넘어가기 전까진 데이터를 트리에 넣기만 함.
            }
            else 
            {
                if(i > K)
                {
                    update(-1, Data[i - K]);
                    // 만약 K가 넘어가는 수는 바로 앞에 수를 제거해 주어야 한다.
                    
                }               
                update(1, Data[i]);
                SUM += search(Mid);
                // 중앙값 찾기
            }
        }
        System.out.println(SUM);
        sc.close();     
    }
}
