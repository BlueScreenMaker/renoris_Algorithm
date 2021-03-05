package baekjoon._2021_03_05;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class _1912 {
    static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testcase = Integer.parseInt(br.readLine());
        dp = new int[testcase];

        String[] stNumbers = br.readLine().split(" ");
        int[] numbers = new int[testcase];
        numbers[0] = Integer.parseInt(stNumbers[0]);
        dp[0] = numbers[0];
        int max = dp[0];

        for (int i = 1; i < stNumbers.length; i++) {
            numbers[i] = Integer.parseInt(stNumbers[i]);
            dp[i] = Math.max(dp[i - 1] + numbers[i], numbers[i]);
            if (max < dp[i]) max = dp[i];
        }

        //결과찾기
        System.out.println(max);
    }
}
