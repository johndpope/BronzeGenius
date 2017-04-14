package HelloWorld;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author xuch.
 */
public class HotelSort {
    private static final String DELIMETER = " ";
    private static final String REGEX_PATTERN = "[,.]";
    private static final String REPLACE_PATTERN = "";

    public static void main(String[] args) throws Exception {
        String ais = "sss";
        String bis = null;
        String is = ais + "::" + bis;
        System.out.println(is);
        System.exit(0);

        Map<Integer, Integer> counts = new HashMap<>();
        Scanner scanner = new Scanner(System.in);
        int var = 0;
        if (scanner.hasNextLine()) {
            final Set<String> keywords = new HashSet<String>(Arrays.asList(scanner.nextLine().split(DELIMETER)));
            if (scanner.hasNextLine()) {
                int reviewNumber = Integer.parseInt(scanner.nextLine());
            }

            List<String> reviews = null;
            while (scanner.hasNextLine()) {
                Integer hotelId = Integer.parseInt(scanner.nextLine());
                counts.put(hotelId, 0);
                if (scanner.hasNextLine()) {
                    reviews = Arrays.asList(scanner.nextLine().replaceAll(REGEX_PATTERN, REPLACE_PATTERN).split(DELIMETER));
                    System.out.println(reviews);
                }
                reviews.forEach(each -> {
                    if (keywords.contains(each)) {
                        counts.put(hotelId, counts.get(hotelId) + 1);
                    }
                });
            }
        }

        //counts.forEach((k, v)->System.out.println("hotel:" + k + ", counts:" + v));

        List<Integer> hotelIds = counts.entrySet().stream().sorted(new Comparator<Map.Entry<Integer, Integer>>() {
            @Override
            public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {
                if (o1.getValue().compareTo(o2.getValue()) == 0) return o1.getKey().compareTo(o2.getKey()) * -1;
                else return Integer.reverse(o1.getValue().compareTo(o2.getValue())) * -1;
            }
        }).map(each -> each.getKey()).collect(Collectors.toList());//.forEach(each -> System.out.println(each));

        hotelIds.forEach(each -> System.out.println(each));

    }
}

/*
location view price metro
2
2
This hotel has pretty good view. But no metro around here. Anyway, the price is low.
1
The service quality determins the price. It has a big swimming pool and beautiful view on the ground.
*/