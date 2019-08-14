import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) throws NumberFormatException, IOException {
        /*
         * https://www.acmicpc.net/problem/1572
         * 중앙값 
         * 앞뒤 빼고 넣기
         * https://matice.tistory.com/54
         */
       
        /* BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] fistInput = br.readLine().split(" ");
        int n = Integer.parseInt(fistInput[0]);
        int k = Integer.parseInt(fistInput[1]);*/
        int[] or = {3,2,5,7,10,3,2,7,8,2,1,9,5,10,7,4};
        //int[] or = {1,2,3};
        //IndexTree temp = new IndexTree(or);
        //temp.print();
        //System.out.println(temp.intervalSum(1, 6));
       // temp.updateTree(0, 0);
       // temp.print();
        
        BinaryIndexTree temB = new BinaryIndexTree(or);
        temB.printTotal();
        temB.printFenwick();
        temB.printOriginal();
        System.out.println(temB.sumTotal(3));
        System.out.println(temB.sumInterVal(2, 4));
    }
}
/*
 구간 합을 구하는 트리의 종류로 인덱스 트리, 세그먼트 트리, 펜윅 트리가 있다. 세그먼트 트리는 인덱스 트리가 포함하고 있는 한 종류이다. 세그먼트 트리로 풀 수 있는 문제는 인덱스 트리로 모두 풀 수 있다.
 
*/
/*
 1. Index Tree (IDT) (기본)
Index Tree란 (https://matice.tistory.com/54)
- 구간의 정보를 빠르게 파악하기 위해 정보들을 2진 트리로 구성하여 두 개 노드의 부모노드에 대표값이나 연산결과를 저장했다가 구간이 주어졌을 때 최대한 커버가 가능한 구간 정보부터 압축적으로 정보를 추출
- 구간이 주어졌을 때 최대한 커버가능한 구간까지 함축적으로 추출하기 위한 방법
- 완벽히 떨어지는 2진트리를 만들기 위해서는 1 + 2 + 2^2 + 2^3 .. 수로 되어 무한 등비수열의 합 공식을 응용 2^n -1 이 전체 크기이다.
- 마지막 노드 리프에 원본 low 데이터가 들어갈 것이니 low data = 2^n 딱 떨어진다면 2^(n+1) = 2Xlow data면 충분하지만 그보다 클경우 2^n < N <= 2^(n+1) 이떄 노드의 최대 크기는 2^(n+1) < tree크기 <= 2^(n+1+1) 이므로
- 정확성을 위해서는  2^(n+2) 을 찾아야 하는데 즉 N < 2^n 의 n을 찾아 *2 해주어야 한다. 
- 즉  2*N < tree크기 <= 4*N이므로 트리의 크기는 명확성을 위해 최대치인 마지막 리프에 들어갈  N인 Input크기의 곱하기 4이다.(대충의 경우)


- ex ) int[] or = {1,4,5,9,7} 일 경우
 
                 26
         19               7
     5       14      7         0
   1   4   5    9  7   0    0      0
- IndexTree의 특징 부모가 n 이면 왼쪽자식은 2n , 오른쪽 자식은 2n+1이다.
- 마지막 리프에 N을 넣어야 한다.
 */

// 구간합을 저장해 놓는 IndexTree
class IndexTree {
    private int data[];
    private int originalStart;
    
    public IndexTree( int[] orignal ) {
        int originalSize = orignal.length;
        originalStart = 1;
        
        // 1 + 2 + 2^2 + 2^3 + 2^4....마지막 리프를 제외한 부모리프까지의 합을 구하는 방법 5개인 경우 2^3 보다 작으니 딱 2^3 = 8이 시작위치임 
        while (originalStart < originalSize)
            originalStart <<= 1;
        
        //data의 크기는 originalStart의 2배
        data = new int[originalStart*2];
        
        for( int index = 0; index < orignal.length; index++ ) {
            updateTree( index, orignal[index] );
        }
    }
    
    public void updateTree( int index, int value) {
        index += originalStart;
        int beforeValue = data[index];
        while(index > 0) {
            data[index] = data[index] - beforeValue + value;
            index>>=1;
        }
    }

/*   
9,8,5,7,2에서 마지막 리프는  9 8 5 7 2 0 0 0
마지막 2 ~ 5리프 8 5 7 2의 구간 합은
시작을 a 끝을 b라고 할 때, a와 b의 값이 짝수인지 홀수인지에 따라서 구현하는 방법이 달라집니다.
위 그림은 8과 2는 부모로 갈 수 없는 값들이라 따로 합하고, 구간이 [a+1, b-1]로 변경되어 합을 구하는 과정을 보여줍니다.
여기서 중요한 것은 배열의 인덱스 값을 기준으로 구간의 시작은 짝수, 끝은 홀수이면 그 구간의 합을 구하기 위해서 부모노드를 참조할 수 있지만,
만약 구간의 시작이 홀수라면 부모노드가 바로 앞 짝수 값도 포함하고 있으므로, 해당 시작 값(홀수)를 따로 합한 다음 구간을 [a+1, b]로 변경하여 다시 합을 구해야 합니다.
마찬가지로 구간의 끝이 짝수라면 구간을 [a, b-1]로 재설정하여 다시 합을 구하는 과정을 반복해야 합니다.
*/
   public int intervalSum( int start, int end ) {
       start += originalStart;
       end += originalStart;
       int sum = 0;
       while ( start < end ) {
           if( start%2 == 1) {
               sum += data[start]; 
               start++;
           }
           if( end%2 == 0) {
               sum += data[end];
               end--;
           }
           start>>=1;
           end>>=1;
       }
       if( start == end ) {
           // 같은 노드내 탐색일 경우 합치기
           sum += data[start];
       }
       
       return sum;
   }
   
    public void print() {
        for (int index = 0; index < data.length; index++) {
            System.out.print(data[index] + " ");
        }
        System.out.println();
    }
}


/*
2.  Binary Indexed Tree & Fenwick Tree (BIT(or Fenwick))
 - IDT 중에서도, 구간 합을 구하는데 특별한 자료구조 형태
 - Panwork tree ( Tree[i] )란 A[i]가 주어졌을 때, A[i]로부터 앞으로 ㄴi의 합이 저장되도록 미리 만들어 놓은 자료구조 (마지막 1이 나타내는 값을 L[i]라고 표현)
 - T[1] = A[1] , T[2] = A[2] + A[1], T[3] = A[3], T[4] = A[1] + A[2] + A[3] + A[4] 이런식의 구조를 만들어 놓음
 - 개인적인 성향으로 origanal 데이터는 보존되어야 한다고 생각되어 T[ 1~원본데이터 크기 ] 는 팬윅 트리 T[ 원본데이터 크기 ~ 원본데이터 크기 ]는 원본 데이터로 구성해 놓음
 - 
*/
class BinaryIndexTree{
    int tree [];
    int orignalSize;
    int treeLength;
    
    public BinaryIndexTree( int[] orignal ) {
        orignalSize = orignal.length;
        tree = new int[orignalSize*2+1];
        treeLength = tree.length-orignalSize;
        for (int index = 1; index <= orignal.length; index++) {
            update(index, orignal[index-1]);
        }
    }
    public void update(int index, int value) {
        int orignalPosision = orignalSize+index;
        int beforeValue     = tree[orignalPosision];
        tree[orignalPosision]   = value;
        while (index < treeLength) {
            tree[index] = tree[index] - beforeValue + value;
         // index & (-index) 는 해당 2진수의 마지막 1의 자리를 찾기 1100 이면 8 , 101이면 1 , 즉 2비트씩 올리는 것
         // index += (index & -index); 은 index의 마지막 1의자리를 더하는것  즉   7일때 111의 마지막 1의자리 1 을 더하는 것 즉 111 > 1000 (8)이됨  한번 더 연산하면 1000 > 10000 이됨
         // 더 직관적으로 index += (index & -index)은 가장 가까운 2의 제곱수를 찾는 행위
         //& 연산자는 양쪽 비트가 모두 1일때만 1, 
         //-index 음수는 마지막 1의자리 빼고 나머지 1,0 전환
            index += (index & -index); 
        }
    }
    public int sumTotal(int index) {
        int res = 0;
        while (index > 0) {
            res += tree[index];
            index &= index-1;
            // 들어온 수의 1101 -> 1100 -> 1000 마지막 1의자리수를 줄여감 
        }
        return res;
    }
    
    public int sumInterVal(int start , int end) {
        return sumTotal(end) - sumTotal(start-1);
    }
    
    public void printTotal() {
        for (int index = 0; index < tree.length; index++) {
            System.out.print(tree[index] + " ");
        }
        System.out.println();
    }
    public void printFenwick() {
        for (int index = 0; index < treeLength; index++) {
            System.out.print(tree[index] + " ");
        }
        System.out.println();
    }
    public void printOriginal() {
        for (int index = treeLength; index < tree.length; index++) {
            System.out.print(tree[index] + " ");
        }
        System.out.println();
    }
}




