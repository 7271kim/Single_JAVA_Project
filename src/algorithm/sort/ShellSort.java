package algorithm.sort;
/*
gap을 설정한다 » 초기 갭 = 원본배열의 크기 /2 >> 짝수시 홀수로 만든다
gap 크기 만큼 떨어진 원소들을 삽입정렬로 정렬한다. ( 0 ~ gap만큼 반복)
gap 크기가 0이될 때 까지 반복한다.
*/
class ShellSort {
    private int [] data;
    private int size;
   
    public ShellSort( int[] data ){
        this.data = data;
        size      = data.length;
    }
    // 오름차순 정렬 1 2 3 4 5
    public void ascendingSrot() {
        sort( size/2, 1 );
    }
    
    public void descendingSrot() {
        sort(size/2,0);
    }
    
    // 1이면 오름차순 , 0이면 내림차순
    public void sort(int gap , int position) {
        if(gap == 0) return;
        gap = gap%2 == 1 ? gap : gap+1; // 짝수시 홀수 만들기
        
        //0 ~ gap만큼 반복
        for (int index = 0; index < gap; index++) {
            //gap크기만큼 떨어진 원소들에대해 삽입정렬 로직
            for (int gapIndex = index + gap; gapIndex < size; gapIndex += gap) {
                int beforIndex  = gapIndex;
                int beforeValue = data[gapIndex];
                for (int indexNext = gapIndex-gap; indexNext >= 0; indexNext-=gap) {
                    int thisValue   = data[indexNext];
                    Boolean next  = position == 1 ? beforeValue < thisValue : beforeValue > thisValue;
                    // 조건에 안맞으면 정렬된 상태라 더이상의 로직은 불필요함
                    if( next ) {
                        // 데이터 변경
                        data[beforIndex] = thisValue;
                        data[indexNext] = beforeValue;

                        //초기화
                        beforIndex  = indexNext;
                    } else {
                        break;
                    }
                }
            }
        }
        sort(gap/2 , position);
    }
    
    public void print() {
        StringBuilder result = new StringBuilder();
        for (int i : data) {
            result.append(i + " ");
        }
        System.out.println(result.toString());
    }
}