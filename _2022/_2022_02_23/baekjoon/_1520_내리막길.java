package _2022._2022_02_23.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class _1520_내리막길 {
    public static int goalX;
    public static int goalY;

    public static int[][] map;
    public static int[][] dp;

    //위, 왼쪽, 아래, 오른쪽
    public static int[] xControl = {0, -1, 0, 1};
    public static int[] yControl = {1, 0, -1, 0};

    public static void main (String[] args) throws IOException {
        read();
        dfs(0,0);
        System.out.println(dp[0][0]);
    }

    public static int dfs(int x, int y) {
        if (x == goalX-1 && y == goalY-1) {
            return 1;
        }

        if (dp[y][x] != -1) {
            return dp[y][x];
        }

        dp[y][x] = 0;
        int hereHeight = map[y][x];

        for (int i = 0; i < 4; i++) {
            int nextX = x + xControl[i];
            int nextY = y + yControl[i];

            if (isMap(nextX, nextY) && map[nextY][nextX] < hereHeight) {
                dp[y][x] += dfs(nextX, nextY);
            }
        }

        return dp[y][x];
    }

    public static boolean isMap(int x, int y) {
        return x > -1 && x < goalX && y > -1 && y < goalY;
    }


    public static void read() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] firstLine = br.readLine().split(" ");
        goalX = Integer.parseInt(firstLine[1]);
        goalY = Integer.parseInt(firstLine[0]);

        map = new int[goalY][goalX];
        dp = new int[goalY][goalX];

        for (int i = 0; i < goalY; i++) {
            String[] parsedLine = br.readLine().split(" ");
            for (int j = 0; j < goalX; j++) {
                map[i][j] = Integer.parseInt(parsedLine[j]);
                dp[i][j] = -1;
            }
        }
        br.close();
    }
}
