/*
intput 배열 준비
첫번째 인덱스와와 다음인데스를 쭉 비교하여 마지막까지 비교 후 가장 작은 최소값을 찾는다. 그리고 교환한다(교환 1회)
두번째 인덱스와 마지막까지 비교하여 가장 작은 최소값을 찾는다. 그리고 교환한다
*/
package algorithm.sort;
class SelectionSort {
    private int [] data; 
   
    public SelectionSort( int[] data ){
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
        for (int index = 0; index < data.length; index++) {
            int change      = data[index];
            int chgeIndex   = index;
            for (int indexNext = index+1; indexNext < data.length; indexNext++) {
                Boolean next  = position == 1 ? data[indexNext] < change : data[indexNext] > change;
                if(next) {
                    change      = data[indexNext];
                    chgeIndex   = indexNext;
                }
            }
            // 데이터 변경 
            if( chgeIndex != index) {
                int temp    = data[index];
                data[index] = change;
                data[chgeIndex] = temp;
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