package baekjoon._2021_02_26;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;

public class _2565 {
    static int[] dp;
    static int[][] line;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testcase = Integer.parseInt(br.readLine());
        line = new int[testcase][2];
        dp = new int[testcase];
        //라인입력
        for (int i = 0; i < testcase; i++) {
            String[] numbers = br.readLine().split(" ");
            line[i][0] = Integer.parseInt(numbers[0]);
            line[i][1] = Integer.parseInt(numbers[1]);
        }
        //정렬
        Arrays.sort(line, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });
        //가능한 최대로 이을수 있는 선의 갯수
        int max = 0;
        for (int i = 0; i < testcase; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (line[i][1] > line[j][1]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1); //이전값들 에서 가능한것+1 보다 현재값이 더높은가?
                }
            }
            max = Math.max(dp[i], max);
        }
        System.out.println(testcase - max);
    }
}
