package _2022._03_30.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class _16236_잘못풀이 {
    private static int totalFish;
    private static int[][] map;
    private static int maxLength;
    private static Shark shark;

    //위 왼 아래 오른
    public static int[] moveX = {0, -1, 0, 1};
    public static int[] moveY = {-1, 0, 1, 0};

    public static void main (String[] args) throws IOException {
        read();
        int result = 0;
        while (totalFish != 0) {
            Node fish = null;
            boolean findFish = false;
            for (int i = 1; i <= maxLength; i++) {
                fish = findFish(i);
                if (fish != null) {
                    findFish = true;
                    break;
                }
            }

            if (!findFish) {
                break;
            }

            totalFish--;
            result += movedToFish(fish);
            map[fish.getY()][fish.getX()] = 0;
            shark.eat(fish.getX(), fish.getY());
        }

        System.out.println(result);
    }

    private static int movedToFish(Node fish) {
        Queue<Node> bfs = new LinkedList<>();
        bfs.add(fish);
        int[][] record = new int[maxLength][maxLength];
        for (int i = 0; i < record.length; i++) {
            Arrays.fill(record[i], 10000);
        }
        record[fish.getY()][fish.getX()] = 1;

        while (bfs.size() > 0) {
            Node node = bfs.poll();
            int x = node.getX();
            int y = node.getY();
            int length = record[y][x];

            for (int i = 0; i < 4; i++) {
                int movedX = x+moveX[i];
                int movedY = y+moveY[i];
                if(isMap(movedX, movedY) && map[movedY][movedX] <= shark.getHuge()) {
                    if (record[movedY][movedX] > length+1) {
                        record[movedY][movedX] = length+1;
                        bfs.add(new Node(movedX, movedY));
                    }
                }
            }
        }

        return record[shark.getY()][shark.getX()]-1;
    }

    public static Node findFish(int length) {
        int x = shark.getX();
        int y = shark.getY();
        Node node = null;
        for (int i = length; i >= -length; i--) {
            for (int j = -length; j <= length; j++) {
                if (i == -length || i == length || j == -length || j == length) {
                    int movedX = x+j;
                    int movedY = y+i;
                    if (isMap(movedX, movedY) && map[movedY][movedX] < shark.getHuge() && map[movedY][movedX] > 0) {
                        node = new Node(movedX, movedY);
                    }
                }
            }
        }
        return node;
    }

    public static boolean isMap(int x, int y) {
        return x >= 0 && x < maxLength && y >=0 && y < maxLength;
    }

    public static Shark read() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        maxLength = Integer.parseInt(br.readLine());

        map = new int[maxLength][maxLength];
        totalFish = 0;

        for (int i = 0; i < maxLength; i++) {
            String[] line = br.readLine().split(" ");
            for (int j = 0; j < maxLength; j++) {
                int num = Integer.parseInt(line[j]);
                if (num == 9) {
                    shark = new Shark(j, i);
                } else {
                    if (num > 0) {
                        map[i][j] = num;
                        totalFish++;
                    }
                }
            }
        }

        return shark;
    }

    static class Node {
        private int x;
        private int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }
    }

    static class Shark {
        private int x;
        private int y;
        private int huge;
        private int point;

        public Shark(int x, int y) {
            this.x = x;
            this.y = y;
            this.huge = 2;
            this.point = 0;
        }

        public void eat(int x, int y) {
            point++;
            if (point == huge) {
                huge++;
                point = 0;
            }
            this.x = x;
            this.y = y;
        }

        public int getHuge() {
            return huge;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

    }
}
