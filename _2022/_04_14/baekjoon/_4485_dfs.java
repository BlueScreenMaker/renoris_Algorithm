package _2022._04_14.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class _4485_dfs {

    //위 왼 아래 오른
    public static int[] moveX = {0, -1, 0, 1};
    public static int[] moveY = {-1, 0, 1, 0};

    public static void main (String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringBuilder sb = new StringBuilder();

        int count = 0;
        while (true){
            count++;
            int[][] map = read(br);
            if (map == null) {
                break;
            }

            //지도 최대 길이
            int maxLength = map.length;
            //dp 초기 설정
            int[][] record = new int[maxLength][maxLength];
            fill(record, Integer.MAX_VALUE);

            //dfs
            dfs(0, 0, 0, map, record);

            //결과 저장
            String line = String.format("Problem %d: %d", count, record[map.length-1][map.length-1]);
            sb.append(line).append("\n");
        }

        br.close();
        System.out.println(sb.toString());
    }

    public static void dfs (int x, int y, int thiefRupee, int[][] map, int[][] record) {
        int hereThiefRupee = thiefRupee+map[x][y];

        if (hereThiefRupee > record[x][y]) {
            return;
        }

        record[x][y] = hereThiefRupee;

        for (int i = 0; i < 4; i++) {
            int movedX = x + moveX[i];
            int movedY = y + moveY[i];

            if (isMap(movedX, movedY, map.length)) {
                dfs(movedX, movedY, hereThiefRupee, map, record);
            }
        }
    }

    public static boolean isMap (int x, int y, int maxLength) {
        return x >= 0 && x < maxLength && y >= 0 && y < maxLength;
    }

    public static void fill(int[][] array ,int num) {
        for (int[] ints : array) {
            Arrays.fill(ints, num);
        }
    }

    public static int[][] read(BufferedReader br) throws IOException {
        int length = Integer.parseInt(br.readLine());

        if (length == 0) {
            return null;
        }

        int[][] map = new int[length][length];

        for (int i = 0; i < length; i++) {
            String[] line = br.readLine().split(" ");
            for (int j = 0; j < length; j++) {
                map[i][j] = Integer.parseInt(line[j]);
            }
        }
        return map;
    }
}
