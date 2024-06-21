package _2024._04_04._14499;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] firstLine = Arrays.stream(br.readLine().split(" "))
            .mapToInt(Integer::parseInt).toArray();

        int[][] map = new int[firstLine[0]][firstLine[1]];
        for (int i = 0; i < map.length; i++) {
            map[i] = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt).toArray();
        }

        Dice dice = new Dice(firstLine[2], firstLine[3], map);

        StringBuilder sb = new StringBuilder();

//        Arrays.stream(br.readLine().split(" "))
//            .mapToInt(Integer::parseInt).forEach(direction -> {
//                int result = dice.roll(direction);
//                if (result > -1) sb.append(result).append("\n");
//            });
        int[] array = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        for (int i = 0; i < array.length; i++) {
            if (array[i] >= 0) sb.append(dice.roll(array[i]));
            if (array[i] >= 0 && i != array.length -1) sb.append("\n");
        }

        System.out.println(sb.toString());
    }

    static class Dice {

        private int x;

        private int y;

        private int[] nums;

        private int[][] map;

        private int[] moveX = {0, 1, -1, 0, 0};
        private int[] moveY = {0, 0, 0, -1, 1};

        private Dice (int x, int y, int[][] map) {
            this.nums = new int[6];
            this.x = x;
            this.y = y;
            this.map = map;
        }

        private boolean isMap(int x, int y) {
            return x >= 0 && y >= 0 && y < map.length && x < map[0].length;
        }

        /**
         * 굴리는 방향 1.right 2.left 3.up 4.down
         * @param direction
         */
        private int roll(int direction) {
            if (!isMap(this.x + moveX[direction], this.y + moveY[direction])) return -1;

            switch (direction) {
                case 1: this.right(); break;
                case 2: this.left(); break;
                case 3: this.up(); break;
                case 4: this.down(); break;
                default: break;
            }

            if (map[this.y][this.x] == 0) {
                map[this.y][this.x] = this.getBottom();
            }else {
                this.setBottom(map[this.y][this.x]);
                map[this.y][this.x] = 0;
            }

            return this.getTop();
        }

        private void up() {
            this.y--;
            int temp = nums[0];
            nums[0] = nums[4];
            nums[4] = nums[5];
            nums[5] = nums[1];
            nums[1] = temp;
        }

        private void down() {
            this.y++;
            int temp = nums[0];
            nums[0] = nums[1];
            nums[1] = nums[5];
            nums[5] = nums[4];
            nums[4] = temp;
        }

        private void left() {
            this.x--;
            int temp = nums[0];
            nums[0] = nums[2];
            nums[2] = nums[5];
            nums[5] = nums[3];
            nums[3] = temp;
        }

        private void right() {
            this.x++;
            int temp = nums[0];
            nums[0] = nums[3];
            nums[3] = nums[5];
            nums[5] = nums[2];
            nums[2] = temp;
        }

        private int getTop() {
            return nums[0];
        }

        private void setBottom(int num) {
            nums[5] = num;
        }

        private int getBottom() {
            return nums[5];
        }
    }

}