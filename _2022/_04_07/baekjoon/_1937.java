package _2022._04_07.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class _1937 {
    public static int[][] record;

    //위 왼 아래 오른
    public static int[] moveX = {0, -1, 0, 1};
    public static int[] moveY = {-1, 0, 1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[][] map = read(br);
        int maxLength  = map.length;
        record = new int[maxLength][maxLength];

        int max = 0;
        for (int i = 0; i < maxLength; i++) {
            for (int j = 0; j < maxLength; j++) {
                int result = dfs(i, j, map);

                if (max < result) {
                    max = result;
                }
            }
        }

        System.out.println(max);
    }

    public static int dfs(int x, int y, int[][] map) {
        if (record[y][x] > 0) {
            return record[y][x];
        }

        int length = 0;
        boolean isFind = false;

        for (int i = 0; i < 4; i++) {
            int movedX = x + moveX[i];
            int movedY = y + moveY[i];

            if (isMap(movedX, movedY, map.length) && map[movedY][movedX] > map[y][x]) {
                int result = dfs(movedX, movedY, map);
                isFind = true;
                if (result > length) {
                    length = result;
                }
            }
        }

        if (!isFind) {
            return 1;
        }

        record[y][x] = length+1;

        return record[y][x];
    }

    // 그래프 내부인지 탐색
    private static boolean isMap (int x, int y, int maxLength) {
        return x >= 0 && x < maxLength && y >= 0 && y < maxLength;
    }

    /**
     * graph -> map
     */
    public static int[][] read(BufferedReader br) throws IOException {
        int length = Integer.parseInt(br.readLine());

        int[][] map = new int[length][length];

        for (int i = 0; i < length; i++) {
            String[] line = br.readLine().split(" ");
            for (int j = 0; j < length; j++) {
                map[i][j] = Integer.parseInt(line[j]);
            }
        }
        br.close();
        return map;
    }
}
