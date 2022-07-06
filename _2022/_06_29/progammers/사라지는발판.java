package _2022._06_29.progammers;

class 사라지는발판 {

    private int finalWinnerMove = Integer.MAX_VALUE;
    private int finalLoserMove = Integer.MAX_VALUE;

    public int solution(int[][] board, int[] aloc, int[] bloc) {
        move(board, new Node(aloc[0], aloc[1]), new Node(bloc[0], bloc[1]), 0, 0, true);
        return finalWinnerMove + finalLoserMove;
    }

    private void move(int[][] board, Node aloc, Node bloc, int aMove, int bMove, boolean isA) {
        if (isA) {
            if (board[aloc.locationY][aloc.locationX] == 0) scoreChange(aMove, bMove, false);
        } else {
            if (board[bloc.locationY][bloc.locationX] == 0) scoreChange(aMove, bMove, true);
        }


        Node movingNode;
        if (isA) {
            movingNode = aloc;
        } else {
            movingNode = bloc;
        }
        boolean canUp = isUp(board, movingNode);
        boolean canDown = isDown(board, movingNode);
        boolean canLeft = isLeft(board, movingNode);
        boolean canRight = isRight(board, movingNode);
        boolean isMove = canUp || canRight || canLeft || canDown;
        if (isMove) {
            if (isA) {
                aMove++;
            } else{
                bMove++;
            }
        }

        if (canUp) {
            Node movedNode = movingNode.up();
            if (isA) {
                move(deleteLocation(board, movingNode), movedNode, bloc, aMove, bMove, false);
            } else {
                move(deleteLocation(board, movingNode), aloc, movedNode, aMove, bMove, true);
            }
        }

        if (canDown) {
            Node movedNode = movingNode.down();
            if (isA) {
                move(deleteLocation(board, movingNode), movedNode, bloc, aMove, bMove, false);
            } else {
                move(deleteLocation(board, movingNode), aloc, movedNode, aMove, bMove, true);
            }
        }

        if (canLeft) {
            Node movedNode = movingNode.left();
            if (isA) {
                move(deleteLocation(board, movingNode), movedNode, bloc, aMove, bMove, false);
            } else {
                move(deleteLocation(board, movingNode), aloc, movedNode, aMove, bMove, true);
            }
        }

        if (canRight) {
            Node movedNode = movingNode.right();
            if (isA) {
                move(deleteLocation(board, movingNode), movedNode, bloc, aMove, bMove, false);
            } else {
                move(deleteLocation(board, movingNode), aloc, movedNode, aMove, bMove, true);
            }
        }
        
        //못움직이니까
        if (!isMove) {
            //A가 움직일 차례면 진것
            scoreChange(aMove, bMove, isA);
            print(board,isA,aMove,bMove, aloc, bloc);
        }
    }

    private void scoreChange(int aMove, int bMove, boolean isA) {
        if (isA) {
            if (finalWinnerMove > bMove) {
                finalWinnerMove = bMove;
                finalLoserMove = aMove;
            } else if(finalWinnerMove == bMove && finalLoserMove < aMove) {
                finalLoserMove = aMove;
            }
        } else {
            if (finalWinnerMove > aMove) {
                finalWinnerMove = aMove;
                finalLoserMove = bMove;
            } else if(finalWinnerMove == aMove && finalLoserMove < bMove) {
                finalLoserMove = bMove;
            }
        }
    }

    private void print (int[][] board, boolean isA, int aMove, int bMove, Node aloc, Node bloc) {
        StringBuilder sb = new StringBuilder();
        sb.append("현재 움직인 상황 a:").append(aMove).append(" b:").append(bMove).append("\n");
        sb.append(String.format("a위치 : %d,%d , B위치 : %d,%d", aloc.locationX,aloc.locationY,bloc.locationX, bloc.locationY)).append("\n");
        sb.append("움직일 차례는:");
        if(isA){
            sb.append("A");
        } else{
            sb.append("B");
        }
        sb.append("\n");
        sb.append("현재 보드 상황:").append("\n");;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                sb.append(board[i][j]);
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }

    private int[][] deleteLocation(int[][] board, Node location) {
        int[][] changeBoard = new int[board.length][board.length];

        for (int i = 0; i < board.length; i++) {
            System.arraycopy(board[i], 0, changeBoard[i], 0, board.length);
        }

        changeBoard[location.locationY][location.locationX] = 0;

        return changeBoard;
    }

    private boolean isUp(int[][] board, Node location) {
        int moveLocationX = location.locationX;
        int moveLocationY = location.locationY + 1;
        return isMap(board, moveLocationX, moveLocationY) && board[moveLocationY][moveLocationX] == 1;
    }

    private boolean isDown(int[][] board, Node location) {
        int moveLocationX = location.locationX;
        int moveLocationY = location.locationY - 1;
        return isMap(board, moveLocationX, moveLocationY) && board[moveLocationY][moveLocationX] == 1;
    }

    private boolean isLeft(int[][] board, Node location) {
        int moveLocationX = location.locationX - 1;
        int moveLocationY = location.locationY;
        return isMap(board, moveLocationX, moveLocationY) && board[moveLocationY][moveLocationX] == 1;
    }

    private boolean isRight(int[][] board, Node location) {
        int moveLocationX = location.locationX + 1;
        int moveLocationY = location.locationY;
        return isMap(board, moveLocationX, moveLocationY) && board[moveLocationY][moveLocationX] == 1;
    }

    private boolean isMap(int[][] board, int x, int y) {
        return x >= 0 && x < board.length && y >= 0 && y < board.length;
    }

    class Node {
        private int locationX;
        private int locationY;

        public Node(int xloc, int yloc) {
            this.locationX = xloc;
            this.locationY = yloc;
        }

        public Node up() {
            return new Node(locationX, locationY + 1);
        }

        public Node left() {
            return new Node(locationX - 1, locationY);
        }

        public Node down() {
            return new Node(locationX, locationY - 1);
        }

        public Node right() {
            return new Node(locationX + 1, locationY);
        }
    }
}