package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class _9184 {
    static int[][][] dp;
    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        try {
            dp=new int[21][21][21];
            dp[0][0][0]=1;
            while (true){
                String[] numbers=br.readLine().split(" ");
                int a=Integer.parseInt(numbers[0]);
                int b=Integer.parseInt(numbers[1]);
                int c=Integer.parseInt(numbers[2]);
                if(a==-1&&b==-1&&c==-1){
                    br.close();
                    System.out.println(sb);
                    break;
                }
                sb.append("w(").append(a).append(", ").append(b).append(", ").append(c).append(") = ").append(w(a, b, c)).append("\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static int w(int a,int b, int c){
        if (a <= 0 || b <= 0 || c <= 0){
            return dp[0][0][0];
        }
        if(a > 20 || b > 20 || c > 20){
            dp[20][20][20]=w(20,20,20);
            return dp[20][20][20];
        }
        if(dp[a][b][c]!=0){
            return dp[a][b][c];
        }
        if(a<b && b<c){
            dp[a][b][c]=w(a,b,c-1)+w(a,b-1,c-1)-w(a,b-1,c);
            return dp[a][b][c];
        }
        else{
            dp[a][b][c]=w(a-1, b, c) + w(a-1, b-1, c) + w(a-1, b, c-1) - w(a-1, b-1, c-1);
            return dp[a][b][c];
        }
    }
}
