package efficiency;

import java.util.LinkedList;
import java.util.Queue;

public class EfficiencyTest2 {

    public static void main (String[] args) {
        Queue<Node> queue = new LinkedList<>();
        for (int i = 0; i < 500; i++) {
            queue.add(new Node(i, i, i, i));

        }

        Runtime.getRuntime().gc();
        long usedMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        System.out.print(usedMemory + " bytes");
    }

    static class Node {
        int x;
        int y;

        int z;
        int count;

        public Node(int x, int y, int z, int count) {
            this.x = x;
            this.y = y;
            this.z = z;
            this.count = count;
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
