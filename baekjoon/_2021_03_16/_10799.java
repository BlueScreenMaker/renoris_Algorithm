package baekjoon._2021_03_16;

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
            if (line[i] == '(') {
                onStick++;
                cuttingSticks++;
            } else {
                onStick--;
                if (table.peek() == '(') { //레이져
                    cuttingSticks--;
                    cuttingSticks += onStick;
                }
            }
            table.push(line[i]);
        }
        System.out.println(cuttingSticks);
    }
}
