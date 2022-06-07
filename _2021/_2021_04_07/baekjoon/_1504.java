package _2021._2021_04_07.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class _1504 {
    static ArrayList<Node>[] info;
    static PriorityQueue<Node> queue;
    static int[] weightList;
    static boolean[] find;
    static final int INF = 200000001;//200000x1000

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int vertexNum = Integer.parseInt(st.nextToken());
        int lineNum = Integer.parseInt(st.nextToken());
        weightList = new int[vertexNum + 1];
        queue = new PriorityQueue<>();
        info = new ArrayList[vertexNum + 1];

        for (int i = 0; i <= vertexNum; i++) {
            info[i] = new ArrayList<>();
        }

        for (int i = 0; i < lineNum; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            info[start].add(new Node(start, end, weight));
            info[end].add(new Node(end, start, weight));
        }

        st = new StringTokenizer(br.readLine());
        int middleFirst = Integer.parseInt(st.nextToken());
        int middleSecond = Integer.parseInt(st.nextToken());

        //----------------
        find = new boolean[vertexNum + 1];
        int result1 = 0;
        int result2 = 0;

        dijkstra(1);

        result1 += weightList[middleFirst];
        result2 += weightList[middleSecond];

        dijkstra(middleFirst);
        result1 += weightList[middleSecond];
        result2 += weightList[middleSecond];
        result2 += weightList[vertexNum];

        dijkstra(middleSecond);
        result1 += weightList[vertexNum];
        long result = Math.min(result1, result2);
        System.out.println((result >= INF) ? -1 : result);
    }

    public static void dijkstra(int start) {
        Arrays.fill(weightList, INF);
        Arrays.fill(find, false);
        weightList[start] = 0;
        queue.offer(new Node(start, start, 0));
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            if (!find[node.end]) {
                find[node.end] = true;
                ArrayList<Node> list = info[node.end];
                for (Node item : list) {
                    if (weightList[item.end] > item.weight + node.weight) {
                        int save = item.weight + node.weight;
                        weightList[item.end] = save;
                        queue.offer(new Node(item.start, item.end, save));//같은 객체를 쓰지않기 위해 새로만들기
                    }
                }
            }
        }
    }


    public static class Node implements Comparable<Node> {
        final int start;
        final int end;
        int weight;

        public Node(int start, int end, int weight) {
            this.start = start;
            this.end = end;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node o) {
            return this.weight - o.weight;
        }
    }
}
