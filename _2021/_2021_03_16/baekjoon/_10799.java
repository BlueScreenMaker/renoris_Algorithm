package _2021._2021_03_16.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class _10799 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] line = br.readLine().toCharArray();
        Stack<Character> table = new Stack<>();

        int cuttingSticks = 0;
        int onStick = 0;

        for (int i = 0; i < line.length; i++) {
            if (line[i] == '(') { //쇠막대기 인지? 레이저인지?
                onStick++;
                cuttingSticks++;
            } else {
                onStick--;       //어쨋든 레이저든 쇠막대기의 끝이든 놓인 쇠막대기중 하나는 끝이낫음!
                if (table.peek() == '(') { //레이져
                    cuttingSticks--;   //전에 놓인것은 쇠막대기가 아니라 레이저기 때문에 잘린쇠막대기에 추가한것을 무효화!
                    cuttingSticks += onStick; //그리고 현재 길이를 늘려가고 있는 쇠막대기를 잘라서 추가
                }
            }
            table.push(line[i]);
        }
        System.out.println(cuttingSticks);
    }
}
