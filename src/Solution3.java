class Solution3 {
    int N = 0;

    public int solution(int[][] board) {
        java.util.Set<String> visited = new java.util.HashSet<>();
        this.N = board.length;
        java.util.Queue<State> q = new java.util.LinkedList<>(); 
        State first = new State(0, 0, false, 0);
        visited.add(first.key());
        q.offer(first);

        while (!q.isEmpty()) {
            State state = q.poll();
            if (state.p2()[0] == N-1 && state.p2()[1] == N-1) {
                return state.move;
            }

            java.util.List<State> results = new java.util.ArrayList<>();
            results.addAll(move(state));
            results.addAll(rotate(state, board));
            results = results.stream()
                .filter(s -> valid(board, s))
                .filter(s -> !visited.contains(s.key()))
                .collect(java.util.stream.Collectors.toList());
            results.forEach(s -> {
                visited.add(s.key());
                q.offer(s);
            });
        }

        return -1;
    }

    private java.util.List<State> move(State s) {
        java.util.List<State> results = new java.util.ArrayList<>();
        results.add(new State(s.x-1, s.y, s.vertical, s.move+1));
        results.add(new State(s.x, s.y-1, s.vertical, s.move+1));
        results.add(new State(s.x+1, s.y, s.vertical, s.move+1));
        results.add(new State(s.x, s.y+1, s.vertical, s.move+1));
        return results;
    }

    private java.util.List<State> rotate(State s, int[][] board) {
        java.util.List<State> results = new java.util.ArrayList<>();
        if (s.vertical) {
            if (s.x > 0 && board[s.y+1][s.x-1] == 0) {
                results.add(new State(s.x-1, s.y, false, s.move+1));
            }
            if (s.x > 0 && board[s.y][s.x-1] == 0) {
                results.add(new State(s.x-1, s.y+1, false, s.move+1));
            }
            if (s.x < N-1 && board[s.y+1][s.x+1] == 0) {
                results.add(new State(s.x, s.y, false, s.move+1));
            }
            if (s.x < N-1 && board[s.y][s.x+1] == 0) {
                results.add(new State(s.x, s.y+1, false, s.move+1));
            }
        } else {
            if (s.y > 0 && board[s.y-1][s.x+1] == 0) {
                results.add(new State(s.x, s.y-1, true, s.move+1));
            }
            if (s.y > 0 && board[s.y-1][s.x] == 0) {
                results.add(new State(s.x+1, s.y-1, true, s.move+1));
            }
            if (s.y < N-1 && board[s.y+1][s.x+1] == 0) {
                results.add(new State(s.x, s.y, true, s.move+1));
            }
            if (s.y < N-1 && board[s.y+1][s.x] == 0) {
                results.add(new State(s.x+1, s.y, true, s.move+1));
            }
        }
        return results;
    }

    private boolean valid(int[][] board, State state) {
        int[] p1 = state.p1();
        int[] p2 = state.p2();
        if (p1[0] < 0 || p1[1] < 0 || p1[0] >= N || p1[1] >= N ||
            p2[0] < 0 || p2[1] < 0 || p2[0] >= N || p2[1] >= N) {
            return false;
        }
        if (board[p1[1]][p1[0]] == 1 || board[p2[1]][p2[0]] == 1) {
            return false;
        }
        return true;
    }

    class State {
        int x;
        int y;
        boolean vertical;
        int move;

        public State(int x, int y, boolean vertical, int move) {
            this.x = x;
            this.y = y;
            this.vertical = vertical;
            this.move = move;
        }

        public int[] p1() {
            return new int[]{x, y};
        }
        public int[] p2() {
            return vertical ? new int[]{x, y+1} : new int[]{x+1, y};
        }
        public String key() {
            return x + "," + y + "," + (vertical ? 1 : 0);
        }
    }
}