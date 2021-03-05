package baekjoon._2021_03_05;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class _9251 {
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        //입력부
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        char[] line1=line.toCharArray();
        line = br.readLine();
        char[] line2=line.toCharArray();

        dp = new int[line1.length + 1][line2.length + 1];

        //계산부
        for (int i = 1; i <= line1.length; i++) {//line1=행
            for (int j = 1; j <= line2.length; j++) {//line2=열
                if (line1[i-1]==line2[j-1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        //결과 추출
        System.out.println(dp[line1.length][line2.length]);
    }
}
