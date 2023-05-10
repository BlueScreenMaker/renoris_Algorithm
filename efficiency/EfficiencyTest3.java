package efficiency;

import java.util.LinkedList;
import java.util.Queue;

public class EfficiencyTest3 {
    public static void main (String[] args) {
        Queue<Node2> queue = new LinkedList<>();
        for (int i = 0; i < 500; i++) {
            queue.add(new Node2(i,i,i,i));

        }

        Runtime.getRuntime().gc();
        long usedMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        System.out.print(usedMemory + " bytes");
    }


}
