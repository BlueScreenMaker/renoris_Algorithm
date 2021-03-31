package programers._2021_03_31;

import java.util.Arrays;
import java.util.Comparator;

public class 단속카메라 {
    public int solution(int[][] routes) {
        Arrays.sort(routes, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] == o2[0]) {
                    return o1[1] - o2[1];
                }
                return o1[0] - o2[0];
            }
        });
        int count = 0;
        int end = -30001;

        for (int i = 0; i < routes.length; i++) {
            int getStart = routes[i][0];
            int getEnd = routes[i][1];
            if(getStart>end){
                count++;
                end=getEnd;
            }else{
                if(getEnd<end){
                    end=getEnd;
                }
            }
        }

        return count;
    }
}
