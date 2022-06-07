package _2022._2022_04_14_.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;

public class _4485 {
    //위 왼 아래 오른
    public static int[] moveX = {0, -1, 0, 1};
    public static int[] moveY = {-1, 0, 1, 0};

    public static void main (String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringBuilder sb = new StringBuilder();

        int count = 0;
        while (true){
            count++;
            int[][] map = read(br);
            if (map == null) {
                break;
            }

            //지도 최대 길이
            int maxLength = map.length;
            //dp 초기 설정
            int[][] record = new int[maxLength][maxLength];
            fill(record, Integer.MAX_VALUE);

            //다익스트라
            dijkstra(map, record);

            //결과 저장
            String line = String.format("Problem %d: %d", count, record[map.length-1][map.length-1]);
            sb.append(line).append("\n");
        }

        br.close();
        System.out.println(sb.toString());
    }

    private static void dijkstra(int[][] map, int[][] record) {
        int mapLength = map.length;
        int startNum = map[0][0];
        PriorityQueue<Node> priorityQueue = new PriorityQueue<>();
        priorityQueue.add(new Node(0, 0, startNum));

        while (priorityQueue.size() > 0) {
            Node node = priorityQueue.poll();

            for (int i = 0; i < 4; i++) {
                int movedX = moveX[i] + node.getX();
                int movedY = moveY[i] + node.getY();

                if (!isMap(movedX, movedY, mapLength)) {
                    continue;
                }

                int nextThiefRupee = node.getThiefRupee() + map[movedY][movedX];

                if (record[movedY][movedX] > nextThiefRupee) {
                    record[movedY][movedX] = nextThiefRupee;
                    priorityQueue.add(new Node(movedX, movedY, nextThiefRupee));
                }
            }
        }
    }

    public static boolean isMap (int x, int y, int maxLength) {
        return x >= 0 && x < maxLength && y >= 0 && y < maxLength;
    }

    public static void fill(int[][] array ,int num) {
        for (int[] ints : array) {
            Arrays.fill(ints, num);
        }
    }

    public static int[][] read(BufferedReader br) throws IOException {
        int length = Integer.parseInt(br.readLine());

        if (length == 0) {
            return null;
        }

        int[][] map = new int[length][length];

        for (int i = 0; i < length; i++) {
            String[] line = br.readLine().split(" ");
            for (int j = 0; j < length; j++) {
                map[i][j] = Integer.parseInt(line[j]);
            }
        }
        return map;
    }


    static class Node implements Comparable<Node> {
        private int x;
        private int y;
        private int thiefRupee;

        public Node(int x, int y, int thiefRupee) {
            this.x = x;
            this.y = y;
            this.thiefRupee = thiefRupee;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }


        public int getThiefRupee() {
            return thiefRupee;
        }

        @Override
        public int compareTo(Node o) {
            if (this.thiefRupee > o.thiefRupee) {
                return 1;
            }else {
                return -1;
            }
        }
    }
}
