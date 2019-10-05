package algorithm.dataStructure;
import java.io.IOException;
import java.util.Scanner;

//Index Tree 응용
/*             
              5 
     2                  3
   1        1         1          2
 1   0   0    1    0     1    1      1
(1   2   3    4    5     6    7      8) 
int[] tree = { 0, 5, 2, 3, 1, 1, 2, 1, 0, 0, 1, 0, 1, 1, 1};
5의 중간인 3의 위치에 있는 1을 구하고 
그 1의 마지막 노드의 index값이 mid가 되는것을 응용 3의 위치의 1은 index = 6 이기 때문에 중간값은 6을 응용

*/

public class Beak1572Mid {
    public static void main(String[] args) throws Exception {
        /*
         * https://www.acmicpc.net/problem/1572
         * 중앙값 
         * 앞뒤 빼고 넣기
         * https://7271kim.github.io/algorithm/java/2019/08/15/algorithm-5.html
         */
        Scanner sc = new Scanner(System.in);
        
        int N = sc.nextInt();
        int K = sc.nextInt();
        int findNumver = (K+1)/2;
        int result = 0;
        
        int[] lowData = new int[N+1];
        
        
        // 0 ~ 65536까지의 정수가 맨 바닥층
        IndexTreeOrignal tree = new IndexTreeOrignal(65537);
        
        for(int i = 1 ; i <= N ; i++) {
            int inputNumber = sc.nextInt();
            lowData[i] = inputNumber;
            if(i < K) {
                tree.update(inputNumber, 1);
                // k가 넘어가기 전까진 데이터를 트리에 넣기만 함.
            } else {
                
                //만약 K보다 크면  수는 바로 앞에 수를 제거해 주어야 한다.
                if(i > K) tree.update(lowData[i-K], -1);
                
                tree.update(inputNumber, 1);
                result += tree.search(findNumver);
            }
        }
        System.out.println(result);
        sc.close();     
    }
}
