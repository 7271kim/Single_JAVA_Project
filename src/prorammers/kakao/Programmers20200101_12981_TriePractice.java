package prorammers.kakao;
/*
F int solution( 끝말잇기에 참여한 사람, 순서대로 주어진 단어 배열 word ) 
    최종 결과를 확인하기 위한 int형 배열 변수 첫번째 값으로 몇번째 사람, 두번째 변수로는 몇회째 결론이 안나면 {0,0} 반환  int[] answer = {0,0};
    사전식 검색을 위한 Trie객체의 tri 변수 trie = new Trie();
    총 몇번을 했는지 확인하기 위한 변수 count = 1;
    진짜 틀려서 종료했는지 판단하는 변수 isBreak = false;
    
    FOR index는 0부터 word.length() -1 까지 1씩 증가
        word배열의 지금 워드를 나타내는 값 tempWork = word[index];
        IF index가 0이 아니고 word[index-1]의 마지막 단어와 word[index-1] 첫 단어가 틀린경우 THEN
            isBreak = true;
                     종료한다. break;
            
        지금 들어간 단어가 trie에 있는 단어인지 확일을 받아오는 변수 isLast = trie.insert( tempWork );
        IF isLast가 true인경우 THEN
            isBreak = true;
                     종료한다 break;
        ELSE
            count를 1증가 시킨다. 
    If isBreak 가 true 경우
        answer[0]은 count를 n으로 나누어 떨어지면 n으로 아니면 나머지이다. count%n == 0 ? n : count%n;
        answer[1]은 count를 n으로 나누어 떨어지면 n으로 나눈 몫 아니면 n으로 나눈 몫 +1이다. count%n == 0 ? count/n -1 : count/n;
    
    answer 반환한다.
    
*/
class Programmers20200101_12981_TriePractice {
    public int[] solution(int n, String[] words) {
        int[] answer =  {0,0}; 
        Trie trie    = new Trie();
        int count    = 1;
        boolean isBreak = false;
        
        for (int index = 0; index < words.length; index++) {
            String tempWork = words[index];
            if( index != 0 ) {
                String lastText       = words[index-1];
                String beforLastIndex = lastText.substring(lastText.length()-1 , lastText.length());
                String startText      = tempWork.substring(0,1);
                
                if( !beforLastIndex.equals(startText) ) {
                    isBreak = true;
                    break;
                }
            }
            
            boolean isLast = trie.insert( tempWork );
            if( isLast ) {
                isBreak = true;
                break;
            } else {
                count++;
            }
        }
        
        if( isBreak ) {
            answer[0] = count%n == 0 ? n : count%n;
            answer[1] = count%n == 0 ? count/n : (count/n) +1;
        }
        return answer;
    }
    
    private class Trie{
        private class Node {
            private char inputChar;
            private Node[] childNode;
            
            private Node () {
                this.childNode   = new Node[26];
            }
            
            private Node ( char inputChar ) {
                this.inputChar = inputChar;
                this.childNode   = new Node[26];
            }
            
            private Node[] getChildNode() {
                return this.childNode;
            }
        }
        
        private Node root;
        
        public Trie() {
            this.root = new Node();
        }
        
        // 삽입
        public boolean insert( String inputText ) {
            Node tempNode = root;
            int size = inputText.length();
            boolean result = false;
            
            for (int index = 0; index < size; index++) {
                char temp = inputText.charAt(index);
                int key   = temp - 'a';
                boolean isLast = index == size-1;
                Node[]childNode = tempNode.getChildNode();
                if( isLast &&  childNode[key] != null ) {
                    result = true;
                    break;
                }
                if( childNode[key] == null ) {
                    Node inputNode = new Node( temp );
                    childNode[key] = inputNode;
                }
                tempNode = childNode[key];
            }
            
            return result;
        }
    }
}