package _2022._08_03.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;

public class _1941_소문난_칠공주 {

    private static boolean[][] map = new boolean[5][5];
    private static HashSet<Integer> hashSet = new HashSet<>();

    private static int result = 0;

    public static void main(String[] args) throws IOException {
        read();
        boolean[][] record = new boolean[5][5];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                int sNum = map[i][j] ? 1 : 0;
                record[i][j] = true;
                dfs(record, sNum, 1);
                record[i][j] = false;
            }
        }

        System.out.println(result);
    }

    private static void dfs(boolean[][] record, int sNum, int totalNum) {
        if (totalNum > sNum + 3) {
            return;
        }

        if (totalNum >= 7) {
            if (sNum > 3) {
                int convertedRecord = convertRecordToInteger(record);
                if (!hashSet.contains(convertedRecord)) {
                    hashSet.add(convertedRecord);
                    result++;
                }
            }
            return;
        }

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (!record[i][j] && canPrincess(j, i, record)) {
                    record[i][j] = true;
                    int sNum2 = sNum + (map[i][j] ? 1 : 0);
                    dfs(record, sNum2, totalNum + 1);
                    record[i][j] = false;
                }
            }
        }
    }

    private static int convertRecordToInteger(boolean[][] record) {
        int result = 0;

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                result += (record[i][j] ? 1 : 0);
                result = result << 1;
            }
        }

        return result;
    }

    public static boolean canPrincess(int x, int y, boolean[][] record) {
        return (isMap(x + 1, y) && record[y][x + 1]) ||
                (isMap(x - 1, y) && record[y][x - 1]) ||
                (isMap(x, y + 1) && record[y + 1][x]) ||
                (isMap(x, y - 1) && record[y - 1][x]);
    }

    public static boolean isMap(int x, int y) {
        return x > -1 && x < 5 && y > -1 && y < 5;
    }


    private static void read() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < 5; i++) {
            char[] line = br.readLine().toCharArray();
            for (int j = 0; j < 5; j++) {
                if (line[j] == 'S') map[i][j] = true;
            }

        }
    }
}
