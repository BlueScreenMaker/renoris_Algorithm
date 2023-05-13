package _2022._08_09.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class _18119 {

    public static int here;
    public static int[] words;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] firstLine = br.readLine().split(" ");
        here = 1;
        for (int i = 0; i < 25; i++) {
            here = here << 1;
            here++;
        }
        words = new int[Integer.parseInt(firstLine[0])];

        int tc = Integer.parseInt(firstLine[1]);
        baseRead(br);
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < tc; i++) {
            String[] line = br.readLine().split(" ");
            int behavior = Integer.parseInt(line[0]);
            int alpha = convertAlphaToInt(line[1]);
            if (behavior == 1) {
                here -= alpha;
            } else {
                here += alpha;
            }

            int result = 0;
            for (int j = 0; j < words.length; j++) {
                int andResult = words[j] & here;
                if (andResult == words[j]) result++;
            }

            sb.append(result).append("\n");
        }
        System.out.println(sb.toString());
    }

    public static int convertAlphaToInt(String alpha) {
        int result = 0;
        for (int i = 0; i < 26; i++) {
            if (alpha.equals(String.valueOf((char) ('a' + i)))) {
                result = (int) Math.pow(2, i);
                break;
            }
        }
        return result;
    }

    public static void baseRead(BufferedReader br) throws IOException {
        for (int i = 0; i < words.length; i++) {
            int word = convertWordToInt(br.readLine());
            words[i] = word;
        }
    }

    public static int convertWordToInt(String word) {
        int result = 0;
        String first = String.valueOf((char) ('a' + 25));
        if (word.contains(first)) result++;

        for (int i = 24; i >= 0; i--) {
            result = result << 1;
            String alpha = String.valueOf((char) ('a' + i));
            if (word.contains(alpha)) result++;
        }

        return result;
    }
}
