package alorithm.dataStructureLow;

// HashMap의 단점 >> 정렬을 지원하지 않는다, 해쉬충돌이 발생한다.
// 문자열 자료구조중 HashMap을 보완할 수 있는 자료구조
// 노드에는 알파벳 수(26개) 만큼의 자식노드와 해당 노드의 값을 가지고 있다. 
// 단점은 모든 노드들이 알파벳 수만큼 하위 노드를 가질필요는 없음
public class MyTtie {
    private Node root;
    
    // 필요 노드 생성
    private class Node {
        private Object value;
        private char inputChar;
        private Node[] childNode;
        private Boolean isLast;
        
        private Node () {
            this.childNode   = new Node[26];
            this.isLast      = false;
        }
        private Node ( char inputChar ) {
            this.inputChar = inputChar;
            this.childNode   = new Node[26];
            this.isLast      = false;
        }
        
        private Node ( char inputChar, int value ) {
            this.inputChar = inputChar;
            this.childNode   = new Node[26];
            this.value       = value;
            this.isLast      = false;
        }
        
        private void setChild ( Node node, int key ) {
            this.childNode[key] = node;
        }
        
        private void setValue ( Object value ) {
            this.value = value;
        }
        
        private Object getValue () {
            return this.value;
        }
       
        private Node getChild ( int key ) {
            return childNode[key];
        }
        
        private Boolean hasChild ( int key ) {
            return childNode[key] != null;
        }
        private void setIslast ( Boolean input) {
            this.isLast = input;
        }
        private Boolean getIsLast () {
            return this.isLast ;
        }
        
    }
    
    public MyTtie() {
        this.root = new Node();
    }
    
    // 삽입
    public void insert( String inputText, int value ) {
        Node tempNode = root;
        int size = inputText.length();
        
        for (int index = 0; index < size; index++) {
            char temp = inputText.charAt(index);
            int key   = changeKey(temp);
           
            // 하위 노드 확인 후 생성
            if(!tempNode.hasChild(key)) {
                Node node = new Node( temp, value );
                tempNode.setChild( node, key );
                tempNode = node;
            } else {
                tempNode = tempNode.getChild(key);
            }
            
            // 문자열의 종료를 나타냄 ABCD 노드가 True라면 ABCD라는 것이 있음
            if(index == size-1) {
                tempNode.setIslast(true);
            }
        }
    }
    
    public void insert( String inputText ) {
        Node tempNode = root;
        int size = inputText.length();
        
        for (int index = 0; index < size; index++) {
            char temp = inputText.charAt(index);
            int key   = changeKey(temp);
           
            // 하위 노드 확인 후 생성
            if(!tempNode.hasChild(key)) {
                Node node = new Node( temp, 1 );
                tempNode.setChild( node, key );
                tempNode = node;
            } else {
                tempNode = tempNode.getChild(key);
            }
            
            // 문자열의 종료를 나타냄 ABCD 노드가 True라면 ABCD라는 것이 있음
            if(index == size-1) {
                tempNode.setIslast(true);
            }
        }
    }
    
    public Boolean search( String inputText ) {
        Boolean result = true;
        int size = inputText.length();
        Node tempNode = root;
        
        for (int index = 0; index < inputText.length(); index++) {
            char temp = inputText.charAt(index);
            int key   = changeKey(temp);
            if( tempNode.hasChild(key) ) {
                tempNode = tempNode.getChild(key);
            } else {
                result = false;
                break;
            }
            
            if(index == size-1) {
                result = tempNode.getIsLast();
            }
        }
        return result;
    }
    
    public Object getValue( String inputText ) {
        Node tempNode = root;
        Object result = tempNode.getValue();
        for (int index = 0; index < inputText.length(); index++) {
            char temp = inputText.charAt(index);
            int key   = changeKey(temp);
            if( tempNode.hasChild(key) ) {
                tempNode = tempNode.getChild(key);
                result = tempNode.getValue();
            } else {
                result = null;
                break;
            }
        }
        return result;
    }
    
    public int changeKey( char input ) {
        return input - 'a';
    }
    
}
