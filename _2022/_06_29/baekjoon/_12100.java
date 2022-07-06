package _2022._06_29.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class _12100 {

    //백준 2048-12100
    //시작시간 2022-06-28 4:40
    //중간정산시간 2022-06-28 6:24 100%에서 실패 -> 원인 -최초 가장큰번호 체크 안함
    //2022-06-28 6:30
    private static int boardWidth;
    private static int max = 0;

    public static void main (String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String map = read(br);
        HashMap<String, Integer> hashMap = new HashMap<>();
        int[][] basicMap = convertMapToIntArray(map);
        for (int i = 0; i < boardWidth; i++) {
            for (int j = 0; j < boardWidth; j++) {
                if (max < basicMap[i][j]) {
                    max = basicMap[i][j];
                }
            }
        }

        Queue<String> queue = new LinkedList<>();
        queue.add(map);
        hashMap.put(map, 0);

        while (queue.size() > 0){
            String getMap = queue.poll();
            int value = hashMap.get(getMap);
            if (value > 4) {
                continue;
            }

            for (int i = 0; i < 4; i++) {
                String parsedMap = directionProvider(getMap, i);
                if (!hashMap.containsKey(parsedMap)) {
                    hashMap.put(parsedMap, value+1);
                    queue.add(parsedMap);
                }
            }
        }

        System.out.println(max);
    }

    public static String directionProvider (String map, int direction) {
        switch (direction) {
            case 0: return up(map);
            case 1: return left(map);
            case 2: return right(map);
            case 3: return down(map);
            default: return null;
        }
    }

    private static String up(String map) {
        int[][] convertedMap = convertMapToIntArray(map);
        boolean[][] checkMap = new boolean[boardWidth][boardWidth];
        for (int i = 0; i < boardWidth; i++) {
            for (int yIndex = 0; yIndex < boardWidth; yIndex++) {
                int nowIndex = yIndex;
                while (true) {
                    if (nowIndex <= 0){
                        break;
                    }
                    int getNum = convertedMap[nowIndex][i];
                    if (getNum > max) {
                        max = getNum;
                    }
                    if(convertedMap[nowIndex-1][i] == 0) {
                        convertedMap[nowIndex][i] = 0;
                        convertedMap[nowIndex-1][i] = getNum;
                        if (checkMap[nowIndex][i]) {
                            checkMap[nowIndex][i] = false;
                            checkMap[nowIndex-1][i] = true;
                        }
                        nowIndex = nowIndex-1;
                    } else if(convertedMap[nowIndex-1][i] == getNum && !checkMap[nowIndex-1][i] && !checkMap[nowIndex][i]) {
                        convertedMap[nowIndex][i] = 0;
                        convertedMap[nowIndex-1][i] = getNum*2;
                        checkMap[nowIndex-1][i] = true;
                        nowIndex = nowIndex-1;
                        if (getNum *2 > max) {
                            max = getNum *2;
                        }
                    } else {
                        break;
                    }
                }
            }
        }
        return convertMapToString(convertedMap);
    }

    private static String left(String map) {
        int[][] convertedMap = convertMapToIntArray(map);
        boolean[][] checkMap = new boolean[boardWidth][boardWidth];
        for (int i = 0; i < boardWidth; i++) {
            for (int xIndex = 0; xIndex < boardWidth; xIndex++) {
                int nowIndex = xIndex;
                while (true) {
                    if (nowIndex <= 0) {
                        break;
                    }
                    int getNum = convertedMap[i][nowIndex];
                    if (getNum > max) {
                        max = getNum;
                    }
                    if(convertedMap[i][nowIndex-1] == 0) {
                        convertedMap[i][nowIndex] = 0;
                        convertedMap[i][nowIndex-1] = getNum;
                        if (checkMap[i][nowIndex]) {
                            checkMap[i][nowIndex] = false;
                            checkMap[i][nowIndex-1] = true;
                        }
                        nowIndex = nowIndex-1;
                    } else if(convertedMap[i][nowIndex-1] == getNum && !checkMap[i][nowIndex-1] && !checkMap[i][nowIndex]) {
                        convertedMap[i][nowIndex] = 0;
                        convertedMap[i][nowIndex-1] = getNum*2;
                        checkMap[i][nowIndex-1] = true;
                        nowIndex = nowIndex-1;
                        if (getNum *2 > max) {
                            max = getNum *2;
                        }
                    } else {
                        break;
                    }
                }
            }
        }
        return convertMapToString(convertedMap);
    }

    private static String down(String map) {
        int[][] convertedMap = convertMapToIntArray(map);
        boolean[][] checkMap = new boolean[boardWidth][boardWidth];
        for (int i = boardWidth-1; i >= 0; i--) {
            for (int xIndex = boardWidth-1; xIndex >= 0; xIndex--) {
                int nowIndex = xIndex;
                while (true) {
                    if (nowIndex >= boardWidth-1){
                        break;
                    }
                    int getNum = convertedMap[nowIndex][i];
                    if (getNum > max) {
                        max = getNum;
                    }
                    if(convertedMap[nowIndex+1][i] == 0) {
                        convertedMap[nowIndex][i] = 0;
                        convertedMap[nowIndex+1][i] = getNum;
                        if (checkMap[nowIndex][i]) {
                            checkMap[nowIndex][i] = false;
                            checkMap[nowIndex+1][i] = true;
                        }
                        nowIndex = nowIndex+1;
                    } else if(convertedMap[nowIndex+1][i] == getNum && !checkMap[nowIndex+1][i] && !checkMap[nowIndex][i]) {
                        convertedMap[nowIndex][i] = 0;
                        convertedMap[nowIndex+1][i] = getNum*2;
                        checkMap[nowIndex+1][i] = true;
                        nowIndex = nowIndex+1;
                        if (getNum *2 > max) {
                            max = getNum *2;
                        }
                    } else {
                        break;
                    }
                }
            }
        }
        return convertMapToString(convertedMap);
    }

    private static String right(String map) {
        int[][] convertedMap = convertMapToIntArray(map);
        boolean[][] checkMap = new boolean[boardWidth][boardWidth];
        for (int i = boardWidth-1; i >= 0; i--) {
            for (int yIndex = boardWidth-1; yIndex >= 0; yIndex--) {
                int nowIndex = yIndex;
                while (true) {
                    if (nowIndex >= boardWidth-1){
                        break;
                    }
                    int getNum = convertedMap[i][nowIndex];
                    if (getNum > max) {
                        max = getNum;
                    }
                    if(convertedMap[i][nowIndex+1] == 0) {
                        convertedMap[i][nowIndex] = 0;
                        convertedMap[i][nowIndex+1] = getNum;
                        if (checkMap[i][nowIndex]) {
                            checkMap[i][nowIndex] = false;
                            checkMap[i][nowIndex+1] = true;
                        }
                        nowIndex = nowIndex+1;
                    } else if(convertedMap[i][nowIndex+1] == getNum && !checkMap[i][nowIndex+1] && !checkMap[i][nowIndex]) {
                        convertedMap[i][nowIndex] = 0;
                        convertedMap[i][nowIndex+1] = getNum*2;
                        checkMap[i][nowIndex+1] = true;
                        nowIndex = nowIndex+1;
                        if (getNum *2 > max) {
                            max = getNum *2;
                        }
                    } else {
                        break;
                    }
                }
            }
        }
        return convertMapToString(convertedMap);
    }

    private static String convertMapToString(int[][] map) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map.length; j++) {
                sb.append(map[i][j]).append(" ");
            }
        }
        return sb.toString();
    }


    private static int[][] convertMapToIntArray(String map) {
        String[] maps = map.split(" ");
        int[][] result = new int[boardWidth][boardWidth];
        for (int i = 0; i < boardWidth; i++) {
            for (int j = 0; j < boardWidth; j++) {
                result[i][j] = Integer.parseInt(maps[i*boardWidth+j]);
            }
        }

        return result;
    }


    public static String read(BufferedReader br) throws IOException {
        boardWidth = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < boardWidth; i++) {
            sb.append(br.readLine()).append(" ");
        }

        return sb.toString();
    }
}
