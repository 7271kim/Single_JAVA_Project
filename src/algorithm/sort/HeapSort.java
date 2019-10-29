package algorithm.sort;
class HeapSort {
    private int [] heap;
    private int [] data;
    private int maxSize;
    private int inputSize;
    private int pointer;
   
    // Heap공간 확보
    public HeapSort( int[] data ){
        maxSize = 1;
        this.data = data;
        inputSize = data.length;
        while( maxSize <= inputSize )
            maxSize <<= 1;
        
        this.heap = new int[maxSize];
    }
    // 오름차순 정렬 1 2 3 4 5
    public void ascendingSrot() {
        //MaxHeap으로 구성 : 1
        makeHeap( 1 );
        for (int index = 0; index < inputSize; index++) {
            data[index] = remove(1);
        }
    }
    
    public void descendingSrot() {
        // MinHeap으로 구성 : 0
        makeHeap( 0 );
        for (int index = 0; index < inputSize; index++) {
            data[index] = remove(0);
        }
    }
    
    // 1이면 MaxHeap , 0이면 MinHeap
    public void makeHeap( int position ) {
        pointer = 0;
        // 마지막 노드에 값 붙이기 
        if(isFull()) {
            throw new ArrayIndexOutOfBoundsException();
        }
        for (int index = 0; index < inputSize; index++) {
            int tempIndex   = ++pointer;
            // 마지막노드에 값 넣기
            int item        = data[index];
            heap[pointer]   = item;
            
            // 부모와 비교 로직
            
            while( tempIndex > 0 && tempIndex != 1) {
                int parentValue = heap[tempIndex/2];
                Boolean sortCheck  = position == 1 ? parentValue < item : parentValue > item;
                if( sortCheck ) {
                    // 교환
                    int swapData = parentValue;
                    heap[tempIndex/2] = item;
                    heap[tempIndex] = swapData;
                } else {
                    break;
                }
                tempIndex /= 2;
            }
        }
    }
    
    // 1일때 Max힙 줄이기 , 0일때 MinHeap 줄이기
    public int remove( int position ) {
        if(isEmpty()) {
            throw new ArrayIndexOutOfBoundsException();
        }
        // root와 마지막 노드를 변경 후 point사이즈를 하나 줄임
        int result      = heap[1];
        int lastChild   = heap[pointer];
        heap[1]         = lastChild;
        heap[pointer--] = 0;
        
        // 자식들 비교확인 로직 자식들 노드 중 값이 더 큰 노드와 교체한다.
        int tempIndex   = 1;
        while( tempIndex*2 <= pointer ) {
            Boolean hasRightChild = tempIndex*2+1 <= pointer;
            int leftChild  = heap[tempIndex*2];
            int rightChild = heap[tempIndex*2+1];
            int parent     = heap[tempIndex];
            int changeValue;
            int changeIndex;
            
            Boolean sortCheck;
            
            if( !hasRightChild ) {
                sortCheck = position == 1 ? parent > leftChild : parent < leftChild;
                
                if( sortCheck ) break;
                
                changeIndex = tempIndex*2;
                changeValue = leftChild;
            } else {
                sortCheck = position == 1 ? parent > leftChild && parent > rightChild  : parent < leftChild && parent < rightChild;
                
                if( sortCheck ) break;
                sortCheck = position == 1 ? leftChild < rightChild : leftChild > rightChild;
                
                changeValue = sortCheck ? rightChild : leftChild;
                changeIndex = sortCheck ? tempIndex*2+1 : tempIndex*2;
                
            }
            
            //교체
            heap[tempIndex]    = changeValue;
            heap[changeIndex]  = parent;
            tempIndex = changeIndex;
            
        }
        
        return result;
    }
    
    public void print() {
        StringBuilder result = new StringBuilder();
        for (int i : data) {
            result.append(i + " ");
        }
        System.out.println(result.toString());
    }
    
    public Boolean isEmpty() {
        //시간 복잡도 O(1)
        return pointer == 0;
    }
    public Boolean isFull() {
        //시간 복잡도 O(1)
        return pointer >= maxSize;
    }
}