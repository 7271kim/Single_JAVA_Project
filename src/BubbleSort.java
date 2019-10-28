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