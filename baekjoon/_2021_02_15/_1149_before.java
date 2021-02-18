package baekjoon._2021_02_15;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class _1149_before {
    static int[][][] dp;
    static int[][] colorCost;

    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            dp = new int[1000][3][3];
            for (int i = 0; i < 1000; i++) {
                for (int j = 0; j < 3; j++) {
                    for (int k = 0; k < 3; k++) {
                        dp[i][j][k] = 1000000;
                    }
                }
            }
            int houseCount = Integer.parseInt(br.readLine());
            colorCost = new int[houseCount][3];
            for (int i = 0; i < houseCount; i++) {
                String[] inputCost = br.readLine().split(" ");
                colorCost[i][0] = Integer.parseInt(inputCost[0]);
                colorCost[i][1] = Integer.parseInt(inputCost[1]);
                colorCost[i][2] = Integer.parseInt(inputCost[2]);

            }
            int resultR = recursion(houseCount - 1, 0);
            int resultG = recursion(houseCount - 1, 1);
            int resultB = recursion(houseCount - 1, 2);
            System.out.println(Math.min(Math.min(resultR, resultG), resultB));
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static int recursion(int n, int preColor) {
        if (n == 0) {
            if (preColor == 0) {
                dp[n][0][preColor] = Math.min(colorCost[n][1], colorCost[n][2]);
                return dp[n][0][preColor];
            } else if (preColor == 1) {
                dp[n][1][preColor] = Math.min(colorCost[n][0], colorCost[n][2]);
                return dp[n][1][preColor];
            } else {
                dp[n][2][preColor] = Math.min(colorCost[n][1], colorCost[n][0]);
                return dp[n][2][preColor];
            }
        } else {
            if (preColor == 0) {
                dp[n][0][preColor] = Math.min(recursion(n - 1, 1) + colorCost[n][1], recursion(n - 1, 2) + colorCost[n][2]);
                return dp[n][0][preColor];
            } else if (preColor == 1) {
                dp[n][1][preColor] = Math.min(recursion(n - 1, 0) + colorCost[n][0], recursion(n - 1, 2) + colorCost[n][2]);
                return dp[n][1][preColor];
            } else {
                dp[n][2][preColor] = Math.min(recursion(n - 1, 1) + colorCost[n][1], recursion(n - 1, 0) + colorCost[n][0]);
                return dp[n][2][preColor];
            }
        }
    }
}
