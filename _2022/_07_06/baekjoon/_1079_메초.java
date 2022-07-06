package _2022._07_06.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class _1079_메초 {

    private static int[] scores;
    private static int[][] relation;
    private static int human;
    private static int myNum;
    private static int maxNight;

    public static void main (String[] args) throws IOException {
        read();

        for (int i = 0; i < human; i++) {
            if (i == myNum) {
                continue;
            }
            dfs(i,1, scores.clone());
        }

        System.out.println(maxNight);
    }

    private static void dfs(int killPeople, int night, int[] scores) {
        //죽이기
        scores[killPeople] = 0;
        
        //밤 갱신
        if (night > maxNight) {
            maxNight = night;
        }
        
        //낮 - 죽은사람에 대한 반응 체크
        for (int i = 0; i < human; i++) {
            if (scores[i] == 0) {
                continue;
            }
            scores[i] += relation[killPeople][i];
        }
        
        //낮 - 투표로 탕탕
        scores[findMaxGuiltyIndex(scores)] = 0;

        //내가 죽엇는지 체크
        if (scores[myNum] == 0) {
            return;
        }

        //밤 - 죽일대상 탐색
        for (int i = 0; i < human; i++) {
            if (i == myNum || scores[i] == 0) { //나와 죽은사람 제외
                continue;
            }

            dfs(i, night+1, scores.clone());
        }
    }

    private static int findMaxGuiltyIndex(int[] score) {
        int max = 0;
        int index = 0;
        for (int i = 0; i < human; i++) {
            if (score[i] > max) {
                index = i;
                max = score[i];
            }
        }
        return index;
    }

    public static void read () throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        human = Integer.parseInt(br.readLine());

        scores = new int[human];
        relation = new int[human][human];
        maxNight = 0;

        String[] secondLine = br.readLine().split(" ");
        for (int i = 0; i < human; i++) {
            scores[i] = Integer.parseInt(secondLine[i]);
        }

        for (int i = 0; i < human; i++) {
            String[] line = br.readLine().split(" ");
            for (int j = 0; j < human; j++) {
                relation[i][j] = Integer.parseInt(line[j]);
            }
        }

        myNum = Integer.parseInt(br.readLine());
    }
}
