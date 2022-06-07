package _2021._2021_02_15.baekjoon;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class _15990 {
    static long[][] dp;
    static int[] numbers;

    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        dp = new long[100001][3];
        try {
            dp[1][0] = dp[2][1] = dp[3][0] = dp[3][1] = dp[3][2] = 1;       //0=>1로 시작하는거
            int testcase = Integer.parseInt(br.readLine());
            numbers = new int[testcase];
            int max = 0;
            for (int i = 0; i < testcase; i++) {
                numbers[i] = Integer.parseInt(br.readLine());
                if (max < numbers[i]) max = numbers[i];
            }

            for (int i = 4; i <= max; i++) {
                dp[i][0] = (dp[i - 1][1]% 1000000009 + dp[i - 1][2]% 1000000009)%1000000009;
                dp[i][1] = (dp[i - 2][0]% 1000000009 + dp[i - 2][2]% 1000000009)%1000000009;
                dp[i][2] = (dp[i - 3][0]% 1000000009 + dp[i - 3][1]% 1000000009)%1000000009;
            }
            for (int i = 0; i < numbers.length; i++) {
                long result = (dp[numbers[i]][0]%1000000009+dp[numbers[i]][1]%1000000009+dp[numbers[i]][2]%1000000009)%1000000009;
                sb.append(result).append("\n");
            }
            System.out.println(sb);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//------------------재귀

    static long[][] dp2;

    public static void main2(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        dp2 = new long[100001][4];
        try {
            dp2[1][0] = dp2[2][1] = dp2[3][0] = dp2[3][1] = dp2[3][2] = 1;       //0=>1로 시작하는거
            int testcase = Integer.parseInt(br.readLine());
            for (int i = 0; i < testcase; i++) {
                int number = Integer.parseInt(br.readLine());
                recursion2(number, 0);
                recursion2(number, 1);
                recursion2(number, 2);
                long result = (dp2[number][0] + dp2[number][1] + dp2[number][2])/1000000009;
                sb.append(result).append("\n");
            }
            System.out.println(sb);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static long recursion2(int number, int locate) {
        if (number <= 0) {
            return 0;
        }
        if (dp2[number][locate] != 0) {
            return dp2[number][locate];

        } else {
            if (locate == 0) {
                dp2[number][locate] = recursion2(number - 1, 1) + recursion2(number - 1, 2);
            }
            if (locate == 1) {
                dp2[number][locate] = recursion2(number - 2, 0) + recursion2(number - 2, 2);
            }
            if (locate == 2) {
                dp2[number][locate] = recursion2(number - 3, 0) + recursion2(number - 3, 1);
            }
            return dp2[number][locate];
        }
    }
}
