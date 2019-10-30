import algorithm.mst.MstKuskal;
import algorithm.mst.MstPrim;

/*
 * https://www.acmicpc.net/problem/1436
 * 종말의 숫자란 어떤 수에 6이 적어도 3개이상 연속으로 들어가는 수
 * N번째 >> N-1에 666을 붙이기 >> X 
 * 문제가 16661도 종말의 수임.
 * 666 ~ 무한대로 while 돌면서 종말의 수 확인
 * 종말의 수 확인법 >> 해당이 문제 >> int형에서 연속된 숫자 확인하는 방법 >> 나는 String변경 후 matches수행
 * findCount가 받은 값이랑 같을 경우 해당 숫자를 출력
 * O( N(최대2,666,799 )*indexOf시간 복잡도(tartget 길이(7) * 찾는문자열 길이(3)) ) >> 56,002,779 >> 10억 안넘어서 괜춘
 * 
 */

public class Main {
    public static void main(String args[]){
        // 1->2
        //  ->3
        // 2->3
        // 2->4
        // 3->4
        // 4->2
        MstKuskal kuskal = new MstKuskal(7);
        kuskal.push(1, 2, 2);
        kuskal.push(2, 7, 7);
        kuskal.push(7, 6, 9);
        kuskal.push(6, 5, 23);
        kuskal.push(5, 4, 1);
        kuskal.push(4, 1, 10);
        kuskal.push(1, 3, 3);
        kuskal.push(2, 3, 3);
        kuskal.push(3, 7, 4);
        kuskal.push(3, 6, 3);
        kuskal.push(3, 5, 6);
        
        System.out.println(" 최소비용 : " + kuskal.getMinValue());
        kuskal.printGraph();
        
        System.out.println("-----------------------");
        
        MstPrim prim = new MstPrim(7);
        prim.push(1, 2, 2);
        prim.push(2, 7, 7);
        prim.push(7, 6, 9);
        prim.push(6, 5, 23);
        prim.push(5, 4, 1);
        prim.push(4, 1, 10);
        prim.push(1, 3, 3);
        prim.push(2, 3, 3);
        prim.push(3, 7, 4);
        prim.push(3, 6, 3);
        prim.push(3, 5, 6);
        
        System.out.println(" 최소비용 : " + prim.getMinValue());
        prim.printGraph();
        
        
    }
}

