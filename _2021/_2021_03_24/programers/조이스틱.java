package _2021._2021_03_24.programers;

import java.util.PriorityQueue;

public class 조이스틱 {
    public int solution(String name) {
        int answer = 0;
        int cursor = 0;
        char[] naming = name.toCharArray();
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
            return this.getCursorCost()-o.getCursorCost(); //
//            return this.getTotalCost() - o.getTotalCost();//
        }
    }
}
