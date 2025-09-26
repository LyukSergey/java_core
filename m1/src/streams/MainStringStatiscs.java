package streams;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class MainStringStatiscs {

    public static void main(String[] args) {
        List<String> data = List.of("Java", "streams", "are", "powerful");
//        StringStatistics stats = data.stream()
//                .collect(new StringStatisticsCollector());
//        // Виведе: StringStatistics{count=4, totalLength=22, initials='Jsap'}
//        System.out.println(stats);

        StringStatistics stats1 = data.parallelStream()
                .collect(new StringStatisticsCollector());
        System.out.println(stats1);
    }


}
