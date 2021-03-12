import java.util.PriorityQueue;

class Solution {
    public int solution(int[] scoville, int K) {
        int answer = 0;
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for (int i = 0; i < scoville.length; i++) {
            queue.offer(scoville[i]);
        }
        int first = 0;

        while (true) {
            first = queue.poll();
            if (first >= K) {
                break;
            }
            int second=0;
            try{
                second = queue.poll();
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