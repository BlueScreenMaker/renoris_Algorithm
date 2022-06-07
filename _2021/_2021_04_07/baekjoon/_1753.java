package _2021._2021_04_07.baekjoon;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class _1753 {
    static ArrayList<Node>[] info;
    static PriorityQueue<Node> queue;
    static int[] weightList;
    static boolean[] find;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int vertexNum = Integer.parseInt(st.nextToken());
        int lineNum = Integer.parseInt(st.nextToken());
        int startVertex = Integer.parseInt(br.readLine());
        weightList = new int[vertexNum + 1];
        queue = new PriorityQueue<>();
        info = new ArrayList[vertexNum + 1];

        for (int i = 0; i <= vertexNum; i++) {
            weightList[i] = 3000001;//300000x10(최대치)
            info[i] = new ArrayList<>();
        }

        for (int i = 0; i < lineNum; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            info[start].add(new Node(end, weight));
        }

        //----------------
        find = new boolean[vertexNum + 1];
        weightList[startVertex] = 0;
        queue.offer(new Node(startVertex, 0));
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            if (!find[node.end]) {
                find[node.end] = true;
                ArrayList<Node> list = info[node.end];
                for (Node item : list) {
                    if (weightList[item.end] > item.weight + node.weight) {
                        weightList[item.end] = item.weight + node.weight;
                        queue.offer(new Node(item.end, weightList[item.end]));//같은 객체를 쓰지않기 위해 새로만들기
                    }
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= vertexNum; i++) {
            sb.append((weightList[i] == 3000001) ? "INF" : weightList[i]).append("\n");
        }
        System.out.println(sb);
    }

    public static class Node implements Comparable<Node> {
        final int end;
        int weight;

        public Node(int end, int weight) {
            this.end = end;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node o) {
            return this.weight - o.weight;
        }
    }
}