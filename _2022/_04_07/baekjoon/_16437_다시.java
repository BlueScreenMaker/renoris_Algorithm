package _2022._04_07.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class _16437_다시 {
    private static ArrayList<IsLand>[] isLands;

    public static void main (String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        read(br);
        System.out.println(dfs(1, 0));
    }

    private static int dfs(int isLandNum, int wolfs) {
        ArrayList<IsLand> isLandList = isLands[isLandNum];

        int totalSheep = 0;

        for (IsLand item : isLandList) {
            if (item.isSheep) {
                int result = item.animalNum - wolfs;
                if (result > 0) {
                    totalSheep += result;
                }
                totalSheep += dfs(item.getIsLandNum(), 0);
            } else {
                totalSheep += dfs(item.getIsLandNum(), item.getAnimalNum());
            }
        }

        System.out.println(String.format("%d 번섬의 총 양의 갯수 :%d", isLandNum, totalSheep));
        return totalSheep;
    }



    public static void read(BufferedReader br) throws IOException {
        int length = Integer.parseInt(br.readLine());

        isLands = new ArrayList[length+1];

        for (int i = 0; i <= length; i++) {
            isLands[i] = new ArrayList<IsLand>();
        }

        for (int i = 2; i <= length; i++) {
            String[] line = br.readLine().split(" ");

            boolean isSheep = line[0].equals("S");
            int num = Integer.parseInt(line[1]);
            int bridge = Integer.parseInt(line[2]);

            isLands[bridge].add(new IsLand(i, isSheep, num));
        }
        br.close();
    }


    static class IsLand {
        private int isLandNum;
        private boolean isSheep;
        private int animalNum;

        public IsLand(int isLandNum, boolean isSheep, int animalNum) {
            this.isLandNum = isLandNum;
            this.animalNum = animalNum;
            this.isSheep = isSheep;
        }

        public int getAnimalNum() {
            return animalNum;
        }

        public boolean isSheep() {
            return isSheep;
        }

        public int getIsLandNum() {
            return isLandNum;
        }
    }
}
