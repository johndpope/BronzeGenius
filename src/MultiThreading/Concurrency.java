package MultiThreading;

import java.util.*;
import java.util.concurrent.*;

/**
 * Created by xuch on 8/2/16.
 */
public class Concurrency extends ArrayList<String>
{
    public static void main(String args[])
    {

        System.out.println(Concurrency.class.getSimpleName());




        /*long start = System.currentTimeMillis();
        System.out.println();

        // initialize pool
        ExecutorService pool = Executors.newFixedThreadPool(3);

        // store futures somewhere
        List<Future<String>> futures = new ArrayList<Future<String>>();

        // submit a bunch of callables to the pool
        for (int i = 0; i < 10; i++)
        {
            System.out.println("Creating! " + i);
            //futures.add(pool.submit(new String("" + i)));
        }

        List<String> stringCollection = new ArrayList<>();
        stringCollection.add("ddd2");
        stringCollection.add("aaa2");
        stringCollection.add("bbb1");
        stringCollection.add("aaa1");
        stringCollection.add("bbb3");
        stringCollection.add("ccc");
        stringCollection.add("bbb2");
        stringCollection.add("ddd1");

        Optional<String> reduced = stringCollection.stream().reduce((s1, s2) -> s1 + "#" + s2);
        reduced.ifPresent(System.out::println);*/

        /*int max = 10000000;
        List<String> values = new ArrayList<>(max);
        for (int i = 0; i++ < max;)
        {
            UUID uuid = UUID.randomUUID();
            values.add(uuid.toString());
        }

        long t0 = System.nanoTime();
        long count = values.stream().sorted().count();
        System.out.println(count);
        long t1 = System.nanoTime();
        long millis = TimeUnit.NANOSECONDS.toMillis(t1 - t0);
        System.out.println(String.format("sequential sort took: %d ms", millis));


        List<String> values1 = new ArrayList<>(max);
        for (int i = 0; i++ < max;)
        {
            UUID uuid = UUID.randomUUID();
            values1.add(uuid.toString());
        }
        t0 = System.nanoTime();
        count = values1.parallelStream().sorted().count();
        System.out.println(count);
        t1 = System.nanoTime();
        millis = TimeUnit.NANOSECONDS.toMillis(t1 - t0);
        System.out.println(String.format("parallel sort took: %d ms", millis));*/

        Map<Integer, String> map = new HashMap<>();
        for (int i = 0; i++ < 10;)
        {
            map.putIfAbsent(i, "val" + i); // if i->null or not mapping for i,
        }

        map.forEach((id, val) -> System.out.println(val));

        Map<String, Integer> wordCounts = new LinkedHashMap<>();
        String s = "Lorem ipsum dolor sit amet consetetur iam nonumy sadipscing " +
                   "elitr, sed diam nonumy eirmod tempor invidunt ut erat sed " +
                   "labore et dolore magna dolor sit amet aliquyam erat sed diam";
        wordCounts.put("sed", 0);
        wordCounts.put("erat", 0);

        //Arrays.stream(s.split(" ")).forEach((k, v) -> v + 1);
        for (String t : s.split(" "))
        {
            wordCounts.computeIfPresent(t, (k, v) -> v + 1);
        }
        System.out.println(wordCounts);

    }
}
