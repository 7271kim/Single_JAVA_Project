import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution3 {
    static int N, M, W[], D[], MAX = 987654321;
    static List<int[]> list = new ArrayList<>();

    static int solution(int n, int[] weak, int[] dist) {
        D = dist;
        N = weak.length;
        M = D.length;
        W = new int[2 * N];

        int ret = MAX, i, j;

        for (i = 0; i < N ; i++) {
            W[i] = weak[i];
            W[i + N] = W[i] + n;
        }

        Arrays.sort(dist);
        permutation(0, new int[M], new boolean[M]);

        for (i = 0; i < N; i++) 
            for (j = 0; j < list.size(); j++) 
                ret = Math.min(ret, inject(i, list.get(j)));

        return ret == MAX ? -1 : ret;
    }

    static int inject(int s, int[] friends) {
        int p = 0, i, a;

        for (i = 0; i < friends.length; i++) {
            a = W[s + p];

            while (p < N && W[s + p] <= a + friends[i]) p++;

            if (p == N) return i + 1;
        }

        return MAX;
    }

    static void permutation(int depth, int[] make, boolean[] use) {
        if (depth == M) {
            int[] tmp = new int[M];

            for (int i = 0; i < M; i++)
                tmp[i] = make[i];

            list.add(tmp);
            return;
        }

        for (int i = 0; i < M; i++) {
            make[depth] = D[i];

            if (!use[i]) {
                use[i] = true;
                permutation(depth + 1, make, use);
                use[i] = false;
            }
        }
    }

}