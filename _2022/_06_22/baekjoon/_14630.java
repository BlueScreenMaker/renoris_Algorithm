package _2022._06_22.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;

public class _14630 {
    private static int goalNum;
    private static int nowNum;
    private static int totalTransFormNum;
    private static char[][] transFormShapes;

    public static void main (String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        read(br);
        dijkstra();
    }

    private static void dijkstra() {
        PriorityQueue<Node> pq = new PriorityQueue<Node>();
        boolean[] visit = new boolean[totalTransFormNum];
        int[] record = new int[totalTransFormNum];
        Arrays.fill(record, Integer.MAX_VALUE);

        pq.add(new Node(nowNum, 0));
        record[nowNum] = 0;

        while (pq.size() > 0) {
            Node node = pq.poll();
            int nodeNum = node.nodeNum;
            int weight = node.weight;

            if (visit[nodeNum]) {
                continue;
            }

            visit[nodeNum] = true;

            for (int i = 0; i < totalTransFormNum; i++) {
                if (i == nodeNum) {
                    continue;
                }
                int nowWeight = weight + calTransForm(nodeNum, i);
                if (nowWeight < record[i]) {
                    pq.add(new Node(i, nowWeight));
                    record[i] = nowWeight;
                }
            }
        }

        System.out.println(record[goalNum]);

    }

    public static int calTransForm(int nowNum, int goalNum) {
        char[] now = transFormShapes[nowNum];
        char[] goal = transFormShapes[goalNum];

        int weight = 0;

        for (int i = 0; i < now.length; i++) {
            int thisNow = now[i] - '0';
            int thisGoal = goal[i] - '0';
            weight += Math.pow(Math.abs(thisNow - thisGoal), 2);
        }

        return weight;
    }

    public static void read(BufferedReader br) throws IOException {
        totalTransFormNum = Integer.parseInt(br.readLine());
        char[] first = br.readLine().toCharArray();

        transFormShapes = new char[totalTransFormNum][first.length];
        transFormShapes[0] = first;

        for (int i = 1; i < totalTransFormNum; i++) {
            transFormShapes[i] = br.readLine().toCharArray();
        }

        String[] line = br.readLine().split(" ");
        nowNum = Integer.parseInt(line[0]) - 1;
        goalNum = Integer.parseInt(line[1]) - 1;
        br.close();
    }

    static class Node implements Comparable<Node> {
        int nodeNum;
        int weight;

        Node (int nodeNum, int weight) {
            this.nodeNum = nodeNum;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node o) {
            return this.weight - o.weight;
        }
    }
}
