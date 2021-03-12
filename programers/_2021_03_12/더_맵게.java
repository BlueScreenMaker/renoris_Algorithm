package programers._2021_03_12;

import java.util.PriorityQueue;

public class 더_맵게 {
    public int solution(int[] scoville, int K) {
        int answer = 0;
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for (int i = 0; i < scoville.length; i++) {
            queue.offer(scoville[i]);
        }
        int first = 0;

        while (true) {
            first = queue.poll();
            if (first >= K) {            //첫번째가 K이상이면 모든 값이 K보다 높은값
                break;
            }
            int second=0;
            try{
                second = queue.poll(); // 첫번째가 K이상이 아닌데 두번째가 없다? -합칠 음식이 없다는뜻=> K스코빌 이상으로 만들지 못함
            }catch (Exception e){
                answer=-1;
                break;
            }
            int result = first + second * 2;
            queue.offer(result);
            answer++;
        }
        return answer;
    }
}
