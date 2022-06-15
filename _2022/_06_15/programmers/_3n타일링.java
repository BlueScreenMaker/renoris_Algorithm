package _2022._06_15.programmers;

public class _3n타일링 {
    private final int DIVIDE = 1000000007;
    public int solution(int n) {
        if (n % 2 != 0) {
            return 0;
        }

        long[] dp = new long[n+1];
        dp[0] = 1;
        dp[2] = 3;

        if (n < 4) {
            return (int) dp[n];
        }

        for (int i = 4; i <= n; i = i +2) {
            dp[i] = (dp[i-2] * 3) % DIVIDE;

            for (int j = i - 4; j >= 0; j = j-2) {
                dp[i] += dp[j] * 2 ;
            }

            dp[i] %= DIVIDE;
        }

        return (int) dp[n];
    }
}
