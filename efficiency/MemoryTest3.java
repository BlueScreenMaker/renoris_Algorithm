package efficiency;

import java.util.LinkedList;
import java.util.Queue;

public class MemoryTest3 {
    public static void main (String[] args) {
        Queue<Node> queue = new LinkedList<>();
        for (int i = 0; i < 500; i++) {
            queue.add(new Node(i,i,i));

        }

        Runtime.getRuntime().gc();
        long usedMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        System.out.print(usedMemory + " bytes");
    }


}
