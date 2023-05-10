package efficiency;

import java.util.LinkedList;
import java.util.Queue;

public class EfficiencyTest6 {
    public static void main (String[] args) {
        Queue<Node3> queue = new LinkedList<>();
        for (int i = 0; i < 500; i++) {
            queue.add(new Node3(i,i));

        }

        Runtime.getRuntime().gc();
        long usedMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        System.out.print(usedMemory + " bytes");
    }


}
