import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Main {

    private static boolean[][] map;
    private static boolean[][] visit;
    private static int[][] pieceNums;
    private static int column;
    private static int row;

    public static void main(String[] args) throws IOException {
        read();

        int pieceNum = 1;

        ArrayList<Piece> pieces = new ArrayList<>();

        for (int i = 0; i < row; i++) {
            Mapper mapper = new Mapper(0);
            for (int j = 0; j < column; j++) {
                if (!map[i][j] || visit[i][j]) continue;
                dfs(j, i, pieceNum, mapper);
                pieces.add(new Piece(pieceNum, mapper.value));
                pieceNum++;
            }
        }

        int max = 0;

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                if (map[i][j]) continue;
                int value = getMaxValue(j, i, pieces);
                max = Math.max(value, max);
            }
        }

        System.out.println(max);
    }

    private static int getMaxValue(int x, int y, ArrayList<Piece> arrayList) {
        int result = 1;
        Set<Integer> set = new HashSet<>();
        set.add(isMap(x, y + 1) ? pieceNums[y + 1][x] : -1);
        set.add(isMap(x, y - 1) ? pieceNums[y - 1][x] : -1);
        set.add(isMap(x - 1, y) ? pieceNums[y][x - 1] : -1);
        set.add(isMap(x + 1, y) ? pieceNums[y][x + 1] : -1);

        for (Piece piece : arrayList) {
            for (int item : set) {
                if (item == piece.pieceNum) result += piece.size;
            }
        }

        return result;
    }

    private static void dfs(int x, int y, int pieceNum, Mapper size) {
        if (visit[y][x]) return;
        visit[y][x] = true;
        pieceNums[y][x] = pieceNum;
        size.value = size.value + 1;
        if (isMap(x, y + 1) && map[y + 1][x]) dfs(x, y + 1, pieceNum, size);
        if (isMap(x, y - 1) && map[y - 1][x]) dfs(x, y - 1, pieceNum, size);
        if (isMap(x + 1, y) && map[y][x + 1]) dfs(x + 1, y, pieceNum, size);
        if (isMap(x - 1, y) && map[y][x - 1]) dfs(x - 1, y, pieceNum, size);
    }

    private static void read() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] firstLine = br.readLine().split(" ");

        row = Integer.parseInt(firstLine[0]);
        column = Integer.parseInt(firstLine[1]);

        map = new boolean[row][column];
        pieceNums = new int[row][column];
        visit = new boolean[row][column];

        for (int i = 0; i < row; i++) {
            String[] line = br.readLine().split(" ");
            for (int j = 0; j < column; j++) {
                map[i][j] = line[j].equals("1");
            }
        }
    }

    private static boolean isMap(int x, int y) {
        return x >= 0 && x < column && y >= 0 && y < row;
    }

    static class Piece {
        int pieceNum;
        int size;

        public Piece(int pieceNum, int size) {
            this.pieceNum = pieceNum;
            this.size = size;
        }
    }

    static class Mapper {
        int value;

        public Mapper(int value) {
            this.value = value;
        }
    }
}
