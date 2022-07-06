package _2022._07_06.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class _1079 {

    private static int[] scores;
    private static int[][] relation;
    private static int totalHuman;
    private static int myNum;
    private static int maxNight;

    public static void main(String[] args) throws IOException {
        read();
        boolean[] dieList = new boolean[totalHuman];
        dfs(0, scores, dieList, totalHuman);
        System.out.println(maxNight);
    }

    private static void dfs(int night, int[] scores, boolean[] dieList, int human) {
        //밤이냐
        if (maxNight < night) {
            maxNight = night;
        }

        if (human % 2 == 0) {
            for (int i = 0; i < totalHuman; i++) {
                //이미 죽엇거나 혹은 자신이면 타겟 선정 x
                if (dieList[i] || i == myNum) {
                    continue;
                }
                dieList[i] = true;
                applyKillSideEffect(scores, i);
                dfs(night + 1, scores, dieList, human - 1);

                //배열 재사용을 위한 원복
                dieList[i] = false;
                rollBackKillSideEffect(scores, i);
            }
        }
        //낮이냐
        else {
            int diePeople = findMaxGuiltyIndex(scores, dieList);
            if (diePeople == myNum) {
                return;
            }

            dieList[diePeople] = true;
            dfs(night, scores, dieList, human - 1);

            //끝나고 dieList 롤백
            dieList[diePeople] = false;
        }


    }

    private static int findMaxGuiltyIndex(int[] score, boolean[] dieList) {
        int max = 0;
        int index = 0;
        for (int i = 0; i < totalHuman; i++) {
            if (score[i] > max && !dieList[i]) {
                index = i;
                max = score[i];
            }
        }
        return index;
    }

    private static void applyKillSideEffect(int[] scores, int diePeople) {
        for (int i = 0; i < totalHuman; i++) {
            if (scores[i] == 0) {
                continue;
            }
            scores[i] += relation[diePeople][i];
        }
    }

    private static void rollBackKillSideEffect(int[] scores, int diePeople) {
        for (int i = 0; i < totalHuman; i++) {
            if (scores[i] == 0) {
                continue;
            }
            scores[i] -= relation[diePeople][i];
        }
    }

    public static void read() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        totalHuman = Integer.parseInt(br.readLine());

        scores = new int[totalHuman];
        relation = new int[totalHuman][totalHuman];
        maxNight = 0;

        String[] secondLine = br.readLine().split(" ");
        for (int i = 0; i < totalHuman; i++) {
            scores[i] = Integer.parseInt(secondLine[i]);
        }

        for (int i = 0; i < totalHuman; i++) {
            String[] line = br.readLine().split(" ");
            for (int j = 0; j < totalHuman; j++) {
                relation[i][j] = Integer.parseInt(line[j]);
            }
        }

        myNum = Integer.parseInt(br.readLine());
    }
}
