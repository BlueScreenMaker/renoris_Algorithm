package _2021._2021_03_16.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class _15828 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int size = Integer.parseInt(br.readLine()); //버퍼의 크기

        Queue<Integer> queue = new LinkedList<>(); // 큐 생성

        while (true) {
            int num = Integer.parseInt(br.readLine());
            if (num == -1) { //-1이면 입력의 끝
                break;
            }
            if (num != 0) { // 0이 아니면 입력
                if (queue.size() != size) { //버퍼의 크기를 안넘엇을때만!
                    queue.offer(num);
                }
            } else { //패킷처리
                queue.poll();
            }
        }

        StringBuilder sb = new StringBuilder();
        while (!queue.isEmpty()) {//큐가 빌대까지 StringBuilder에 삽입
            sb.append(queue.poll()).append(" ");
        }
        System.out.println(sb.length() == 0 ? "empty" : sb);
    }
}
