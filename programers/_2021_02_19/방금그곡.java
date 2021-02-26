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
                if(count==melodychar.length){
                    count=0;
                }
            }
            //m과 지속한것을 대조
            boolean find=false;
            for (int j = 0; j < sb.length(); j++) { //곡길이만큼의 멜로디 배열
                if(find) break;
                for (int k = 0; k < m.length(); k++) { //네오가 기억하는 멜로디
                    try{
                        if(!m.substring(k,k+1).equals(sb.substring(j+k,j+1+k))){ //네오가 기억하는 멜로디의 첫음이 현재 곡길이의 첫음이 아니면 현재 곡길이+1
                            break;
                        }
                    }catch (Exception e){
                        break;
                    }
                    if(k==m.length()-1){ //네오가 기억하는 멜로디의 와 현재 곡길이 만큼의 결과가 다맞았다고 했을때 그 곡길이 뒤에 #이 있냐없냐..
                        try{//여기서 에러뜨면 현재 곡길이 또한 마지막이므로 뒤에 #이 없다고 볼수있다
                            if(!sb.substring(j+k+1,j+k+2).equals("#")){
                                find=true;
                            }
                        }catch (Exception e){
                            find=true;
                        }
                    }
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

    public String solution2(String m, String[] musicinfos) {
        String answer = "(None)";
        ArrayList<String> findmusiclist = new ArrayList<>();
        m = m.replace("C#", "c");
        m = m.replace("D#", "d");
        m = m.replace("A#", "a");
        m = m.replace("A#", "a");
        m = m.replace("F#", "f");

        for (int i = 0; i < musicinfos.length; i++) {
            String[] info = musicinfos[i].split(","); 
            String[] startTime = info[0].split(":");
            String[] endTime = info[1].split(":");
            String musicName = info[2];
            String melody = info[3];

            //musicinfos, m의 멜로디를 전부 바꿈
            int timeDistance = Integer.parseInt(endTime[0]) - Integer.parseInt(startTime[0]);
            int minuteDistance = Integer.parseInt(endTime[1]) - Integer.parseInt(startTime[1]) + (timeDistance * 60);
            StringBuilder sb = new StringBuilder();
            melody = melody.replace("C#", "c");
            melody = melody.replace("D#", "d");
            melody = melody.replace("A#", "a");
            melody = melody.replace("G#", "g");
            melody = melody.replace("F#", "f");

            //melody를 곡 재생시간만큼 삽입
            int count = 0;
            for (int j = 0; j < minuteDistance; j++) {
                String item = melody.substring(count, count + 1);
                sb.append(item);
                count++;
                if (count == melody.length()) {
                    count = 0;
                }
            }

            //이곡이 맞다면 맞는 음악 리스트에 삽입
            String line = sb.toString();
            if (line.contains(m)) {
                findmusiclist.add(musicName + "," + minuteDistance);
            }
        }

        //맞는 음악리스트에서 가장 시간이 긴것+ 시간이 긴것중 가장 빠른것 을 빼내는작업
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
