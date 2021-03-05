package baekjoon._2021_03_05;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;

public class _12865 {


    //시초
    static int[] dp;
    static int[][] info;
    static boolean[][] productList;

    public static void main(String[] args) throws IOException {
        //시초
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] firstLine = br.readLine().split(" ");
        int productCount = Integer.parseInt(firstLine[0]);
        int maxWeight = Integer.parseInt(firstLine[1]);

        dp = new int[maxWeight + 1];
        info = new int[productCount][2]; //[i][0]=weight [1]=value
        for (int i = 0; i < productCount; i++) {
            String[] line = br.readLine().split(" ");
            info[i][0] = Integer.parseInt(line[0]);
            info[i][1] = Integer.parseInt(line[1]);
        }

        Arrays.sort(info, new Comparator<int[]>() {// 무게순으로 오름차순 정렬 무게가 같다면 value 순으로 내림차순 정렬
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] == o2[0]) {
                    return o2[1] - o1[1];
                }
                return o1[0] - o2[0];
            }
        });
        productList = new boolean[maxWeight + 1][productCount];
        for (int i = 1; i <= maxWeight; i++) {
            int weight = i;
            int max = 0;
            boolean[] hereProductList = new boolean[productCount]; //밑에 초기화 용도로 생성
            for (int j = 0; j < productCount; j++) {
                int value = info[j][1];
                int etcMax = 0;
                int usableW = weight - info[j][0];
                hereProductList = new boolean[productCount];
                if (usableW >= 0) {
                    hereProductList[j] = true;
                    for (int k = 1; k < i; k++) {
                        if (usableW < k) break;
                        if (!productList[k][j]) {
                            if (dp[k] > etcMax) {
                                etcMax = dp[k];
                                hereProductList = productList[k].clone();
                                hereProductList[j] = true;
                            }
                        }
                    }
                    if (max < value + etcMax) {
                        max = value + etcMax;
                        productList[i] = hereProductList;
                    }
                } else {
                    break;
                }
            }
            dp[i] = max;
        }
        System.out.println(dp[maxWeight]);
    }
}
