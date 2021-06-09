package baekjoon._2021_06_09;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class _19638 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        StringTokenizer st = new StringTokenizer(line, " ");
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
        int testcase = Integer.parseInt(st.nextToken());
        int height = Integer.parseInt(st.nextToken());
        int maxCount = Integer.parseInt(st.nextToken());

        //입력
        for (int i = 0; i < testcase; i++) {
            pq.offer(Integer.parseInt(br.readLine()));
        }

        //뿅망치 얍얍
        int count = 0;
        boolean find = false;
        for (int i = 0; i < maxCount; i++) {
            Integer num = pq.poll();
            if (num < height) {
                count = i;
                find = true;
            } else {
                if (num > 1) {
                    num >>= 1;
                }
            }
            pq.offer(num);
            if (find) {
                break;
            }
        }

        if (!find) {
            count = maxCount;
        }

        //결과
        if (pq.peek() >= height) {
            System.out.println("NO");
            System.out.println(pq.peek());
        } else {
            System.out.println("YES");
            System.out.println(count);
        }
    }
}
