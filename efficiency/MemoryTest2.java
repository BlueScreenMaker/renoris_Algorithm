package efficiency;

import java.util.LinkedList;
import java.util.Queue;

public class MemoryTest2 {
    public static void main (String[] args) {

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
        int count;

        public Node(int x, int y, int count) {
            this.x = x;
            this.y = y;
            this.count = count;
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
