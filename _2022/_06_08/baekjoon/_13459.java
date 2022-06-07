package _2022._06_08.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class _13459 {
    private static int height;
    private static int width;
    private static boolean isFinish;

    public static void main (String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String map = read(br);

        HashMap<String, Integer> hashMap = new HashMap<>();
        Queue<String> queue = new LinkedList<>();
        queue.add(map);
        hashMap.put(map, 0);

        while (queue.size() > 0) {
            String item = queue.poll();
            int count = hashMap.get(item);

            for (int i = 0; i < 4; i++) {
                String getMap = movingProvider(item, i);
                int movedCount = count+1;
                if (getMap != null) {
                    if (hashMap.containsKey(getMap)) {
                        if (hashMap.get(getMap) > movedCount) {
                            queue.add(getMap);
                            hashMap.replace(getMap, movedCount);
                            if (!getMap.contains("R") && movedCount <= 10) {
                                isFinish = true;
                            }
                        }
                    } else {
                        queue.add(getMap);
                        hashMap.put(getMap, movedCount);
                        if (!getMap.contains("R") && movedCount <= 10) {
                            isFinish = true;
                        }
                    }
                }
            }
        }

        System.out.println(isFinish ? "1" : "0");

    }

    private static String movingProvider(String map, int direction) {
        switch (direction) {
            case 0 :
                return up(map);
            case 1 :
                return right(map);
            case 2 :
                return down(map);
            case 3 :
                return left(map);
            default :
                return null;
        }
    }

    //위로 기울이기 - 가중치 -넓이
    private static String up(String map) {
        char[] line = map.toCharArray();
        for (int i = width; i < line.length-width; i++) {
            if (!moveBoll(i, -width, line)) {
                return null;
            }
        }

        return new String(line);
    }

    //아래로 기울이기 - 가중치 넓이
    private static String down(String map) {
        char[] line = map.toCharArray();
        for (int i = line.length-1-width; i >= width; i--) {
            if (!moveBoll(i, width, line)) {
                return null;
            }
        }

        return new String(line);
    }

    //왼쪽으로 기울이기 - 가중치 -1
    private static String left(String map) {
        char[] line = map.toCharArray();
        for (int i = width; i < line.length-width; i++) {
            if (!moveBoll(i, -1, line)) {
                return null;
            }
        }


        return new String(line);
    }

    //오른쪽으로 기울이기 가중치 1
    private static String right(String map) {
        char[] line = map.toCharArray();
        for (int i = line.length-1-width; i >= width; i--) {
            if (!moveBoll(i, 1, line)) {
                return null;
            }
        }

        return new String(line);
    }

    //파란공이 들어갔으면 false반환, 정상적인 이동이면 true반환
    private static boolean moveBoll(int nowIndex, int temp, char[] line) {
        boolean inBlueBoll = true;
        char boll = line[nowIndex];
        if (boll != 'R' && boll != 'B') {
            return true;
        }

        int nextIndex = nowIndex+temp;

        while (true) {
            if (line[nextIndex] == '#' || line[nextIndex] == 'R' || line[nextIndex] == 'B') {
                break;
            }
            if (line[nextIndex]=='O') {
                if (boll == 'B') {
                    inBlueBoll = false;
                }
                line[nowIndex] = '.';
                break;
            }
            line[nowIndex] = '.';
            line[nextIndex] = boll;
            nowIndex += temp;
            nextIndex += temp;
        }

        return inBlueBoll;
    }
    public static String read (BufferedReader br) throws IOException {
        //첫번째 넓이 가져오기
        String[] firstLine = br.readLine().split(" ");
        height = Integer.parseInt(firstLine[0]);
        width = Integer.parseInt(firstLine[1]);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < height; i++) {
            sb.append(br.readLine());
        }

        return sb.toString();
    }
}
