package efficiency;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class EfficiencyTest4 {
    // expectedTestCaseNum
    private static final int ETCN = 500;
    public static void main (String[] args) {
        int[] array = new int[ETCN]; // 25000개의 int
        Arrays.fill(array, Integer.MAX_VALUE);

        Queue<Node> queue = new LinkedList<>();
        for (int i = 0; i < 500; i++) {
            queue.add(new Node(i));
        }

        Runtime.getRuntime().gc();
        long usedMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        System.out.print(usedMemory + " bytes");

    }
    static class Node {
        int x;

        public Node(int x) {
            this.x = x;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            return x == node.x;
        }
    }

}
