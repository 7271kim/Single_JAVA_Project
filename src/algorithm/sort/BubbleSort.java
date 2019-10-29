/*
intput 배열 준비
1번 인덱스 값과 과 2번 인덱스 값을 비교 큰 친구를 2번으로 교환, 2번과 3번을 비교 큰 친구를 3번으로 변경.. N까지 진행한다.
1번 인덱스 값과 과 2번 인덱스 값을 비교 큰 친구를 2번으로 교환, 2번과 3번을 비교 큰 친구를 3번으로 변경.. N-1까지 진행한다.
*/

package algorithm.sort;
class BubbleSort {
    int [] data; 
   
    BubbleSort( int[] data ){
        this.data = data;
    }
    // 오름차순 정렬 1 2 3 4 5
    public void ascendingSrot() {
        sort(1);
    }
    
    public void descendingSrot() {
        sort(0);
    }
    
    // 1이면 오름차순 , 0이면 내림차순
    public void sort(int position) {
        for (int indexCycle = 0; indexCycle < data.length;indexCycle++) {
            for (int index = 0; index< data.length - indexCycle-1 ; index++) {
                int nextIndex      = index+1;
                int nextValue      = data[nextIndex];
                int beforeValue   = data[index];
                Boolean next  = position == 1 ? beforeValue > nextValue : beforeValue < nextValue;
                // 데이터 교환
                if( next ) {
                    // 데이터 변경
                    data[index]     = nextValue;
                    data[nextIndex] = beforeValue;
                }
            }
        }
    }
    
    public void print() {
        StringBuilder result = new StringBuilder();
        for (int i : data) {
            result.append(i + " ");
        }
        System.out.println(result.toString());
    }
}