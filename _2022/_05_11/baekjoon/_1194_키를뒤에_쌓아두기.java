package _2022._05_11.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class _1194_키를뒤에_쌓아두기 {
    private static int lengthX;
    private static int lengthY;

    private static final int UPPER_LOWER_CASE = 32;

    private static boolean isOut = false;

    public static void main (String[] args) throws IOException {
        //지도 가져오기
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String map = read(br);
        br.close();
        //저장할 dp
        Map<String, Integer> dp = new HashMap<>();

        Queue<String> queue = new LinkedList<>();
        queue.add(map);
        dp.put(map, 0);
        int min = -1;

        while (queue.size() > 0) {
            String item = queue.poll();
            int foot = dp.get(item);
            for (int i = 0; i < 4; i++) {
                String movedMap = move(item, i);
                if (movedMap != null && !dp.containsKey(movedMap)) {
                    queue.add(movedMap);
                    dp.put(movedMap, foot+1);
                    if (isOut) {
                        min = foot + 1;
                        break;
                    }
                }
            }

            if (isOut) {
                break;
            }
        }

        System.out.println(min);
    }

    private static String read(BufferedReader br) throws IOException {
        String[] firstLine = br.readLine().split(" ");
        lengthY = Integer.parseInt(firstLine[0]);
        lengthX = Integer.parseInt(firstLine[1]);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < lengthY; i++) {
            sb.append(br.readLine());
        }

        return sb.toString();
    }

    private static String move(String map, int direction) {
        int location = map.indexOf('0');
        switch (direction) {
            case 0 :
                return up(location, map);
            case 1 :
                return right(location, map);
            case 2 :
                return down(location, map);
            case 3 :
                return left(location, map);
            default:
                return null;

        }
    }

    private static String up(int location, String map) {

        //위로갈수있는지 체크
        if (location < lengthX) {
            return null;
        }

        int targetLocation = location - lengthX;
        char targetChar = map.charAt(targetLocation);

        //갈수있는곳인가?
        if (!isMove(targetChar, map)) {
            return null;
        }

        if (targetChar == '1') {
            isOut = true;
        }

        //해당 위치가 키이면 키 get
        map = getKey(targetChar, map);

        //위치 변환
        StringBuilder sb = new StringBuilder();
        sb.append(map.substring(0, targetLocation)); // 첫번째에서 갈위치 전까지
        sb.append("0"); // 갈 위치에 내 위치 할당
        sb.append(map.substring(targetLocation+1, location));// 갈위치 이후부터 현재 내 위치까지
        sb.append("."); // 현재 내위치에 빈공간 할당
        if(map.length() > location+1) { // 만약 현재 키를 가지고 잇거나 내위치가 마지막이 아니라면
            sb.append(map.substring(location+1));
        }

        return sb.toString();
    }

    private static String left(int location, String map) {
        //왼쪽으로 갈 수 있는지 체크
        if (location%lengthX == 0) {
            return null;
        }

        int targetLocation = location - 1;
        char targetChar = map.charAt(targetLocation);

        //갈수있는곳인가?
        if (!isMove(targetChar, map)) {
            return null;
        }

        if (targetChar == '1') {
            isOut = true;
        }

        //해당 위치가 키이면 키 get
        map = getKey(targetChar, map);

        //위치 변환
        StringBuilder sb = new StringBuilder();
        sb.append(map.substring(0, targetLocation)); // 첫번째에서 갈위치 전까지
        sb.append("0"); // 갈 위치에 내 위치 할당
        sb.append("."); // 현재 내위치에 빈공간 할당
        if(map.length() > location+1) { // 만약 현재 키를 가지고 잇거나 내위치가 마지막이 아니라면
            sb.append(map.substring(location+1));
        }

        return sb.toString();
    }


    private static String right(int location, String map) {
        //오른쪽으로 갈 수 있는지 체크
        if (location%lengthX == lengthX-1) {
            return null;
        }

        int targetLocation = location + 1;
        char targetChar = map.charAt(targetLocation);

        //갈수있는곳인가?
        if (!isMove(targetChar, map)) {
            return null;
        }

        if (targetChar == '1') {
            isOut = true;
        }

        //해당 위치가 키이면 키 get
        map = getKey(targetChar, map);

        //위치 변환
        StringBuilder sb = new StringBuilder();
        sb.append(map.substring(0, location)); // 첫번째에서 현위치 전까지
        sb.append("."); // 현 위치에 빈 공간 할당
        sb.append("0"); // 갈 위치에 내 위치 할당
        if(map.length() > targetLocation+1) { // 만약 현재 키를 가지고 잇거나 내위치가 마지막이 아니라면
            sb.append(map.substring(targetLocation+1));
        }

        return sb.toString();
    }


    private static String down(int location, String map) {
        //아래쪽으로 갈 수 있는지 체크
        if (location > lengthX*lengthY-1 - lengthX) {
            return null;
        }

        int targetLocation = location + lengthX;
        char targetChar = map.charAt(targetLocation);

        //갈수있는곳인가?
        if (!isMove(targetChar, map)) {
            return null;
        }

        if (targetChar == '1') {
            isOut = true;
        }

        //해당 위치가 키이면 키 get
        map = getKey(targetChar, map);

        //위치 변환
        StringBuilder sb = new StringBuilder();
        sb.append(map.substring(0, location)); // 첫번째에서 현위치 전까지
        sb.append("."); // 현 위치에 빈 공간 할당
        sb.append(map.substring(location+1, targetLocation));// 갈위치 이후부터 현재 내 위치까지
        sb.append("0"); // 갈 위치에 내 위치 할당
        if(map.length() > targetLocation+1) { // 만약 현재 키를 가지고 잇거나 내위치가 마지막이 아니라면
            sb.append(map.substring(targetLocation+1));
        }

        return sb.toString();
    }


    private static boolean isMove(char moveLocation, String map) {
        //문에 들어갈수 있나?
        if (moveLocation >= 65 && moveLocation <= 90) {
            char lowerCase = (char) (moveLocation + UPPER_LOWER_CASE);
            if (map.indexOf(lowerCase, lengthX*lengthY-1) < 0) {
                return false;
            }
        }

        //이동할수 없는 곳인가?
        return moveLocation != '#';
    }

    private static String getKey(char moveLocation, String map) {
        //해당 위치가 키인가?
        if (moveLocation >= 97 && moveLocation <= 122) {
            map = map.concat(String.valueOf(moveLocation));
        }
        return map;
    }

}
