package _2022._06_15.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class _13424 {
    private static int[] peopleRoomNums;
    private static int[][] dp;
    private static final int MAX = 1000000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testCase = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < testCase; i++) {
            read(br);
            //노드 1개부터 N개까지
            for (int j = 0; j < dp.length; j++) {
                for (int k = 0; k < dp.length; k++) {
                    for (int l = 0; l < dp.length; l++) {
                        dp[k][l] = Math.min(dp[k][l], dp[k][j]+dp[j][l]);
                    }
                }
            }

            int[] sumWeight = new int[dp.length];
            int min = MAX;
            int minRoom = -1;
            for (int j = 1; j < sumWeight.length; j++) {
                for (int k = 0; k < peopleRoomNums.length; k++) {
                    int peopleRoomNum = peopleRoomNums[k];
                    if (peopleRoomNum == 0) continue;
                    sumWeight[j] += dp[peopleRoomNum][j];
                }
                if (min > sumWeight[j]) {
                    minRoom = j;
                    min = sumWeight[j];
                }
            }
            sb.append(minRoom).append("\n");
        }
        System.out.println(sb.toString());
    }


    private static void read (BufferedReader br) throws IOException {
        String[] firstLine = br.readLine().split(" ");
        int room = Integer.parseInt(firstLine[0]);
        int road = Integer.parseInt(firstLine[1]);

        dp = new int[room+1][room+1];

        for (int i = 0; i < dp.length; i++) {
            Arrays.fill(dp[i], 1000000);
        }

        for (int i = 1; i < dp.length; i++) {
            dp[i][i] = 0;
        }

        for (int i = 0; i < road; i++) {
            String[] line = br.readLine().split(" ");
            int roomA = Integer.parseInt(line[0]);
            int roomB = Integer.parseInt(line[1]);
            int weight = Integer.parseInt(line[2]);
            dp[roomA][roomB] = weight;
            dp[roomB][roomA] = weight;
        }

        int peopleNum = Integer.parseInt(br.readLine());
        String[] peopleRoomNumsString = br.readLine().split(" ");
        peopleRoomNums = new int[peopleNum+1];

        for (int i = 0; i < peopleNum; i++) {
            peopleRoomNums[i] = Integer.parseInt(peopleRoomNumsString[i]);
        }
    }
}
