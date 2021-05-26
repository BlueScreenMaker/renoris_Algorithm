package baekjoon._2021_05_26;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class _7662 {
    public static void main1(String[] args) throws Exception {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        PriorityQueue<Integer> maxQueue=new PriorityQueue<>(Comparator.reverseOrder());
        PriorityQueue<Integer> minQueue=new PriorityQueue<>();
        int testcase= Integer.parseInt(br.readLine());
        StringBuilder sb=new StringBuilder();
        for (int i = 0; i < testcase; i++) {
            int testNum=Integer.parseInt(br.readLine());
            for (int j = 0; j < testNum; j++) {
                String[] input=br.readLine().split(" ");
                //더하기
                if(input[0].equals("I")){
                    int num=Integer.parseInt(input[1]);
                    if(maxQueue.size()>0){
                        maxQueue.offer(num);
                    }else{
                        minQueue.offer(num);
                    }
                    //빼기
                }else if (input[0].equals("D")){
                    if(minQueue.size()>0||maxQueue.size()>0){
                        if(input[1].equals("-1")){
                            int size=maxQueue.size();
                            for (int k = 0; k < size; k++) {
                                minQueue.offer(maxQueue.poll());
                            }
                            minQueue.poll();
                        }else{
                            int size=minQueue.size();
                            for (int k = 0; k < size; k++) {
                                maxQueue.offer(minQueue.poll());
                            }
                            maxQueue.poll();
                        }
                    }
                }
            }

            if(maxQueue.size()==0&&minQueue.size()==0){
                sb.append("EMPTY").append("\n");
            }else{
                if(maxQueue.isEmpty()){
                    while (!minQueue.isEmpty()){
                        maxQueue.offer(minQueue.poll());
                    }
                }
                sb.append(maxQueue.peek()).append(" ");
                while (!maxQueue.isEmpty()){
                    minQueue.offer(maxQueue.poll());
                }
                sb.append(minQueue.peek()).append("\n");
            }
        }
        System.out.println(sb.toString());

    }
    public static void main2(String[] args) throws Exception {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        PriorityQueue<Integer> maxQueue=new PriorityQueue<>(Comparator.reverseOrder());
        PriorityQueue<Integer> minQueue=new PriorityQueue<>();
        PriorityQueue<Integer> deleteMax=new PriorityQueue<>(Comparator.reverseOrder());
        PriorityQueue<Integer> deleteMin=new PriorityQueue<>();
        StringBuilder sb=new StringBuilder();
        int testcase= Integer.parseInt(br.readLine());

        for (int i = 0; i < testcase; i++) {
            int testNum=Integer.parseInt(br.readLine());
            for (int j = 0; j < testNum; j++) {
                String[] input=br.readLine().split(" ");
                //더하기
                if(input[0].equals("I")){
                    int num=Integer.parseInt(input[1]);
                    maxQueue.offer(num);
                    minQueue.offer(num);
                }//빼기
                else if (input[0].equals("D")){
                    if(minQueue.size()>=deleteMin.size()&&maxQueue.size()>=deleteMax.size()){
                        if(input[1].equals("-1")){
                            deleteMax.offer(minQueue.peek());
                            deleteMin.offer(minQueue.peek());
                        }else{
                            deleteMin.offer(maxQueue.poll());
                            deleteMax.offer(maxQueue.peek());
                        }
                    }
                }
            }
            //끝나고
            Queue<Integer> minout=new LinkedList<>();
            Queue<Integer> maxout=new LinkedList<>();
            deleteFind(minQueue, deleteMin, minout);
            deleteFind(maxQueue, deleteMax, maxout);
            if(maxQueue.isEmpty()||minQueue.isEmpty()){
                sb.append("EMPTY").append("\n");
            }else{
                sb.append(maxQueue.peek()).append(" ").append(minQueue.peek()).append("\n");
            }
        }
        System.out.println(sb.toString());
    }

    private static void deleteFind(PriorityQueue<Integer> minQueue, PriorityQueue<Integer> deleteMin, Queue<Integer> minout) {
        while (!minQueue.isEmpty()){
            int num=minQueue.poll();
            if(!deleteMin.isEmpty()){
                if(num!=deleteMin.peek()){
                    System.out.println("delete.peek="+deleteMin.peek());
                    minout.offer(num);
                    deleteMin.poll();
                }
            }
        }
        while (!minout.isEmpty()){
            minQueue.offer(minout.poll());
        }
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb=new StringBuilder();
        int testcase= Integer.parseInt(br.readLine());
        for (int i = 0; i < testcase; i++) {
            TreeMap<Integer,Integer> treeMap=new TreeMap<>();
            int testNum=Integer.parseInt(br.readLine());
            for (int j = 0; j < testNum; j++) {
                String[] input=br.readLine().split(" ");
                //더하기
                if(input[0].equals("I")){
                    Integer num=Integer.parseInt(input[1]);
                    if(!treeMap.containsKey(num)){
                        treeMap.put(num,1);
                    }else{
                        treeMap.put(num,treeMap.get(num)+1);
                    }
                }//빼기
                else if (input[0].equals("D")){
                    if(!treeMap.isEmpty()){
                        if(input[1].equals("-1")){
                            int firstKey=treeMap.firstKey();
                            int num=treeMap.get(firstKey)-1;
                            if(num==0){
                                treeMap.remove(firstKey);
                            }else{
                                treeMap.put(firstKey,num);
                            }
                        }else{
                            int lastKey=treeMap.lastKey();
                            int num=treeMap.get(lastKey)-1;
                            if(num==0){
                                treeMap.remove(lastKey);
                            }else{
                                treeMap.put(lastKey,num);
                            }
                        }
                    }
                }
            }
            //끝나고
            if(treeMap.isEmpty()){
                sb.append("EMPTY").append("\n");
            }else{
                sb.append(treeMap.lastKey()).append(" ").append(treeMap.firstKey()).append("\n");
            }
        }
        System.out.println(sb.toString());
    }
}
