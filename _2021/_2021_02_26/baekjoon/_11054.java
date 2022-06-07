package _2021._2021_02_26.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class _11054 {
    static int[][] dp;
    static int[] numbers;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testcase = Integer.parseInt(br.readLine());
        String[] preNumbers = br.readLine().split(" ");
        numbers = new int[testcase];
        dp = new int[testcase][2];
        int result = 0;
        if (testcase < 3) {
            result = testcase;
        } else {
            //수 셋팅
            for (int i = 0; i < testcase; i++) {
                numbers[i] = Integer.parseInt(preNumbers[i]);
            }

            for (int i = 0; i < dp.length; i++) {
                makeDp(i, numbers[i], 0);
            }
            for (int i = dp.length - 1; i >= 0; i--) {
                makeDp(i, numbers[i], 1);
            }

            for (int i = 0; i < dp.length - 1; i++) {
                if (result < dp[i][0] + dp[i][1]) {
                    result = dp[i][0] + dp[i][1];
                }
            }
            result--;
        }
        System.out.println(result);
    }


    public static int makeDp(int num, int here, int isup) {
        int max = 0;
        if (isup == 0) {
            for (int i = 0; i < num; i++) {
                if (here > numbers[i]) {
                    if (max < dp[i][0]) {
                        max = dp[i][0];
                    }
                }
            }
            return dp[num][0] = max + 1;
        } else {
            for (int i = dp.length - 1; i > num; i--) {
                if (here > numbers[i]) { //거꾸로 가기때문에 크다고 비교해줘야함
                    if (max < dp[i][1]) {
                        max = dp[i][1];
                    }
                }
            }
            return dp[num][1] = max + 1;
        }

    }
}
