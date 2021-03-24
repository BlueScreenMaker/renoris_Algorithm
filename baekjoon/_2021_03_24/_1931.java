package baekjoon._2021_03_24;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;

public class _1931 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int num = Integer.parseInt(br.readLine());
        int count = 0;
        int endTime = 0;
        int[][] infos = new int[num][2];
        for (int i = 0; i < num; i++) {
            String[] info = br.readLine().split(" ");
            infos[i][0] = Integer.parseInt(info[0]);
            infos[i][1] = Integer.parseInt(info[1]);
        }

        Arrays.sort(infos, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if(o1[0]==o2[0]){
                    return o1[1]-o2[1];
                }
                return o1[0] - o2[0];
            }
        });

        for (int i = 0; i < num; i++) {
            int getStart = infos[i][0];
            int getEnd = infos[i][1];
            if (endTime <= getStart) {
                endTime = getEnd;
                count++;
            } else {
                if (endTime >= getEnd) {
                    endTime = getEnd;
                }
            }
        }
        System.out.println(count);
    }
}
