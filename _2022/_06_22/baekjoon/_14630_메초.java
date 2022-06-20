package _2022._06_22.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;

public class _14630_메초 {
    private static int goalNum;
    private static int nowNum;
    private static int[][] transFormNodes;

    public static void main (String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        read(br);
        dijkstra();

    }

    private static void dijkstra() {
        PriorityQueue<Node> pq = new PriorityQueue<Node>();
        boolean[] visit = new boolean[transFormNodes.length];
        int[] record = new int[transFormNodes.length];
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

            for (int i = 0; i < transFormNodes.length; i++) {
                if (i == nodeNum) {
                    continue;
                }
                int nowWeight = weight + transFormNodes[nodeNum][i];
                if (nowWeight < record[i]) {
                    pq.add(new Node(i, nowWeight));
                    record[i] = nowWeight;
                }
            }
        }

        System.out.println(record[goalNum]);

    }

    public static int calTransForm(int nowNum, int goalNum, String[] transForms) {
        char[] now = transForms[nowNum].toCharArray();
        char[] goal = transForms[goalNum].toCharArray();

        int weight = 0;

        for (int i = 0; i < now.length; i++) {
            int thisNow = now[i] - '0';
            int thisGoal = goal[i] - '0';
            weight += Math.pow(Math.abs(thisNow - thisGoal), 2);
        }

        return weight;
    }

    public static void read(BufferedReader br) throws IOException {
        int totalTransFormNum = Integer.parseInt(br.readLine());
        String[] transFormShapes = new String[totalTransFormNum];
        transFormNodes = new int[totalTransFormNum][totalTransFormNum];


        for (int i = 0; i < totalTransFormNum; i++) {
            transFormShapes[i] = br.readLine();
        }

        for (int i = 0; i < totalTransFormNum; i++) {
            for (int j = 0; j < totalTransFormNum; j++) {
                int weight = calTransForm(i, j, transFormShapes);
                transFormNodes[j][i] = weight;
                transFormNodes[i][j] = weight;
            }
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
