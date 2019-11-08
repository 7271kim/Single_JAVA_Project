class Solution3 {
    int N = 0;

    public int solution(int n) {
        int answer = 0;
        int[] d = new int[n+1];
        d[0] = 1;
        d[1] = 0;
        d[2] = 3;
        for(int i = 3;i<n+1;i++) {
            if(i%2 == 1) {
                continue;
            }
            d[i] = 3*d[i-2];
            for (int j = (i - 4); j >= 0; j -= 2) {
                d[i] += (2 * d[j]);
            }
            d[i]%=1000000007;
        }
        answer = d[d.length-1];
        return answer;
    }
}