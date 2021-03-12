package baekjoon._2021_03_12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class _9935 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        char[] baseChar = br.readLine().toCharArray();
        char[] fireChar = br.readLine().toCharArray();

        Stack<Character> line = new Stack<>();
        Stack<Character> lineStorage = new Stack<>();

        Stack<Character> fire = new Stack<>();
        Stack<Character> fireStorage = new Stack<>();

        for (int i = 0; i < fireChar.length; i++) {
            fire.push(fireChar[i]);
        }

        for (int i = 0; i < baseChar.length; i++) {
            line.push(baseChar[i]);
            boolean correct = true;
            while (!fire.isEmpty()) {
                char fireGet = fire.pop();
                fireStorage.push(fireGet);

                if (line.isEmpty()) { //확인도중 라인이 비엇을때
                    correct = false;
                    break;
                }

                char lineGet = line.pop();
                lineStorage.push(lineGet);

                if (lineGet != fireGet) {
                    correct = false;
                    break;
                }
            }

            if (correct) {
                while (!lineStorage.isEmpty()) {
                    lineStorage.pop();
                }
            } else {
                while (!lineStorage.isEmpty()) {
                    line.push(lineStorage.pop());
                }
            }
            while (!fireStorage.isEmpty()) {
                fire.push(fireStorage.pop());
            }
        }

        StringBuilder sb = new StringBuilder();
        Stack<Character> result = new Stack<>();
        while (!line.isEmpty()) {
            result.push(line.pop());
        }
        while (!result.isEmpty()) {
            sb.append(result.pop());
        }

        if (sb.toString().equals("")) System.out.println("FRULA");
        else System.out.println(sb);
    }
}
