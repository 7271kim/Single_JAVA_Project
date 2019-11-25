package prorammers.kakao;
import java.util.PriorityQueue;

// https://programmers.co.kr/learn/courses/30/lessons/42626
// 우선순위 정렬이 필요
// 내가 만든것 외 다른것도 보기 아... PriorityQueue 자체가 min으로 구성됨.
class Programmers42626Priqueue {
    public int solution(int[] scoville, int K) {
        int answer = 0;
        MyHeap minHeap = new MyHeap(scoville.length);
        
        for (int index = 0; index < scoville.length; index++) {
            minHeap.insertMinHeap(scoville[index]);
        }
        
        int top = -1;
        
        while( minHeap.isSize() > 1 && top <= K ) {
            int first  = minHeap.removeMin();
            int second = minHeap.removeMin();
            int calc   = first + second*2;
            minHeap.insertMinHeap(calc);
            
            top = minHeap.getTop();
            
            answer++;
            
        }
        
        if( minHeap.isSize() == 1 && top < K ) answer = -1;
        
        return answer;
    }
    
    public int solution2(int[] scoville, int K) {
        PriorityQueue<Integer> q = new PriorityQueue<>();

        for(int i = 0; i < scoville.length; i++)
            q.add(scoville[i]);

        int count = 0;
        while(q.size() > 1 && q.peek() < K){
            int weakHot = q.poll();
            int secondWeakHot = q.poll();

            int mixHot = weakHot + (secondWeakHot * 2);
            q.add(mixHot);
            count++;
        }

        if(q.size() <= 1 && q.peek() < K)
            count = -1;

        return count;
    }
    
    private class MyHeap {
        private int maxSize;
        private int pointer; // 마지막 노드의 위치를 저장하기 위해 필요
        private int[] heap;
        
        // 배열이다보니 크기지정이 필요하다.
        MyHeap(int inputSize){
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
                    int swapData = parentValue;
                    heap[tempIndex/2] = item;
                    heap[tempIndex] = swapData;
                } else {
                    break;
                }
                tempIndex /= 2;
            }
        }
        
        public int removeMin() {
            if(isEmpty()) {
                throw new ArrayIndexOutOfBoundsException();
            }
            int result      = heap[1];
            int lastChild   = heap[pointer];
            heap[1]         = lastChild;
            heap[pointer--] = 0;
            
            int tempIndex   = 1;
            while( tempIndex*2 <= pointer ) {
                Boolean hasRightChild = tempIndex*2+1 <= pointer;
                int leftChild  = heap[tempIndex*2];
                int rightChild = heap[tempIndex*2+1];
                int parent     = heap[tempIndex];
                int changeValue;
                int changeIndex;
                
                if( !hasRightChild ) {
                    if( parent < leftChild ) break;
                    changeIndex = tempIndex*2;
                    changeValue = leftChild;
                } else {
                    if( parent < leftChild && parent < rightChild ) break;
                    changeValue = leftChild > rightChild ? rightChild : leftChild;
                    changeIndex = leftChild > rightChild ? tempIndex*2+1 : tempIndex*2;
                    
                }
                
                heap[tempIndex]    = changeValue;
                heap[changeIndex]  = parent;
                tempIndex = changeIndex;
            }
            
            return result;
        }
        
        public Boolean isEmpty() {
            return pointer == 0;
        }
        public Boolean isFull() {
            return pointer >= maxSize;
        }
        public int isSize() {
            return pointer;
        }
        public int getTop() {
            return heap[1];
        }
    }
}


