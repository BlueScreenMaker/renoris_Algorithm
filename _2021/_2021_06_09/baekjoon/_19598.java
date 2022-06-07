package _2021._2021_06_09.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class _19598 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testcase = Integer.parseInt(br.readLine());
        PriorityQueue<Conference> conferences = new PriorityQueue<>();
        Queue<Conference> other=new LinkedList<>();
        StringTokenizer st;
        int result = 0;

        //입력
        for (int i = 0; i < testcase; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            conferences.offer(new Conference(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }

        //찾기
        while (true) {
            int end=0;
            boolean find=false;
            while (true){
                Conference conference=conferences.poll();
                if(conference==null){
                    break;
                }
                if(conference.start>=end){
                    end=conference.end;
                    find=true;
                }else{
                    other.offer(conference);
                }
            }
            if(!find){
                break;
            }else{
                conferences.addAll(other);
                other=new LinkedList<>();
                result++;
            }
        }
        System.out.println(result);
    }


    static class Conference implements Comparable<Conference> {
        int start;
        int end;

        public Conference(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Conference o) {
            if(this.start-o.start==0){
                return this.end-o.end;
            }
            return this.start - o.start;
        }
    }


    public static void main2(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testcase = Integer.parseInt(br.readLine());
        PriorityQueue<Conference2> conferences = new PriorityQueue<>();
        int[][] list = new int[testcase][2];
        StringTokenizer st;
        int result = 0;

        //입력
        for (int i = 0; i < testcase; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            list[i][0] = Integer.parseInt(st.nextToken());
            list[i][1] = Integer.parseInt(st.nextToken());
        }
        //정렬
        Arrays.sort(list, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] - o2[0] == 0) {
                    return o1[1] - o2[1];
                }
                return o1[0] - o2[0];
            }
        });

        //계산
        conferences.offer(new Conference2(list[0][0], list[0][1]));
        for (int i = 1; i < testcase; i++) {
            Conference2 conference = conferences.poll();
            Conference2 item = new Conference2(list[i][0], list[i][1]);
            if (conference.end <= list[i][0]) {
                conferences.offer(item);
            } else {
                conferences.offer(conference);
                conferences.offer(item);
            }
        }

        System.out.println(conferences.size());
    }


    static class Conference2 implements Comparable<Conference> {
        int start;
        int end;

        public Conference2(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Conference o) {
            return this.end - o.end;
        }
    }
}
