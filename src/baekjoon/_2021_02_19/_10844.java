
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class _10844 {
    static long[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        dp = new long[101][10]; //100의 자리까지의수 , 0~9
        Arrays.fill(dp[1],1);
        int number= Integer.parseInt(br.readLine());
        for(int i=2; i<=number; i++){
            for (int j = 0; j < 10; j++) {
                if(j==0){
                    dp[i][j]=dp[i-1][j+1];
                }else if(j==9){
                    dp[i][j]=dp[i-1][j-1];
                }else{
                    dp[i][j]=((dp[i-1][j-1])+(dp[i-1][j+1]))%1000000000;
                }
            }
        }
        int result=0;
        for (int i = 1; i < 10; i++) { //1은제외
            result+=dp[number][i];
            result%=1000000000;
        }
        System.out.println(result);
    }
}
