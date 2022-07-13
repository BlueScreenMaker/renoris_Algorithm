import java.util.TreeSet;

class Solution {

    public final String IMPOSSIBLE = "IMPOSSIBLE";
    public String solution(int m, int n, String[] board) {
        char[][] map = convertCharArrays(board, n, m);
        TreeSet<Character> treeSet = findAlpha(map);

        StringBuilder sb = new StringBuilder();
        boolean isImpossible = false;
        while (treeSet.size() > 0) {
            boolean canRemove = false;
            for (char item : treeSet) {
                if (lineCheck(item, map)) {
                    canRemove = true;
                    treeSet.remove(item);
                    removeAlpha(item, map);
                    sb.append(item);
                    break;
                }
            }
            if(!canRemove) {
                isImpossible = true;
                break;
            }
        }

        return isImpossible ? IMPOSSIBLE : sb.toString();
    }

    private void removeAlpha (char target, char[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == target) board[i][j] = '.';
            }
        }
    }

    private boolean lineCheck(char target, char[][] board) {
        int[] indexs = findIndex(target, board);
        
        //직선인경우
        if (indexs[0] == indexs[2] || indexs[1] == indexs[3]) {
            return canNoDraft(indexs, board);
        }
        //한번 꺾는 경우
        else {
            return canOneDraft(indexs, board);
        }
    }
    
    private boolean canOneDraft (int[] indexs, char[][] board) {;
        //상하 먼저
        boolean verticalFirst = true;
        for (int i = indexs[1]+1; i <= indexs[3] ; i++) {
            if (board[i][indexs[0]] != '.') {
                verticalFirst = false;
                break;
            }
        }

        if (verticalFirst) {
            if (indexs[0] < indexs[2]) {
                for (int i = indexs[0]; i < indexs[2]; i++) {
                    if (board[indexs[3]][i] != '.') {
                        verticalFirst = false;
                        break;
                    }
                }
            }
            else {
                for (int i = indexs[2]+1; i <= indexs[0]; i++) {
                    if (board[indexs[3]][i] != '.') {
                        verticalFirst = false;
                        break;
                    }
                }
            }
        }

        //좌우먼저
        boolean horizontalFirst = true;
        if (indexs[0] < indexs[2]) {
            for (int i = indexs[0] + 1; i <= indexs[2]; i++) {
                if (board[indexs[1]][i] != '.') {
                    horizontalFirst = false;
                    break;
                }
            }
        }
        else {
            for (int i = indexs[2]; i < indexs[0]; i++) {
                if (board[indexs[1]][i] != '.') {
                    horizontalFirst = false;
                    break;
                }
            }
        }

        if (horizontalFirst) {
            if (indexs[0] < indexs[2]) {
                for (int i = indexs[1]; i < indexs[3]; i++) {
                    if (board[i][indexs[2]] != '.') {
                        horizontalFirst = false;
                        break;
                    }
                }
            }else {
                for (int i = indexs[1]; i < indexs[3]; i++) {
                    if (board[i][indexs[2]] != '.') {
                        horizontalFirst = false;
                        break;
                    }
                }
            }
        }

        return horizontalFirst || verticalFirst;

    }
    
    
    private boolean canNoDraft(int[] indexs, char[][] board) {
        //상하 직선 -x축이 서로같다
        boolean result = true;
        if (indexs[0] == indexs[2]) {
            for (int i = indexs[1] + 1; i < indexs[3] ; i++) {
                if (board[i][indexs[0]] != '.') {
                    result = false;
                    break;
                }
            }
        }
        //좌우 직선
        else {
            for (int i = indexs[0] + 1; i < indexs[2] ; i++) {
                if (board[indexs[1]][i] != '.') {
                    result = false;
                    break;
                }
            }
        }

        return result;
    }

    private int[] findIndex(char target, char[][] board) {
        int[] indexs = new int[4];
        boolean findOne = false;

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (target == board[i][j]) {
                    if (findOne) {
                        indexs[2] = j;
                        indexs[3] = i;
                    } else {
                        indexs[0] = j;
                        indexs[1] = i;
                        findOne = true;
                    }
                }
            }
        }

        return indexs;
    }
    
    private TreeSet<Character> findAlpha(char[][] board) {
        TreeSet<Character> treeSet = new TreeSet<>();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] >= 'A' && board[i][j] <= 'Z'){
                    treeSet.add(board[i][j]);
                }
            }
        }

        return treeSet;
    }

    private char[][] convertCharArrays(String[] board, int x, int y) {
        char[][] result = new char[y][x];
        for (int i = 0; i < y; i++) {
            result[i] = board[i].toCharArray();
        }

        return result;
    }
}