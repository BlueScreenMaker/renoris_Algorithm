package _2021._2021_05_19.baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class _9536 {
    public static void main(String[] args) throws Exception {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));
        int testcase=Integer.parseInt(br.readLine());
        for (int i = 0; i < testcase; i++) {
            String[] line=br.readLine().split(" ");

            String voice="";
            while (true){
                voice=br.readLine();
                if(voice.equals("what does the fox say?")){
                    break;
                }
                String[] info=voice.split(" ");

                for (int j = 0; j < line.length; j++) {
                    if(line[j].equals(info[2])){
                        line[j]="";
                    }
                }
            }

            for (int j = 0; j < line.length; j++) {
                if(!line[j].equals("")){
                    bw.append(line[j]).append(" ");
                }
            }
            bw.append("\n");
        }
        bw.flush();


    }
}
