package _2022._03_09.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class _17070_파이프_옮기기1 {

    public static int num;
    public static int[][] map;
    public static int[][][] dp; //y축 x축 방향(좌, 하, 대각)

    public static void main(String[] args) throws IOException {
        read();
        dfs(1, 0, Direction.right);
        System.out.println(dp[0][1][0]);
    }

    public static int dfs (int x, int y, Direction direction) {
        if (dp[y][x][direction.ordinal()] > 0) {
            return dp[y][x][direction.ordinal()];
        }

        boolean isRight = isMap(x+1, y);
        boolean isBottom = isMap(x , y+1);
        boolean isDiagonal = isMap(x+1, y+1);

        int count = 0;

        if (direction == Direction.right) {
            //오른쪽
            if (isRight) {
                count += dfs(x+1, y, Direction.right);
            }

            //대각선
            if (isRight && isBottom && isDiagonal) {
                count += dfs(x+1, y+1, Direction.diagonal);
            }
        }

        if (direction == Direction.bottom) {
            //아래
            if (isBottom) {
                count += dfs(x, y+1, Direction.bottom);
            }

            //대각선
            if (isRight && isBottom && isDiagonal) {
                count += dfs(x+1, y+1, Direction.diagonal);
            }
        }

        if (direction == Direction.diagonal) {
            //오른쪽
            if (isRight) {
                count += dfs(x+1, y, Direction.right);
            }
            //아래
            if (isBottom) {
                count += dfs(x, y+1, Direction.bottom);
            }

            //대각선
            if (isRight && isBottom && isDiagonal) {
                count += dfs(x+1, y+1, Direction.diagonal);
            }
        }

        dp[y][x][direction.ordinal()] = count;
        return dp[y][x][direction.ordinal()];
    }

    public static boolean isMap(int x, int y) {
        return x < num && x >= 0 && y < num && y >= 0 && map[y][x] == 0;
    }

    public static void read() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        num = Integer.parseInt(br.readLine());

        map = new int[num][num];
        dp = new int[num][num][3];

        for (int i = 0; i < 3; i++) {
            dp[num-1][num-1][i] = 1;
        }

        for (int i = 0; i < num; i++) {
            String[] line = br.readLine().split(" ");
            for (int j = 0; j < num; j++) {
                int location = Integer.parseInt(line[j]);
                if (location == 1) {
                    map[i][j] = 1;
                }
            }
        }
        br.close();
    }

    public enum Direction {
        right,
        bottom,
        diagonal
    }
}
