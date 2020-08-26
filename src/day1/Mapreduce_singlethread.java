import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Mapreduce_singlethread {
    static class Tuple<String, Integer>{
        public final String key;
        public final Integer value;

        public Tuple(String key, Integer value){
            this.key = key;
            this.value = value;
        }
    }

    public static String spliting(String filepath) throws IOException {
        File filePath = new File(filepath);
        StringBuffer sb = new StringBuffer();
        InputStream is = new FileInputStream(filePath);
        String line; // 用来保存每行读取的内容
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        line = reader.readLine(); // 读取第一行
        while (line != null) { // 如果 line 为空说明读完了
            line = line.replaceAll( "[\\pP+~$`^=|<>～｀＄＾＋＝｜＜＞￥×]" , "");
            line = line.toLowerCase();
            sb.append(line); // 将读到的内容添加到 buffer 中
            sb.append(" "); // 添加换行符
            line = reader.readLine(); // 读取下一行
        }
        reader.close();
        is.close();
        return sb.toString();
    }


    //将每个字符串中的单词分隔开<k2,v2>,list[<deer,1>,<car,1>]
    public static ArrayList<Tuple> mapping(String str){
        ArrayList<Tuple> list = new ArrayList<>();
        String[] strings = str.split(" ");
        for (int i = 0 ;i < strings.length; i++){
            Tuple tuple = new Tuple(strings[i],1);
            list.add(tuple);
        }
        return list;
    }

    public static HashMap<String,ArrayList<Integer>> shuffling(ArrayList<Tuple> list){
        HashMap<String,ArrayList<Integer>> shuffe = new HashMap<>();
        for(int i = 0 ;i<list.size();i++){
            Tuple tuple = list.get(i);
            if(shuffe.containsKey(tuple.key)){
                shuffe.get(tuple.key).add(1);
            }else {
                ArrayList<Integer> a = new ArrayList<>();
                a.add(1);
                shuffe.put((String)(tuple.key),a);
            }
        }
        return shuffe;
    }

    public static ArrayList<Tuple> reducing(HashMap<String,ArrayList<Integer>> shuffe){
        ArrayList<Tuple> res = new ArrayList<>();
        for (String s:shuffe.keySet()
             ) {
            int a = 0;
            for(int i = 0 ;i<shuffe.get(s).size();i++){
                a += shuffe.get(s).get(i);
            }
            Tuple key = new Tuple(s,a);
            res.add(key);
        }
        return res;
    }

    public static void output(ArrayList<Tuple> res){
        for (int i = 0 ;i < res.size(); i++){
            System.out.println(res.get(i).key+" "+res.get(i).value);
        }
    }

    public static void main(String[] args) throws  Exception{

        long startTime = System.currentTimeMillis();

        //spliting
        String s = spliting("C:\\Users\\Qingfo\\IdeaProjects\\test\\src\\hamlet.txt");

        //mapping
        ArrayList<Tuple> list  = mapping(s);

        //shuffling
        HashMap<String,ArrayList<Integer>> shuffe = shuffling(list);

        //reducing
        ArrayList<Tuple> res = reducing(shuffe);

        output(res);

        long endTime = System.currentTimeMillis();
        System.out.println("use time: " + (endTime - startTime) +  "ms");
    }
}
