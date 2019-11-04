import java.util.*;

class Solution3 {
    static final int MAX = 9;
    boolean[] done;     //수리여부
    boolean[] used;
    public int solution(int n, int[] weak, int[] dist) {
        int answer = MAX;
        Integer[] temp = Arrays.stream(dist).boxed().toArray(Integer[]::new); 
        Arrays.sort(temp, Collections.reverseOrder());      
        dist = Arrays.stream(temp).mapToInt(Integer::intValue).toArray();

        done = new boolean[weak.length];
        used = new boolean[dist.length];
        for(int i = 0; i < weak.length; i++) {
            int[] line = makeLine(n, weak, i);
            used[0] = false;
            System.out.println(Arrays.toString(line));
            answer = Math.min(answer, repair(line, dist, 0, 0));
        }

        if(answer == MAX) return -1;
        return answer;
    }

    int repair(int[] weak, int[] dist, int cur, int cnt) {
        if(repairCheck(weak.length)) {
            return cnt;
        }

        if(cur == weak.length) return MAX;

        Queue<Integer> q = new LinkedList<>();

        int ret = MAX;
        for(int i = 0; i < dist.length; i++) {
            if(used[i]) continue;
            used[i] = true;
            int next = cur; //  cour 은 지금 들어온 시작점
            for(int j = cur; j < weak.length; j++) {
                int diff = weak[j] - weak[cur];
                if(diff <= dist[i]) {
                    q.add(j);
                    done[j] = true;
                }
                else {
                    next = j;
                    break;
                }
            }
            ret = Math.min(ret, repair(weak, dist, next, cnt+1));           
            while(!q.isEmpty()) {
                done[q.poll()] = false;
            }
            used[i] = false;
        }

        return ret;
    }

    int[] makeLine(int n, int[] weak, int start) {
        int[] ret = new int[weak.length];
        int first = weak[start];
        for(int i = 0; i < weak.length; i++) {
            ret[i] = weak[(i+start)%weak.length];
            if(ret[i] < first) ret[i]+=n;
        }
        return ret;
    }
    boolean repairCheck(int m) {
        for(int i = 0; i < m; i++) {
            if(!done[i]) return false;
        }
        return true;
    }
}