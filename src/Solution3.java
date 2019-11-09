import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

class Solution3 {
    public int solution(String[] user_id, String[] banned_id) {
        int answer = 0;
        Map<String, ArrayList<String>> before = new HashMap<String, ArrayList<String>>(); 
        
        for (int index = 0; index < banned_id.length; index++) {
            String search = banned_id[index];
            
            if(before.containsKey(search)) continue;
            
            before.put(search, new ArrayList<String>());
            
            out : for (int indexUser = 0; indexUser < user_id.length; indexUser++) {
                String thisText = user_id[indexUser];
                if(thisText.length() != search.length()) {
                    continue;
                } else {
                    for (int indexChar = 0; indexChar < thisText.length(); indexChar++) {
                        char searchChar   = search.charAt(indexChar);
                        char thisTextChar = thisText.charAt(indexChar);
                        if( searchChar != '*' && searchChar != thisTextChar  ) {
                            continue out ;
                        }
                    }
                    before.get(search).add(thisText);
                }
            }
        }
        
        int beforeSize = before.size();
        Queue<TempClass> que = new LinkedList<Solution3.TempClass>();
        
        String[] keySey = new String[beforeSize];
        int count = 0;
        for (String key : before.keySet()) {
            keySey[count++] = key;
        }
        
                
        for (int indexBefore = 0; indexBefore < before.get(keySey[0]).size(); indexBefore++) {
            int next =1 ;
            String[] temp  = new String[user_id.length];
            String thisTex =  before.get(keySey[0]).get(indexBefore);
            
            for (int indexUser_id = 0; indexUser_id < user_id.length; indexUser_id++) {
                if(!user_id[indexUser_id].equals(thisTex)) {
                    temp[indexUser_id] = user_id[indexUser_id];
                }
            }
            
            TempClass pushQue = new TempClass(1, temp);
            que.add(pushQue);
        }
        
        while(!que.isEmpty()) {
            TempClass top = que.poll();
            int next = top.next;
            String[] nextText = top.nextText;
            
            if( next < beforeSize -1 ) {
                String nextSearch = keySey[next];
                ArrayList<String> array = before.get(nextSearch);
                for (int index = 0; index < before.get(nextSearch).size(); index++) {
                    String findText = before.get(nextSearch).get(index);
                    for (int indexNextText = 0; indexNextText < nextText.length; indexNextText++) {
                        if( findText.equals(nextText[indexNextText]) ) {
                            nextText[indexNextText] = "";
                            TempClass pushQue = new TempClass(next+1, nextText);
                            que.add(pushQue);
                        }
                    }
                }
            } else {
                
                String nextSearch = keySey[next];
                
                for (int index = 0; index < before.get(nextSearch).size(); index++) {
                    String findText = before.get(nextSearch).get(index);
                    for (int indexNextText = 0; indexNextText < nextText.length; indexNextText++) {
                        if( findText.equals(nextText[indexNextText]) ) {
                            answer++;
                        }
                    }
                }
            }
        }
        
        return answer;
    }
    
    private class TempClass {
        int next;
        String[] nextText;
        
        private TempClass( int next, String[] nextText ) {
            this.next     = next;
            this.nextText = nextText;
        }
    }
}