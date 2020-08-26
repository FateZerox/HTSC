package day2;

import java.util.*;
import java.util.concurrent.*;

/**
 * <h3>JAVA</h3>
 * <p>线程池</p>
 **/
public class MyThread {

    private int threadcount;
    private String fileName;
    private MapReduce_multithread mapReduce;


    public MyThread(int threadcount, String fileName) {
        this.threadcount = threadcount;
        this.fileName = fileName;
        this.mapReduce = new MapReduce_multithread();

    }

    public List<List> getFile() {
        List file = mapReduce.readFromFile(fileName);
        List<List> splitLists = mapReduce.splitting(threadcount, file);
        return splitLists;
    }

    public void createthread(List<List> splitLists) throws ExecutionException, InterruptedException {
        // 创建一个线程池
        List<MapReduce_multithread.Tuple> allTuples = Collections.synchronizedList(new ArrayList<>());
        ExecutorService pool = Executors.newFixedThreadPool(threadcount);

        for (int i = 0; i < threadcount; i++) {
            final int id = i;
            pool.execute(() -> {
                try {
                    List<MapReduce_multithread.Tuple> mappingTuples = mapReduce.mapping(splitLists.get(id));
                    allTuples.addAll(mappingTuples);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
        pool.shutdown();
        List<MapReduce_multithread.Tuple> allOriginTuples;
        while (true){
            if(pool.isTerminated()){
                allOriginTuples=allTuples;
                break;
            }
        }


        Map<String, ArrayList<Integer>> shuffleMap = mapReduce.shuffling(allOriginTuples);
        ExecutorService pool2 = Executors.newFixedThreadPool(100);
        List<MapReduce_multithread.Tuple> finalResult = Collections.synchronizedList(new ArrayList<>());
        for (String skey : shuffleMap.keySet()) {
            final String s = skey;
            pool2.execute(() -> {
                try {
                    HashMap<String, ArrayList<Integer>> temp = new HashMap<>();
                    temp.put(s, shuffleMap.get(s));
                    List<MapReduce_multithread.Tuple> reduceList = mapReduce.reducing(temp);
                    finalResult.addAll(reduceList);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });

        }
        pool2.shutdown();
        while (true){
            if(pool2.isTerminated()){
                mapReduce.printResult(finalResult);
                break;
            }
        }

    }


}
