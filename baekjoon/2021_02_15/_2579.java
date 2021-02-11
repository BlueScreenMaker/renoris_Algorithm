package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class _2579 {
    static int[][] dp = new int[301][2];
    static int[] stair = new int[301];

    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            int stairNum = Integer.parseInt(br.readLine());
            for (int i = 0; i < stairNum; i++) {
                stair[i] = Integer.parseInt(br.readLine());
            }
            dp[0][0] = stair[0];
            dp[1][0] = stair[1];
            dp[1][1] = stair[1] + stair[0];
            for (int i = 2; i < stairNum; i++) {
                dp[i][0] = stair[i] + Math.max(dp[i - 2][0], dp[i - 2][1]);
                dp[i][1] = stair[i] + dp[i - 1][0];
//                dp[i][1] = stair[i] + Math.max(Math.max(dp[i - 1][0], dp[i - 2][0]), dp[i - 2][1]); 속도는 이게 더나옴... 미스터리...
            }
            System.out.println(Math.max(dp[stairNum - 1][0], dp[stairNum - 1][1]));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    static int[] dp2;
    static int[] stair2;

    public static void main2(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        dp2 = new int[301];
        try {
            int stairNum = Integer.parseInt(br.readLine());
            stair2 = new int[stairNum];
            for (int i = 0; i < stairNum; i++) {
                stair2[i] = Integer.parseInt(br.readLine());
            }
            int result;
            if (stairNum == 2) {
                result = stair2[0] + stair2[1];
            } else {
                int notchain = recursion(stairNum - 3, true);
                int chain = recursion(stairNum - 2, false);
                result = Math.max(notchain + stair2[stairNum - 1], chain);
            }
            System.out.println(result);
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static int recursion(int stairNum, boolean notChain) {
        if (stairNum >= 0) {
            if (dp2[stairNum] != 0) {
                return dp2[stairNum];
            }
            if (stairNum == 0) {
                dp2[stairNum] = stair2[stairNum];
                return dp2[stairNum];
            }
            if (notChain) {
                dp2[stairNum] = stair2[stairNum] + Math.max(recursion(stairNum - 1, false), recursion(stairNum - 2, true));
            } else {
                dp2[stairNum] = stair2[stairNum] + recursion(stairNum - 2, true);
            }
            return dp2[stairNum];
        } else {
            return 0;
        }
    }
}
