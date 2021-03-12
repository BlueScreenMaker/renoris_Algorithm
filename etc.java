import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class etc {
    public void bsInsert(ArrayList<Integer> list, int item){
        int right=list.size()-1;
        int left=0;
        int mid=0;
        while(left<=right){
            mid=(left+right)/2;
            if(mid==right) break;
            int get=list.get(mid);
            if(get<item){
                left=mid+1;
            }else{
                right=mid;
            }
        }
        list.add(mid+1,item);
    }
}
