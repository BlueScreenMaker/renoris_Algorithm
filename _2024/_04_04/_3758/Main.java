package _2024._04_04._3758;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int testCase = Integer.parseInt(br.readLine());

        for (int i = 0; i < testCase; i++) {
            int[] firstLine = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt).toArray();

            Info info = new Info(firstLine);
            for (int j = 0; j < firstLine[3]; j++) {
                int[] line = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt).toArray();
                //0부터 팀번호와 문제번호가 존재하기에 맨앞단에서 가공
                info.submit(line[0]-1, line[1]-1, line[2]);
            }

            sb.append(info.getMyTeamOrder()).append("\n");
        }

        System.out.println(sb.toString());

    }

    static class Info {
        //팀의 정보들
        private Team[] teams;

        //내 팀의 id
        private int myTeamId;

        //총 제출 횟수
        private int totalSubmitCount;

        public Info (int[] info) {
            this.teams = new Team[info[0]];
            //모든 팀이 적어도 한번씩 제출을 할것이기에 미리 팀 전부 셋팅
            for (int i = 0; i < info[0]; i++) {
                this.teams[i] = new Team(i+1, info[1]);
            }

            this.myTeamId = info[2];
            this.totalSubmitCount = 0;
        }

        public void submit(int teamNum, int problemNum, int score) {
            this.totalSubmitCount++;
            this.teams[teamNum].submit(problemNum, score, this.totalSubmitCount);
        }

        public int getMyTeamOrder () {
            Arrays.sort(teams);
            for (int i = 0; i < teams.length; i++) {
                if (teams[i].teamId == myTeamId) return ++i;
            }
            return 0;
        }
    }

    static class Team implements Comparable<Team> {

        private int teamId;

        //받은 점수 총합
        private int[] scores;

        //마지막 제출시 순서
        private int lastSubmit;

        //제출 횟수
        private int countSubmit;

        public Team (int teamId, int problemNum) {
            this.teamId = teamId;
            this.scores = new int[problemNum];
            this.lastSubmit = 0;
            this.countSubmit = 0;
        }

        //팀의 전체 점수 계산
        public int getTotalScore() {
            return Arrays.stream(scores).sum();
        }

        //팀 점수 제출
        public void submit(int problem, int score, int submitOrder) {
            this.countSubmit++;
            if (this.scores[problem] < score) this.scores[problem] = score;
            this.lastSubmit = submitOrder;
        }

        //정렬시 팀 순위를 요구사항에 맞게 정렬
        @Override
        public int compareTo(Team otherTeam) {
            if (this.getTotalScore() == otherTeam.getTotalScore()) {
                if (this.countSubmit == otherTeam.countSubmit) {
                    return this.lastSubmit - otherTeam.lastSubmit;
                }
                return this.countSubmit - otherTeam.countSubmit ;
            }
            return otherTeam.getTotalScore() - this.getTotalScore();
        }
    }
}
