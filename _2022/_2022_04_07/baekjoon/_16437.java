package _2022._2022_04_07.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class _16437 {
    private static ArrayList<Integer>[] roads;
    private static IsLand[] isLandsInfo;

    public static void main (String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        read(br);
        System.out.println(dfs(1));
    }

    public static long dfs(long isLandNum) {
        long totalSheep = 0;
        long wolf = 0;
        IsLand isLand = isLandsInfo[(int) isLandNum];

        if (isLand.isSheep) {
            totalSheep += isLand.animalNum;
        } else {
            wolf += isLand.animalNum;
        }

        for (long item : roads[(int) isLandNum]) {
            long result = dfs(item);
            if (result >= wolf) {
                totalSheep += result - wolf;
                wolf = 0;
            } else {
                wolf = wolf - result;
            }
        }

        return totalSheep;
    }


    public static void read(BufferedReader br) throws IOException, IOException {
        int length = Integer.parseInt(br.readLine());
        roads = new ArrayList[length+1];
        isLandsInfo = new IsLand[length+1];

        for (int i = 1; i <= length; i++) {
            roads[i] = new ArrayList<Integer>();
        }

        isLandsInfo[1] = new IsLand(true, 0);

        for (int i = 2; i <= length; i++) {
            String[] line = br.readLine().split(" ");

            boolean isSheep = line[0].equals("S");
            int num = Integer.parseInt(line[1]);
            int bridge = Integer.parseInt(line[2]);
            roads[bridge].add(i);
            isLandsInfo[i] = new IsLand(isSheep, num);
        }
        br.close();
    }


    static class IsLand {
        private boolean isSheep;
        private long animalNum;

        public IsLand(boolean isSheep, int animalNum) {
            this.animalNum = animalNum;
            this.isSheep = isSheep;
        }

        public long getAnimalNum() {
            return animalNum;
        }

        public boolean isSheep() {
            return isSheep;
        }
    }
}
