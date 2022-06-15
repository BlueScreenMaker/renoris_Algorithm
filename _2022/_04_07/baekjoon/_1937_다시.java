package _2022._04_07.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class _1937_다시 {
    //위 왼 아래 오른
    public static int[] moveX = {0, -1, 0, 1};
    public static int[] moveY = {-1, 0, 1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[][] map = read(br);
        int maxLength  = map.length;

        int max = 0;
        for (int i = 0; i < maxLength; i++) {
            for (int j = 0; j < maxLength; j++) {
                int result = bfs(i, j, map);

                if (max < result) {
                    max = result;
                }
            }
        }

        System.out.println(max+1);
    }

    private static int bfs(int x, int y, int[][] map) {
        Queue<Node> queue = new LinkedList();
        Node firstNode = new Node(x, y, 0);
        queue.add(firstNode);

        int maxLength = 0;

        while (queue.size() > 0) {
            Node node = queue.poll();

            int length = node.getLength();
            for (int i = 0; i < 4; i++) {
                int movedX = node.getX() + moveX[i];
                int movedY = node.getY() + moveY[i];

                if (isMap(movedX, movedY, map.length) && map[node.getY()][node.getX()] < map[movedY][movedX]) {
                    queue.add(new Node(movedX, movedY, length+1));
                    if (maxLength < length+1) {
                        maxLength = length+1;
                    }
                }
            }
        }

        return maxLength;

    }

    static class Node {
        private int x;
        private int y;
        private int length;

        public Node(int x, int y, int length) {
            this.x = x;
            this.y = y;
            this.length = length;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public int getLength() {
            return length;
        }
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
