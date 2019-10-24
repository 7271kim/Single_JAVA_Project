package algorithm.dataStructure;
public class MyStackArray {
    private int top;
    private int maxSize;
    private Object[] stackArray;
    
    // 배열이다보니 크기지정이 필요하다.
    MyStackArray(int size){
        this.top = -1;
        this.maxSize = size;
        this.stackArray = new Object[size];
    }
    // pop() ,  push() , peek() , isEmpty() 4가지의 함수 구현
    public void push( Object item ) {
       //시간 복잡도 O(1)
       if(top >= maxSize-1) throw new ArrayIndexOutOfBoundsException();
       stackArray[++top] = item;
    }
    
    public Object pop() {
        // top을 바꿔주고 top 데이터를 가지고옴
        //시간 복잡도 O(1)
        if(isEmpty()) {
            throw new ArrayIndexOutOfBoundsException();
        } else {
            return stackArray[top--];
        }
     }
    
    public Object peek() {
        // top 데이터만 가지고 옴
        //시간 복잡도 O(1)
        if(isEmpty()) {
            throw new ArrayIndexOutOfBoundsException();
        } else {
            return stackArray[top];
        }
     }
    
    public Boolean isEmpty() {
        //시간 복잡도 O(1)
        return top == -1;
    }
}
