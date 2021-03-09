package baekjoon._2021_03_12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class _1406 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] inputbase = br.readLine().toCharArray();
        Stack<Character> in = new Stack<>();
        Stack<Character> out = new Stack<>();

        for (int i = 0; i < inputbase.length; i++) {
            in.push(inputbase[i]);
        }

        int commandNumber = Integer.parseInt(br.readLine());
        for (int i = 1; i <= commandNumber; i++) {
            switch (br.read()) {
                case 76://L
                    if (in.size() != 0) {
                        out.push(in.pop());
                    }
                    break;
                case 68://D
                    if (out.size() != 0) {
                        in.push(out.pop());
                    }
                    break;
                case 66://B
                    if (in.size() != 0) {
                        in.pop();
                    }
                    break;
                case 80://P
                    br.read();
                    in.push((char) br.read());
            }
            br.read(); //개행문자 제거
        }

        StringBuilder sb = new StringBuilder();
        while (!in.isEmpty()) {
            out.push(in.pop());
        }
        while (!out.isEmpty()) {
            sb.append(out.pop());
        }
        System.out.println(sb);

    }

    public static void main3(String[] args) throws IOException {//시간초과
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] inputbase = br.readLine().toCharArray();
        Stack<Character> in = new Stack<>();
        Stack<Character> out = new Stack<>();

        for (int i = 0; i < inputbase.length; i++) {
            in.push(inputbase[i]);
        }

        int commandNumber = Integer.parseInt(br.readLine());
        for (int i = 1; i <= commandNumber; i++) {
            switch (br.read()) {
                case 76://L
                    if (in.size() != 0) {
                        out.push(in.pop());
                    }
                    break;
                case 68://D
                    if (out.size() != 0) {
                        in.push(out.pop());
                    }
                    break;
                case 66://B
                    if (in.size() != 0) {
                        in.pop();
                    }
                    break;
                case 80://P
                    br.read();
                    in.push((char) br.read());
            }
            br.read(); //개행문자 제거
        }

        StringBuilder sb = new StringBuilder();
        int insize = in.size();
        int outsize = out.size();
        for (int i = 0; i < insize; i++) {
            sb.insert(0, in.pop());
        }
        for (int i = 0; i < outsize; i++) {
            sb.append(out.pop());
        }
        System.out.println(sb);
    }

    public static void main2(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String inputbase = br.readLine();
        StringBuilder base = new StringBuilder();
        base.append(inputbase);
        int commandNumber = Integer.parseInt(br.readLine());
        int index = base.length();
        for (int i = 0; i < commandNumber; i++) {
            String[] commandLine = br.readLine().split(" ");
            switch (commandLine[0]) {
                case "L":
                    if (index != 0) {
                        index--;
                    }
                    break;
                case "D":
                    if (index != base.length()) {
                        index++;
                    }
                    break;
                case "B":
                    if (index != 0) {
                        base.delete(index - 1, index);
                        index--;
                    }
                    break;
                case "P":
                    base.insert(index, commandLine[1]);
                    index += commandLine[1].length();
            }
        }
        System.out.println(base);
    }
}
