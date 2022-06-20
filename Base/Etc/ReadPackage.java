package Base.Etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ReadPackage {

    /**
     * graph -> map
     * 주는 넓이가 1개 => 정사각형
     */

    public static int[][] read() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int length = Integer.parseInt(br.readLine());

        int[][] map = new int[length][length];

        for (int i = 0; i < length; i++) {
            String[] line = br.readLine().split(" ");
            for (int j = 0; j < length; j++) {
                map[i][j] = Integer.parseInt(line[j]);
            }
        }
        br.close();
        return map;
    }
}
