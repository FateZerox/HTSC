package day2;

import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Mapper implements Runnable {

    BlockingQueue<String> strings;
    List<Tuple<String, Long>> collector;
    CountDownLatch latch;

    public Mapper(BlockingQueue<String> strings, List<Tuple<String, Long>> collector, CountDownLatch latch) {
        this.strings = strings;
        this.collector = collector;
        this.latch = latch;
    }

    public void map(String text, List<Tuple<String, Long>> collector) {
        collector.addAll(Stream.of(text.split(" "))
                .filter(s -> s != null && s.length() > 0)
                .map(i -> new Tuple<>(i, 1L))
                .collect(Collectors.toList()));
    }

    @Override
    public void run() {
        try {
            String str = strings.take();
            map(str, collector);
        } catch (InterruptedException e) {

        } finally {
            latch.countDown();
        }

    }


}
