package programers._2021_02_19;

import java.util.ArrayList;

public class 방금그곡 {
    public String solution(String m, String[] musicinfos) {
        String answer = "(None)";
        ArrayList<String> findmusiclist=new ArrayList<>();
        for (int i = 0; i < musicinfos.length; i++) {
            String[] info = musicinfos[i].split(",");
            String[] startTime = info[0].split(":");
            String[] endTime = info[1].split(":");
            String musicName = info[2];
            String melody = info[3];


            //musicinfos 내부의 멜로디를 곡 재생시간만큼 지속
            int timeDistance = Integer.parseInt(endTime[0])-Integer.parseInt(startTime[0]);
            int minuteDistance = Integer.parseInt(endTime[1])-Integer.parseInt(startTime[1])+(timeDistance*60);
            StringBuilder sb = new StringBuilder();
            char[] melodychar=melody.toCharArray();
            int count=0;
            for (int j = 0; j < minuteDistance; j++) {
                sb.append(melodychar[count]);
                if(melodychar[count]=='C'||melodychar[count]=='D'||melodychar[count]=='F'||melodychar[count]=='G'||melodychar[count]=='A'){
                    if(count<melodychar.length-1){
                        if(melodychar[count+1]=='#'){
                            sb.append(melodychar[count+1]);
                            count++;
                        }
                    }
                }
                count++;
                if(count>melodychar.length-1){
                    count-=melodychar.length;
                }
            }

            //m과 지속한것을 대조
            boolean find=false;
            for (int j = 0; j < sb.length(); j++) { //
                if(find) break;
                for (int k = 0; k < m.length(); k++) {
                    try{
                        if(!m.substring(k,k+1).equals(sb.substring(j,j+1))){
                            break;
                        }
                    }catch (Exception e){
                        break;
                    }
                    if(k==m.length()-1){
                        try{
                            if(!sb.substring(j+1,j+2).equals("#")){
                                find=true;
                            }
                        }catch (Exception e){
                            find=true;
                        }
                    }
                    j++;
                }
            }
            if (find){
                String result=musicName+","+minuteDistance;
                findmusiclist.add(result);
            }
        }

        //모은것들중 가장 시간이 긴것을 빼내는 작업
        if (findmusiclist.size() != 0) {
            int max = 0;
            for (int i = 0; i < findmusiclist.size(); i++) {
                String[] info = findmusiclist.get(i).split(",");
                if (max < Integer.parseInt(info[1])) {
                    max = Integer.parseInt(info[1]);
                    answer = info[0];
                }
            }
        }
        return answer;
    }
}
