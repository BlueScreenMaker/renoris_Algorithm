package _2021._2021_05_26.programers;

import java.util.TreeMap;

public class 이중우선순위큐 {
    public int[] solution(String[] operations) {
        int[] result=new int[2];
        TreeMap<Integer,Integer> map=new TreeMap<>();
        for (int i = 0; i < operations.length; i++) {
            String[] record=operations[i].split(" ");
            int num=Integer.parseInt(record[1]);
            if(record[0].equals("I")){
                if(!map.containsKey(num)){
                    map.put(num,1);
                }else{
                    map.put(num,map.get(num)+1);
                }
            }else{
                if(!map.isEmpty()){
                    if(num==-1){
                        int firstKey=map.firstKey();
                        if(map.get(firstKey)==1){
                            map.remove(firstKey);
                        }else{
                            map.put(firstKey,map.get(firstKey)-1);
                        }
                    }else{
                        int lastKey=map.lastKey();
                        if(map.get(lastKey)==1){
                            map.remove(lastKey);
                        }else{
                            map.put(lastKey,map.get(lastKey)-1);
                        }
                    }
                }
            }
        }
        if(!map.isEmpty()){
            result[0]=map.lastKey();
            result[1]=map.firstKey();
        }
        return result;
    }
}
