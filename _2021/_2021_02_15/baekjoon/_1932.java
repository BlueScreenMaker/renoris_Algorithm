package _2021._2021_02_15.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class _1932 {
    static int[][] dp;
    static int[][] triangle;
    //364
    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            dp = new int[501][501];
            for(int i=0; i<501; i++){
                Arrays.fill(dp[i],-1);
            }
            int width=Integer.parseInt(br.readLine());
            triangle =new int[width][width];
            for(int i=0;i<width;i++){
                String[] line=br.readLine().split(" ");
                for(int j=0; j<=i; j++){
                    triangle[i][j]=Integer.parseInt(line[j]);
                }
            }
            for(int i=0; i<width; i++){
                for(int j=0; j<=i; j++){
                    if(i==0){
                        dp[i][j]=triangle[i][j];
                    }else{
                        if(j==0){
                            dp[i][j]=dp[i-1][j]+triangle[i][j];
                        }else{
                            dp[i][j]=Math.max(dp[i-1][j]+triangle[i][j],dp[i-1][j-1]+triangle[i][j]);
                        }
                    }
                }
            }
            int max=0;
            for(int i=0; i<=width; i++){
                if(max<dp[width-1][i]){
                    max=dp[width-1][i];
                }
            }
            System.out.println(max);
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //356
    static int[][] dp2;
    static int[][] triangle2;
    public static void main2(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            dp2 = new int[501][501];
            for(int i=0; i<501; i++){
                Arrays.fill(dp2[i],-1);
            }
            int width=Integer.parseInt(br.readLine());
            triangle2 =new int[width][width];
            for(int i=0;i<width;i++){
                String[] line=br.readLine().split(" ");
                for(int j=0; j<=i; j++){
                    triangle2[i][j]=Integer.parseInt(line[j]);
                }
            }
            int max=0;
            for(int i=0; i<width; i++){
                if(max< recursion2(width-1,i)){
                    max= recursion2(width-1,i);
                }
            }
            System.out.println(max);
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static int recursion2(int x, int y){
        if(dp2[x][y]!=-1){
            return dp2[x][y];
        }
        if(x==0){
            dp2[x][y]= triangle2[x][y];
        }else{
            if(y!=0){
                dp2[x][y]=Math.max(triangle2[x][y]+ recursion2(x-1,y), triangle2[x][y]+ recursion2(x-1,y-1));
            }else{
                dp2[x][y]= triangle2[x][y]+ recursion2(x-1,y);
            }
        }
        return dp2[x][y];
    }

    //324
    static int[][] dp3;
    static int[][] triangle3;
    public static void main3(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            dp3 = new int[501][501];
            for(int i=0; i<501; i++){
                Arrays.fill(dp3[i],-1);
            }
            int width=Integer.parseInt(br.readLine());
            triangle3 =new int[width][width];
            for(int i=0;i<width;i++){
                String[] line=br.readLine().split(" ");
                for(int j=0; j<=i; j++){
                    triangle3[i][j]=Integer.parseInt(line[j]);
                }
            }
            int max=0;
            for(int i=0; i<width; i++){
                if(max< recursion3(width-1,i)){
                    max= recursion3(width-1,i);
                }
            }
            System.out.println(max);
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static int recursion3(int x, int y){
        if(dp3[x][y]!=-1){
            return dp3[x][y];
        }
        if(x==0){
            dp3[x][y]= triangle3[x][y];
        }else{
            if(y!=0){
                if(triangle3[x-1][y]==-1){
                    dp3[x][y]= dp3[x-1][y-1]+ triangle3[x][y];
                }
                else{
                    dp3[x][y]=Math.max(triangle3[x][y]+ recursion3(x-1,y), triangle3[x][y]+ recursion3(x-1,y-1));
                }
            }else{
                dp3[x][y]= triangle3[x][y]+ recursion3(x-1,y);
            }
        }
        return dp3[x][y];
    }
}
