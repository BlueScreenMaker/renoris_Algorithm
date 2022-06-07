package _2021._2021_03_16.programers;

import java.util.*;

public class 디스크컨트롤러 {
    public int solution(int[][] jobs) {
        Arrays.sort(jobs, new Comparator<int[]>() {//받은 일 정렬 - 받는시간 오름차순 //같으면 소요시간 오름차순
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

        for (int i = 0; i < jobNum; i++) { //
            queue.offer(jobs[i]);
        }
        
        while (!queue.isEmpty() || !queueOrder.isEmpty()) { //앞으로 처리해야할일과 현재 처리중인일이 없을때까지 반복
            if (queueOrder.isEmpty()) { //현재 처리해야할 일이 있나?
                //현재 처리해야할 일이 없다
                int[] first = queue.poll();
                time = first[0] + first[1]; //시간을 그 일의 시작 시간+ 그 일의 소요시간
                answer += first[1]; //소요된시간 추가
            } else { //현재 처리해야할 일이 있다
                Item first = queueOrder.poll(); // 처리해야할 일을 하나꺼내서
                answer += first.spendTime + time - first.startTime; // 그일 에서부터 소요시간+지난시간
                time += first.spendTime; //그리고 현재시간을 소요된만큼 up
            }
            while (true) {
                if (queue.isEmpty()) { //앞으로 처리해야할 일이 전부 처리중인 일로 갔으면 break
                    break;
                }
                int[] item = queue.peek(); //아이템을 하나뽑고 그 아이템이 현재 시간을 지나쳣으면 처리해야할 일에 놓기
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

    public class Item implements Comparable<Item> { //소요시간만 비교할수 있게 만든 자료형
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
