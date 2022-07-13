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
        boolean verticalFirst =
                searchVertical(indexs[1], indexs[3], indexs[0], board) &&
                board[indexs[3]][indexs[0]] == '.' &&
                searchHorizontal(indexs[0], indexs[2], indexs[3], board);

        //좌우먼저
        boolean horizontalFirst = searchHorizontal(indexs[0], indexs[2], indexs[1], board) && board[indexs[1]][indexs[2]] == '.' && searchVertical(indexs[1], indexs[3], indexs[2], board);

        return horizontalFirst || verticalFirst;
    }

    private boolean searchHorizontal(int targetX, int goalX, int y, char[][] board) {
        if (targetX > goalX) {
            int temp = targetX;
            targetX = goalX;
            goalX = temp;
        }

        boolean result = true;
        for (int i = targetX+1; i < goalX; i++) {
            if (board[y][i] != '.') {
                result = false;
                break;
            }
        }
        return result;
    }

    private boolean searchVertical(int targetY, int goalY, int x, char[][] board) {
        if (targetY > goalY) {
            int temp = targetY;
            targetY = goalY;
            goalY = temp;
        }

        boolean result = true;
        for (int i = targetY+1; i < goalY; i++) {
            if (board[i][x] != '.') {
                result = false;
                break;
            }
        }
        return result;
    }



    private boolean canNoDraft(int[] indexs, char[][] board) {
        //상하 직선 -x축이 서로같다
        boolean result = true;
        if (indexs[0] == indexs[2]) {
            result = searchVertical(indexs[1], indexs[3], indexs[0], board);
        }
        //좌우 직선
        else {
            result = searchHorizontal(indexs[0], indexs[2], indexs[1], board);
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