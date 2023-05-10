package efficiency;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class MemoryTest1 {
    public static void main (String[] args) {
        int[][] array = new int[500][500]; // 25000개의 int
        for (int i = 0; i < array.length; i++) {
            Arrays.fill(array[i], Integer.MAX_VALUE);
        }

        Queue<Node> queue = new LinkedList<>();
        for (int i = 0; i < 500; i++) {
            queue.add(new Node(i,i));
        }

        Runtime.getRuntime().gc();
        long usedMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        System.out.print(usedMemory + " bytes");

    }
    static class Node {
        int x;
        int y;

        public Node(int x, int y) {
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
