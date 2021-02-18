package baekjoon._2021_02_19;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class _2156 {
    static int[][] dp = new int[10001][2];
    static int[] wine = new int[10001];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int wineValue = Integer.parseInt(br.readLine());
        for (int i = 0; i < wineValue; i++) {
            wine[i] = Integer.parseInt(br.readLine());
        }
        dp[0][0] = wine[0];
        dp[1][0] = wine[1];
        dp[1][1] = wine[1] + wine[0];
        for (int i = 2; i <= wineValue; i++) {
            if(i>2){
                dp[i][0] = wine[i] + Math.max(Math.max(dp[i - 2][0], dp[i - 2][1]),Math.max(dp[i - 3][0],dp[i - 3][1]));
            }else{
                dp[i][0] = wine[i] + Math.max(dp[i - 2][0], dp[i - 2][1]);
            }
            dp[i][1] = wine[i] + Math.max(Math.max(dp[i - 1][0], dp[i - 2][0]), dp[i - 2][1]);
        }
        int result = Math.max(Math.max(dp[wineValue][0], dp[wineValue][1]), dp[wineValue-1][1]);
        System.out.println(result);

    }
}
