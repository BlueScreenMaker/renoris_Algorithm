package _2022._08_31.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class _1062 {
    //anta tica - a c n t i

    private static int[] words;
    private static int alphaCount;
    private static int basicAlpha = Integer.parseInt("10100000100001000001000000", 2); //0,2,8,13,19
    private static int max = 0;

    public static void main(String[] args) throws IOException {
        read();

        if (alphaCount < 5) {
            System.out.println("0");
            return;
        }
        int result = 0;
        if (alphaCount == 5) {
            for (int j : words) {
                if ((j & basicAlpha) == j) {
                    result++;
                }
            }
            if (max < result) {
                max = result;
            }
            System.out.println(result);
            return;
        }

        for (int i = 0; i < 26; i++) {
            if (i == 0 || i == 2 || i == 8 || i == 13 || i == 19) {
                continue;
            }
            int alpha = (int) Math.pow(2, 25 - i);
            dfs(basicAlpha + alpha, i, alphaCount - 6);
        }

        System.out.println(max);
    }

    public static void dfs(int word, int alphaIndex, int count) {
        if (count <= 0) {
            int result = 0;
            for (int j : words) {
                if ((j & word) == j) {
                    result++;
                }
            }
            if (max < result) {
                max = result;
            }

            return;
        }

        for (int i = alphaIndex + 1; i < 26; i++) {
            if (i == 0 || i == 2 || i == 8 || i == 13 || i == 19) {
                continue;
            }

            int alpha = (int) Math.pow(2, 25 - i);
            if ((word & alpha) == alpha) {
                continue;
            }

            word += alpha;
            dfs(word, i, count - 1);
            word -= alpha;
        }
    }

    public static void read() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] firstLine = br.readLine().split(" ");
        int tc = Integer.parseInt(firstLine[0]);
        words = new int[tc];
        alphaCount = Integer.parseInt(firstLine[1]);

        for (int i = 0; i < tc; i++) {
            String line = br.readLine();
            int word = convertStringToInt(line);
            words[i] = word;
        }
    }

    private static int convertStringToInt(String line) {
        char start = 'a';
        int word = 0;

        for (int i = 0; i < 26; i++) {
            if (line.contains(String.valueOf((char) (start + i)))) {
                word++;
            }
            word = word << 1;
        }

        word = word >> 1;
        return word;
    }
}
