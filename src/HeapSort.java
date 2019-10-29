/*
gap을 설정한다 » 초기 갭 = 원본배열의 크기 /2 >> 짝수시 홀수로 만든다
gap 크기 만큼 떨어진 원소들을 삽입정렬로 정렬한다. ( 0 ~ gap만큼 반복)
gap 크기가 0이될 때 까지 반복한다.
*/
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
        while( tempIndex < pointer ) {
            if( tempIndex*2 >= maxSize ) break; 
            int leftChild  = heap[tempIndex*2];
            int rightChild = heap[tempIndex*2+1];
            int tempValue;
            int changeValue;
            int changeIndex;
            
            
            Boolean sortCheck  = position == 1 ? leftChild < lastChild && rightChild < lastChild : leftChild > lastChild && rightChild > lastChild;
            
            // 자식들보다 크다면 break;
            if( sortCheck ) {
                break;
            }
            
            if( position == 1) {
                // 둘 다 큰경우 큰 자식이랑 변경로직
                if( leftChild >= lastChild && rightChild >= lastChild ) {
                    changeValue = leftChild > rightChild ? leftChild : rightChild;
                    changeIndex = leftChild > rightChild ? tempIndex*2 : tempIndex*2+1;
                } else {
                    // 각각 변경 여기 들어온거면 왼쪽 오른쪽 자식중 비교값이랑 하나만 큰 경우다.
                    changeValue = leftChild >= lastChild ? leftChild : rightChild;
                    changeIndex = leftChild >= lastChild ? tempIndex*2 : tempIndex*2+1;
                }
            } else {
                
                // 둘 작은 경우 작은 자식이랑 변경로직
                if( tempIndex*2+1 > pointer ) {
                 // Min의 문제는 pointer가 벗어난 오른쪽 값이 0이기 때문에 문제가 발생
                 // 이때는 왼쪽과 비교해줘야 한다.
                    if( leftChild <= lastChild ) {
                        changeValue = leftChild;
                        changeIndex = tempIndex*2;
                    } else {
                        break;
                    }
                } else if( leftChild <= lastChild && rightChild <= lastChild ) {
                    changeValue = leftChild < rightChild ? leftChild : rightChild;
                    changeIndex = leftChild < rightChild ? tempIndex*2 : tempIndex*2+1;
                } else {
                    // 각각 변경 여기 들어온거면 왼쪽 오른쪽 자식중 비교값이랑 하나만 작은 경우다.
                    changeValue = leftChild <= lastChild ? leftChild : rightChild;
                    changeIndex = leftChild <= lastChild ? tempIndex*2 : tempIndex*2+1;
                }
            }
            
            //값 변경 
            tempValue = changeValue;
            heap[changeIndex] = lastChild;
            heap[tempIndex] = tempValue;
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