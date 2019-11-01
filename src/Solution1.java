public class Solution1 {
    static boolean isOk = false;
 
    public boolean solution(int[][] key, int[][] lock) {
        int len = lock.length;
        //3배 확장 후 중앙으로 옮기기
        int[][] copyLock = new int[len*3][len*3];
        for(int i=0; i<len; i++) {
            for(int j=0; j<len; j++) {
                copyLock[i+len][j+len] = lock[i][j];
            }
        }
        
        dfs(key, copyLock, 0);
        return isOk;
    }
    public void dfs(int[][]key, int [][] lock, int cnt) {
        check(key, lock, 0, 0);
        if(isOk) return;
        if(cnt >= 4) return;
        int[][] temp = rotate(key);
        dfs(temp, lock, cnt+1);
    }
    
    public void check(int[][] key, int[][] lock, int x, int y) {
        if(isOk) return;
        if(y+key.length>lock.length) {
            y=0;
            x++;
        }
        if(x+key.length>lock.length) return;
 
        int[][] copyLock = new int[lock.length][lock.length];
        for(int i=0; i<lock.length; i++) {
            copyLock[i] = lock[i].clone();
        }
 
        boolean isFail = false;
        loop:
            for(int i=0; i<key.length; i++) {
                for(int j=0; j<key.length; j++) {
                    if(key[i][j]==1) {
                        if(copyLock[i+x][j+y]==1) {
                            isFail = true;
                            break loop;
                        }
                        copyLock[i+x][j+y] = key[i][j];
                    }
                }
            }
        if(!isFail) {
            loop:
                for(int i=0; i<lock.length/3; i++) {
                    for(int j=0; j<lock.length/3; j++) {
                        if(copyLock[i+lock.length/3][j+lock.length/3] != 1) {
                            isFail = true;
                            break loop;
                        }
                    }
                }
        }
        if(!isFail) {
            isOk = true;
        }
        check(key, lock, x, y+1);
    }
    
    public int[][] rotate(int [][] key) { //회전
        int len = key.length;
        int[][] temp = new int[len][len];
        for(int i=0; i<len; i++) {
            for(int j=0; j<len; j++) {
                temp[i][j] = key[len-j-1][i];
            }
        }
        return temp;
    }
}