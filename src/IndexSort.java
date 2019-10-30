class IndexSort {
    private int [] data;
    private int [] orignal;
   
    // Heap공간 확보
    public IndexSort( int[] orignal, int maxRange ){
        this.orignal     = orignal;
        data = new int[maxRange+1];
        for (int index = 0; index < orignal.length; index++) {
            this.data[orignal[index]] += 1;
        }
    }
    // 오름차순 정렬 1 2 3 4 5
    public void ascendingSrot() {
        sortAcc();
    }
    
    public void descendingSrot() {
        sortDec();
    }
    
    public void sortAcc() {
        int count = 0;
        
        for (int index = 0; index < data.length; index++) {
            int thisNumber = data[index];
            if( thisNumber != 0) {
                for (int temp = 0; temp < thisNumber; temp++) {
                    orignal[count++] = index;
                }
            }
        }
    }
    
    public void sortDec() {
        int count = 0;
        
        for (int index = data.length-1; index > 0; index--) {
            int thisNumber = data[index];
            if( thisNumber != 0) {
                for (int temp = 0; temp < thisNumber; temp++) {
                    orignal[count++] = index;
                }
            }
        }
    }
    
    
    public void print() {
        StringBuilder result = new StringBuilder();
        for (int i : orignal) {
            result.append(i + " ");
        }
        System.out.println(result.toString());
    }
}