package day2;

import java.io.*;
import java.util.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.stream.Collectors;

/**
 * 多线程版MapReduce
 *
 * */
public class MapReduce_multithread {

    public void splitter() {
        try {
            String path = MapReduce_multithread.class.getResource("").getPath();
            File file = new File(path + "/hamlet.txt");
            FileReader fr = new FileReader(file);

            //获取行数
            LineNumberReader reader = new LineNumberReader(fr);
            reader.skip(Long.MAX_VALUE);
            int lines = reader.getLineNumber();
            reader.close();

            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));

            StringBuilder sBuilder = new StringBuilder();
            String line;
            int count = 0;
            while((line = br.readLine()) != null) {
                line = line.replaceAll("[\\pP+~$`^=|<>～｀＄＾＋＝｜＜＞￥×]", " ").toLowerCase();
                sBuilder.append(line).append(" ");
                count++;
                if (count == lines / MAPPER_NUM + 1) {
                    lineStrings.put(sBuilder.toString());
                    count = 0;
                    sBuilder = new StringBuilder();
                }
            }
            if (count != 0) {
                lineStrings.put(sBuilder.toString());
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void initMapJob() {
        for (int i = 0; i < MAPPER_NUM; i++) {
            Thread t = new Thread(new Mapper(lineStrings, spills, mapJobLatch));
            t.start();
        }
    }

    public void initReduceJob() {
        for (int i = 0; i < REDUCER_NUM; i++) {
            Thread t = new Thread(new Reducer(shuffles, results, reduceJobLatch));
            t.start();
        }
    }

    public void start() throws InterruptedException {
        splitter();
        mapJobLatch.await();
        shuffle();
        reduceJobLatch.await();
    }

    public void shuffle() {
        spills.forEach(s -> {
            if (merge.containsKey(s.getA())) {
                Tuple<String, List<Long>> m = merge.get(s.getA());
                shuffle(s, m);
            } else {
                Tuple<String, List<Long>> m = new Tuple<>(s.getA(), new ArrayList<>());
                shuffle(s, m);
                merge.put(s.getA(), m);
            }
        });
        Map<Integer, List<Tuple<String, List<Long>>>> groups = merge.values()
                .stream()
                .collect(Collectors.groupingBy(t -> Math.abs(t.getA().hashCode()) % REDUCER_NUM));
        groups.values().forEach(e -> {
                    try {
                        shuffles.put(e);
                    } catch (InterruptedException ignored) {

                    }
                });
    }

    public void print() {
        results.forEach(r -> System.out.println(r.getA() + " : " + r.getB()));
    }

    public static void shuffle(Tuple<String, Long> input, Tuple<String, List<Long>> collector) {
        if (input.getA().equals(collector.getA())) {
            collector.getB().add(input.getB());
        }
    }


    public static final int MAPPER_NUM = 6;
    public static final int REDUCER_NUM = 6;

    CountDownLatch mapJobLatch = new CountDownLatch(MAPPER_NUM);
    CountDownLatch reduceJobLatch = new CountDownLatch(REDUCER_NUM);

    BlockingQueue<String> lineStrings = new LinkedBlockingQueue<>();
    List<Tuple<String, Long>> spills = Collections.synchronizedList(new ArrayList<>());
    Map<String, Tuple<String, List<Long>>> merge = new HashMap<>();
    BlockingQueue<List<Tuple<String, List<Long>>>> shuffles = new LinkedBlockingQueue<>();
    List<Tuple<String, Long>> results = Collections.synchronizedList(new ArrayList<>());

    public static void main(String[] args) throws InterruptedException {
        long startTime = System.currentTimeMillis();
        MapReduce_multithread mapReduce = new MapReduce_multithread();
        mapReduce.initMapJob();
        mapReduce.initReduceJob();
        mapReduce.start();
        mapReduce.print();
        long endTime = System.currentTimeMillis();
        System.out.println("消耗时间: " + (endTime - startTime) +  "ms");
    }
}
