package algorithm.dataStructure;
public class MyHeapArray {
    private int maxSize;
    private int pointer; // 마지막 노드의 위치를 저장하기 위해 필요
    private int[] heap;
    
    // 배열이다보니 크기지정이 필요하다.
    MyHeapArray(int inputSize){
        
        maxSize = 1;
        pointer = 0;
        while( maxSize <= inputSize )
            maxSize <<= 1;
        
        this.heap = new int[maxSize];
    }
    
    //insertMaxHeap(), insertMinHeap(), removeMax(), removeMin() ,print(), isFull(), isEmpty() 7가지의 함수 구현
    
    // 항상 마지막 노드에 값을 붙인 후 부모노드와 비교를 통해 교체한다.
    // 시간 복잡도 : logN
    public void insertMinHeap( int item ) {
        if(isFull()) {
            throw new ArrayIndexOutOfBoundsException();
        }
        
        int tempIndex   = ++pointer;
        // 마지막노드에 값 넣기
        heap[pointer] = item;
        
        // 부모와 비교 로직
        while( tempIndex > 0 && tempIndex != 1) {
            int parentValue = heap[tempIndex/2];
            if( parentValue > item ) {
                int swapData = item;
                heap[tempIndex/2] = parentValue;
                heap[tempIndex] = swapData;
            } else {
                break;
            }
            tempIndex /= 2;
        }
    }
    
    public void insertMaxHeap( int item ) {
        if(isFull()) {
            throw new ArrayIndexOutOfBoundsException();
        }
        
        int tempIndex   = ++pointer;
        // 마지막노드에 값 넣기
        heap[pointer] = item;
        
        // 부모와 비교 로직
        while( tempIndex > 0 && tempIndex != 1) {
            int parentValue = heap[tempIndex/2];
            if( parentValue < item ) {
                int swapData = parentValue;
                heap[tempIndex/2] = item;
                heap[tempIndex] = swapData;
            } else {
                break;
            }
            tempIndex /= 2;
        }
    }
    
    // 삭제로직 : root노드를 가지고 오고 마지막 노드를 root노드의 값과 변경
    // 자식들을 찾아가며 비교 로직을 수행한다.
    // logN
    public int removeMax() {
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
            
            // 자식들보다 크다면 break;
            if( leftChild < lastChild && rightChild < lastChild ) {
                break;
            }
            // 둘 다 큰경우 큰 자식이랑 변경로직
            if( leftChild >= lastChild && rightChild >= lastChild ) {
                changeValue = leftChild > rightChild ? leftChild : rightChild;
                changeIndex = leftChild > rightChild ? tempIndex*2 : tempIndex*2+1;
            } else {
                // 각각 변경 여기 들어온거면 왼쪽 오른쪽 자식중 비교값이랑 하나만 큰 경우다.
                changeValue = leftChild >= lastChild ? leftChild : rightChild;
                changeIndex = leftChild >= lastChild ? tempIndex*2 : tempIndex*2+1;
            }
            tempValue = changeValue;
            heap[changeIndex] = lastChild;
            heap[tempIndex] = tempValue;
            tempIndex = changeIndex;
        }
        
        return result;
    }
    
    public int removeMin() {
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
            if( tempIndex*2 > pointer ) break; 
            
            int leftChild  = heap[tempIndex*2];
            int rightChild = heap[tempIndex*2+1];
            int tempValue;
            int changeValue;
            int changeIndex;
            
            // 자식들보다 작다면 break;
            if( leftChild > lastChild && rightChild > lastChild ) {
                break;
            }
            
            // 둘 작은 경우 작은 자식이랑 변경로직
            if( tempIndex*2+1 > pointer ) {
             // Min의 문제는 pointer가 벗어난 오른쪽 값이 0이기 때문에 문제가 발생
             // 이때는 왼쪽과 비교해줘야 한다.
                changeValue = leftChild <= lastChild ? leftChild : lastChild;
                changeIndex = tempIndex*2;
            } else if( leftChild <= lastChild && rightChild <= lastChild ) {
                changeValue = leftChild < rightChild ? leftChild : rightChild;
                changeIndex = leftChild < rightChild ? tempIndex*2 : tempIndex*2+1;
            } else {
                // 각각 변경 여기 들어온거면 왼쪽 오른쪽 자식중 비교값이랑 하나만 작은 경우다.
                changeValue = leftChild <= lastChild ? leftChild : rightChild;
                changeIndex = leftChild <= lastChild ? tempIndex*2 : tempIndex*2+1;
            }
            tempValue = changeValue;
            heap[changeIndex] = lastChild;
            heap[tempIndex] = tempValue;
            tempIndex = changeIndex;
        }
        
        return result;
    }
    
    public Boolean isEmpty() {
        //시간 복잡도 O(1)
        return pointer == 0;
    }
    public Boolean isFull() {
        //시간 복잡도 O(1)
        return pointer >= maxSize;
    }
    
    public void print() {
        StringBuilder temp = new StringBuilder();
        for (int index = 1; index <= pointer; index++) {
            temp.append(heap[index] + " ");
        }
        System.out.println(temp.toString());
    }
    
}
