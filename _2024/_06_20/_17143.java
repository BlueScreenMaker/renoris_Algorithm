package _2024._06_20;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class _17143 {
    private static int[] directionX = {0, 0, 1, -1};
    private static int[] directionY = {-1, 1, 0, 0};

    private static int maxX;
    private static int maxY;

    private static Collection<Shark> sharks;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //최대 크기 파싱
        String[] firstLine = br.readLine().split(" ");
        maxX = Integer.parseInt(firstLine[1]);
        maxY = Integer.parseInt(firstLine[0]);
        int sharkNum = Integer.parseInt(firstLine[2]);
        sharks = new ArrayList<>();

        for (int i = 0; i < sharkNum; i++) {
            String[] line = br.readLine().split(" ");
            int x = Integer.parseInt(line[1]);
            int y = Integer.parseInt(line[0]);
            int speed = Integer.parseInt(line[2]);
            int direction = Integer.parseInt(line[3]);
            int size = Integer.parseInt(line[4]);
            String alpha = String.valueOf((char) (i+97));

            Shark shark = new Shark(alpha, x, y,speed,direction,size);
            sharks.add(shark);
        }

        int result = 0;

        for (int i = 1; i <= maxX; i++) {
            result += fishing(i);
            sharks.forEach(Shark::moveBySpeed);
            eating();
        }

        System.out.println(result);
    }

    private static void eating() {
        Map<String, Shark> map = new HashMap<>();

        sharks.forEach((shark) -> {
            String key = shark.x + "_" + shark.y;
            if (!map.containsKey(key) || (map.containsKey(key) && map.get(key).size < shark.size)) {
                map.put(key, shark);
            }
        });

        sharks = map.values();

    }

    private static int fishing(int x) {
        Optional<Shark> close = sharks.stream().filter(shark -> shark.x == x).min((shark1, shark2) -> shark1.y - shark2.y);

        if (!close.isPresent()) {
            return 0;
        }

        sharks.remove(close.get());
        return close.get().size;
    }


    static class Shark {

        String alpha;

        Integer x;

        Integer y;

        int speed;

        int direction;

        int size;

        public Shark(String alpha, int x, int y, int speed, int direction, int size) {
            this.alpha = alpha;
            this.x = x;
            this.y = y;
            this.speed = speed;
            this.direction = direction - 1; //실제값과 움직이는 위치 보정
            this.size = size;
        }

        public void moveBySpeed() {
            move(speed);
        }

        private void move(int moveDistance) {
            //움직임 시작
            boolean isVerticalMove = direction < 2;
            Integer moveTarget = isVerticalMove ? this.y : this.x; //call by reference 될줄 알았는데 안되네
            int max = isVerticalMove ? maxY : maxX;
            int[] directions = isVerticalMove ? directionY : directionX;

            int preResult = moveTarget + moveDistance * directions[direction];

            //움직인 범위가 끝쪽을 넘어가지 않는 경우
            if ( preResult <= max && preResult >= 1) {
                if (isVerticalMove) this.y = preResult;
                else this.x = preResult;
            }
            //움직인 범위가 끝쪽을 넘어갈 경우
            else {
                //최대쪽으로 넘어 갔을경우
                int diff;
                if (preResult > max) {
                    diff = preResult - max;
                    if (isVerticalMove) this.y = max;
                    else this.x = max;
                }
                //최소쪽으로 넘어갓을 경우
                else {
                    diff = Math.abs(-1 + preResult);
                    if (isVerticalMove) this.y = 1;
                    else this.x = 1;
                }
                reverseDirection(); //끝쪽에 도달했으니 방향 전환
                move(diff); //남은거리만큼 다시 이동
            }
        }

        public void reverseDirection() {
            this.direction += direction % 2 == 0 ? 1 : -1;
        }
    }

}
