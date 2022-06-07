package _2021._2021_02_15.programers;
class N진수게임 {
    private StringBuilder sb = new StringBuilder();
    private StringBuilder answer = new StringBuilder();

    public String solution(int n, int t, int m, int p) {
        //진법 :n 2~16
        //미리 구할 숫자의 개수:t 0<t<=1000
        //게임에 참가하는 인원:m 2<=m <=100
        //튜브의 순서 p   1<=p<=m 0부터 시작하는 특성상 -1해줘야함
        p--;
        int count = 0;
        int num = 0;
        sb.append("0");
        while (count < t) {
            for (int i = 0; i < m; i++) {
                try {
                    sb.substring(0, 1);
                } catch (Exception e) {
                    num++;
                    sb = changeLine(num, n);
                }
                if (i == p) {
                    answer.append(sb.substring(0, 1));
                    count++;
                    if (count > t) {
                        break;
                    }
                }
                sb.delete(0, 1);
            }
        }
        return answer.toString();
    }

    public StringBuilder changeLine(int num, int base) {
        StringBuilder sb = new StringBuilder();
        do {
            int result = num % base;
            if (result < 10) {
                sb.insert(0, result);
            } else {
                result += 55;
                sb.insert(0, (char) result);
            }
            num /= base;
        }
        while (num != 0);
        return sb;
    }
}
