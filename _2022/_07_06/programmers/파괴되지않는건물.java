package _2022._07_06.programmers;

class 파괴되지않는건물 {

    //참고 https://jangcenter.tistory.com/121
    public int solution(int[][] board, int[][] skill) {

        int[][] preSum = new int[board.length+1][board[0].length+1];

        //데미지
        for (int[] ints : skill) {
            int startY = ints[1];
            int startX = ints[2];
            int endY = ints[3];
            int endX = ints[4];
            int damage = ints[0] == 1 ? -ints[5] : ints[5];

            preSum[startY][startX] += damage;
            preSum[startY][endX + 1] -= damage;
            preSum[endY + 1][startX] -= damage;
            preSum[endY + 1][endX + 1] += damage;
        }

        //데미지 계산
        for (int y = 1; y < preSum.length; y++) {
            for (int x = 0; x < preSum.length; x++) {
                preSum[y][x] += preSum[y - 1][x];
            }
        }

        for (int x = 1; x < preSum.length; x++) {
            for (int y = 0; y < preSum.length; y++) {
                preSum[y][x] += preSum[y][x - 1];
            }
        }

        int count = 0;
        //반영 및 확인
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] + preSum[i][j] > 0) count++;
            }
        }
        return count;
    }
}