package _2022._09_21.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class _1034 {

    public static String[] rows;
    public static int[] zeroCounts;
    public static int pushCount;

    public static void main(String[] args) throws IOException {
        read();

        int result = 0;

        for (int i = 0; i < zeroCounts.length; i++) {
            if (!((isSameOddOrEvan(zeroCounts[i], pushCount)) && (zeroCounts[i] <= pushCount))) continue;
            int brightRow = 1;

            for (int j = i + 1; j < zeroCounts.length; j++) {
                if (rows[i].equals(rows[j])) brightRow++;
            }

            result = Math.max(result, brightRow);
        }


        System.out.println(result);

    }

    private static boolean isSameOddOrEvan(int num, int num2) {
        return (isOdd(num) && isOdd(num2)) || (isEven(num) && isEven(num2));
    }

    private static boolean isOdd(int num) {
        return num % 2 == 1;
    }

    private static boolean isEven(int num) {
        return num % 2 == 0;
    }


    public static void read() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] firstLine = br.readLine().split(" ");
        int rowCount = Integer.parseInt(firstLine[0]);
        int columnCount = Integer.parseInt(firstLine[1]);

        zeroCounts = new int[rowCount];
        rows = new String[rowCount];

        for (int i = 0; i < rowCount; i++) {
            String line = br.readLine();
            char[] lineParts = line.toCharArray();
            int zeroCount = 0;
            for (int j = 0; j < columnCount; j++) {
                if (lineParts[j] == '0') {
                    zeroCount++;
                }
            }

            rows[i] = line;
            zeroCounts[i] = zeroCount;
        }

        pushCount = Integer.parseInt(br.readLine());
    }

}
