package _2022._03_09.programmers;

import java.util.ArrayList;

public class _1차_뉴스클러스터링2 {
    private static final int MULTI_VALUE = 65536;

    public int solution(String str1, String str2) {
        ArrayList<String> str1Subset = subSet(str1);
        ArrayList<String> str2Subset = subSet(str2);

        ArrayList<String> inter = new ArrayList<>();
        ArrayList<String> union = new ArrayList<>();

        for (String set1Item : str1Subset) {
            for (String set2Item : str2Subset) {
                if (set1Item.equals(set2Item)) {
                    inter.add(set1Item);
                    break;
                }
            }
        }

        union.addAll(str1Subset);
        union.addAll(str2Subset);

        double interLength = inter.size();
        double unionLength = union.size()-interLength;

        if (interLength == 0 && unionLength == 0) {
            return MULTI_VALUE;
        }

        double divide = interLength/unionLength;

        double result = divide*MULTI_VALUE;
        return (int) result;
    }

    public ArrayList<String> subSet(String str) {
        char[] line = str.toUpperCase().toCharArray();
        ArrayList<String> result = new ArrayList<>();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < line.length-1; i++) {
            char front = line[i];
            char back = line[i+1];
            sb = new StringBuilder();

            if (isASCII(front) && isASCII(back)) {
                sb.append(front).append(back);
                result.add(sb.toString());
            }
        }

        return result;
    }

    public boolean isASCII (char alpha) {
        return alpha >= 64 && alpha <= 90;
    }
}
