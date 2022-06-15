package _2022._03_09.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class _11725 {

    private static int nodeCount;
    private static ArrayList<Integer>[] graph;
    private static int[] result;

    public static void main (String[] args) throws IOException {
        read();

        Queue<Integer> bfs = new LinkedList<>();
        bfs.add(1);
        result[1] = 1;
        while (bfs.size() > 0) {
            int findNum = bfs.poll();
            for (int node : graph[findNum]) {
                if (result[node] == 0){
                    result[node] = findNum;
                    bfs.offer(node);
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 2; i <= nodeCount; i++) {
            sb.append(result[i]).append("\n");
        }

        System.out.println(sb.toString());
    }

    private static void read() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        nodeCount = Integer.parseInt(br.readLine());
        graph = new ArrayList[nodeCount+1];
        result = new int[nodeCount+1];

        for (int i = 1; i <= nodeCount; i++) {
            graph[i] = new ArrayList<Integer>();
        }

        for (int i = 0; i < nodeCount-1; i++) {
            String[] line = br.readLine().split(" ");
            int num1 = Integer.parseInt(line[0]);
            int num2 = Integer.parseInt(line[1]);
            graph[num1].add(num2);
            graph[num2].add(num1);
        }
        br.close();
    }
}
