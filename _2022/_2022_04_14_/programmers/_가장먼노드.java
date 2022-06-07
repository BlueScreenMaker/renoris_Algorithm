package _2022._2022_04_14_.programmers;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class _가장먼노드 {
    public int solution(int n, int[][] edge) {
        ArrayList<Integer>[] lists = edgeToArrayLists(n, edge);
        boolean[] visit = new boolean[n+1];
        int[] counts = new int[n+1];

        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(1, 1));

        while (queue.size() > 0) {
            Node node = queue.poll();
            if (visit[node.num]) {
                continue;
            }

            visit[node.num] = true;
            counts[node.length]++;
            for (int item : lists[node.num]) {
                if (!visit[item]) {
                    queue.add(new Node(item, node.length+1));
                }
            }
        }

        //추출
        int result = 0;
        for (int i = counts.length-1; i >= 0; i--) {
            if (counts[i] != 0) {
                result = counts[i];
                break;
            }
        }

        return result;
    }

    public ArrayList<Integer>[] edgeToArrayLists (int nodeNum, int[][] edge) {
        ArrayList<Integer>[] lists = new ArrayList[nodeNum+1];
        for (int i = 0; i <= nodeNum; i++) {
            lists[i] = new ArrayList<>();
        }

        for (int[] array : edge) {
            int num1 = array[0];
            int num2 = array[1];

            lists[num1].add(num2);
            lists[num2].add(num1);
        }

        return lists;
    }

    private class Node {
        private int num;
        private int length;

        public Node(int num, int length) {
            this.num = num;
            this.length = length;
        }
    }
}
