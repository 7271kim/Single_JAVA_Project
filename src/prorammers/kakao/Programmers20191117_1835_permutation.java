package prorammers.kakao;
// https://programmers.co.kr/learn/courses/30/lessons/1835
// 드디어 .... 진짜 드디어 전체 탐색 및 BFS, 순열 등등등 하나로 정리됨.
class Programmers20191117_1835_permutation {
    public int solution(int n, String[] data) {
        String[] name = {"A", "C", "F", "J", "M", "N", "R", "T"};
        boolean[] checked = new boolean[name.length];
        return getTotalData( name, checked, 0, "", 7, data );
    }

    private int getTotalData(String[] name, boolean[] checked, int index, String next, int target, String[] data) {
        if( index == target ) {
            int count = 0;
            int dataSize = data.length;
            for (int dataIndex = 0; dataIndex < dataSize; dataIndex++) {
                String checkText = data[dataIndex];
                char startName = checkText.charAt(0);
                char endName   = checkText.charAt(2);
                char condi     = checkText.charAt(3);
                int size       = checkText.charAt(4) - '0';
                
                int compare    = Math.abs(next.indexOf(startName) - next.indexOf(endName))-1;
               
                if( condi == '=') {
                    if( compare == size ) count++;
                    else return 0;
                } else if( condi == '<' ) {
                    if( compare < size ) count++;
                    else return 0;
                } else if( condi == '>' ) {
                    if( compare > size ) count++;
                    else return 0;
                }
            }
            
            if( count == dataSize ) {
                return 1;
            } else {
                return 0;
            }
            
        }
        int answer = 0 ;
        for (int indexName = 0; indexName < name.length; indexName++) {
            if( !checked[indexName] ) {
                checked[indexName] = true;
                next += name[indexName];
                answer += getTotalData( name, checked, index+1, next, 8, data );
                next = next.substring(0,next.length()-1);
                checked[indexName] = false;
            }
        }
        return answer;
    }
}