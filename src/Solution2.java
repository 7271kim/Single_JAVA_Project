import java.util.Arrays;

class Solution2 {
    static int[] dx = {0,0,1,1};
    static int[] dy = {0,1,0,1};
    
    public static int solution(int m, int n, String[] board) {
        int answer = 0;
        char[][] map = new char[m][n];
        boolean[][] visited;
        for (int i = 0; i < m; i++) {
            map[i] = board[i].toCharArray();
        }
        
        while(true) {
            visited = new boolean[m][n];
            int cnt = 0;
            
            for (int i = 0; i < m-1; i++) {
                for (int j = 0; j < n-1; j++) {
                    char c = map[i][j];
                    
                    if(c == '.') continue;
                    
                    boolean same = true;
                    for (int k = 0; k < 4; k++) {
                        if(map[i+dx[k]][j+dy[k]] != c) {
                            same = false;
                            break;
                        }
                    }
                    if(!same) continue;
                    
                    for (int k = 0; k < 4; k++) {
                        int nx = i+dx[k];
                        int ny = j+dy[k];
                        if(!visited[nx][ny]) cnt++;
                        visited[nx][ny] = true;
                    }
                }
            }
            
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if(visited[i][j]) 
                        map[i][j] = '.';
                }
            }
            
            if(cnt == 0) break;
            answer += cnt;
            
            down(m,n,map);
        }
        return answer;
    }    
     
    public static void down(int m, int n, char[][] map) {
        
        for (int i = m-1; i >= 0 ; i--) {
            for (int j = 0; j < n; j++) {
                if(map[i][j] == '.') continue;
                
                int nx = i;
                char c = map[i][j];
                map[i][j] = '.';
                while(true) {
                    if(nx+1 >= m || map[nx+1][j] != '.') break;
                    nx++;
                }
                map[nx][j] = c;
            }
        }
        
    }
}