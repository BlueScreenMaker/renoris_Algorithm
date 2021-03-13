package programers._2021_03_16;

import java.util.*;

public class 디스크컨트롤러 {
    public int solution(int[][] jobs) {
        Arrays.sort(jobs, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] == o2[0]) {
                    return o1[1] - o2[1];
                }
                return o1[0] - o2[0];
            }
        });

        int answer = 0;
        int jobNum = jobs.length; //처리해야할 일의 갯수
        int time = 0; //현재까지 진행된 시간
        PriorityQueue<Item> queueOrder = new PriorityQueue<>();
        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < jobNum; i++) {
            queue.offer(jobs[i]);
        }
        while (!queue.isEmpty() || !queueOrder.isEmpty()) {
            if (queueOrder.isEmpty()) {
                int[] first = queue.poll();
                time = first[0] + first[1];
                answer += first[1];
            } else {
                Item first = queueOrder.poll();
                answer += first.spendTime + time - first.startTime;
                time += first.spendTime;
            }
            while (true) {
                if (queue.isEmpty()) {
                    break;
                }
                int[] item = queue.peek();
                if (item[0] < time) {
                    queue.poll();
                    Item makeitem = new Item(item[0], item[1]);
                    queueOrder.offer(makeitem);
                } else {
                    break;
                }
            }
        }
        answer = answer / jobs.length;
        return answer;
    }
    //시이바아아알
    public class Item implements Comparable<Item> {
        int startTime;
        int spendTime;

        public Item(int startTime, int spendTime) {
            this.startTime = startTime;
            this.spendTime = spendTime;
        }

        @Override
        public int compareTo(Item o) {
            if (this.spendTime > o.spendTime) {
                return 1;
            } else if (this.spendTime < o.spendTime) {
                return -1;
            }
            return 0;
        }
    }
}
