package _2021._2021_03_24.programers;

public class 큰수만들기 {
    public String solution(String number, int k) {
        char[] line = number.toCharArray();
        StringBuilder sb = new StringBuilder();
        int start = 0; //찾고 다음위치
        int temp = line.length;
        for (int i = 0; i < line.length - k; i++) { //k개의 수를 뺀만큼 찾아온다.
            int max = -1;
            int index = 0;
            temp--;
            for (int j = start; j < line.length - temp + k; j++) {
                if (line[j] - 48 > max) {
                    max = line[j] - 48;
                    index = j;
                }
            }
            start = index + 1;
            sb.append(max);
        }
        return sb.toString();
    }
}
