package _2022._02_23.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class _4811_알약 {
    public static long[] dp;
    public static Queue<Integer> testCases;
    public static int max;

    public static void main (String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        read();
        basicSetting();

        int size = testCases.size();
        for (int i = 0; i < size; i++) {
            sb.append(dp[testCases.poll()]).append("\n");
        }
        System.out.println(sb.toString());
    }

    public static void basicSetting() {

        dp = new long[31];

        dp[0] = 1;
        dp[1] = 1;
        dp[2] = 2;

        for (int i = 3; i <= max; i++) {
            int start = i-1;
            for (int j = start; j >= 0; j--) {
                dp[i] += dp[j] * dp[start-j];
            }
        }
    }

    //3개의 전체알약이 있을때
    //


    public static void read() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        testCases = new LinkedList<>();

        int temp = -1;
        max = 0;

        do{
            temp = Integer.parseInt(br.readLine());
            if (temp == 0) {
                break;
            }

            if (max < temp) {
                max = temp;
            }

            testCases.add(temp);

        } while (true);
        br.close();
    }
}
