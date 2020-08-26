package day2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * <p>multithreading mapreduce </p>
 *
 **/



public class MapReduce_multithread {
    public static class Tuple<String, Integer> {
        public final String key;
        public final Integer value;

        public Tuple(String key, Integer value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public java.lang.String toString() {
            return key + "," + value;
        }
    }

    public List readFromFile(String fileName) {
        List<String> list = new ArrayList<>();
        try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
            //按行处理
            list = stream
                    .map(s -> s.replaceAll("[\\pP\\p{Punct}]", " "))
                    .map(String::toLowerCase)
                    .collect(Collectors.toList());

        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<List> splitting(int threadNum, List list) {
        List<List> splitLists = Collections.synchronizedList(new ArrayList<>());
        int left = 0;
        int right = list.size() / threadNum;
        if (threadNum > 1) {
            for (int i = 0; i < threadNum - 1; i++) {
                splitLists.add(list.subList(left, right));
                left = right ;
                right = left + list.size() / threadNum;
            }
        }
        splitLists.add(list.subList(left, list.size()));
        return splitLists;
    }

    public List<Tuple> mapping(List list) {
        List<Tuple> tuples = Collections.synchronizedList(new ArrayList<>());
        for (int i = 0; i < list.size(); i++) {
            String[] s = list.get(i).toString().split(" ");
            for (int j = 0; j < s.length; j++) {
                Tuple tuple = new Tuple(s[j], 1);
                tuples.add(tuple);
            }
        }
        return tuples;
    }

    public Map<String, ArrayList<Integer>> shuffling(List<Tuple> tuples) {

        Map<String, ArrayList<Integer>> listMap = Collections.synchronizedMap(new HashMap<>());
        for (int i = 0; i < tuples.size(); i++) {
            if (listMap.containsKey(tuples.get(i).key)) {
                listMap.get(tuples.get(i).key).add((Integer) tuples.get(i).value);
            } else {
                ArrayList<Integer> temp = new ArrayList<>();
                temp.add((Integer) tuples.get(i).value);
                listMap.put(String.valueOf(tuples.get(i).key), temp);
            }

        }
        return listMap;
    }

    public List<Tuple> reducing(Map<String, ArrayList<Integer>> listHashMap) {
        List<Tuple> tuples = Collections.synchronizedList(new ArrayList<>());
        for (String s : listHashMap.keySet()) {
            tuples.add(new Tuple(s, listHashMap.get(s).size()));
        }
        return tuples;
    }

    public void printResult(List<Tuple> results) {

        for (int i = 0; i < results.size(); i++) {
            System.out.println(results.get(i).toString());
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        long startTime = System.currentTimeMillis();

        MyThread myThread = new MyThread(4, "C:\\Users\\Qingfo\\IdeaProjects\\HIT\\src\\day2\\hamlet.txt");
        List<List> splitLists = myThread.getFile();
        myThread.createthread(splitLists);

        long endTime = System.currentTimeMillis();
        System.out.println("消耗时间: " + (endTime - startTime) +  "ms");
    }
}



