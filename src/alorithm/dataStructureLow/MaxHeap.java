package alorithm.dataStructureLow;

public class MaxHeap <T extends Comparable<T> > {
    private int maxSize;
    private int pointer; // 마지막 노드의 위치를 저장하기 위해 필요
    private Object[] heap;
    
    
    public MaxHeap( int arrSize ){
        arrSize++;
        maxSize = 1;
        pointer = 0;
        while( maxSize <= arrSize ) {
            maxSize <<= 1;
        }
        
        this.heap = new Object[arrSize];
    }
    
    public void add( T item ) {
        if(isFull()) {
            throw new ArrayIndexOutOfBoundsException();
        }
        
        int tempIndex   = ++pointer;
        heap[pointer] = item;
        
        while( tempIndex > 0 && tempIndex != 1) {
            @SuppressWarnings("unchecked")
            T parentValue = (T) heap[tempIndex/2];
            
            if( parentValue.compareTo( item ) < 0) {
                T swapData = parentValue;
                heap[tempIndex/2] = item;
                heap[tempIndex] = swapData;
            } else {
                break;
            }
            tempIndex /= 2;
        }
    }
    
    @SuppressWarnings("unchecked")
    public T pop() {
        if(isEmpty()) {
            throw new ArrayIndexOutOfBoundsException();
        }
        T result      = (T)heap[1];
        T lastChild   = (T)heap[pointer];
        heap[1]         = lastChild;
        heap[pointer--] = 0;
        
        int tempIndex   = 1;
        while( tempIndex*2 <= pointer ) {
            Boolean hasRightChild = tempIndex*2+1 <= pointer;
            T leftChild  = (T)heap[tempIndex*2];
            T rightChild = (T)heap[tempIndex*2+1];
            T parent     = (T)heap[tempIndex];
            T changeValue = null;
            int changeIndex;
            
            if( !hasRightChild ) {
                
                if( parent.compareTo(leftChild)  > 0 ) break;
                changeIndex = tempIndex*2;
                changeValue = leftChild;
            } else {
                if( parent.compareTo(leftChild)  > 0 && parent.compareTo(rightChild)  > 0 ) break;
                
                changeValue = leftChild.compareTo(rightChild) < 0? rightChild : leftChild;
                changeIndex = leftChild.compareTo(rightChild) < 0? tempIndex*2+1 : tempIndex*2;
                
            }
            
            //교체
            heap[tempIndex]    = changeValue;
            heap[changeIndex]  = parent;
            tempIndex = changeIndex;
        }
        
        return result;
    }
    
    public boolean isEmpty() {
        return pointer == 0;
    }
    
    private boolean isFull() {
        return pointer >= maxSize;
    }
}
