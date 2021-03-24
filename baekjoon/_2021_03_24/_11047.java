package baekjoon._2021_03_24;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class _11047 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line = br.readLine().split(" ");
        int testcase = Integer.parseInt(line[0]);
        int goal = Integer.parseInt(line[1]);

        int[] coin = new int[testcase];
        int max = 0;
        int count = 0;
        for (int i = 0; i < testcase; i++) {
            coin[i] = Integer.parseInt(br.readLine());
            if (coin[i] <= goal) {
                max = i;
            }
        }

        for (int i = max; i >= 0; i--) {
            int use = goal / coin[i];
            goal -= (coin[i] * use);
            count += use;
            if (goal == 0) {
                break;
            }
        }
        System.out.println(count);
    }
}
