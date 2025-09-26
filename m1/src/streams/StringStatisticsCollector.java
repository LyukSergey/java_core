package streams;

import java.util.Collections;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

public class StringStatisticsCollector implements Collector<String, StringStatisticsCollector.Accumulator, StringStatistics> {

    static class Accumulator {

        long count = 0;
        long totalLength = 0;
        StringBuilder initials = new StringBuilder();
    }

    @Override
    public Supplier<Accumulator> supplier() {
        return Accumulator::new;
    }

    @Override
    public BiConsumer<Accumulator, String> accumulator() {
        return ((accumulator, s) -> {
            if (s != null && !s.isEmpty()) {
                System.out.println("accumulator processing string: " + s);
                accumulator.count++;
                accumulator.totalLength += s.length();
                accumulator.initials.append(s.charAt(0));
            }
        });
    }

    @Override
    public BinaryOperator<Accumulator> combiner() {
        return (acc1, acc2) -> {
            System.out.println("combiner processing");
            acc1.count += acc2.count;
            acc1.totalLength += acc2.totalLength;
            acc1.initials.append(acc2.initials);
            return acc1;
        };
    }

    @Override
    public Function<Accumulator, StringStatistics> finisher() {
        System.out.println("finisher processing");
        return acc -> new StringStatistics(acc.count, acc.totalLength, acc.initials.toString());
    }

    @Override
    public Set<Characteristics> characteristics() {
        System.out.println("characteristics processing");
        return Collections.emptySet();
    }
}
