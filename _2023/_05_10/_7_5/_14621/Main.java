package _2023._05_10._7_5._14621;

import _2021._2021_04_07.baekjoon._1504.Node;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class Main {
    static int[] parent;
    static ArrayList<Road> roadList;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] firstLine = br.readLine().split(" ");
        int schoolNum = Integer.parseInt(firstLine[0]);
        int roadNum = Integer.parseInt(firstLine[1]);

        String[] university = new String[schoolNum + 1];

        for (int i = 1; i <= schoolNum; i++) {
            university = br.readLine().split(" ");
        }

        roadList = new ArrayList<>();
        for (int i = 0; i < roadNum; i++) {
            int[] lines = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            roadList.add(new Road(lines[0], lines[1], lines[2]));
        }

        parent = new int[schoolNum + 1];
        for (int i = 1; i <= schoolNum; i++) {
            parent[i] = i;
        }

        // 사심 경로를 기준으로 오름차순 정렬.
        Collections.sort(roadList);

        int cnt = 0;
        int ans = 0;

        // 크루스칼 알고리즘 수행.
        for (int i = 0; i < roadList.size(); i++) {
            Road road = roadList.get(i);

            if (find(road.start) != find(road.end)) {
                if (!university[road.start].equals(university[road.end])) {
                    cnt++;
                    ans += road.weight;

                    union(road.start, road.end);
                }
            }
        }

        System.out.println((cnt != schoolNum - 1 ? -1 : ans) + "\n");


    }

    public static int find(int x) {
        if (x == parent[x]) {
            return x;
        }

        return parent[x] = find(parent[x]);
    }

    public static void union(int x, int y) {
        x = find(x);
        y = find(y);

        if (x != y) {
            parent[y] = x;
        }
    }

    static class Road implements Comparable<Road> {
        int start;
        int end;
        int weight;

        Road(int start, int end, int weight) {
            this.start = start;
            this.end = end;
            this.weight = weight;
        }

        @Override
        public int compareTo(Road o) {
            return this.weight - o.weight;
        }
    }

}