package baekjoon._2021_02_15;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class _9461 {
    static long[] dp;
    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb=new StringBuilder();
        try {
            dp=new long[101];
            long testcase=Integer.parseInt(br.readLine());
            for(int i=0; i<testcase; i++){
                long num=Integer.parseInt(br.readLine());
                sb.append(recursion(num)).append("\n");
            }
            System.out.println(sb);
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static long recursion(long num){
        int i=(int) num;
        if(dp[i]!=0){
            return dp[i];
        }
        if(i==1||i==2||i==3){
            dp[i]=1;
            return dp[i];
        }
        dp[i]=recursion(i-2)+recursion(i-3); //숫자 넘어가면 중간에서 overflow
        return dp[i];
    }
}
