package alorithm.dataStructureLow;

public class  DoubleLinkedList<E> {
    private Node<E> head;
    private Node<E> tail;
    private int size = 0;
    
    public void add( E item ) {
        Node<E> temp = new Node<>( item );
        if( tail == null ) {
            head = temp;
            tail = temp;
        } else {
            Node<E> lastNode = getNode(size-1);
            lastNode.next = temp;
            temp.before = lastNode;
            temp.next = head;
            head.before = temp;
            tail = temp;
        }
        System.out.println(temp.item +"이 삽입되어씁니다.");
        size++;
    }
    private void checkingValidataion( int index ) {
        if( size < index + 1 ) {
            throw new IndexOutOfBoundsException("size를 확인하세요");
        } 
    }
    public void remove( int index ) {
        Node<E> temp = getNode(index);
        Node<E> nextNode = temp.next;
        Node<E> beforeNode = temp.before;
        beforeNode.next = nextNode;
        nextNode.before = beforeNode;
        System.out.println(temp.item +"이 지워졌습니다.");
        if( index == 0 ) {
            head = nextNode;
        }
        if( size == 1 ) {
            head = null;
            tail = null;
        } else if( index == size-1 ) {
            tail = beforeNode;
        }
        size--;
    }
    
    public E get( int index ) {
        Node<E> temp = getNode(index);
        
        return temp.item;
    }
    
    private Node<E> getNode( int index ) {
        checkingValidataion(index);
        Node<E> temp;
        // 효율적 탐색을 위한 설계
        if ( index < size / 2 ) {
            // 앞에서 부터 찾기 
            temp = head;
            for (int inner = 0; inner < index; inner++) {
                temp = head.next;
            }
        } else {
            //뒤에서 부터 찾기
          temp = tail;
            for (int inner = size - 1; inner > index; inner--) {
                temp = temp.before;
            }
        }
        return temp;
    }
    
    @Override
    public String toString() {
        if( size == 0 ) {
            return "노드가 비었습니다.";
        }
        StringBuilder result = new StringBuilder();
        Node<E> temp = head;
        for( int index = 0; index < size; index++ ) {
            result.append("노드 순회 index : ").append(index).append(" ").append(temp.item).append("\n");
            temp = temp.next;
        }
        return result.toString();
    }
    // DoubleLinkedList에서 사용할 중첩클래스
    private static class Node<E> {
        E item;
        Node<E> next;
        Node<E> before;

        public Node(E item ) {
            this.item = item;
            this.next = null;
            this.before = null;
        }
    }
}
