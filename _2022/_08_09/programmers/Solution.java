package _2022._08_09.programmers;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

class Solution {

    private byte[][] map;
    private int width;

    public int[][] solution(int n, int[][] build_frame) {
        width = n + 1;
        map = new byte[n + 1][n + 1];
        for (int i = 0; i < map.length; i++) {
            Arrays.fill(map[i], (byte) -1);
        }

        PriorityQueue<Frame> pq = new PriorityQueue<>();
        //작업시작
        for (int i = 0; i < build_frame.length; i++) {
            int x = build_frame[i][0];
            int y = build_frame[i][1];
            byte type = (byte) build_frame[i][2];
            boolean isBuild = build_frame[i][3] == 1;

            if (isBuild) {
                if (type == 0 && canBuildPillar(x, y)) {
                    map[y][x] = 0;
                    pq.add(new Frame(x, y, (byte) 0));
                }

                if (type == 1 && canBuildBo(x, y)) {
                    map[y][x] = 1;
                    pq.add(new Frame(x, y, (byte) 1));
                }
            } else {
                if (type == 0 && canDestroyPillar(x, y)) {
                    map[y][x] = -1;
                    removeFrame(x, y, (byte) 0, pq);
                }

                if (type == 1 && canDestroyBo(x, y)) {
                    map[y][x] = -1;
                    removeFrame(x, y, (byte) 1, pq);
                }
            }
        }

        int[][] result = new int[pq.size()][3];
        int index = 0;
        while (pq.size() > 0) {
            Frame frame = pq.poll();
            result[index][0] = frame.getX();
            result[index][1] = frame.getY();
            result[index][2] = frame.getType();
            index++;
        }

        return result;

    }

    private void removeFrame(int x, int y, byte type, PriorityQueue<Frame> pq) {
        Queue<Frame> queue = new LinkedList<>();
        while (pq.size() > 0) {
            Frame frame = pq.poll();
            if (x == frame.getX() && y == frame.getY() && type == frame.getType()) {
                break;
            }
            queue.add(frame);
        }

        while (queue.size() > 0) {
            pq.add(queue.poll());
        }
    }

    private boolean canBuildBo(int x, int y) {
        return isMap(x, y) &&
                //양쪽에 보
                (((isMap(x - 1, y) && map[y][x - 1] == 1) && (isMap(x + 1, y) && map[y][x + 1] == 1)) ||
                        //왼쪽(세울곳 바로아래) 기둥
                        (isMap(x, y - 1) && map[y - 1][x] == 0) ||
                        //오른쪽 (세울쪽 오른쪽 아래) 기둥
                        (isMap(x + 1, y - 1) && map[y - 1][x + 1] == 0)
                );
    }

    private boolean canBuildPillar(int x, int y) {
        if (isMap(x, y)) {
            if (y == 0) {
                return true;
            }

            return //아래쪽에 기둥
                    (isMap(x, y - 1) && map[y - 1][x] == 0) ||
                            //왼쪽 보
                            (isMap(x - 1, y) && map[y][x - 1] == 1);


        }

        return false;
    }

    private boolean canDestroyBo(int x, int y) {
        //보 오른쪽에 기둥이 없어야함
        return isMap(x, y) && !(isMap(x + 1, y) && map[y][x + 1] == 1) &&
                //오른쪽이 보일때 그 오른쪽도 보가 아니여야함
                !(isMap(x + 1, y) && map[y][x + 1] == 1 && isMap(x + 2, y) && map[y][x + 2] == 1);
    }


    private boolean canDestroyPillar(int x, int y) {
        return isMap(x, y) &&
                //위에 기둥과 보가 없어야함
                !(isMap(x, y + 1) && map[y + 1][x] == 1 && map[y + 1][x] == 0);
    }

    private boolean isMap(int x, int y) {
        return x >= 0 && x < width && y >= 0 && y < width;
    }

    class Frame implements Comparable<Frame> {
        int x;
        int y;
        byte type;

        public Frame(int x, int y, byte type) {
            this.x = x;
            this.y = y;
            this.type = type;
        }

        public byte getType() {
            return type;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        @Override
        public int compareTo(Frame frame) {
            if (this.x == frame.x) {
                if (this.y == frame.y) {
                    return this.type - frame.type;
                }
                return this.y - frame.y;
            }
            return this.x - frame.x;
        }
    }
}