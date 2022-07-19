package _2022._07_20.programmers;

import java.util.HashMap;

class 다단계칫솔판매 {
    private HashMap<String, Member> memberHashMap = new HashMap<>();
    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        //다단계 멤버 리스트 저장
        for (int i = 0; i < enroll.length; i++) {
            Member member = new Member(enroll[i]);
            memberHashMap.put(enroll[i], member);
            
            //후원자도 저장
            if (!referral[i].equals("-")) {
                member.setReferral(memberHashMap.get(referral[i]));
            }
        }

        for (int i = 0; i < seller.length; i++) {
            memberHashMap.get(seller[i]).sell(amount[i]*100);
        }

        int[] result = new int[enroll.length];
        for (int i = 0; i < result.length; i++) {
            result[i] = memberHashMap.get(enroll[i]).getMoney();
        }
        
        return result;
    }

    class Member {
        private String name;
        private int money;
        private Member referral;

        public Member(String name) {
            this.name = name;
        }

        public void setReferral(Member referral) {
            this.referral = referral;
        }

        public int getMoney() {
            return money;
        }

        public void sell(int revenue) {

            if (revenue < 10) {
                money+= revenue;
                return;
            }

            //어차피 단위는 10단위기에
            if (revenue < 100) {
                money += revenue % 10;
            }

            int distribution = revenue/10;

            if (referral != null) {
                referral.sell(distribution);
            }
            money += distribution*9;
        }
    }
}