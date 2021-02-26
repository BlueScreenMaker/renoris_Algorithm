package baekjoon._2021_02_19;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class _1463 {
    static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        dp = new int[1000001]; //1~10^6 의 정수
        //초깃값 부여
        dp[1] = 0;
        dp[2] = 1;
        dp[3] = 1;
        int num = Integer.parseInt(br.readLine());

        for (int i = 4; i <= num; i++) {
            if (i % 3 == 0 && i % 2 == 0) {
                dp[i] = 1 + Math.min(dp[i / 3], dp[i / 2]);
            } else if (i % 3 == 0) {
                dp[i] = 1 + Math.min(dp[i / 3], dp[i - 1]);
            } else if (i % 2 == 0) {// mod 3==1
                dp[i] = 1 + Math.min(dp[i / 2], dp[i - 1]);
            } else {
                dp[i] = 1 + dp[i - 1];
            }
        }
        System.out.println(dp[num]);
    }

    static int[] dp2;

    public static void main2(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        dp2 = new int[1000001]; //1~10^6 의 정수
        //초깃값 부여
        dp2[2] = 1;
        dp2[3] = 1;
        int num = Integer.parseInt(br.readLine());
        int result = 0;
        if (num > 1) {
            result = recursion(num);
        }
        System.out.println(result);
    }

    public static int recursion(int num) {
        if (dp2[num] != 0) {
            return dp2[num];
        }
        if(num % 3 == 0&&num % 2 == 0){
            dp2[num] = 1 + Math.min(recursion(num / 3), recursion(num /2));
        }
        else if (num % 3 == 0) {
            dp2[num] = 1 + Math.min(recursion(num / 3), recursion(num - 1));
        } else if (num % 2 == 0) {
            dp2[num] = 1 + Math.min(recursion(num / 2), recursion(num - 1));
        } else {
            dp2[num] = 1 + recursion(num - 1);
        }
        return dp2[num];
    }

}
