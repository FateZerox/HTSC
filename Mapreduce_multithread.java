import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.*;


public class Mapreduce_multithread {

    public static final int MAX_MAPPER = 4;
    public static final int MAX_REDUCER = 4;

    CountDownLatch mapLatch = new CountDownLatch(MAX_MAPPER);
    CountDownLatch reduceLatch = new CountDownLatch(MAX_REDUCER);

    static class Tuple<String, Integer>{
        public final String key;
        public final Integer value;

        public Tuple(String key, Integer value){
            this.key = key;
            this.value = value;
        }
    }


    static class Mapper implements Runnable {
        CopyOnWriteArrayList<String> input;
        CountDownLatch latch;


        Mapper(CopyOnWriteArrayList<String> input, CountDownLatch latch) {
            this.input = input;
            this.latch=latch;
        }

        @Override
        public void run()  {
            Mapreduce_multithread.mapping(linelist,mapping_res);
        }
    }

    static class Reducer implements Runnable {
        CountDownLatch latch;

        Reducer(CountDownLatch latch) {
            this.latch = latch;
        }

        @Override
        public void run()  {
            Mapreduce_multithread.reducing(shuffling_res,reducing_res);
        }
    }

    public void initMapJob() {
        for (int i = 0; i < MAX_MAPPER; i++) {
            Thread t = new Thread(new Mapper(linelist,mapLatch));
            t.start();
        }
    }

    public void initReduceJob() {
        for (int i = 0; i < MAX_REDUCER; i++) {
            Thread t = new Thread(new Reducer(reduceLatch));
            t.start();
        }
    }


    public static void spliting(String filepath) throws IOException {
        try {
            File filePath = new File(filepath);
            //StringBuffer sb = new StringBuffer();
            InputStream is = new FileInputStream(filePath);
            String line; // 用来保存每行读取的内容
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            line = reader.readLine(); // 读取第一行
            while (line != null) { // 如果 line 为空说明读完了
                line = line.replaceAll("[\\pP+~$`^=|<>～｀＄＾＋＝｜＜＞￥×]", "");
                line = line.toLowerCase();
                //sb.append(line); // 将读到的内容添加到 buffer 中
                //sb.append(" "); // 添加换行符
                linelist.add(line);
                line = reader.readLine(); // 读取下一行
            }
            reader.close();
            is.close();
            //return linelist;
            //return sb.toString();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void mapping(CopyOnWriteArrayList<String> linelist,CopyOnWriteArrayList<Tuple> list){
        //ArrayList<Tuple> list = new ArrayList<>();
        for (String str :linelist) {
            String[] strings = str.split(" ");
            for (int i = 0; i < strings.length; i++) {
                Tuple tuple = new Tuple(strings[i], 1);
                list.add(tuple);
            }
        }
        //return list;
    }

    public static void shuffling(){
        //HashMap<String,ArrayList<Integer>> shuffe = new HashMap<>();
        for(int i = 0 ;i<mapping_res.size();i++){
            Tuple tuple = mapping_res.get(i);
            if(shuffling_res.containsKey(tuple.key)){
                shuffling_res.get(tuple.key).add(1);
            }else {
                ArrayList<Integer> a = new ArrayList<>();
                a.add(1);
                shuffling_res.put((String)(tuple.key),a);
            }
        }
        //return shuffe;
    }

    public static void reducing(ConcurrentHashMap<String,ArrayList<Integer>> shuffe, CopyOnWriteArrayList<Tuple> res){
        //ArrayList<Tuple> res = new ArrayList<>();
        for (String s:shuffe.keySet()
        ) {
            int a = 0;
            for(int i = 0 ;i<shuffe.get(s).size();i++){
                a += shuffe.get(s).get(i);
            }
            Tuple key = new Tuple(s,a);
            res.add(key);
        }
        //return res;
    }

    public static void outputResult(){
        for (int i = 0 ;i < reducing_res.size(); i++){
            System.out.println(reducing_res.get(i).key+" "+reducing_res.get(i).value);
        }
    }

    public void start(String filepath) throws IOException, InterruptedException {
        spliting(filepath);
        mapLatch.await();
        shuffling();
        reduceLatch.await();
    }

    static CopyOnWriteArrayList<String> linelist = new CopyOnWriteArrayList<>();
    static CopyOnWriteArrayList<Tuple> mapping_res  = new CopyOnWriteArrayList<>();
    static ConcurrentHashMap<String,ArrayList<Integer>> shuffling_res = new ConcurrentHashMap<>();
    static CopyOnWriteArrayList<Tuple> reducing_res = new CopyOnWriteArrayList<>();

    public static void main(String[] args) throws  Exception{
        String filepath = Mapreduce_multithread.class.getResource("").getPath()+ "\\hamlet.txt";

        long startTime = System.currentTimeMillis();
        Mapreduce_multithread mapReduce = new Mapreduce_multithread();

        mapReduce.initMapJob();
        mapReduce.initReduceJob();
        mapReduce.start(filepath);
        mapReduce.outputResult();
        long endTime = System.currentTimeMillis();
        System.out.println("use time: " + (endTime - startTime) +  "ms");

    }
}
