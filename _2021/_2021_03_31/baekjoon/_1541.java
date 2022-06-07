package _2021._2021_03_31.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class _1541 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line = br.readLine().split("-");
        int count=0;
        String[] first=line[0].split("\\+");
        for (int i = 0; i < first.length; i++) {
            count+=Integer.parseInt(first[i]);
        }
        if (line.length > 1) {
            for (int i = 1; i < line.length; i++) {
                String[] second=line[i].split("\\+");
                for (int j = 0; j < second.length; j++) {
                    count-=Integer.parseInt(second[j]);
                }
            }
        }

        System.out.println(count);
    }
}
