package baekjoon._2021_02_05;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class _1003 {
    static int[][] dp;
    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        try {
            int testCase=Integer.parseInt(br.readLine());
            dp=new int[41][2];
            for(int i=0; i<testCase; i++){
                int n = Integer.parseInt(br.readLine());
                fibonacci(n);
                sb.append(dp[n][0]).append(" ").append(dp[n][1]).append("\n");
            }
            System.out.println(sb);
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static int[][] fibonacci(int n) {
        if (dp[n][0] == 0 && dp[n][1] == 0) {
            if (n == 0) {
                dp[0][0]++;
                return dp;
            }
            if (n == 1) {
                dp[1][1]++;
            } else {
                dp[n][0] = fibonacci(n - 1)[n - 1][0] + fibonacci(n - 2)[n - 2][0];
                dp[n][1] = fibonacci(n - 1)[n - 1][1] + fibonacci(n - 2)[n - 2][1];
            }
        }
        return dp;
    }
}
