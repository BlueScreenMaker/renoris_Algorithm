package _2021._2021_04_07.programers;

public class 네트워크 {
    private int count;
    public boolean[] find;
    public int[][] computers;
    public int solution(int n, int[][] computers) {
        this.computers=computers;
        find=new boolean[n];
        for (int i = 0; i < n; i++) {
            if(!find[i]){
                dfs(i);
                count++;
            }
        }
        return count;
    }

    public void dfs(int start){
        find[start]=true;
        for (int i = 0; i < computers[start].length; i++) {
            if(computers[start][i]==1){
                if(!find[i]){
                    dfs(i);
                }
            }
        }
    }
}
