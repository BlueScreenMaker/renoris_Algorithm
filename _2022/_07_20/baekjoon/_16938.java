package _2022._07_20.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class _16938 {
    //백준 - 캠프준비 - 16938
    //https://www.acmicpc.net/problem/16938

    private static int maxDifficult;
    private static int minDifficult;
    private static int intervalDifficult;
    private static int[] problemDifficult;
    private static int result;
    public static void main(String[] args) throws IOException {
        read();
        Arrays.sort(problemDifficult);
        boolean[] check;
        for (int i = 0; i < problemDifficult.length; i++) {
            check = new boolean[problemDifficult.length];
            dfs(i, 0, check);
        }
        System.out.println(result);
    }

    private static void dfs(int index, int sum, boolean[] check) {
        check[index] = true;
        sum += problemDifficult[index];

        if (sum >= minDifficult && sum <= maxDifficult) {
            int minCheckIndex = 0;
            int maxCheckIndex = 0;
            int checkCount = 0;
            boolean minCheck = false;
            for (int i = 0; i < check.length; i++) {
                if (check[i]){
                    if (!minCheck) {
                        minCheckIndex = i;
                        minCheck = true;
                    }
                    checkCount++;
                }
            }

            for (int i = check.length-1; i >= 0; i--) {
                if (check[i]){
                    maxCheckIndex = i;
                    break;
                }
            }

            if (problemDifficult[maxCheckIndex] - problemDifficult[minCheckIndex] >= intervalDifficult && checkCount > 1) {
                result++;
            }
        }


        for (int i = index; i < problemDifficult.length; i++) {
            if (check[i]) continue;
            if (sum + problemDifficult[index] > maxDifficult) continue;
            dfs(i, sum, check);
        }

        check[index] = false;

    }

    public static void read() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] firstLine = br.readLine().split(" ");

        int problem = Integer.parseInt(firstLine[0]);
        minDifficult = Integer.parseInt(firstLine[1]);
        maxDifficult = Integer.parseInt(firstLine[2]);
        intervalDifficult = Integer.parseInt(firstLine[3]);
        problemDifficult = new int[problem];

        String[] secondLine = br.readLine().split(" ");
        for (int i = 0; i < problem; i++) {
            problemDifficult[i] = Integer.parseInt(secondLine[i]);
        }

        br.close();
    }
}
