import java.util.Arrays;

class Solution {

    //셔틀버스
    //09:00 시부터 n회, t분 간격, m명이 탈수있음
    //n <= 10회
    //t <= 60분
    //m <= 45명
    //09:00에 도착한셔틀은 09:00에 있는애도 태우고간다


    //셔틀은 9시부터 n회 t분 간격 m승객이 탈수 있음
    //일단 탈수있는 버스중 가장 막차를 탄다
    //=> 경우의 수가 잇음
    //가장 막차가 왔을때 남아있는 사람이 m 보다 적다
    //=> 그럼 그냥 버스 도착시간에 딱맞춰서 도착
    //가장 막차가 왓을때 남아있는 사람이 m보다 많다.
    //=> m의 마지막사람보다 1분 일찍오기 - 정렬 되어 있어야함
    public String solution(int n, int t, int m, String[] timetable) {
        int[] timeNumTable = convertStringToIntTimeTable(timetable);
        Arrays.sort(timeNumTable);

        int busTime = 60 * 9;
        int index = 0;
        
        //막차 이전에 탈 사람들 태워서 보내버리기
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < m; j++) {
                if (timeNumTable[index] <= busTime) {
                    index++;
                } else {
                    break;
                }
            }
            busTime += t;
        }

        //막차가 도착했을때 버스장에 있는 사람 갯수 (막차 이후에 온 사람은 어차피 못탈 사람들이니 제외)
        int remainPeople = countRemainPeople(timeNumTable, index, busTime);

        //남아있는 사람이 마지막에 데리고갈 사람보다 적으면 그냥 막차시간에 탑승
        if (remainPeople < m) {
            return convertTimeIntToString(busTime);
        }

        //많으면 마지막에 탈 사람보다 1분빨리 도착
        return convertTimeIntToString(timeNumTable[index + m - 1] - 1);

    }

    private int countRemainPeople(int[] timeNumTable, int startIndex, int busTime) {
        int result = 0;
        for (int i = startIndex; i < timeNumTable.length; i++) {
            if (timeNumTable[startIndex] <= busTime) {
                result++;
                startIndex++;
            }
        }

        return result;
    }

    private int[] convertStringToIntTimeTable(String[] timeTable) {
        int[] convertedTimeTable = new int[timeTable.length];
        for (int i = 0; i < timeTable.length; i++) {
            convertedTimeTable[i] = convertTimeStringToInt(timeTable[i]);
        }
        return convertedTimeTable;
    }

    private int convertTimeStringToInt(String time) {
        int result = 0;
        String[] line = time.split(":");
        result += 60 * Integer.parseInt(line[0]);
        result += Integer.parseInt(line[1]);
        return result;
    }

    private String convertTimeIntToString(int time) {
        int hour = time / 60;
        int minute = time % 60;

        return String.format("%s:%s", addPrefixZero(hour), addPrefixZero(minute));
    }

    private String addPrefixZero(int num) {
        StringBuilder sb = new StringBuilder();
        if (num / 10 == 0) {
            sb.append("0");
        }
        sb.append(num);
        return sb.toString();
    }

}