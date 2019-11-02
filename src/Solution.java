class Solution {
 // https://programmers.co.kr/learn/courses/30/lessons/60060
 // 이전 기본은 >> 무식하게 검색쿼리를 하나 픽 후 전체를 돌아보는것.
 // 10억 * 10억의 효율성이 나와버림
 // 좋은 방법이 없을까 
 // 찾는것이니 자료구조를 찾는것에 맞게 세팅을 한다면?
 // 문자열과 ?의 갯수를 키로하고 개수를 카운팅하는 HashMap 자료구조 세팅
 // 더 좋은것은 찾을것만 세팅하기
 // 안되는 이유 Trie 자료구조를 알고 있어야함.
 // Map구조로 불가
 // ??AB를 위해서 Reverse한 BA?? 를 넣어주어야 한다. >> 즉 정방 , reverse 두개를 넣어야함
 // 단어 길이 별로 트라이 만들기
    public int[] solution(String[] words, String[] queries) {
        int[] answer = new int[queries.length];
        MyTtie[] total = new MyTtie[10001]; // length 1 ~ 10000까지
        
        // 개수별 trie를 위해 초기화
        for (int index = 1; index < total.length; index++) {
            MyTtie temp = new MyTtie();
            total[index] = temp;
        }
        
        for (int index = 0; index < words.length; index++) {
            String tempText = words[index];
            int textLength  = tempText.length();
            total[textLength].insert(tempText);
        }
        
        for (int index = 0; index < queries.length; index++) {
            String tempText = queries[index];
            int textLength  = tempText.length();
            answer[index]   = total[textLength].getValue(tempText) ;
        }
        
        
        return answer;
    }
    
    public class MyTtie {
        private Node root;
        
        private class Node {
            private int value;
            private char inputChar;
            private Node[] childNode;
            
            private Node () {
                this.childNode   = new Node[26];
            }
            private Node ( char inputChar ) {
                this.inputChar = inputChar;
                this.value     = 1;
                this.childNode = new Node[26];
            }
            private void setChild ( Node node, int key ) {
                this.childNode[key] = node;
            }
            
            private void setValue ( int value ) {
                this.value = value;
            }
            
            private int getValue () {
                return this.value;
            }
           
            private Node getChild ( int key ) {
                return childNode[key];
            }
            
            private Boolean hasChild ( int key ) {
                return childNode[key] != null;
            }
            
        }
        
        public MyTtie() {
            this.root = new Node();
        }
        
        // ??AB를 위해서 Reverse한 BA?? 를 넣어주어야 한다. >> 즉 정방 , reverse 두개를 넣어야함
        public void insert( String inputText ) {
            Node tempNode = root;
            int size = inputText.length();
            // 정방
            for (int index = 0; index < size; index++) {
                char temp = inputText.charAt(index);
                int key   = changeKey(temp);
                
                if(!tempNode.hasChild(key)) {
                    Node node = new Node( temp );
                    tempNode.setChild( node, key );
                    tempNode = node;
                } else {
                    tempNode = tempNode.getChild(key);
                    tempNode.setValue(tempNode.getValue()+1);
                }
            }
            
            // 후방
            tempNode = root;
            for (int index = size-1; index >= 0; index--) {
                char temp = inputText.charAt(index);
                int key   = changeKey(temp);
                
                if(!tempNode.hasChild(key)) {
                    Node node = new Node( temp );
                    tempNode.setChild( node, key );
                    tempNode = node;
                } else {
                    tempNode = tempNode.getChild(key);
                    tempNode.setValue(tempNode.getValue()+1);
                }
            }
        }
        
        public int getValue( String inputText ) {
            Node tempNode = root;
            int result = tempNode.getValue();
            if( inputText.charAt(0) == '?' ) {
                for (int index = inputText.length()-1; index >= 0; index--) {
                    char temp = inputText.charAt(index);
                    if( temp == '?' ) break;
                    int key   = changeKey(temp);
                    if( tempNode.hasChild(key) ) {
                        tempNode = tempNode.getChild(key);
                        result = tempNode.getValue();
                    } else {
                        result = 0;
                        break;
                    }
                }
            } else {
                for (int index = 0; index < inputText.length(); index++) {
                    char temp = inputText.charAt(index);
                    if( temp == '?' ) break;
                    int key   = changeKey(temp);
                    if( tempNode.hasChild(key) ) {
                        tempNode = tempNode.getChild(key);
                        result = tempNode.getValue();
                    } else {
                        result = 0;
                        break;
                    }
                }
            }
            
            return result;
        }
        
        public int changeKey( char input ) {
            return input - 'a';
        }
        
    }
}