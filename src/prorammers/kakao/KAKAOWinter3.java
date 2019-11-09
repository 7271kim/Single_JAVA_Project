package prorammers.kakao;
import java.util.HashMap;

// 회귀의 문제 DFS를 실행해야하며 최종 결과물의 중복여부를 어떻게 판단할 것인가 문제
class KAKAOWinter3 {
    int result = 0;
    int banned_id_size   = 0;
    HashMap<String, String> cheked = new HashMap<String, String>();
    
    public int solution( String[] user_id, String[] banned_id ) {
        
        banned_id_size = banned_id.length;
        
        recursive( user_id, banned_id, 0 );
        
        return result;
    }
    
    public void recursive( String[] user_id, String[] banned_id, int count ) {
        if( banned_id_size == count ) {
            StringBuilder temp = new StringBuilder();
            for (int index = 0; index < user_id.length; index++) {
                if( user_id[index].equals("") )
                    temp.append(index);
            }
            String resultKey = temp.toString();
            if( !cheked.containsKey(resultKey) ) {
                result++;
                cheked.put(resultKey, resultKey);
            }
            
            return;
        }
        
        // 하나하나 검사
        for (int index = count; index < banned_id.length; index++) {
            String search = banned_id[index];
            banned_id[index] = "";
            
            out : for (int indexUser = 0 ; !search.equals("") && indexUser < user_id.length; indexUser++) {
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
                    
                    user_id[indexUser] = "";
                    recursive( user_id, banned_id, count+1);
                    user_id[indexUser] = thisText;
                }
            }
            banned_id[index] = search;
        }
    }
}