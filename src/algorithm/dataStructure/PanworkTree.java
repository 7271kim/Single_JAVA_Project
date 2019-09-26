package algorithm.dataStructure;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
public class PanworkTree{
    
    public static void main(String args[]) throws IOException{
        /*
         * https://www.acmicpc.net/problem/3653
         * 팬윅 트리에대한 이해
         * https://www.acmicpc.net/blog/view/21
         * https://stack07142.tistory.com/297
         * https://m.blog.naver.com/PostView.nhn?blogId=occidere&logNo=221059582892&proxyReferer=https%3A%2F%2Fwww.google.com%2F
         * 참고
         */
        /*
         Panwork tree 란 Tree[i]는   A[i]가 주어졌을 때, A[i] 로부터 앞으로 ㄴ[i]( 해당 수를 2진수로 나타냈을때 마지막 i의 값 )의 합이 저장됨
         
         CORE! 업데이트와, 부분 합
         
        static int sum(int p) {
            int res = 0;
            while (p > 0) {
                res += T[p];
                p &= p-1;
            }
            return res;
        }
        
        static void update(int p, int val) {
            while (p < T.length) {
                T[p] += val;
                p += p & (-p);
            }
        }
         */
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int totaExam = Integer.parseInt(br.readLine());
        for ( int row = 0; row < totaExam; row++ ) {
            String next[] = br.readLine().split(" ");
            
            int videoNumber = Integer.parseInt(next[0]);
            int totalCheck  = Integer.parseInt(next[1]);
            int original [] = new int[ videoNumber + 1 ]; // original[3] = 3 >> 3번의 책은 3번의 위치에 있다.
            FenwickTree fenwickTree = new FenwickTree(videoNumber + totalCheck +1);
            
            String videoDetail[] = br.readLine().split(" ");
             
            for(int putTree = 1; putTree <= videoNumber; putTree++) {
                original[putTree] = putTree+totalCheck;
                fenwickTree.update(original[putTree], 1);
            }
            
            for( int showVideo = 0; showVideo < totalCheck; showVideo ++) {
                int show =  Integer.parseInt(videoDetail[showVideo]);
                System.out.println(fenwickTree.sum(original[show])-1);
                fenwickTree.update( original[show] , -1 );
                original[show] = totalCheck - showVideo;
                fenwickTree.update( original[show] , 1 );
            }
        }
    }
}

class FenwickTree{
    int tree [];
    
    public FenwickTree(int size) {
        tree = new int[size];
    }
    public void update(int p, int val) {
        while (p < tree.length) {
            tree[p] += val;
            p += p & (-p);
        }
    }
    public int sum(int p) {
        int res = 0;
        while (p > 0) {
            res += tree[p];
            p &= p-1;
        }
        return res;
    }
}

