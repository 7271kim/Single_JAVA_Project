package prorammers.kakao;
/*
https://programmers.co.kr/learn/courses/30/lessons/12985
F int solution( 게임 참가자 수 n, a번 참가자 a, b번 참가자 b )
    결과값 리턴할 변수 answer
    토너먼트 대진에 부모를 만나는 횟수로 저장할 tree = new Tree(n);
    
    answer = tree.get(a,b);
    
    answer를 리턴한다.
    
    class Tree 
        실제 트리에 저장된 데이터를 위한 배열 번수 data[0]은 사용하지 않을 것이다. int[] data; 
        들어온 초기 세팅 데이터가 저장될 위치를 저장할 변수 indexStart;
        
        생성자 Tree ( 게임 참가자 수 n )
            indexStart = 1;
            
            WHILE indexStart 보다 n이 작다면 indexStart < n
                 indexStart비트연산으로 1 좌측으로 보냄(*2시킴) indexStart <<= 1;
            
            data를 indexStart*2 크기만큼 할당(0안씀) data = new int[indexStart*2];
            
    F int get( a , b )
            최종 결과값을 반환하기 위한 result = 1;
            a의 부모 값을 할당 할 parentA = ( a + indexStart -1 ) / 2;
            b의 부모 값을 할당 할 parentB = ( b + indexStart -1 ) / 2;
            
            WHILE parentA != parentB 
                parentA 의 상위 부모 할당 parentA /= 2;
                parentA 의 상위 부모 할당 parentB /= 2;
                result를 1증가 시킴
            
            result 반환 
        */

class Programmers20200105_12985_tree {
    public int solution(int n, int a, int b) {
        int answer = 1;
        int indexStart = 1;
        while ( indexStart < n ) {
            indexStart <<= 1;
        }
        int  parentA = ( a + indexStart - 1 ) / 2;
        int  parentB = ( b + indexStart - 1 ) / 2;
        
        while( parentA != parentB ) {
            parentA /= 2;
            parentB /= 2;
            answer++;
        }
        
        /*TempTree tree = new TempTree(n);
        answer = tree.get(a,b);*/
        
        return answer;
    }
    
    private class TempTree {
        private int indexStart;
        
        TempTree( int n ){
            indexStart = 1;
            while ( indexStart < n ) {
                indexStart <<= 1;
            }
        }
        
        private int get( int a, int b ) {
            int result = 1;
            int  parentA = ( a + indexStart - 1 ) / 2;
            int  parentB = ( b + indexStart - 1 ) / 2;
            
            while( parentA != parentB ) {
                parentA /= 2;
                parentB /= 2;
                result++;
            }
            
            return result;
        }
    }
}