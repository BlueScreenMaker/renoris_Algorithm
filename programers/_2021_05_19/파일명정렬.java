package programers._2021_05_19;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class 파일명정렬 {
    public String[] solution(String[] files) {
        Item[] array = new Item[files.length];

        for (int i = 0; i < files.length; i++) {
            array[i] = new Item(files[i]);
        }

        Arrays.sort(array, new Comparator<Item>() {
            @Override
            public int compare(Item o1, Item o2) {
                int result = o1.head.toLowerCase().compareTo(o2.head.toLowerCase());

                if (result == 0) {
                    result = o1.number - o2.number;
                }
                return result;
            }
        });
        String[] answer = new String[files.length];
        for (int i = 0; i < answer.length; i++) {
            answer[i] = array[i].originalName;
        }

        return answer;
    }


    static class Item {
        String originalName;
        String head;
        Integer number;

        public Item(String s) {
            this.originalName = s; // 2 3 a s d
            head = s.split("[0-9]")[0];
            
            char[] other = originalName.replace(head, "").toCharArray();
            StringBuilder sb = new StringBuilder();
            for (char in : other) {
                if (Character.isDigit(in) && sb.length() < 5) {
                    sb.append(in);
                } else {
                    break;
                }
            }
            number = Integer.parseInt(sb.toString());
        }
    }
}
