package baekjoon._2021_05_26;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

public class _1655 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int testcase = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> left = new PriorityQueue<>(Comparator.reverseOrder());
        PriorityQueue<Integer> right = new PriorityQueue<>();

        for (int i = 0; i < testcase; i++) {
            int num = Integer.parseInt(br.readLine());
            if (left.size() <= right.size()) { //둘중 값중 작은값을 더 우선시하기때문에 left를 우선으로 해준다
                left.offer(num);
            } else {
                right.offer(num);
            }
            if (left.size() > 0 && right.size() > 0) {
                if (left.peek() > right.peek()) {
                    int first = left.poll();
                    left.offer(right.poll());
                    right.offer(first);
                }
            }
            sb.append(left.peek()).append("\n");
        }
        System.out.println(sb.toString());
    }
}
