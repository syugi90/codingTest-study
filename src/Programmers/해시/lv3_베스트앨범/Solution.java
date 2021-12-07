package Programmers.해시.lv3_베스트앨범;

import java.sql.Array;
import java.util.*;
import java.util.Map.Entry;

/*
    코딩테스트연습 > 해시 > 베스트앨범
    https://programmers.co.kr/learn/courses/30/lessons/42579

    스트리밍 사이트에서 장르 별로 가장 많이 재생된 노래를 두 개씩 모아 베스트 앨범을 출시하려 합니다.
    노래는 고유 번호로 구분하며, 노래를 수록하는 기준은 다음과 같습니다.
        1.속한 노래가 많이 재생된 장르를 먼저 수록합니다.
        2.장르 내에서 많이 재생된 노래를 먼저 수록합니다.
        3.장르 내에서 재생 횟수가 같은 노래 중에서는 고유 번호가 낮은 노래를 먼저 수록합니다.
    노래의 장르를 나타내는 문자열 배열 genres와 노래별 재생 횟수를 나타내는 정수 배열 plays가 주어질 때,
    베스트 앨범에 들어갈 노래의 고유 번호를 순서대로 return 하도록 solution 함수를 완성하세요.
 */
class Solution {

    public int[] solution(String[] genres, int[] plays) {
        int[] answer = {};
        List<Integer> answerList = new ArrayList<>();

        HashMap<String, Integer> genresMap = new HashMap();
        for(int i=0; i<genres.length; i++){
            genresMap.put(genres[i],genresMap.getOrDefault(genres[i],0)+plays[i]);
        }

        List<Entry<String,Integer>> gen_list = new ArrayList<Entry<String, Integer>>(genresMap.entrySet());
        Collections.sort(gen_list, new Comparator<Entry<String, Integer>>() {
            @Override
            public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {
                return o2.getValue().compareTo(o1.getValue());
            }
        });

        //출력테스트
      for(Entry<String,Integer> g : gen_list){
            System.out.println("g = " + g);
        }

        for(Entry<String, Integer> entry : gen_list){
            HashMap<Integer, Integer> playMap = new HashMap();
            String gen = entry.getKey();
            for(int i=0; i<plays.length; i++){
                if(gen.equals(genres[i])){
                    playMap.put(i,plays[i]);
                }
            }

            List<Entry<Integer,Integer>> play_list = new ArrayList<Entry<Integer,Integer>>(playMap.entrySet());
            Collections.sort(play_list, new Comparator<Entry<Integer, Integer>>() {
                @Override
                public int compare(Entry<Integer, Integer> o1, Entry<Integer, Integer> o2) {
                    return o2.getValue().compareTo(o1.getValue());
                }
            });

            //출력테스트
            for(Entry<Integer,Integer> p : play_list){
                System.out.println("p = " + p);
            }

            answerList.add(play_list.get(0).getKey());
            if(play_list.size() > 1){
                answerList.add(play_list.get(1).getKey());
            }
        }

        answer = new int[answerList.size()];
        for(int i=0; i<answerList.size(); i++){
            answer[i] = answerList.get(i);
        }

        return answer;
    }

}
