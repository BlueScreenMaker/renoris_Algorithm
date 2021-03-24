package programers._2021_03_24;

import java.util.PriorityQueue;

public class 조이스틱 {
    public int solution(String name) {
        int answer = 0;
        char[] naming = name.toCharArray();
        int cursor = 0;
        PriorityQueue<Item> pq = new PriorityQueue<>();

        for (int i = 0; i < naming.length; i++) {
            int a = naming[i] - 'A';
            int b = 'Z' - naming[i] + 1;
            int alphabetCost = Math.min(a, b);//알파벳 옮기는거
            if (alphabetCost != 0) {
                Item item = new Item(alphabetCost, i, naming.length - 1);
                item.renewal(0);
                pq.offer(item);
            }
        }

        while (!pq.isEmpty()) {
            Item item = pq.poll();
            cursor = item.getCursor();
            answer += item.totalCost;
            Item[] array = new Item[pq.size()];
            for (int i = 0; i < array.length; i++) {
                Item temp = pq.poll();
                temp.renewal(cursor);
                array[i] = temp;
            }
            for (Item value : array) {
                pq.offer(value);
            }
        }

        return answer;
    }

    public class Item implements Comparable<Item> {
        private final int alphabetCost;
        private final int cursor;
        private final int maxCursor;
        private int cursorCost;
        private int totalCost;

        public Item(int alphaCost, int cursor, int maxCursor) {
            this.alphabetCost = alphaCost;
            this.cursor = cursor;
            this.maxCursor = maxCursor;
        }

        public void renewal(int hereCursor) {
            if (cursor > hereCursor) {
                cursorCost = Math.min(cursor - hereCursor, maxCursor - cursor + hereCursor + 1);
            } else {
                cursorCost = Math.min(hereCursor - cursor, maxCursor - hereCursor + cursor + 1);
            }
            totalCost = alphabetCost + cursorCost;
        }

        public int getTotalCost() {
            return totalCost;
        }

        public int getCursor() {
            return cursor;
        }

        public int getCursorCost(){
            return cursorCost;
        }
        @Override
        public int compareTo(Item o) {
//            return this.cursor-o.cursor; //63.6점 ..
            return this.getCursorCost()-o.getCursorCost(); //100점....ㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋ시발 이거 그리디 아니잖아
//            return this.getTotalCost() - o.getTotalCost();//이게.. 그리디인데...?
        }
    }
}
