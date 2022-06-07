package _2022._2022_03_30.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class _4963 {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    //12시부터 시계방향
    private static int[] xFind = {0, 1, 1, 1, 0, -1, -1, -1};
    private static int[] yFind = {1, 1, 0, -1, -1, -1, 0, 1};

    public static void main(String[] args) throws IOException {
        String firstLine = br.readLine();
        StringBuilder sb = new StringBuilder();
        while (!firstLine.equals("0 0")) {
            sb.append(analysisMap(firstLine)).append("\n");
            firstLine = br.readLine();
        }
        br.close();
        System.out.println(sb.toString());
    }

    public static int analysisMap (String firstLine) throws IOException {
        boolean[][] map = makeMap(firstLine);
        int maxX = map[0].length;
        int maxY = map.length;

        boolean[][] visit = new boolean[maxY][maxX];

        int count = 0;

        for (int i = 0; i < maxY; i++) {
            for (int j = 0; j < maxX; j++) {
                if (findIsLand(j, i, maxX, maxY, map, visit)) count++;
            }
        }

        return count;
    }

    private static boolean findIsLand(int x, int y, int maxX, int maxY, boolean[][] map, boolean[][] visit) {
        if (visit[y][x] || !map[y][x]) {
            return false;
        }

        visit[y][x] = true;

        //8방향 탐색
        for (int i = 0; i < 8; i++) {
            int movedX = x+xFind[i];
            int movedY = y+yFind[i];
            if (isMap(movedX, movedY, maxX, maxY)) {
                findIsLand(movedX, movedY, maxX, maxY, map, visit);
            }
        }

        return true;
    }

    public static boolean isMap(int x, int y, int maxX, int maxY) {
        return x >= 0 && x < maxX && y >=0 && y < maxY;
    }

    private static boolean[][] makeMap(String firstLine) throws IOException {
        String[] mapSize = firstLine.split(" ");
        int width = Integer.parseInt(mapSize[0]);
        int height = Integer.parseInt(mapSize[1]);

        boolean[][] map = new boolean[height][width];

        for (int i = 0; i < height; i++) {
            String[] line = br.readLine().split(" ");
            for (int j = 0; j < width; j++) {
                if (line[j].equals("1")) {
                    map[i][j] = true;
                } else {
                    map[i][j] = false;
                }
            }
        }

        return map;
    }
}
