package _2022._06_08.programmers;

public class 숫자블록_fail {
    public int[] solution(long begin, long end) {
        int[] array = new int[(int) (end-begin) +1];
        for (long i = end; i >= begin; i--) { //배열에 채우는 과정
            long index = i-begin;
            long half = i/2;
            for (long j = half; j >= 1; j--) {//배열에 들어갈 숫자를 구하는 과정
                if (i%j == 0) {
                    array[(int)index] = (int) j;
                    break;
                }
            }
        }
        return array;
    }
}
