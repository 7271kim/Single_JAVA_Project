class InsertionSort {
    int [] data; 
   
    InsertionSort( int[] data ){
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
        for (int index = 1; index < data.length; index++) {
            int beforIndex  = index;
            int beforeValue = data[beforIndex];
            for (int indexNext = index-1; indexNext >= 0; indexNext--) {
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
    
    public void print() {
        StringBuilder result = new StringBuilder();
        for (int i : data) {
            result.append(i + " ");
        }
        System.out.println(result.toString());
    }
}