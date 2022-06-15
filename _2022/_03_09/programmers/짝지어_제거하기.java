package _2022._03_09.programmers;

import java.util.Stack;

public class 짝지어_제거하기 {

    private Stack<Character> left = new Stack<>();
    private Stack<Character> right = new Stack<>();

    public int solution(String s) {
        basicSetting(s);

        while (left.size() > 0) {
            if (right.size() == 0) {
                right.push(left.pop());
            }
            if (left.size() == 0) {
                break;
            }

            char leftChar = left.peek();
            char rightChar = right.peek();

            if (leftChar == rightChar) {
                left.pop();
                right.pop();
            } else {
                right.push(left.pop());
            }
        }

        return right.size() == 0 ? 1 : 0;
    }

    private void basicSetting(String s) {
        left = new Stack<>();
        right = new Stack<>();
        char[] chars = s.toCharArray();
        for (char item : chars) {
            left.push(item);
        }
    }
}
