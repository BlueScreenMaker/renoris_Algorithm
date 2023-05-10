package efficiency;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class EfficiencyTest1 {
    // expectedTestCaseNum
    private static final int ETCN = 500;
    public static void main (String[] args) {
        int[][][] array = new int[ETCN][ETCN][ETCN]; // 25000개의 int
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length; j++) {
                Arrays.fill(array[i][j], Integer.MAX_VALUE);
            }
        }

        Queue<Node> queue = new LinkedList<>();
        for (int i = 0; i < 500; i++) {
            queue.add(new Node(i,i,i));
        }

        Runtime.getRuntime().gc();
        long usedMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        System.out.print(usedMemory + " bytes");

    }
    static class Node {
        int x;
        int y;
        int z;

        public Node(int x, int y, int z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            return x == node.x && y == node.y && z == node.z;
        }
    }

}
