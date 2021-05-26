package programers._2021_05_19;

public class 신규_아이디_추천 {
    public String solution(String new_id) {
        //1단계-소문자 치환
        new_id=new_id.toLowerCase();

        //2단계 new_id에서 알파벳 소문자, 숫자, 빼기(-), 밑줄(_), 마침표(.)를 제외한 모든 문자를 제거합니다.
        new_id= new_id.replaceAll("[^a-z0-9-_.]",""); //^ 아닌것 (a-z까지 0-9까지 - . _)
        //3단계 new_id에서 마침표(.)가 2번 이상 연속된 부분을 하나의 마침표(.)로 치환합니다.
        new_id = new_id.replaceAll("[.]{2,}","."); // [.] 마침표를 2번이상 반복된것을 치우기
        //4단계 new_id에서 마침표(.)가 처음이나 끝에 위치한다면 제거합니다.
        new_id = new_id.replaceAll("^[.]|[.]$",""); //^가 대괄호 안이면 제외 밖이면 처음을 뜻함 &은 끝을 뜻함 즉 ^[.]은 처음에서 다음. [.]$은 끝에서 다음.
        //5단계 new_id가 빈 문자열이라면, new_id에 "a"를 대입합니다.
        if(new_id.equals("")){
            new_id="a";
        }

        //6단계 new_id의 길이가 16자 이상이면, new_id의 첫 15개의 문자를 제외한 나머지 문자들을 모두 제거합니다.
        if(new_id.length()>15){
            new_id=new_id.substring(0,15);
//            new_id = new_id.replaceAll("[.]$","");//끝에 위치한 마침표 제거
            new_id = new_id.replaceAll("\\.$","");//끝에 위치한 마침표 제거
        }

        //7new_id의 길이가 2자 이하라면, new_id의 마지막 문자를 new_id의 길이가 3이 될 때까지 반복해서 끝에 붙입니다.
        if(new_id.length()<=2){
            String a=new_id.substring(new_id.length()-1);
            StringBuilder new_idBuilder = new StringBuilder(new_id);
            while (!(new_idBuilder.length()>=3)){
                new_idBuilder.append(a);
            }
            new_id = new_idBuilder.toString();
        }
        return new_id;

    }
}
