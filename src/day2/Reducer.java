package day2;

import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;

public class Reducer implements Runnable {

    BlockingQueue<List<Tuple<String, List<Long>>>> tuples;
    List<Tuple<String, Long>> collector;
    CountDownLatch latch;

    public Reducer(BlockingQueue<List<Tuple<String, List<Long>>>> tuples, List<Tuple<String, Long>> collector, CountDownLatch latch) {
        this.tuples = tuples;
        this.collector = collector;
        this.latch = latch;
    }

    public void reduce(Tuple<String, List<Long>> value, List<Tuple<String, Long>> output) {
        long sum = value.getB().stream()
                .reduce(Long::sum)
                .get();
        Tuple<String, Long> r = new Tuple<>(value.getA(), sum);
        output.add(r);
    }



    @Override
    public void run() {
        try {
            List<Tuple<String, List<Long>>> inputList = tuples.take();
            inputList.forEach(i -> reduce(i, collector));
        } catch (InterruptedException e) {

        } finally {
            latch.countDown();

        }
    }

}
