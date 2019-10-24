package algorithm.dataStructure;
public class MyQueueArray {
    private int top; // 맨 위에 있는 데이터를 위한 변수
    private int tail; // 꼬리에 있는 데이터를 위한 변수
    private int maxSize;
    private Object[] stackArray;
    
    // 배열이다보니 크기지정이 필요하다.
    MyQueueArray(int size){
        this.top = -1;
        this.tail = -1;
        this.maxSize = size;
        this.stackArray = new Object[size];
    }
    //poll(),  add() , peek() , isEmpty() 4가지의 함수 구현
    public void add( Object item ) {
       //시간 복잡도 O(1)
       if(tail >= maxSize-1) throw new ArrayIndexOutOfBoundsException();
       stackArray[++tail] = item;
    }
    
    public Object poll() {
        // top을 바꿔주고 top 데이터를 가지고옴
        //시간 복잡도 O(1)
        if(isEmpty()) {
            throw new ArrayIndexOutOfBoundsException();
        } else {
            return stackArray[++top];
        }
     }
    
    public Object peek() {
        // top 데이터만 가지고 옴
        //시간 복잡도 O(1)
        if(isEmpty()) {
            throw new ArrayIndexOutOfBoundsException();
        } else {
            return stackArray[top+1];
        }
     }
    
    public Boolean isEmpty() {
        //시간 복잡도 O(1)
        if( top == tail ) {
            // 초기화 로직
            top  = -1;
            tail = -1;
        }
        return top == tail;
    }
}
