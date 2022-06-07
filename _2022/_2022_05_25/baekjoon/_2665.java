package _2022._2022_05_25.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class _2665 {

    private static int width;
    private static boolean[][] map;
    private static int[][] record;
    private static int[] moveX = {0, 1, 0 ,-1};
    private static int[] moveY = {1, 0 ,-1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        read(br);
        dijkstra();

        System.out.println(record[width-1][width-1]);
    }

    private static void dijkstra() {
        Queue<Node> queue = new LinkedList<>();

        queue.add(new Node(0,0));
        record[0][0] = 0;

        while (queue.size() > 0) {
            Node node = queue.poll();

            int x = node.getX();
            int y = node.getY();
            int value = record[y][x];

            for (int i = 0; i < 4; i++) {
                int movedX = x+moveX[i];
                int movedY = y+moveY[i];

                if (isMap(movedX, movedY)) {
                    int movedValue = map[movedY][movedX] ? value : value+1;
                    if (record[movedY][movedX] > movedValue) {
                        queue.add(new Node(movedX, movedY));
                        record[movedY][movedX] = movedValue;
                    }
                }

            }
        }
    }


    public static boolean isMap (int x, int y) {
        return x >= 0 && x < width && y >= 0 && y < width;
    }

    public static boolean[][] read(BufferedReader br) throws IOException {
        width = Integer.parseInt(br.readLine());

        map = new boolean[width][width];
        record = new int[width][width];

        for (int i = 0; i < width; i++) {
            char[] line = br.readLine().toCharArray();
            for (int j = 0; j < width; j++) {
                map[i][j] = (line[j] - '0') == 1;
                record[i][j] = Integer.MAX_VALUE;
            }
        }

        br.close();

        return map;
    }

    static class Node {
        private final int x;
        private final int y;

        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }
    }
}
