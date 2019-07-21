package algorithm.dataStructure;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class GetBinaryTree{
    public static void main(String args[]) throws Exception{
        /*
         * https://www.acmicpc.net/problem/5639
         * 알고리즘 문제
         * 마지막 문자열까지 받기
         * 이진트리 한번도 안해본 것.
         * 트리 데이터를 저장하는 것.
         */
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String inputData = br.readLine();
        BinaryTree result = new BinaryTree(Integer.parseInt(inputData));
        while ( (inputData = br.readLine()) != null && inputData.length() != 0) {    //EOF까지 입력받음
            result.addTree(result, Integer.parseInt(inputData));
        }
        
        printPostOrder(result);
    }
    
    public static  void printPostOrder( BinaryTree inputData ) {
        if( inputData.left != null ) printPostOrder( inputData.left);
        if( inputData.right != null ) printPostOrder(inputData.right);
        System.out.println(inputData.node);
    }
}

class BinaryTree {
    int node;
    BinaryTree left;
    BinaryTree right;
    
    public BinaryTree( int node ) {
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
    
}
