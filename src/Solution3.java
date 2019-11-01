class Solution3 {
    private static int[][] key_1;
    private static int[][] key_2;
    private static int[][] key_3;
    private static int[][] key_4;
    private static int[][] lock_2;
    private static int cnt;
    private static boolean flag;
    private static int aa;

    public boolean solution(int[][] key, int[][] lock) {
        boolean answer = false;
        // 자물쇠의 맵이 주어졌고.
        // key맵도 주어졌으니.둘다 최대 20x20 맵
        // 완탐을 해야할거 같음.
        // lock 맵의 한칸마다 key맵을 넣어봐야할듯.(최대 22x22x4(각도전환))
        // 모든 lock의 빈칸을 해결했고, 들어맞는 곳이 있으면 return
        // 넣는걸 어떻게 구현하는지가 관건?

        // 일단 key를 각도 돌린 애들을 미리 구해놔
        key_1 = new int[key.length][key.length];
        key_2 = new int[key.length][key.length];
        key_3 = new int[key.length][key.length];
        key_4 = new int[key.length][key.length];
        for (int i = 0; i < key.length; i++) {
            for (int j = 0; j < key.length; j++) {
                key_1[i][j] = key[i][j];
            }
        }
        for (int i = 0; i < key.length; i++) {
            for (int j = 0; j < key.length; j++) {
                key_2[j][key.length - i - 1] = key_1[i][j];
            }
        }
        for (int i = 0; i < key.length; i++) {
            for (int j = 0; j < key.length; j++) {
                key_3[j][key.length - i - 1] = key_2[i][j];
            }
        }
        for (int i = 0; i < key.length; i++) {
            for (int j = 0; j < key.length; j++) {
                key_4[j][key.length - i - 1] = key_3[i][j];
            }
        }

        for (int i = 0; i < lock.length; i++) { // 채워야 하는 홈의 갯수 세주고
            for (int j = 0; j < lock.length; j++) {
                if(lock[i][j] == 0)
                    cnt++;
            }
        }

        // lock의 맵을 더 크게 만들자.
        lock_2 = new int[lock.length + (key.length - 1) * 2][lock.length + (key.length - 1) * 2];
        for (int i = 0; i < lock_2.length; i++) {
            for (int j = 0; j < lock_2.length; j++) {
                lock_2[i][j] = 2;
            }
        }
        for (int i = 0; i < lock.length; i++) {
            for (int j = 0; j < lock.length; j++) {
                lock_2[i + key.length - 1][j + key.length - 1] = lock[i][j];
            }
        }

        for (int i = 0; i < lock_2.length; i++) {
            for (int j = 0; j < lock_2.length; j++) {
            }
        }
        int tempCnt = cnt;
        loop: for (int i = 0; i < lock.length + (key.length - 1); i++) {
            for (int j = 0; j < lock.length + (key.length - 1); j++) {
                for (int p = 0; p < 4; p++) {
                    go(i, j, p);
                    if(cnt == 0 && flag == false) {
                        answer = true;
                        break loop;
                    }
                    cnt = tempCnt;
                    flag = false;
                }
            }
        }
        return answer;
    }
    private static void go(int i, int j, int p) {
        // p는 각도
        if (p == 0) {
            for (int k = 0; k < key_1.length; k++) {
                for (int k2 = 0; k2 < key_1.length; k2++) {
                    if(lock_2[k+i][k2+j] == 2)
                        continue;
                    if(lock_2[k+i][k2+j] == 1 && key_1[k][k2] == 1)
                        flag = true;
                    if(lock_2[k+i][k2+j] == 0 && key_1[k][k2] == 1)
                        cnt--;
                }
            }
        } else if (p == 1) {
            for (int k = 0; k < key_1.length; k++) {
                for (int k2 = 0; k2 < key_1.length; k2++) {
                    if(lock_2[k+i][k2+j] == 2)
                        continue;
                    if(lock_2[k+i][k2+j] == 1 && key_2[k][k2] == 1)
                        flag = true;
                    if(lock_2[k+i][k2+j] == 0 && key_2[k][k2] == 1)
                        cnt--;
                }
            }
        } else if (p == 2) {
            for (int k = 0; k < key_1.length; k++) {
                for (int k2 = 0; k2 < key_1.length; k2++) {
                    if(lock_2[k+i][k2+j] == 2)
                        continue;
                    if(lock_2[k+i][k2+j] == 1 && key_3[k][k2] == 1)
                        flag = true;
                    if(lock_2[k+i][k2+j] == 0 && key_3[k][k2] == 1)
                        cnt--;
                }
            }
        } else if (p == 3) {
            for (int k = 0; k < key_1.length; k++) {
                for (int k2 = 0; k2 < key_1.length; k2++) {
                    if(lock_2[k+i][k2+j] == 2)
                        continue;
                    if(lock_2[k+i][k2+j] == 1 && key_4[k][k2] == 1)
                        flag = true;
                    if(lock_2[k+i][k2+j] == 0 && key_4[k][k2] == 1)
                        cnt--;
                }
            }
        }
    }
}