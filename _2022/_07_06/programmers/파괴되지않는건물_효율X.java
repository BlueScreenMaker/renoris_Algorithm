package _2022._07_06.programmers;

class 파괴되지않는건물_효율X {
    public int solution(int[][] board, int[][] skill) {
        boolean isAttack = false;
        for (int i = 0; i < skill.length; i++) {
            if (skill[i][0] == 1) {
                isAttack = true;
            }else {
                isAttack = false;
            }
            for (int j = skill[i][1]; j <= skill[i][3]; j++) {
                for (int k = skill[i][2]; k <= skill[i][4]; k++) {
                    if (isAttack) {
                        board[j][k] -= skill[i][5];
                    } else {
                        board[j][k] += skill[i][5];
                    }
                }
            }
        }

        int count = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] > 0) count++;
            }
        }
        return count;
    }
}