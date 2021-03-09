package baekjoon._2021_03_12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class _12789 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int student = Integer.parseInt(br.readLine());
        Stack<Integer> one = new Stack<>();
        Stack<Integer> two = new Stack<>();
        String[] numbers = br.readLine().split(" ");
        int count = 1;

        for (int i = student - 1; i >= 0; i--) {
            one.push(Integer.parseInt(numbers[i]));
        }
        String result = "Nice";
        while (count <= student) {
            if (!one.isEmpty()) {
                int first = one.pop();
                if (first == count) { //번호표가 맞을경우 뺀다
                    count++;
                } else {
                    if (!two.isEmpty()) { //번호표가 틀렷을경우 보낼것인가 아니면 다른줄에 있는애를 볼것인가
                        int second = two.pop();
                        if (second == count) { //다른줄에 있는애가 맞앗으면 보내고 다시 원래줄로 돌려보냄
                            count++;
                            one.push(first);
                        } else { //다른줄에 있는애가 틀렷으면 개를 원위치 시키고 원래줄에있는애를 다른줄로
                            two.push(second);
                            two.push(first);
                        }
                    } else {
                        two.push(first);
                    }
                }
            } else { //원래줄이 텅비었다?
                int third = two.pop();
                if (third == count) {
                    count++;
                } else {
                    result = "Sad";
                    break;
                }
            }
        }
        System.out.println(result);
    }
    public static void main2(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int student = Integer.parseInt(br.readLine());
        Stack<Integer> one = new Stack<>();
        Stack<Integer> two = new Stack<>();
        String[] numbers = br.readLine().split(" ");
        int count = 1;

        for (int i = student - 1; i >= 0; i--) {
            one.push(Integer.parseInt(numbers[i]));
        }
        String result = "Nice";
        while (count <= student) {
            if (!one.isEmpty()) {
                int first = one.pop();
                if (first == count) {
                    count++;
                } else {
                    if (!two.isEmpty()) {
                        int second = two.pop();
                        if (second == count) count++;
                        else {
                            two.push(second);
                            two.push(first);
                        }
                    } else {
                        two.push(first);
                    }
                }
            } else {
                int third = two.pop();
                if (third == count) {
                    count++;
                } else {
                    result = "Sad";
                    break;
                }
            }
            System.out.println(one);
            System.out.println(two);
        }
        System.out.println(result);


    }
}
