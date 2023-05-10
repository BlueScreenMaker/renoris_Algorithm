package _2023._05_10._16948;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Main2 {

    private static int mapSize;
    private static boolean[][] check;
    /**
     *
     (y-2, x-1), (y-2, x+1), (y, x-2), (y, x+2), (y+2, x-1), (y+2, x+1)
     */
    private static final int[] directionX = {-1, 1, -2, 2, -1, +1};
    private static final int[] directionY = {-2, -2, 0, 0, 2, 2};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        mapSize = Integer.parseInt(br.readLine());
        check = new boolean[mapSize][mapSize];
        Integer[] secondLine = Arrays.stream(br.readLine().split(" ")).map(Integer::parseInt).toArray(Integer[]::new);
        Node firstNode = new Node(secondLine[1], secondLine[0], 0);
        check[secondLine[1]][secondLine[0]] = true;

        Node resultNode = new Node(secondLine[3], secondLine[2], 0);
        Queue<Node> queue = new LinkedList<>();
        queue.add(firstNode);

        int result = -1;

        while (queue.size() > 0) {
            Node node = queue.poll();

            check[node.y][node.x] = true;

            if (node.equals(resultNode)) {
                result = node.moveCount;
                break;
            }

            for (int i = 0; i < directionX.length; i++) {
                Node movedNode = getMoveNode(node, i);
                if (movedNode != null && !check[movedNode.y][movedNode.x]) {
                    queue.add(movedNode);
                }
            }
        }

        System.out.println(result);
    }

    public static Node getMoveNode(Node node, int directionNum) {
        int x = node.x + directionX[directionNum];
        int y = node.y + directionY[directionNum];
        int moveCount = node.moveCount + 1;
        if (isMap(x, y)) {
            return new Node(x, y, moveCount);
        }
        return null;
    }

    public static boolean isMap(int x, int y) {
        return x >= 0 && y >= 0 && x < mapSize && y < mapSize;
    }

    static class Node {
        private int x;
        private int y;

        private int moveCount;

        public Node (int x, int y, int moveCount) {
            this.x = x;
            this.y = y;
            this.moveCount = moveCount;
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
