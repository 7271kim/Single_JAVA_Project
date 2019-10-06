package algorithm.dataStructure;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TreeStudy {
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
- IndexTree의 특징 부모의 인덱스가 n이면 왼쪽자식은 2n , 오른쪽 자식은 2n+1이다.
- 마지막 리프에 N을 넣어야 한다.
 */

// 구간합을 저장해 놓는 IndexTree
class IndexTree {
    private int tree[];
    private int originalStart;
    
    public IndexTree( int[] orignal ) {
        int originalSize = orignal.length;
        originalStart = 1;
        
        // 1 + 2 + 2^2 + 2^3 + 2^4....마지막 리프를 제외한 부모리프까지의 합을 구하는 방법 5개인 경우 2^3 보다 작으니 딱 2^3 = 8이 시작위치임 
        while (originalStart < originalSize)
            originalStart <<= 1;
        
        //data의 크기는 originalStart의 2배
        tree = new int[originalStart*2];
        
        for( int index = 0; index < orignal.length; index++ ) {
            update( index, orignal[index] );
        }
    }
    
    // 오리지널에서 수정되어야 할  index와 수정할 값  value
    // ex) int[] ori = {1,2,3,4} >> {0,2,3,4}
    // update( 0, 1 );
    public void update( int index, int value) {
        index += originalStart;
        int beforeValue = tree[index];
        while(index > 0) {
            tree[index] = tree[index] - beforeValue + value;
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
   public int sumInterVal( int start, int end ) {
       start += originalStart;
       end += originalStart;
       int sum = 0;
       while ( start < end ) {
           if( start%2 == 1) {
               sum += tree[start]; 
               start++;
           }
           if( end%2 == 0) {
               sum += tree[end];
               end--;
           }
           start>>=1;
           end>>=1;
       }
       if( start == end ) {
           // 같은 노드내 탐색일 경우 합치기
           sum += tree[start];
       }
       
       return sum;
   }
   public int sumTotal( int end ) {
       int start = 0;
       start += originalStart;
       end += originalStart;
       int sum = 0;
       while ( start < end ) {
           if( start%2 == 1) {
               sum += tree[start]; 
               start++;
           }
           if( end%2 == 0) {
               sum += tree[end];
               end--;
           }
           start>>=1;
           end>>=1;
       }
       if( start == end ) {
           sum += tree[start];
       }
       
       return sum;
   }
    public void printOriginal() {
        for (int index = 0; index < tree.length; index++) {
            System.out.print(tree[index] + " ");
        }
        System.out.println();
    }
    
    // 특수 케이스 찾기 
    // https://www.acmicpc.net/problem/1572
    /*             
                5 
        2                  3
      1        1          1          2
    1   0   0     1    0     1    1      1
    (1   2   3    4    5     6    7      8) 
    int[] tree = { 0, 5, 2, 3, 1, 1, 2, 1, 0, 0, 1, 0, 1, 1, 1};
    5의 중간인 3의 위치에 있는 1을 구하고 
    그 1의 마지막 노드의 index값이 mid가 되는것을 응용 3의 위치의 1은 index = 6 이기 때문에 중간값은 6을 응용
    
    */
    public int search( int findNumber ) {
        int findIndex = 1;
        int leftChild;
        while(findIndex < originalStart) {
            //findNumber를 찾아가는 과정 
            // 1. findeNumber = 3 , leftChild = 2
            // 2. findeNumber = 1 , leftChild = 1
            // 3. findeNumber = 1 , leftChild = 0
            // 4. break; 
            
            // 자식을 확인  
            leftChild = tree[findIndex*2]; 
            // 왼쪽 자식노드 확인 자식노드에 저장된 수 확인 
            
            // 자식노드에 저장된 수가  ( ex) 5 ) 중간값 (ex ) 2 )보다 크거나 같다면 계속 자식 탐색
            // 5의 왼쪽 자식은 2 , 오른쪽자식은 3 , findNumber = 3
            
            if(findNumber <= leftChild) {
                findIndex = findIndex * 2; // 자식 노드의 왼쪽 값
            } else {
                // 맨 처음 findNumber = 3 , leftChild = 2임으로 여기 로직 수행
                // 다음 찾는 값은 왼쪽값을 빼고 진행 
                // 3에서 2를 제외한 1을 찾으면 된다.
                findNumber = findNumber - leftChild;
                // 자식의 오른쪽 노드를 탐색한다.
                findIndex = findIndex * 2 + 1; 
                //자식 노드의 오른쪽 값부터 다시 시작
            }
        }

        // 최종 index의 위치인 i에서 시작점을 빼면 중간 값을 추출할 수 있음
        return findIndex - originalStart;
    }
    // 위 함수랑 동일한데 회귀로 둘림
    public int search( int rootIndex, int findNumber ) {
        if( rootIndex >= originalStart ) return rootIndex - originalStart;
        
        int leftChild =  tree[rootIndex*2];
        if( findNumber <=leftChild  ) {
            return search( rootIndex*2, findNumber );
        } else {
            return search( rootIndex*2+1, findNumber - leftChild );
        }
    }
    
   // 트리에 바닥에 저장된 값 확인
    public int getTreeData ( int index ) {
        return tree[originalStart + index];
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
        for (int index = 0; index < orignal.length; index++) {
            update(index, orignal[index]);
        }
    }

    //int [] temp = {1,7,3,4}; tree.update(1,2) => 7 -> 2로 변경됨 
    public void update(int index, int value) {
        index += 1; // 팬윅트리는 A[1] 부터 시작
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
    //int [] temp = {1,7,3,4}; tree.sumTotal(2); => 1+7+3;
    public int sumTotal(int index) {
        int res = 0;
        index++; // A[1]부터기 때문
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

class FenwickTreeOriganl{
    int tree [];
    
    public FenwickTreeOriganl(int size) {
        tree = new int[size];
    }
    public void update(int index, int val) {
        int orignal  = sumTotal( index)-sumTotal( index-1);
        int increase = val - orignal;
        while ( index < tree.length) {
            tree[ index ] += increase;
            index +=  index & (- index);
        }
    }
    
    public int sumTotal(int endIndex) {
        int res = 0;
        while (endIndex > 0) {
            res += tree[endIndex];
            endIndex &= endIndex-1;
        }
        return res;
    }
    
    public int sumInterVal(int startIndex, int endIndex) {
        return sumTotal(endIndex) - sumTotal(startIndex);
    }
    
    public void printOriginal() {
        for (int index = 0; index < tree.length; index++) {
            System.out.print(tree[index] + " ");
        }
        System.out.println();
    }
}

class IndexTreeOrignal{
    int tree [];
    int originalStart;
    
    public IndexTreeOrignal(int originalSize) {
        originalStart = 1;
        while (originalStart < originalSize)
            originalStart <<= 1;
        tree = new int[originalStart*2];
    }
    public int sumTotal ( int endIndex ) {
        return sumInterVal(0, endIndex);
    }
    
    public int sumInterVal(int startIndex, int endIndex) {
        int sum = 0;
        startIndex += originalStart;
        endIndex += originalStart;
        while (startIndex < endIndex) {
            if ((startIndex & 1) == 1) {
                sum += tree[startIndex];
                startIndex++;
            }
            if ((endIndex & 1) == 0) {
                sum += tree[endIndex];
                endIndex--;
            }
            startIndex /= 2;
            endIndex /= 2;
        }
        if (startIndex == endIndex)
            sum += tree[startIndex];
        return sum;
    }
 
    public void update(int index, int val) {
        index += originalStart;
        int minus = tree[index];
        int pointer = index;
        while (pointer != 0) {
            tree[pointer] = tree[pointer] - minus + val;
            pointer /= 2;
        }
    }
    
    public void printOriginal() {
        for (int index = 0; index < tree.length; index++) {
            System.out.print(tree[index] + " ");
        }
        System.out.println();
    }
    // 특수 케이스 찾기 
    // https://www.acmicpc.net/problem/1572
    /*             
                5 
           2                  3
       1        1          1          2
    1   0   0     1    0     1    1      1
    (1   2   3    4    5     6    7      8) 
    int[] tree = { 0, 5, 2, 3, 1, 1, 2, 1, 0, 0, 1, 0, 1, 1, 1};
    5의 중간인 3의 위치에 있는값을 구하는 방법.
    5의 좌우 노드를 확인 후 최종 3에 맞는 index를 찾기 3의 위치에 맞는 인덱스는 index = 6 이기 때문에 중간값은 6
    
    */
    public int search( int findNumber ) {
        int findIndex = 1;
        int leftChild;
        while(findIndex < originalStart) {
            //findNumber를 찾아가는 과정 
            // 1. findeNumber = 3 , leftChild = 2
            // 2. findeNumber = 1 , leftChild = 1
            // 3. findeNumber = 1 , leftChild = 0
            // 4. break; 
            
            // 자식을 확인  
            leftChild = tree[findIndex*2]; 
            // 왼쪽 자식노드 확인 자식노드에 저장된 수 확인 
            
            // 자식노드에 저장된 수가  ( ex) 5 ) 중간값 (ex ) 2 )보다 크거나 같다면 계속 자식 탐색
            // 5의 왼쪽 자식은 2 , 오른쪽자식은 3 , findNumber = 3
            
            if(findNumber <= leftChild) {
                findIndex = findIndex * 2; // 자식 노드의 왼쪽 값
            } else {
                // 맨 처음 findNumber = 3 , leftChild = 2임으로 여기 로직 수행
                // 다음 찾는 값은 왼쪽값을 빼고 진행 
                // 3에서 2를 제외한 1을 찾으면 된다.
                findNumber = findNumber - leftChild;
                // 자식의 오른쪽 노드를 탐색한다.
                findIndex = findIndex * 2 + 1; 
                //자식 노드의 오른쪽 값부터 다시 시작
            }
        }

        // 최종 index의 위치인 i에서 시작점을 빼면 중간 값을 추출할 수 있음
        return findIndex - originalStart;
    }
    // 위에 함수랑 동일인데 회귀로 처리시
    public int search( int rootIndex, int findNumber ) {
        if( rootIndex >= originalStart ) return rootIndex - originalStart;
        
        int leftChild =  tree[rootIndex*2];
        if( findNumber <=leftChild  ) {
            return search( rootIndex*2, findNumber );
        } else {
            return search( rootIndex*2+1, findNumber - leftChild );
        }
    }
    
    // 트리에 바닥에 저장된 값 확인
    public int getTreeData ( int index ) {
        return tree[originalStart + index];
    }
}

class BinaryTreeOri {
    int node;
    BinaryTree left;
    BinaryTree right;
    
    public BinaryTreeOri( int node ) {
        this.node   = node;
        this.left  = null;
        this.right  = null;
    }
    
    /*
     CORE : Tree 또한 저장된 데이터 형식이다. >>  BinaryTree result = new BinaryTree(Integer.parseInt(inputData)); 결과 하나하나 나무 데이터가 만들어지는 것.
     Defaul : 노드가 1개일 경우 ( 50 30 60 >> 정상 노출 ) : 핵심로직 >> 여러개일때 동기화작전 >> 회귀 >> 왼쪽 오른쪽 로직이 반복된다. >> 반복하는 것을 종료 조건과 더불어 회귀한다. >> 핵심은 . 달아 놓을것이 무엇인가. 왼쪽 오른쪽은 트리데이터가 달릴수 있다.
     public BinaryTree addTree ( BinaryTree orignalTree, int value ) {
        BinaryTree tempBinaryTree = new BinaryTree(value);
        if( value < orignalTree.node ) {
            orignalTree.lefet = tempBinaryTree;
        } else if( value > orignalTree.node ) {
            orignalTree.right = tempBinaryTree;
        }
        return orignalTree;
    }*/
    
    // 반복해야 하는 것을 종료 조건과 더불어 회귀시긴다.
    public BinaryTree addTree ( BinaryTree orignalTree, int value ) {
        if( orignalTree == null ) return new BinaryTree(value);
        if( value < orignalTree.node ) {
            orignalTree.left = addTree(orignalTree.left, value);
        } else if( value > orignalTree.node ) {
            orignalTree.right = addTree(orignalTree.right,value);
        }
        return orignalTree;
    }
    
    public void post() {
        if(left != null) left.post();
        if(right != null) right.post();
        System.out.println(node);
    }
    public void pre() {
        System.out.println(node);
        if(left != null) left.pre();
        if(right != null) right.pre();
    }
    
}
