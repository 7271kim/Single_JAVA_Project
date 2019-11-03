class Solution3 {
    int N = 0;
    int ans = Integer.MAX_VALUE;

    public int solution(int n, int[] weak, int[] dist) {
        java.util.Arrays.sort(dist);
        for (int i = 0; i < dist.length / 2; i++) {
            int temp = dist[i];
            dist[i] = dist[dist.length - 1 - i];
            dist[dist.length - 1 - i] = temp;
        }

        this.N = n;
        int min = Integer.MAX_VALUE;
        dfs(0, weak, dist, 0);

        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    private void dfs(int d, int[] weak, int[] dist, int visited) {
        if (visited == ((1 << weak.length) - 1)) {
            ans = Math.min(ans, d);
            return;
        }
        if (d == dist.length) {
            return;
        }

        for (int i = 0; i < weak.length; i++) {
            if ((visited & (1 << i)) > 0) {
                continue;
            }
            java.util.Set<Integer> set = new java.util.HashSet<>();
            for (int j = 0; j < weak.length; j++) {
                int k = (i + j) >= weak.length ? i + j - weak.length : i + j;
                if ((visited & (1 << k)) > 0) {
                    continue;
                }
                int diff = weak[k] - weak[i];
                if (diff < 0) {
                    diff += N;
                }
                if (diff <= dist[d]) {
                    set.add(k);
                    visited = visited | (1 << k);
                } else {
                    break;
                }
            }
            dfs(d+1, weak, dist, visited);
            for (int v : set) {
                visited = visited & ~(1 << v);
            }
        }
    }
}
