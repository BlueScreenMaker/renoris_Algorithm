package _2022._06_01.programmers;

public class 브라이언의_고민 {

    private final String INVALID = "invalid";

    public String solution(String sentence) {
        char[] words = sentence.toCharArray();
        StringBuilder sb = new StringBuilder();

        boolean isInvalid = false;
        boolean isRule1 = false;
        char rule1Alpha = ' ';
        boolean isRule2 = false;
        char rule2Alpha = ' ';

        //맨 처음 철자
        if (isUpper(words[0])) {
            sb.append(words[0]);
        }else {
            isRule2 = true;
            rule2Alpha = words[0];
        }

        for (int i = 1; i < words.length; i++) {
            char alpha = words[i];
            char beforeAlpha = words[i-1];

            //현 철자가 대문자
            if (isUpper(alpha)) {
                //현 철자가 대문자이면서 이전철자 대문자
                if(isUpper(beforeAlpha)) {
                    if (isRule1) { //현 철자 및 이전철자가 대문자인데 rule1이 true이면 안됨
                        isInvalid = true;
                        break;
                    } else {
                        sb.append(alpha);
                    }
                }else {
                    if(!isRule1 && !isRule2) { // 현 철자가 대문자이고 이전이 소문자면 rule1,2 둘중 하나는 켜져있어야함
                        isInvalid = true;
                        break;
                    }
                }
            } else {
                //현철자가 소문자이면서 이전 철자 대문자
                if(isUpper(beforeAlpha)) {
                    if (isRule1) {

                    }else {

                    }
                }
            }
        }



        return "";
    }

    private boolean isUpper(char alpha) {
        return Character.isUpperCase(alpha);
    }
}

