package _2022._2022_06_01.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class _1719 {

    private static int locationSize;
    private static ArrayList<Node>[] graph;
    private static StringBuilder sb = new StringBuilder();// 인스턴스 보호 위해 전역조치

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        read(br);

        sb = new StringBuilder();

        for (int i = 1; i <= locationSize; i++) {
            firstLootToString(i, dijkstra(i));
        }

        System.out.println(sb.toString());

    }

    private static int[] dijkstra (int startNum) {
        int[] dp = new int[locationSize +1];
        boolean[] visit = new boolean[locationSize + 1];
        int[] loot = new int[locationSize+1];

        Arrays.fill(dp, Integer.MAX_VALUE);

        PriorityQueue<Node> priorityQueue = new PriorityQueue<>();
        priorityQueue.add(new Node(startNum, 0));
        dp[startNum] = 0;

        while (priorityQueue.size() > 0) { ;
            Node node = priorityQueue.poll();
            int locationNum = node.getLocationNum();
            int time = node.getTime();

            if (visit[locationNum]) {
                continue;
            }

            visit[locationNum] = true;

            for (Node item : graph[locationNum]) {
                int nowTime = item.getTime() + time;

                if (nowTime < dp[item.getLocationNum()]) {
                    dp[item.getLocationNum()] = nowTime;
                    loot[item.getLocationNum()] = locationNum;
                    priorityQueue.add(new Node(item.getLocationNum(), nowTime));
                }
            }
        }

        return loot;
    }

    private static void firstLootToString (int startNum, int[] loot) {
        for (int i = 1; i < loot.length; i++) {
            if (startNum == i) {
                sb.append("- ");
            }else {
                int result = 0;
                for (int j = i; j != startNum; j=loot[j]) {
                    result = j;
                }
                sb.append(result).append(" ");
            }

        }
        sb.append("\n");
    }

    public static void read(BufferedReader br) throws IOException {
        //첫번째 라인
        String[] firstLine = br.readLine().split(" ");

        //집하장
        locationSize = Integer.parseInt(firstLine[0]);
        //간선
        int lootNum = Integer.parseInt(firstLine[1]);

        graph = new ArrayList[locationSize +1];

        for (int i = 0; i < locationSize + 1; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < lootNum; i++) {
            String[] line = br.readLine().split(" ");
            int location1 = Integer.parseInt(line[0]);
            int location2 = Integer.parseInt(line[1]);
            int time = Integer.parseInt(line[2]);
            graph[location1].add(new Node(location2, time));
            graph[location2].add(new Node(location1, time));
        }
    }

    static class Node implements Comparable<Node> {
        private final int locationNum;
        private final int time;

        public Node(int locationNum, int time) {
            this.locationNum = locationNum;
            this.time = time;
        }

        public int getLocationNum() {
            return locationNum;
        }

        public int getTime() {
            return time;
        }

        @Override
        public int compareTo(Node o) {
            return this.time - o.time;
        }
    }
}
