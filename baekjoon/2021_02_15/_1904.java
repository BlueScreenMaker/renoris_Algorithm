package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class _1904 {
    static int[] dp;
    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            dp=new int[1000001];
            int num=Integer.parseInt(br.readLine());
            for(int i=1; i<=num; i++){
                if(i==1){
                    dp[i]=1;
                }else{
                    if(i==2){
                        dp[i]=2;
                    }else{
                        dp[i]=(dp[i-1]+dp[i-2])%15746; //마지막에 나누면 중간과정에서 int 범위 overflow
                    }
                }
            }
            br.close();
            System.out.println(dp[num]);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //일반적인 dp재귀
    //다만 사용시 java.lang.stackOverflow
    public static int recursion_normal(int n){
        if(dp[n]!=0){
            return dp[n];
        }
        if(n==1){
            dp[n]=1;
            return dp[n];
        }
        if(n==2){
            dp[n]=2;
            return dp[n];
        }
        else{
            dp[n]=(recursion_normal(n-1)+recursion_normal(n-2))%15746;
            return dp[n];
        }
    }
}
