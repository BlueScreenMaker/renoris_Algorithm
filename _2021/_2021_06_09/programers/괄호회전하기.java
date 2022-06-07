package _2021._2021_06_09.programers;

import java.util.Stack;

public class 괄호회전하기 {
    public int solution(String s) {
        int length = s.length();
        int answer = 0;
        for (int i = 0; i < length; i++) {
            StringBuilder sb = new StringBuilder();
            Stack<Character> stack = new Stack<>();
            boolean isNot = false;

            //회전
            sb.append(s);
            String line = sb.substring(0, i);
            sb.delete(0, i);
            sb.append(line);

            //변환
            char[] array = sb.toString().toCharArray();

            //찾기
            for (int j = 0; j < array.length; j++) {
                switch (array[j]) {
                    case '[':
                    case '{':
                    case '(':
                        stack.push(array[j]);
                        break;
                    default:
                        if (!check(stack, array[j])) {
                            isNot = true;
                        }
                }

                if (isNot) {
                    break;
                }
            }
            if(!isNot&&stack.size()==0){
                answer++;
            }
        }
        return answer;
    }

    public boolean check(Stack<Character> stack, char item) {
        if (stack.size() > 0) {
            char inner = stack.peek();
            switch (item) {
                case ']':
                    if (inner == '[') {
                        stack.pop();
                    }
                    else{
                        return false;
                    }
                    break;
                case '}':
                    if (inner == '{') {
                        stack.pop();
                    }
                    else{
                        return false;
                    }
                    break;
                case ')':
                    if (inner == '(') {
                        stack.pop();
                    }else{
                        return false;
                    }
                    break;

            }
            return true;
        } else {
            return false;
        }
    }

}
