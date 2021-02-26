package baekjoon._2021_02_26;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class _11053 {
    static int[] dp;
    static int[] numbers;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testcase = Integer.parseInt(br.readLine());
        String[] preNumbers = br.readLine().split(" ");
        numbers = new int[testcase];
        dp = new int[testcase];
        //입력부분
        for (int i = 0; i < testcase; i++) {
            numbers[i] = Integer.parseInt(preNumbers[i]);
        }

        //연산
        for (int i = 0; i < testcase; i++) {
            recursion(i, numbers[i], testcase);
        }
        
        //결과 찾기
        int max = 0;
        for (int i = 0; i < dp.length; i++) {
            if (max < dp[i] + 1) {
                max = dp[i] + 1;
            }
        }
        System.out.println(max);
    }


    public static int recursion(int num, int max, int limit) {
        if (num < limit) {
            if (dp[num] != 0) {
                return dp[num];
            }
            for (int i = num + 1; i < limit; i++) {
                if (numbers[i] > max) {
                    dp[num] = Math.max(1 + recursion(i, numbers[i], limit), dp[num]);
                }
            }
            return dp[num];
        } else {
            return 0;
        }
    }
}
