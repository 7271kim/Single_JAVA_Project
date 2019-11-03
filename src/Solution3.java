import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
class Solution3 {
    public int[][] solution(int n, int[][] build_frame) {
        int N = n + 1, i;
        List<int[]> list = new ArrayList<>();
        boolean[][][] m = new boolean[N][N][2];

        for (i = 0; i < build_frame.length; i++) {
            int v[] = build_frame[i], x = v[0], y = v[1], a = v[2], b = v[3];

            if (a == 0) {
                if (b == 1 && isPillar(m, x, y)) add(list, m, x, y, 0);
                else if (b == 0) {
                    remove(list, m, x, y, 0);
                    if (isProblem(m, list)) add(list, m, x, y, 0);
                }
            } else {
                if (b == 1 && isBeam(m, x, y)) add(list, m, x, y, 1);
                else if (b == 0) {
                    remove(list, m, x, y, 1);
                    if (isProblem(m, list)) add(list, m, x, y, 1);
                }
            }
        }

        Collections.sort(list, new Comparator<int[]>() {
            public int compare(int[] o1, int[] o2) {
                int x1 = o1[0], y1 = o1[1], a1 = o1[2];
                int x2 = o2[0], y2 = o2[1], a2 = o2[2];

                if (x2 > x1) return -1;
                else if (x2 == x1) {
                    if (y2 > y1) return -1;
                    else if (y2 == y1) return a1 > a2 ? 1 : -1;
                    return 1;
                }
                return 1;
            }
        });

        int[][] answer = new int[list.size()][];

        for (i = 0; i < list.size(); i++)
            answer[i] = list.get(i);

        return answer;
    }

    static boolean isPillar(boolean[][][] m, int x, int y) {
        return y == 0 || m[x][y][1] || (x > 0 && m[x - 1][y][1]) || m[x][y - 1][0];
    }

    static boolean isBeam(boolean[][][] m, int x, int y) {
        int N = m.length;

        if (m[x][y - 1][0] || m[x + 1][y - 1][0]) return true;

        if (0 < x && x < N - 1 && m[x - 1][y][1] && m[x + 1][y][1]) return true;

        return false;
    }

    static boolean isProblem(boolean[][][] m, List<int[]> list) {
        for (int i = 0; i < list.size(); i++) {
            int v[] = list.get(i), x = v[0], y = v[1], a = v[2];

            if ((a == 0 && !isPillar(m, x, y)) || (a == 1 && !isBeam(m, x, y))) return true;
        }

        return false;
    }

    static void remove(List<int[]> list, boolean[][][] m, int x, int y, int a) {
        for (int i = 0; i < list.size(); i++) {
            int[] v = list.get(i);

            if (v[0] == x && v[1] == y && v[2] == a) {
                m[x][y][a] = false;
                list.remove(i);
                return;
            }
        }
    }

    static void add(List<int[]> list, boolean[][][] m, int x, int y, int a) {
        m[x][y][a] = true;
        list.add(new int[] { x, y, a});
    }
}
