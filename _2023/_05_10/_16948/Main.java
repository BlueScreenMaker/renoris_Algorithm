package _2023._05_10._16948;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

    private static int mapSize;
    private static int[][] check;
    /**
     *
     (y-2, x-1), (y-2, x+1), (y, x-2), (y, x+2), (y+2, x-1), (y+2, x+1)
     */
    private static final int[] directionX = {-1, 1, -2, 2, -1, +1};
    private static final int[] directionY = {-2, -2, 0, 0, 2, 2};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        mapSize = Integer.parseInt(br.readLine());
        check = new int[mapSize][mapSize];
        Integer[] secondLine = Arrays.stream(br.readLine().split(" ")).map(Integer::parseInt).toArray(Integer[]::new);
        Node firstNode = new Node(secondLine[1], secondLine[0]);

        for (int i = 0; i < check.length; i++) {
            Arrays.fill(check[i], Integer.MAX_VALUE);
        }

        check[secondLine[0]][secondLine[1]] = 0;

        Node resultNode = new Node(secondLine[3], secondLine[2]);
        Queue<Node> queue = new LinkedList<>();
        queue.add(firstNode);

        int result = -1;

        while (queue.size() > 0) {
            Node node = queue.poll();
            int moveCount = check[node.y][node.x];

            if (node.equals(resultNode)) {
                result = moveCount;
                break;
            }

            for (int i = 0; i < directionX.length; i++) {
                int x = node.x + directionX[i];
                int y = node.y + directionY[i];
                int movedCount = check[node.y][node.x] + 1;
                if (isMap(x, y) && check[y][x] > movedCount) {
                    queue.add(new Node(x, y));
                    check[y][x] = movedCount;
                }
            }
        }

        System.out.println(result);
    }

    public static boolean isMap(int x, int y) {
        return x >= 0 && y >= 0 && x < mapSize && y < mapSize;
    }

    static class Node {
        int x;
        int y;

        public Node (int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            return x == node.x && y == node.y;
        }
    }
}
