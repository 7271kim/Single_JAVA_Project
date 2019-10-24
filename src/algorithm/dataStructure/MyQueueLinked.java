package algorithm.dataStructure;
public class MyQueueLinked {
    // 연결 리스트를 위한 Node클래스
    private class NodeClass { 
        Object data; 
        NodeClass link; 
        
        private NodeClass( Object item ) {
            this.data = item;
            this.link = null;
        }
    } 
    
    private NodeClass top;  // 맨 위에 있는 데이터를 위한 변수
    private NodeClass tail; // 꼬리에 있는 데이터를 위한 변수
    
    MyQueueLinked(){
        top  = null;
        tail = null;
    }
    
    //poll(),  add() , peek() , isEmpty() 4가지의 함수 구현
    public void add( Object item ) {
       // 새로운 노드를 만들고 기존 tail을 해당으로 교체하면서 기존 tail.link를 신규 노드로 변경
       // 첫 진입 시 top이랑 테일은 같다.
       // 시간 복잡도 O(1)
       NodeClass old = tail;
       // 이제 신규로 만든 것들은 tail에 집어넣기
       tail = new NodeClass(item);;
       if( isEmpty() ) {
           top = tail;
       } else {
           old.link = tail;
       }
    }
    
    public Object poll() {
        // top을 탑에 연결된 link로 바꿔주고 top데이터 가지고 오기
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
