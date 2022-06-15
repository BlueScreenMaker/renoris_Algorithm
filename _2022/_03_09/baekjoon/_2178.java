package _2022._03_09.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class _2178 {

    private static boolean[][] map;
    private static int[][] record;
    private static int objectX;
    private static int objectY;

    //위 왼 아래 오른
    public static int[] moveX = {0, -1, 0, 1};
    public static int[] moveY = {-1, 0, 1, 0};

    public static void main (String[] args) throws IOException {
        read();

        Queue<Node> bfs = new LinkedList<>();
        bfs.add(new Node(objectX-1, objectY-1));
        record[objectY-1][objectX-1] = 1;

        while (bfs.size() > 0) {
            Node node = bfs.poll();
            int x = node.getX();
            int y = node.getY();
            int length = record[y][x];

            for (int i = 0; i < 4; i++) {
                int movedX = x+moveX[i];
                int movedY = y+moveY[i];
                if(isMap(movedX, movedY)) {
                    if (record[movedY][movedX] > length+1) {
                        record[movedY][movedX] = length+1;
                        bfs.add(new Node(movedX, movedY));
                    }
                }
            }
        }
        System.out.println(record[0][0]);
    }

    public static boolean isMap(int x, int y) {
        return x < objectX && x >= 0 && y < objectY && y >= 0 && map[y][x];
    }

    private static void read() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] firstLine = br.readLine().split(" ");
        objectX = Integer.parseInt(firstLine[1]);
        objectY = Integer.parseInt(firstLine[0]);
        map = new boolean[objectY][objectX];
        record = new int[objectY][objectX];
        for (int i = 0; i < objectY; i++) {
            char[] line = br.readLine().toCharArray();
            Arrays.fill(record[i], 10001);

            for (int j = 0; j < line.length; j++) {
                if (line[j] == '1') {
                    map[i][j] = true;
                }else {
                    map[i][j] = false;
                }
            }
        }

        br.close();
    }

    static class Node {
        private int x;
        private int y;

        public Node(int x, int y) {
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
