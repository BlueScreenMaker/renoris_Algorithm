package programers._2021_03_05;

public class 삼각달팽이 {
    public int[] solution(int n) {
        //배열의 크기
        int size = 0;
        for (int i = 1; i <= n; i++) {
            size += i;
        }
        int[][] arrays = new int[n][n];

        int state = 0;
        int depth = 0;
        int count = n;
        int width = 0;
        for (int i = 1; i <= size; i++) {
            if (state == 0) {
                arrays[depth][width] = i;
                count--;
                if (count == 0) { //상태변경 and 꺽기
                    state = 1;
                    n--;
                    count = n;
                    width++;
                } else {
                    depth++;
                }
            } else if (state == 1) {
                arrays[depth][width] = i;
                count--;
                if (count == 0) {//상태변경 and 꺽기
                    state = 2;
                    n--;
                    count = n;
                    depth--;
                    width--;
                } else {
                    width++;
                }
            } else {
                arrays[depth][width] = i;
                count--;
                if (count == 0) {//상태변경 and 꺽기
                    state = 0;
                    n--;
                    count = n;
                    depth++;
                } else {
                    depth--;
                    width--;
                }
            }
        }
        int[] result = new int[size];
        depth = 0;
        width = 0;
        for (int i = 0; i < size; i++) {
            if (arrays[depth][width] == 0) {
                depth++;
                width = 0;
            }
            result[i] = arrays[depth][width];
            width++;
        }
        return result;


    }
}
