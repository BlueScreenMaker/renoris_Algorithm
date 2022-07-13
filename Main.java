import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    //백준 - 로봇청소기 - 4991
    //https://www.acmicpc.net/problem/4991

    public static int[] moveX = {0, 1, 0, -1};
    public static int[] moveY = {1, 0, -1, 0};

    public static int maxMove;
    public static int startX;
    public static int startY;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        while (true) {
            //초기화
            maxMove = 0;

            //맵 크기 찾기
            String[] mapLength = br.readLine().split(" ");
            
            //없을시 테스트 케이스 종료
            if (isEnd(mapLength)) break;
            
            //맵 불러오기             
            char[][] map = convertStringToMap(br, mapLength);
            boolean[][] check = new boolean[map.length][map[0].length];
            int dirt = countDirt(map);
            findStartNode(map);
            
            //알고리즘
            dfs(startX, startY, 0, dirt, map, check);
            
            //결과
            sb.append(maxMove).append("\n");
        }

        System.out.println(sb.toString());
    }

    public static void dfs(int x, int y, int move, int dirt, char[][] map, boolean[][] check) {
        boolean[][] backUp = check;
        boolean isDirt = map[y][x] == '*';

        if (isDirt) {
            check = new boolean[map.length][map[0].length];
            dirt--;
            //먼지를 전부 제거시 바로 종료
            if (dirt == 0) {
                if (maxMove > move) {
                    maxMove = move;
                }
                return;
            }
            //먼지제거
            map[y][x] = '.';
        }

        //현재 위치 체크
        check[y][x] = true;

        //4방위 탐색
        for (int i = 0; i < 4; i++) {
            int movedX = x+moveX[i];
            int movedY = y+moveY[i];
            if (isMove(movedX, movedY, map, check)) {
                dfs(movedX, movedY, move+1, dirt, map, check);
            };
        }

        //롤백
        if (isDirt) {
            map[y][x] = '*';
            check = backUp;
        } else {
            check[y][x] = false;
        }
    }

    public static char[][] convertStringToMap(BufferedReader br, String[] stringMapLength) throws IOException {
        int x = Integer.parseInt(stringMapLength[0]);
        int y = Integer.parseInt(stringMapLength[1]);

        char[][] map = new char[y][x];
        for (int i = 0; i < y; i++) {
            map[i] = br.readLine().toCharArray();
        }

        return map;
    }

    public static void findStartNode (char[][] map) {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map.length; j++) {
                if (map[i][j] == 'o'){
                    startX = j;
                    startY = i;
                }
            }
        }
    }

    public static int countDirt(char[][] map) {
        int dirt = 0;

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if (map[i][j] == '*') dirt++;
            }
        }

        return dirt;
    }

    public static boolean isMove(int x, int y, char[][] map, boolean[][] check) {
        return isMap(x, y, map) && map[y][x] != 'x' && !check[y][x];
    }

    public static boolean isMap (int x, int y, char[][] map) {
        return x > -1 && x < map[0].length && y > -1 && y < map.length;
    }

    public static boolean isEnd(String[] mapLength){
        return mapLength[0].equals("0") && mapLength[1].equals("0");
    }
}
