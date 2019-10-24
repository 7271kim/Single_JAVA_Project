package algorithm.dataStructure;
public class MyStackLinked {
    // 연결 리스트를 위한 Node클래스
    private class NodeClass { 
        Object data; 
        NodeClass link; 
        
        private NodeClass( Object item ) {
            this.data = item;
            this.link = null;
        }
    } 
    
    // 맨 위에 있는 데이터를 위한 변수
    private NodeClass top;
    
    MyStackLinked(){
        top = null;
    }
    // pop() ,  push() , peek() , isEmpty() 4가지의 함수 구현
    public void push( Object item ) {
       // 새로운 노드를 만들고 기존 top을 해당 노드에 연결
       // 시간 복잡도 O(1)
       NodeClass temp = new NodeClass(item);
       temp.link = top;
       // 이제 신규 top은 지금 만든 것이다.
       top = temp;
    }
    
    public Object pop() {
        // top을 바꿔주고 top 데이터를 가지고옴
        // 시간 복잡도 O(1)
        if(isEmpty()) {
            throw new ArrayIndexOutOfBoundsException();
        } else {
            Object item = top.data;
            top = top.link;
            return item;
        }
     }
    
    public Object peek() {
        // top 데이터만 가지고 옴
        // 시간 복잡도 O(1)
        if(isEmpty()) {
            throw new ArrayIndexOutOfBoundsException();
        } else {
            return top.data;
        }
     }
    
    public Boolean isEmpty() {
        // 시간 복잡도 O(1)
        return top == null;
    }
}
