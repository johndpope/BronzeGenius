package MultiThreading;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @author xuch.
 */
public class Volatile {

//    Map configOptions;
//    List<String> configFile;
//    boolean initialized;
//
//    public static void main(String[] args) {
//        Map configOptions;
//        List<String> configFile;
//        boolean initialized;
//
//        Thread a = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                configOptions = new HashMap<Integer, Integer>();
//                for (int i = 0; i < 1000; i++) {
//                    configOptions.put(i, i);
//                }
//
//                configFile = new LinkedList<>();
//                Iterator it = configOptions.entrySet().iterator();
//                while (it.hasNext()) {
//                    Map.Entry pair = (Map.Entry) it.next();
//                    configFile.add(pair.getKey() + "-" + pair.getValue());
//                }
//
//                initialized = true;
//
//            }
//        });
//
//        Thread b = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                while (!initialized) {
//                    //TimeUnit.SECONDS.sleep(1);
//                    try {
//                        Thread.sleep(1000L);
//                    } catch (Exception e) {
//
//                    }
//                }
//
//                // do something after initialized
//                System.out.println("do something after initialized");
//            }
//        });
//    }

}
