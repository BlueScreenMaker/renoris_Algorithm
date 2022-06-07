package _2022._2022_03_23.programmers;

import java.util.ArrayList;

public class _1차_뉴스_클러스터링 {
    private static final int MULTI_VALUE = 65536;

    public int solution(String str1, String str2) {
        ArrayList<String> str1Subset = subSet(str1);
        ArrayList<String> str2Subset = subSet(str2);

        ArrayList<String> union = new ArrayList<>();
        ArrayList<String> inter = new ArrayList<>();

        for (String item : str1Subset) {
            if (str2Subset.remove(item)) {
                inter.add(item);
            }
            union.add(item);
        }

        union.addAll(str2Subset);

        double interSize = inter.size();
        double unionSize = union.size();

        if (union.size() == 0) {
            return MULTI_VALUE;
        }

        return (int) (interSize/unionSize*MULTI_VALUE);
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
