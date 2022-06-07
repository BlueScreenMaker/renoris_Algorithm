package _2022._2022_03_09.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class _14567_선수과목 {
    public static int number; //과목의수
    public static ArrayList<Integer>[] graph;
    public static int[] dp;

    public static void main(String[] args) throws IOException {
        read();

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < dp.length; i++) {
            dfs(i);
            sb.append(dp[i]).append(" ");
        }

        System.out.println(sb.toString());
    }

    public static int dfs(int findNum) {
        if (dp[findNum] > 0) {
            return dp[findNum];
        }

        int count = 0;
        for (int num : graph[findNum]) {
            int result = dfs(num);
            if (result > count) {
                count = result;
            }
        }

        dp[findNum] = count+1;
        return dp[findNum];
    }

    public static void read() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] firstLine = br.readLine().split(" ");

        number = Integer.parseInt(firstLine[0]);

        dp = new int[number+1];
        graph = new ArrayList[number+1];

        for (int i = 0; i <= number; i++) {
            graph[i] = new ArrayList<Integer>();
        }

        int preRequiredNumber = Integer.parseInt(firstLine[1]);
        for (int i = 0; i < preRequiredNumber; i++) {
            String[] line = br.readLine().split(" ");
            int pre = Integer.parseInt(line[0]);
            int object = Integer.parseInt(line[1]);

            graph[object].add(pre);
        }
    }
}
